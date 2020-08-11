package dev.kun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class OAuthSecureConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    /**
     * 指定Token存储方式（JdbcTokenStore,RedisTokenStore,InMemoryTokenStore,JwtTokenStore等实现）
     * @return
     */
    @Bean
    public TokenStore jdbcTokenStrore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 当前资源的ID
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("product_api").tokenStore(jdbcTokenStrore());
    }

    /**
     * TODO 需要完善
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST,"/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH,"/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT,"/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE,"/**").access("#oauth2.hasScope('write')")
                .and()
                .cors();
    }
}
