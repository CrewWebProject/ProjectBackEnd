package com.project.crewwebproject.service;

import com.project.crewwebproject.domain.User;
import com.project.crewwebproject.dto.JwtTokenDto;
import com.project.crewwebproject.dto.UserDto;
import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.jwt.TokenProvider;
import com.project.crewwebproject.repository.UserRepository;
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
    public void signup(UserDto userDto) {
        User user = new User();

        user.signUp(userDto , passwordEncoder.encode(userDto.getUserPassword()));

        userRepository.save(user);
    }

    public JwtTokenDto login(UserDto userDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getUserPassword());

        //authenticate 메서드가 실행이 될 때 CustomMemberDetailsService 에서 loadMemberByMembername 메서드가 실행 됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //위의 결과값을 가지고 SecurityContext 에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User findedUser = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new PrivateException(StatusCode.LOGIN_USER_ID_FAIL));

        //TODO : refreshToken 구현 필요
        String accessToken = tokenProvider.createAccessToken(authentication.getName(), findedUser.getUserName());

        JwtTokenDto jwtTokenDto = new JwtTokenDto(accessToken);

        return jwtTokenDto;
    }
}
