package th.co.erp.sme.base.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import th.co.erp.sme.base.BaseRequest;
import th.co.erp.sme.exception.model.BusinessException;
import th.co.erp.sme.util.constant.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;

import static th.co.erp.sme.util.constant.Constant.Format.FormatterDateTime;
import static th.co.erp.sme.util.constant.Constant.Format.FormatterYearDate;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchBaseRequest extends BaseRequest {

    private String searchValue;

    private String sort;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMM")
    private YearMonth dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMM")
    private YearMonth dateTo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetimeFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetimeTo;


    public LocalDateTime getDateTimeFrom() {
        return datetimeFrom;
    }

    public LocalDateTime getDateTimeTo() {
        return datetimeTo;
    }

    public LocalDate getDateFrom() {
        return !Objects.isNull(dateFrom) ? dateFrom.atDay(1) : null; // First day of the month
    }

    public LocalDate getDateTo() {
        return !Objects.isNull(dateTo) ? dateTo.atEndOfMonth() : null; // Last day of the month
    }

    public String getStringDateTimeFrom() {
        return Objects.isNull(datetimeFrom) ? null : datetimeFrom
                .format(FormatterDateTime);
    }

    public String getStringDateTimeTo() {
        return Objects.isNull(datetimeTo) ? null : datetimeTo
                .format(FormatterDateTime);
    }

    public String getStringDateFrom() {
        return Objects.isNull(dateFrom) ? null : dateFrom
                .atDay(1)
                .format(FormatterYearDate);
    }

    public String getStringDateTo() {
        return Objects.isNull(dateTo) ? null : dateTo
                .atEndOfMonth()
                .format(FormatterYearDate);
    }

    public Sort getSort() {
        if (sort == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorCode.BadRequestCode, "sort Required");
        }
        String[] partSort = this.sort.split(",");
        if (partSort.length < 2) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorCode.BadRequestCode, "sort Required");
        }
        String sortValue = partSort[0];
        String sortDirection = partSort[1];
        Sort.Order order = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Order.asc(sortValue)
                : Sort.Order.desc(sortValue);
        return Sort.by(order);
    }

}