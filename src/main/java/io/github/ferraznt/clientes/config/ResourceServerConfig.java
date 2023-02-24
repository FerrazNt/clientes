package io.github.ferraznt.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/usuarios/**","/h2-console/**").permitAll()
                .antMatchers("/api/clientes/**", "/api/servicos-prestado/**").authenticated()
                .anyRequest().denyAll()
                // and incluido para liberar Console H2 (Não deve ser feito em Produção para Banco Local)
                .and().headers().frameOptions().disable();
    }
}
