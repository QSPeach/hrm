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
@TableName("t_tenant")
public class Tenant extends Model<Tenant> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long tenantType;

    @TableField("companyName")
    private String companyName;

    @TableField("companyNum")
    private String companyNum;

    private Integer state;

    private String address;

    private String logo;

    @TableField("registerTime")
    private Long registerTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
