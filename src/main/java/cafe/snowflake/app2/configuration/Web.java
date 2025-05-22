package cafe.snowflake.app2.configuration;

import cafe.snowflake.app2.auth.JwtConfigurer;
import cafe.snowflake.app2.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Web {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .securityMatchers(matchers -> matchers
//                    .requestMatchers("/"))
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/hello","/rest").permitAll()
                    .anyRequest().authenticated())
//            .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable())
//            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
//            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.disable())
            .csrf(AbstractHttpConfigurer::disable)
            .securityContext(configurer -> new JwtConfigurer(jwtTokenProvider));
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

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
