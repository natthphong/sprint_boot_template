package th.co.erp.sme.base.list;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageBaseRequest extends SearchBaseRequest {

    private int page;

    private int size;

    public Pageable getPageable(){
        return PageRequest.of(this.page, this.size,super.getSort());
    }
}