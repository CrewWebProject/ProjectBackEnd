package com.project.crewwebproject.config;


import com.project.crewwebproject.jwt.JwtAccessDeniedHandler;
import com.project.crewwebproject.jwt.JwtAuthenticationEntryPoint;
import com.project.crewwebproject.jwt.JwtSecurityConfig;
import com.project.crewwebproject.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //스프링 시큐리티를 사용하기 위함
@RequiredArgsConstructor //jwt 클래스 생성자 주입을 위함
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize 어노테이션을 사용하기 위함
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                // 크로스 사이트 요청 위조 방지 off -> 시큐리티 자체 제공 csrf 토큰이 아닌 jwt 토큰을 사용
                .csrf().disable()

                // cors 설정
                .cors()
                .and()

                //요청시 로그인 유무 및 권한을 체크 후 Exception을 핸들링할때 사용할 클래스들을 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //세션을 사용하지 않음을 선언 (STATELESS)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/test/**").permitAll() //Test용 메서드는 권한설정X
//                .antMatchers(HttpMethod.GET ,"/anonypost/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll() //스웨거 권한설정 X
                .antMatchers("/swagger-resources/**").permitAll() //스웨거 권한설정 X
                .antMatchers("/swagger-ui.html").permitAll() //스웨거 권한설정 X
                .antMatchers("/v2/api-docs").permitAll() //스웨거 권한설정 X
                .antMatchers("/v3/api-docs").permitAll() //스웨거 권한설정 X
                .antMatchers("/webjars/**").permitAll() //스웨거 권한설정 X
                .anyRequest().authenticated() // 나머지 API는 권한 설정

                //JWTFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스도 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

    }

}