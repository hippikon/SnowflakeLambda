package cafe.snowflake.app2.configuration;

import cafe.snowflake.app2.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class WebOidc {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .securityMatchers(matchers -> matchers
//                    .requestMatchers("/"))
//            .httpBasic(Customizer.withDefaults())
//            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/hello","/rest", "/", "/login").permitAll()
                    .anyRequest().authenticated())
//            .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable())
//            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
//            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.disable())
            .csrf(AbstractHttpConfigurer::disable)
//            .securityContext(configurer -> new JwtConfigurer(jwtTokenProvider))
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/oauth2/authorization/google") // Custom login page
                    .defaultSuccessUrl("/useer", true) // Redirect after successful login
                    .failureUrl("/login?error=true"));
//            .formLogin((form) -> form
//                .loginPage("/login")
//                .permitAll()
//            );
        return http.build();
    }

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/hello", "/rest").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .with(new JwtConfigurer(jwtTokenProvider), Customizer.withDefaults());
        return http.build();
    }
    */

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/hello", "/rest");
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            OidcUser oidcUser = new OidcUserService().loadUser(userRequest);
            System.out.println("OIDC User: " + oidcUser);
            return oidcUser;
        };
    }
}
