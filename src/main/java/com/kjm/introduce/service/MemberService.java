package com.kjm.introduce.service;

import com.kjm.introduce.dto.MemberDto;

/**
 * MemberService.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
public interface MemberService {

    void save(MemberDto memberDto);
    void delete(Long memberId);
    void update(Long memberId, MemberDto memberDto);
//    JwtToken login(MemberDto memberDto);

}
