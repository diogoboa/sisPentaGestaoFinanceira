package penta.sisPenta.gestaoFinanceira.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true,prePostEnabled = true)
public class WebSecurityConfig {



   /* @Bean
    public UserDetailsServiceImplements userDetailsServiceImplements() {
        return new UserDetailsServiceImplements();
    }
*/

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/*", "*","/app/**","/images/**","/arquivos/**","/static/**","/fonts/**","/js/**","/css/**", "/webfonts**", "/api/login").permitAll()
                    .anyRequest().authenticated()
            );


        return http.build();

    }

      /*http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*","/app/**","/images/**","/fonts/**","/js/**","/css/**", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();*/


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring();
    }



}