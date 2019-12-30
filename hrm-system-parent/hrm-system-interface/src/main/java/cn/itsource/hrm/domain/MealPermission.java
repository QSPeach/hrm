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
@TableName("t_meal_permission")
public class MealPermission extends Model<MealPermission> {

    private static final long serialVersionUID=1L;

    private Long mealId;

    private Long permissionId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
