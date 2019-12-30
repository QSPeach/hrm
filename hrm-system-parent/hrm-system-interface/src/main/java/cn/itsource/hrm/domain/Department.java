package cn.itsource.hrm.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qs.peach
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门编号
     */
    private String sn;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门的上级分类层级id
     */
    @TableField("dirPath")
    private String dirPath;

    /**
     * 部门状态，0正常，1禁用
     */
    private Integer state;

    /**
     * 部门管理员，关联Employee表id
     */
    private Long managerId;

    /**
     * 上级部门
     */
    private Long parentId;

    /**
     * 部门所属机构(租户)
     */
    private Long tenantId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
