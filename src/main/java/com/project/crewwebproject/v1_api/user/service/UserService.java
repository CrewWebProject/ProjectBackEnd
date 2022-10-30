package com.project.crewwebproject.v1_api.user.service;

import com.project.crewwebproject.auth.jwt.dto.JwtTokenDto;
import com.project.crewwebproject.config.exception.PrivateException;
import com.project.crewwebproject.config.exception.StatusCode;
import com.project.crewwebproject.auth.jwt.TokenProvider;
import com.project.crewwebproject.v1_api.user.domain.User;
import com.project.crewwebproject.v1_api.user.dto.requestDto.UserSignUpRequestDto;
import com.project.crewwebproject.v1_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignUpRequestDto userSignUpRequestDto) {
        User user = new User();

        user.signUp(userSignUpRequestDto, passwordEncoder.encode(userSignUpRequestDto.getUserPassword()));

        userRepository.save(user);
    }

    public JwtTokenDto login(UserSignUpRequestDto userSignUpRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userSignUpRequestDto.getUserId(), userSignUpRequestDto.getUserPassword());

        //authenticate 메서드가 실행이 될 때 CustomMemberDetailsService 에서 loadMemberByMembername 메서드가 실행 됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //위의 결과값을 가지고 SecurityContext 에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User findedUser = userRepository.findById(userSignUpRequestDto.getUserId())
                .orElseThrow(() -> new PrivateException(StatusCode.LOGIN_USER_ID_FAIL));

        //TODO : refreshToken 구현 필요
        String accessToken = tokenProvider.createAccessToken(authentication.getName(), findedUser.getUserName());

        JwtTokenDto jwtTokenDto = new JwtTokenDto(accessToken);

        return jwtTokenDto;
    }
}
