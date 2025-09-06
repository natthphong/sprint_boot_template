package th.co.erp.sme.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import th.co.erp.sme.model.entity.AuditEntity;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity,Integer> {

     List<AuditEntity> findByTableNameAndPrimaryKeyOrderByIdDesc(String tableName,String primaryKey);

}
