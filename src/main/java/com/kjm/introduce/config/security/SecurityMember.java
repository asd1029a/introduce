package com.kjm.introduce.config.security;

import com.kjm.introduce.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * SecurityMember.java
 * Class 설명을 작성하세요.
 *
 * @author 김정민
 * @since 2023.04.06
 */
@Getter
public class SecurityMember implements UserDetails {

    private final Member member;
    private final List<GrantedAuthority> grantedAuthorities;

    public SecurityMember(Member member, List<GrantedAuthority> grantedAuthorities) {
        this.member = member;
        this.grantedAuthorities = grantedAuthorities;
    }

    public Long getId() {
        return member.getId();
    }

    public String getEmail() {
        return member.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
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
}
