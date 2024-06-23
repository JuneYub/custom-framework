package com.june.customframework.config.oauth;

import com.june.customframework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService {

    private final UserRepository userRepository;
}
