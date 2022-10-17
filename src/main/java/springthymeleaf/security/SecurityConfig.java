package springthymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/", "/inicio")
                .access("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/clientes/**", "/servicos/**","/produtos/**","/ordemservico/**")
                .access("hasAuthority('ROLE_ADMIN')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/inicio", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
                
    }

    //@Override
    //public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers(HttpMethod.POST,"/clientes/**","/servicos/**","/produtos/**","/ordemservico/**");
        //web.ignoring().antMatchers("/servicos/**", "/clientes/**", "/produtos/**");
        // web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
        //     @Override
        //     protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation filter) {
        //         WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, filter);
        //         root.setDefaultRolePrefix(""); //remove the prefix ROLE_
        //         return root;
        //     }
        // });
    //}

}
