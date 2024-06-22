package com.june.customframework.service;

import com.june.customframework.config.jwt.TokenProvider;
import com.june.customframework.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createdNewAccessToken(String refreshToken) {
        // 전달받은 리프레시 토큰으로 토큰 유효성 검사를 진행
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        // 유효한 토큰이면 리프레시 토큰으로 사용자 id를 찾는다.
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        // 사용자 id로 사용자를 찾는다.
        User user = userService.findById(userId);
        // 토큰 제공자의 토큰생성 메서드를 호출해 새로운 액세스 토큰을 생성한다.
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
