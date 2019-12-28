package cn.itsource.hrm.web.controller;

import cn.itsource.hrm.service.ICourseTypeService;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.query.CourseTypeQuery;
import cn.itsource.basic.util.AjaxResult;
import cn.itsource.basic.util.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    public ICourseTypeService courseTypeService;

    /**
    * 保存和修改公用的
    * @param courseType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseType courseType){
        try {
            if(courseType.getId()!=null){
                courseTypeService.updateById(courseType);
            }else{
                courseTypeService.save(courseType);
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
            courseTypeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CourseType get(@PathVariable("id")Long id)
    {
        return courseTypeService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<CourseType> list(){

        return courseTypeService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<CourseType> page(@RequestBody CourseTypeQuery query)
    {
        Page<CourseType> page = courseTypeService.page(new Page<CourseType>(query.getPage(), query.getRows()));
        return new PageList<CourseType>(page.getTotal(),page.getRecords());
    }

    @RequestMapping(value = "/treeData",method = RequestMethod.GET)
    public List<CourseType> getTreeData(){
        return courseTypeService.treeData();
    }

//    public List<CourseType> getByParentId(Long id){
//        List<CourseType> list = courseTypeService.list(new QueryWrapper<CourseType>().eq("pid", id));
//
//        if (list==null&&list.size()==0){
//            return null;
//        }
//
//        for (CourseType courseType : list) {
//            List<CourseType> list1 = getByParentId(courseType.getId());
//            courseType.setChildren(list1);
//        }
//        return list;
//    }
//    public List<CourseType> loadTreeDataLoop(){
//        List<CourseType> firstLevelTypes = new ArrayList<CourseType>();
//        List<CourseType> list = courseTypeService.list(new QueryWrapper<CourseType>());
//        Map<Long,CourseType> map = new HashMap<Long,CourseType>();
//        for (CourseType courseType : list) {
//            map.put(courseType.getId(), courseType);
//        }
//
//        for (CourseType courseType : list) {
//            if (courseType.getId()==0L){
//                firstLevelTypes.add(courseType);
//            }else {
//                CourseType parent = map.get(courseType.getPid());
//                if (parent != null){
//                    parent.getChildren().add(courseType);
//                }
//            }
//        }
//        return firstLevelTypes;
//    }
}
