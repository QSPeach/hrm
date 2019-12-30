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
@TableName("t_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    @TableField("realName")
    private String realName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态：0正常，1锁定，2注销
     */
    private Integer state;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 所属租户
     */
    private Long tenantId;

    /**
     * 员工类型 ， 1普通员工 ，2客服人员，3管理员，或其他
     */
    private Integer type;

    @TableField("inputTime")
    private Long inputTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
