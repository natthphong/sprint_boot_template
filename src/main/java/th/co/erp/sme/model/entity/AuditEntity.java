package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_audit")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditEntity {
    @Id
    @SequenceGenerator(name = "tbl_audit_id_seq", sequenceName = "tbl_audit_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_audit_id_seq")
    private Integer id;
    private String tableName;
    private String primaryKey;
    private LocalDateTime updateDate;
    private Integer updateBy;
    private String detail;
    private String status;
}
