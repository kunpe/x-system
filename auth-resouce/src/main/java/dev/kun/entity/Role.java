package dev.kun.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@TableName("role")
public class Role implements GrantedAuthority {

    @TableField("id")
    private Long id;
    @TableField("role_name")
    private String roleName;
    @TableField("role_desc")
    private String roleDesc;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
