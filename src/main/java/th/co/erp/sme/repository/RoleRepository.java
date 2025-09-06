package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.RoleEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    List<RoleEntity> findAllByOrderByRoleCodeAsc();


    @Query(value = """
            
                select
                tp.object_code || '_' || tp.action_code as permission
            from tbl_employee te
                     left join tbl_role tr on :roleId = tr.id
                     left join tbl_role_permission trp on trp.role_id = tr.id
                     left join tbl_permission tp on tp.id = trp.permission_id
            where te.id = :empId and
                  tr.is_deleted = 'N' and
                  trp.is_deleted = 'N' and
                  tp.is_deleted = 'N'
            ;
            """, nativeQuery = true)
    List<String> findAllPermissionByEmpIdAndRoleId(Integer empId, Integer roleId);


    @Query("""
        select e from RoleEntity e left join EmployeeRoleHistoryEntity erhe on erhe.roleId = e.id
                where  erhe.endDate is null and erhe.employeeId = :empId order by erhe.createDate desc limit 1
        """)
    Optional<RoleEntity> findCurrentRole(Integer empId);


//    List<RoleEntity> findAll(Pageable pageable);
}
