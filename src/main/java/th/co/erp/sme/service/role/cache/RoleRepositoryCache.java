package th.co.erp.sme.service.role.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.list.PageBaseRequest;
import th.co.erp.sme.model.entity.RoleEntity;
import th.co.erp.sme.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryCache {
    private final RoleRepository roleRepository;

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "rolesCache", key = "'all'"),
                    @CacheEvict(cacheNames = "rolesPageCache", allEntries = true) // if using Option B
            }
    )
    public RoleEntity createRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "rolesCache", key = "'all'"),
            @CacheEvict(cacheNames = "rolesPageCache", allEntries = true)
    })
    public RoleEntity updateRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "rolesCache", key = "'all'"),
            @CacheEvict(cacheNames = "rolesPageCache", allEntries = true)
    })
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "rolesPageCache",
            key = "T(java.lang.String).format('p:%d|s:%d|o:%s', #pageable.pageNumber, #pageable.pageSize, #pageable.sort)")
    public Page<RoleEntity> findAllByPageAble(Pageable pageable) {
        log.info("findAllByPageAbleFromDB");
        return roleRepository.findAll(pageable);
    }
}
