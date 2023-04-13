package com.kjm.introduce.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * JwtToken.java
 * Class 설명을 작성하세요.
 *
 * @author danusys
 * @since 2023.04.12
 */

@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}
