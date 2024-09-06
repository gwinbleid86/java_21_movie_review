package kg.attractor.movie_review_21.config;

import kg.attractor.movie_review_21.model.CustomOAuth2User;
import kg.attractor.movie_review_21.service.impl.AuthUserDetailsService;
import kg.attractor.movie_review_21.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthUserDetailsService authUserDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/movies").hasAuthority("ADMIN")
                        .requestMatchers("/create").hasAuthority("ADMIN")
                        .anyRequest().permitAll())
                .oauth2Login(oAuth2 -> oAuth2
                        .loginPage("/auth/login")
                        .userInfoEndpoint(userConfig -> userConfig
                                .userService(customOAuth2UserService))
                        .successHandler(((request, response, authentication) -> {
                            var oAuthUser = (CustomOAuth2User) authentication.getPrincipal();
                            authUserDetailsService.processOAuthPostLogin(oAuthUser.getAttribute("email"));
                            response.sendRedirect("/");
                        })));
        return http.build();
    }
}
