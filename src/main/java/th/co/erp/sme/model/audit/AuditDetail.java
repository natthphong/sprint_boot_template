package th.co.erp.sme.model.audit;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDetail implements Serializable {
    private String field;
    private String oldValue;
    private String newValue;
}
