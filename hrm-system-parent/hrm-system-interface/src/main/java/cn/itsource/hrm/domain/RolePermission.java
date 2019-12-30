package cn.itsource.hrm.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("t_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID=1L;

    private Long roleId;

    private Long permissionId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
