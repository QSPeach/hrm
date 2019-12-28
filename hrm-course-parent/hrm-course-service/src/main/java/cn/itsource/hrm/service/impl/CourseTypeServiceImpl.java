package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.service.ICourseTypeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author qs.peach
 * @since 2019-12-26
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    private String COURSE_TYPE = "treeData1";

    public List<CourseType> treeData() {
        String s = redisClient.get(COURSE_TYPE);
        List<CourseType> list;
        if (StringUtils.isNotEmpty(s)){
            list = JSONObject.parseArray(s, CourseType.class);
        }else {
            list = loadTreeDataLoop();
            String s1 = JSONObject.toJSONString(list);
            redisClient.set(COURSE_TYPE, s1);
        }
        return list;
    }

    public List<CourseType> loadTreeDataLoop(){
        List<CourseType> firstLevelTypes = new ArrayList<CourseType>();
        List<CourseType> list = courseTypeMapper.selectList(null);
        Map<Long,CourseType> map = new HashMap<Long,CourseType>();
        for (CourseType courseType : list) {
            map.put(courseType.getId(), courseType);
        }
        for (CourseType courseType : list) {
            if (courseType.getPid() == 0L){
                firstLevelTypes.add(courseType);
            }else {
                CourseType courseType1 = map.get(courseType.getPid());
                if (courseType1 != null){
                    courseType1.getChildren().add(courseType);
                }
            }
        }
        return firstLevelTypes;
    }

    public void synOperate(){
        List<CourseType>list = loadTreeDataLoop();
        String s1 = JSONObject.toJSONString(list);
        redisClient.set(COURSE_TYPE, s1);
    }

    @Override
    public boolean save(CourseType entity) {
        super.save(entity);
        synOperate();
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        synOperate();
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        super.updateById(entity);
        synOperate();
        return true;
    }
}
