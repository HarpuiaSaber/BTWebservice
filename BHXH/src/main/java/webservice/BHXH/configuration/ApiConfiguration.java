package webservice.BHXH.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class ApiConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().antMatcher("/api/**").authorizeRequests().antMatchers("/api/admin/**")
				.hasAnyRole("ADMIN").antMatchers("/api/user/**").authenticated().anyRequest().permitAll();
	}

}
