package cn.itsource.hrm.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("t_tenant_meal")
public class TenantMeal extends Model<TenantMeal> {

    private static final long serialVersionUID=1L;

    private Long mealId;

    private Long tenantId;

    /**
     * 璇ユ満鏋勭殑璇ュ椁愬埌鏈熸椂闂?
     */
    @TableField("expireDate")
    private Long expireDate;

    /**
     * 状态,是否过期 0 未支付，1已经购买，2过期
     */
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
