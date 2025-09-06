package th.co.erp.sme.base;


public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
    V execute(T input);
}

