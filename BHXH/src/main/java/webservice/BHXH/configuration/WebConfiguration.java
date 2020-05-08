package webservice.BHXH.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home").permitAll().antMatchers("/admin/**").hasAnyRole("ADMIN").and()
				.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/auth").defaultSuccessUrl("/dashboard").failureUrl("/login?e=1").and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID").and().exceptionHandling()
				.accessDeniedPage("/error/403.html").and().rememberMe().rememberMeCookieName("app-remember-me")
				.tokenValiditySeconds(2592000);
	}

}
