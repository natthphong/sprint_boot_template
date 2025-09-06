package th.co.erp.sme.configuration.jpa;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


import th.co.erp.sme.model.audit.AuditDetail;
import th.co.erp.sme.model.entity.AuditEntity;
import th.co.erp.sme.repository.AuditRepository;
import th.co.erp.sme.util.constant.Constant;
import th.co.erp.sme.util.JsonHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditListeners {


    @Autowired
    @Lazy
    private AuditRepository auditRepository;





    @PrePersist
    public void prePersist(Object entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Integer user =(Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != -1){
            Class<?> clazz = entity.getClass();
            Method setCreateBy = clazz.getMethod("setCreateBy", Integer.class);
            setCreateBy.invoke(entity, user);
        }
    }

    @PreUpdate
    public void beforeUpdate(Object currentState) {
        Integer user =(Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Class<?> clazz = currentState.getClass();
        Object previousState = ReflectionUtils.invokeMethod(
                Objects.requireNonNull(ReflectionUtils.findMethod(clazz, "getPreviousState")), currentState);
        List<Field> primaryFieldOpt = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) != null).toList();
        try {
            Method setCreateBy = clazz.getMethod("setUpdateBy", Integer.class);
            setCreateBy.invoke(currentState,user);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        if (!primaryFieldOpt.isEmpty()) {
            String tableName = clazz.getAnnotation(Table.class).name();
            String primaryKey = "";
            for (Field primaryField : primaryFieldOpt){
                ReflectionUtils.makeAccessible(primaryField);
                 primaryKey +=String.valueOf( ReflectionUtils.getField(primaryField, currentState));
            }


            List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> field.getAnnotation(OneToMany.class) == null)
                    .toList();
            List<AuditDetail> auditDetails = new ArrayList<>();
            for (Field field : fields) {
                Optional<AuditDetail> opt = this.saveAuditTrail(field, previousState, currentState);
                if (opt.isPresent()
                        && !field.getName().equalsIgnoreCase(Constant.IgnoreField.UPDATE_BY)
                        && !field.getName().equalsIgnoreCase(Constant.IgnoreField.UPDATE_DATE)) {
                    auditDetails.add(opt.get());
                }
            }
            if (!auditDetails.isEmpty()) {
                AuditEntity audit = new AuditEntity();
                audit.setTableName(tableName);
                audit.setPrimaryKey(primaryKey);
                audit.setUpdateDate(LocalDateTime.now());
                audit.setUpdateBy(user);
                audit.setDetail(JsonHelper.objectToJsonString(auditDetails));
                audit.setStatus(Constant.UPDATE);
                auditRepository.save(audit);
            }
        }
    }


    private Optional<AuditDetail> saveAuditTrail(Field field, Object previousState, Object currentState) {
        String columnName = field.getName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
        ReflectionUtils.makeAccessible(field);
        Object oldValue = Objects.isNull(previousState) ? null : ReflectionUtils.getField(field, previousState);
        Object newValue = ReflectionUtils.getField(field, currentState);
        if ((!Objects.isNull(oldValue) || !Objects.isNull(newValue)) && field.getAnnotation(ManyToOne.class) != null) {
            Class<?> fkClazz = Objects.isNull(newValue) ? null : newValue.getClass();
            if (Objects.isNull(newValue)) {
                fkClazz = oldValue.getClass();
            }
            Optional<Field> fkFieldOpt = Arrays.stream(fkClazz.getDeclaredFields())
                    .filter(fkField -> fkField.getAnnotation(Id.class) != null)
                    .findFirst();
            if (fkFieldOpt.isPresent()) {
                Field fkField = fkFieldOpt.get();
                ReflectionUtils.makeAccessible(fkField);
                oldValue = Objects.isNull(oldValue) ? null : ReflectionUtils.getField(fkField, oldValue);
                newValue = Objects.isNull(newValue) ? null : ReflectionUtils.getField(fkField, newValue);
            }
        }
        if ((Objects.isNull(oldValue) && !Objects.isNull(newValue)) || (!Objects.isNull(oldValue) && !oldValue.equals(newValue))) {
            AuditDetail auditDetail = new AuditDetail();
            auditDetail.setField(columnName);
            auditDetail.setOldValue(Objects.isNull(oldValue) ? null : String.valueOf(oldValue));
            auditDetail.setNewValue(Objects.isNull(newValue) ? null : String.valueOf(newValue));
            return Optional.of(auditDetail);
        }
        return Optional.empty();
    }

    @PreRemove
    public void beforeDelete(Object currentState) {
        Class<?> clazz = currentState.getClass();
        Integer user =(Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Field> primaryFieldOpt = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) != null).toList();

        if (!primaryFieldOpt.isEmpty()) {
            String tableName = clazz.getAnnotation(Table.class).name();
            String primaryKey = "";
            for (Field primaryField : primaryFieldOpt) {
                ReflectionUtils.makeAccessible(primaryField);
                primaryKey += String.valueOf(ReflectionUtils.getField(primaryField, currentState));
            }
            AuditEntity audit = new AuditEntity();
            audit.setTableName(tableName);
            audit.setPrimaryKey(primaryKey);
            audit.setUpdateDate(LocalDateTime.now());
            audit.setUpdateBy(user);
            audit.setDetail(JsonHelper.objectToJsonString(currentState));
            audit.setStatus(Constant.DELETED);
            auditRepository.save(audit);
        }

    }
}
