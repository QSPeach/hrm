package cn.itsource.hrm.domain;

import java.math.BigDecimal;
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
@TableName("t_meal")
public class Meal extends Model<Meal> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 套餐名称
     */
    @TableField("mealName")
    private String mealName;

    /**
     * 套餐价格
     */
    @TableField("mealPrice")
    private BigDecimal mealPrice;

    /**
     * 套餐状态，0正常，1禁用
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
