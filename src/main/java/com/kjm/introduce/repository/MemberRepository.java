package com.kjm.introduce.repository;

import com.kjm.introduce.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * MemberRepository.java
 * Class 설명을 작성하세요.
 *
 * @author 김정민
 * @since 2023.04.06
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
