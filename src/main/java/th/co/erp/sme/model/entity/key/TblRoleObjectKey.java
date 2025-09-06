package th.co.erp.sme.model.entity.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TblRoleObjectKey implements Serializable {

    private String objectCode;
    private Integer userId;
}
