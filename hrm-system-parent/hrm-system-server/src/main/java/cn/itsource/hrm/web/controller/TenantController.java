package cn.itsource.hrm.web.controller;

import cn.itsource.hrm.service.ITenantService;
import cn.itsource.hrm.domain.Tenant;
import cn.itsource.hrm.query.TenantQuery;
import cn.itsource.basic.util.AjaxResult;
import cn.itsource.basic.util.PageList;
import cn.itsource.hrm.web.dto.TenantDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    @Autowired
    public ITenantService tenantService;

    /**
    * 保存和修改公用的
    * @param tenant  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Tenant tenant){
        try {
            if(tenant.getId()!=null){
                tenantService.updateById(tenant);
            }else{
                tenantService.save(tenant);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            tenantService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Tenant get(@PathVariable("id")Long id)
    {
        return tenantService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Tenant> list(){

        return tenantService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<Tenant> page(@RequestBody TenantQuery query)
    {
        Page<Tenant> page = tenantService.page(new Page<Tenant>(query.getPage(), query.getRows()));
        return new PageList<Tenant>(page.getTotal(),page.getRecords());
    }

    @PostMapping("/register")
    public AjaxResult Register(@RequestBody TenantDto tenantDto){
        try {
            tenantService.registTenant(tenantDto);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败"+e.getMessage());
        }
    }
}
