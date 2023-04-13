package com.kjm.introduce.config;

import com.kjm.introduce.config.security.SecurityMember;
import com.kjm.introduce.domain.AuthorityGrade;
import com.kjm.introduce.domain.Member;
import com.kjm.introduce.domain.Role;
import com.kjm.introduce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * UserDetailsServiceImpl.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(" 존재하지 않는 회원입니다."));

        return new SecurityMember(member, member.getAuthorityGrades().stream()
                .map(AuthorityGrade::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
