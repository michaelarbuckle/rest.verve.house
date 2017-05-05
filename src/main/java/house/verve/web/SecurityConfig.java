/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package house.verve.web;

import house.verve.security.CustomAuthenticationProvider;
import house.verve.security.VerveAuthenticationEntryPoint;

//import org.baeldung.security.google2fa.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import house.verve.security.AuthoritiesConstants;
import house.verve.security.TokenAuthenticationService;
import house.verve.security.StatelessLoginFilter;
import house.verve.security.StatelessAuthenticationFilter;


import house.verve.security.VerveUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@EnableWebSecurity
//@EnableOAuth2Sso

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private TokenAuthenticationService  tokenAuthenticationService; 

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
/**/
		http.
		httpBasic().authenticationEntryPoint(entryPoint())
        .and().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
	        .csrf()
	        .disable()
	        .headers()
	        .frameOptions()
	        .disable()
	    .and()
	    .authorizeRequests().antMatchers("/registerNewUser", "/login", "/oauth/authorize", "/oauth/confirm_access").permitAll()
	    
        .antMatchers("/messages").authenticated()
        .antMatchers("/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
        .anyRequest().permitAll();
		
		http.exceptionHandling()
        .authenticationEntryPoint(entryPoint())
        .and()
		// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		.addFilterBefore(new StatelessLoginFilter("/login", tokenAuthenticationService, ((VerveUserDetailsService)userDetailsService), authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)

		// custom Token based authentication based on the header previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);

 	
       
       // .anyRequest().authenticated();
		 
		/*
		 http
         .csrf().disable()
         .authorizeRequests()
             .antMatchers("/login*", "/logout*", "/signin/**", "/signup/**","/registerNewUser","/qrcode*").permitAll()
             .antMatchers("/invalidSession*").anonymous()
             .anyRequest().authenticated()
         	.and()
    		.requestCache()
    			.requestCache(new NullRequestCache())
    			.and()
    		.httpBasic().and()
    	    .logout().logoutSuccessUrl("/").permitAll()
             .and()
             .sessionManagement()
                 .invalidSessionUrl("/invalidSession.html")
                 .sessionFixation().none()
             .and()
             .logout()
                   .deleteCookies("JSESSIONID")
                 .permitAll();
		  */

	     //  http.csrf().disable(); 
		/*
	      http
	        .httpBasic()
	      .and()
	        .authorizeRequests().anyRequest().permitAll()
	          .anyRequest().authenticated();
	      
	 	 	
		 http.authorizeRequests().antMatchers("**").permitAll().and().csrf().disable().and().headers().frameOptions().sameOrigin().xssProtection();

		 http
	      .httpBasic().and()
	      .authorizeRequests()
	        .antMatchers("/*").permitAll().anyRequest()
	        .authenticated().and()
	      .csrf()
	        .csrfTokenRepository(CsrfTokenRepository);
	*/
	
	}
	// @formatter:on

	// @formatter:off
	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
	

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	 
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {	    	 
	        return super.authenticationManagerBean();
	    }
	 
	    @Override
	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProvider());
	    }
	    
	    // beans

	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder( passwordEncoder());
	        return authProvider;
	    }
	    
	    /*
	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	    */

	    private CsrfTokenRepository csrfTokenRepository() {
	    	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	    	  repository.setHeaderName("X-XSRF-TOKEN");
	    	  return repository;
	    	}
	    
	    @Bean
	    public AuthenticationEntryPoint entryPoint() {
	        return (request, response, authException) -> { System.out.println("hit commence");response.sendError(HttpServletResponse.SC_UNAUTHORIZED);};
	    }
	    /*
	    private AuthenticationEntryPoint entryPoint0() {
	           return new LoginUrlAuthenticationEntryPoint("/login"); // note 3ii
	      }
	    
	    private AuthenticationEntryPoint entryPoint() {
	           return new VerveAuthenticationEntryPoint("/login"); // note 3ii
	      }
	    */
	    
	    
	    
	    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
	           UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
	           try {
				filter.setAuthenticationManager(authenticationManager());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login?login_error=true");
	           filter.setAuthenticationFailureHandler(failureHandler);
	           return filter;
	      }

}
