package com.xiongantaoli.background.config;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.xiongantaoli.background.security.auth.MyAuthenticationProvider;

/**
 * Spring Security配置
 * 
 * @author 王震
 *
 */
@Configuration  
@EnableWebSecurity  
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAuthenticationProvider provider;// 自定义验证
	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {//.defaultSuccessUrl("/welcome")
		http.authorizeRequests().antMatchers("/kaptcha/getKaptchaImage", "/druid/**").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").failureUrl("/login?error").permitAll().authenticationDetailsSource(authenticationDetailsSource)
				.and().headers().frameOptions().sameOrigin().contentTypeOptions().disable().and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h-ui/**").antMatchers("/h-ui.admin/**").antMatchers("/lib/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}



}
