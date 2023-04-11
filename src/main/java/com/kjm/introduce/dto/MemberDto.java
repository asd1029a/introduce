package com.kjm.introduce.dto;

import com.kjm.introduce.domain.Member;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * MemberDto.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
@Data
public class MemberDto {

    @NotBlank
    @Email(message = "e-mailの形式に合ってません。")
    private String email;

    @NotBlank(message = "お名前を入力してください。")
    private String name;

    @Length(min = 11, max = 15, message = "番号が変です。")
    private String phone;

    @Length(min = 8, max = 20, message = "8~20以内に　入力してください。")
    private String password;
    @NotBlank(message = "ニックネームを入力してください。")
    private String nickname;

    public Member createMember() {
        return Member.builder()
                .email(email)
                .phone(phone)
                .name(name)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
