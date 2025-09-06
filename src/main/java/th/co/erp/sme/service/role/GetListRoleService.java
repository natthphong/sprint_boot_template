package th.co.erp.sme.service.role;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.base.list.PageBaseRequest;
import th.co.erp.sme.model.entity.RoleEntity;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.repository.RoleRepository;
import th.co.erp.sme.service.role.cache.RoleRepositoryCache;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetListRoleService implements BaseService<PageBaseRequest, BaseResponse> {

    private final RoleRepositoryCache roleRepositoryCache;

    @Override
    public BaseResponse execute(PageBaseRequest req) {
        return new ResponseModel<>()
                .successWithBody(roleRepositoryCache.findAllByPageAble(
                        req.getPageable()
                ));
    }
}
