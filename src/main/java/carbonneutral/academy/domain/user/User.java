package carbonneutral.academy.domain.user;

import carbonneutral.academy.api.controller.auth.dto.request.PatchAdditionalInfoReq;
import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.user.enums.Role;
import carbonneutral.academy.domain.user.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, length = 20)
    private String username;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SocialType socialType;

    @Column(nullable = false)
    private int point = 0;

    @Column(length = 20)
    private String nickname;

    private boolean gender;

    @Column(nullable = false)
    private boolean isFinished = false;


    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateAdditionalInfo(PatchAdditionalInfoReq request) {
        this.nickname = request.getNickname();
        this.gender = request.isGender();
        this.isFinished = true;
    }
}
