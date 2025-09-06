package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_employee", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_employee__company_code__username__is_deleted", columnNames = {"company_code", "username", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "default_branch_code")
    private String defaultBranchCode;


    @Column(name = "first_name_th", nullable = false)
    private String firstNameTh;

    @Column(name = "last_name_th", nullable = false)
    private String lastNameTh;

    @Column(name = "first_name_en")
    private String firstNameEn;

    @Column(name = "last_name_en")
    private String lastNameEn;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "employment_type_code", nullable = false)
    private String employmentTypeCode;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_photo_key")
    private String profilePhotoKey;

    @Column(name = "face_template_key")
    private String faceTemplateKey;

    @Column(name = "signature_image_key")
    private String signatureImageKey;

    @Column(name = "base_salary", precision = 12, scale = 2)
    private BigDecimal baseSalary;

    @Column(name = "base_hourly_rate", precision = 12, scale = 2)
    private BigDecimal baseHourlyRate;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode = "THB";

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private String isActive = "Y";

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "created_by")
    private Integer createBy;

    @Column(name = "updated_at")
    private LocalDateTime updateDate;

    @Column(name = "updated_by")
    private Integer updateBy;

    @Column(name = "status")
    private String status;

    @Column(name = "face_verify_at")
    private LocalDateTime faceVerifyAt;

    @PrePersist public void insert(){ this.createDate = LocalDateTime.now(); }
    @PreUpdate public void update(){ this.updateDate = LocalDateTime.now(); }
}
