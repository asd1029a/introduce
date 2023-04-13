package com.kjm.introduce.service;

import com.kjm.introduce.domain.Member;
import com.kjm.introduce.dto.MemberDto;
import com.kjm.introduce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MemberServiceImpl.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void save(MemberDto memberDto) {
        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        Member member = memberDto.createMember(passwordEncoder);
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
        memberRepository.delete(member);
    }

    @Override
    @Transactional
    public void update(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
        member.update(memberDto);

    }

//    @Override
//    public JwtToken login(MemberDto memberDto) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(memberDto.getEmail(), memberDto.getPassword());
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        return jwtTokenProvider.generateToken(authentication);
//    }
}
