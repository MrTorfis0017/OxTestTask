package com.ox.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Builder
@Getter
@Setter
public class TokenDTO {

    private String accessToken;

    private Date accessTokenExpiration;

    private String refreshToken;

    private Date refreshTokenExpiration;
}
