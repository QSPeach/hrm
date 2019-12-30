package cn.itsource.hrm.web.dto;

import cn.itsource.hrm.domain.Tenant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TenantDto {
    private Tenant tenant;
    private String username;
    private String password;
    private Long meal;
}
