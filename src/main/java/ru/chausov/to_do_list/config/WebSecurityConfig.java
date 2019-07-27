package ru.chausov.to_do_list.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/registration","/users/add").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if(userRepository.findByUsername("admin") == null) {
            User user = User.builder().username("admin")
                    .password("123")
                    .name("")
                    .lastName("")
                    .birthDate("")
                    .company("")
                    .address("")
                    .gender("male")
                    .role(Role.ADMIN)
                    .active(true).build();

            userRepository.save(user);
        }

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) //он же deprecated
                .usersByUsernameQuery("select username, password, active from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }

}
