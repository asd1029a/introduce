package com.kjm.introduce.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * AuthorityGrade.java
 * Class 설명을 작성하세요.
 *
 * @author danusys
 * @since 2023.04.06
 */
@Entity
@Getter
public class AuthorityGrade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static AuthorityGrade setRoleAndMember(Role role, Member member) {
        AuthorityGrade authorityGrade = new AuthorityGrade();
        authorityGrade.role = role;
        member.addAuthorityList(authorityGrade);
        authorityGrade.member = member;

        return authorityGrade;
    }

    public String getRoleName() {
        return role.name() != null ? role.name(): "BRONZE";
    }
}
