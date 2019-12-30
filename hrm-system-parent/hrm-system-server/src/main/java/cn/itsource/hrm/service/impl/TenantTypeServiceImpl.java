package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.domain.TenantType;
import cn.itsource.hrm.mapper.TenantTypeMapper;
import cn.itsource.hrm.service.ITenantTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户(机构)类型表 服务实现类
 * </p>
 *
 * @author qs.peach
 * @since 2019-12-28
 */
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

}
