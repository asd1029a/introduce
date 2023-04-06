package com.kjm.introduce.domain;

import com.kjm.introduce.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Member.java
 * Class 설명을 작성하세요.
 *
 * @author 김정민
 * @since 2023.04.06
 */
@Entity
@Getter
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phone;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AuthorityGrade> authorityGrades;

    public void addAuthorityList(AuthorityGrade authorityGrade) {
        authorityGrades.add(authorityGrade);
    }

    public void update(MemberDto memberDto) {
        this.email = memberDto.getEmail();
        this.phone = memberDto.getPhone();
        this.password = memberDto.getPassword();
        this.nickname = memberDto.getNickname();
    }
}
