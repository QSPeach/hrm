package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.domain.*;
import cn.itsource.hrm.mapper.*;
import cn.itsource.hrm.service.ITenantService;
import cn.itsource.hrm.web.dto.TenantDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qs.peach
 * @since 2019-12-28
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private TenantMealMapper tenantMealMapper;

    @Autowired
    private MealPermissionMapper mealPermissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    @Transactional
    public void registTenant(TenantDto tenantDto) {
        //添加租户表
        Tenant tenant = tenantDto.getTenant();
        tenant.setState(0);
        tenant.setRegisterTime(System.currentTimeMillis());
        baseMapper.insert(tenant);
        //添加租户套餐中间表
        TenantMeal tenantMeal = new TenantMeal();
        tenantMeal.setTenantId(tenant.getId());
        tenantMeal.setState(0);
        tenantMeal.setExpireDate(System.currentTimeMillis());
        tenantMeal.setMealId(tenantDto.getMeal());
        tenantMealMapper.insert(tenantMeal);

        //创建租户管理员的角色
        Role role = new Role();
        role.setName("租户管理员");
        role.setSn("TenantAdmin");
        role.setTenant(tenant.getId());
        roleMapper.insert(role);

        //根据套餐id查询对应的权限id
        List<MealPermission> mealPermissions = mealPermissionMapper.selectList(new QueryWrapper<MealPermission>()
                .eq("meal_id", tenantDto.getMeal()));

        //添加角色权限中间表
        List<RolePermission> list = new ArrayList<RolePermission>();
        for (MealPermission mealPermission : mealPermissions) {
            Long permissionId = mealPermission.getPermissionId();
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(role.getId());
            list.add(rolePermission);
        }
        if (list != null && list.size() > 0){
            for (RolePermission rolePermission : list) {
                rolePermissionMapper.insert(rolePermission);
            }
        }

        //创建员工
        Employee employee = new Employee();
        employee.setUsername(tenantDto.getUsername());
        employee.setPassword(tenantDto.getPassword());
        employee.setState(0);
        employee.setInputTime(System.currentTimeMillis());
        employee.setTenantId(tenant.getId());
        employee.setType(3);
        employeeMapper.insert(employee);

        //创建员工角色中间表
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployeeId(employee.getId());
        employeeRole.setRoleId(role.getId());
        employeeRoleMapper.insert(employeeRole);
    }
}
