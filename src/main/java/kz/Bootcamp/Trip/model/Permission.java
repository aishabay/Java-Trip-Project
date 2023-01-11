package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "t_permissions")
public class Permission extends BaseModel implements GrantedAuthority {

    @Column(name = "permission_name")
    private String permission;

    @Override
    public String getAuthority() {
        return permission;
    }
}
