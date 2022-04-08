package com.delfsoftware.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager manager;
	
	/*@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RedisConnectionFactory redis;*/
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("seguranca-back")
				.secret(passwordEncoder.encode("123"))
				.authorizedGrantTypes("password", "refresh_token")
				/* client credencial para uma outra aplicação
				.and()
					.withClient("facturamento")
					.secret(passwordEncoder.encode("fact123"))
					.authorizedGrantTypes("client_credentials")*/
				.scopes("write","read")
				.accessTokenValiditySeconds(60*5); //1 minuto
				/* accessTokenValiditySeconds(60*60*6)6h
				 * */
				
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			//security.checkTokenAccess("permitAll()");
			security.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.authenticationManager(manager)
				.accessTokenConverter(jwt());
				//.userDetailsService(userDetailsService)
				//.tokenStore(redisTokenStore());
	}
	
	/* o redis mante´m o token valido mesmo depois de finalzar aplicação ele cumpre o tempo do token
	private TokenStore redisTokenStore() {
		return new RedisTokenStore(redis);
	}
	*/
	// refresh token
	
	@Bean
	public JwtAccessTokenConverter jwt() {
		JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
		
		jwtAccessTokenConverter.setSigningKey("segur4nc0");
		
		return jwtAccessTokenConverter;
	}
}
