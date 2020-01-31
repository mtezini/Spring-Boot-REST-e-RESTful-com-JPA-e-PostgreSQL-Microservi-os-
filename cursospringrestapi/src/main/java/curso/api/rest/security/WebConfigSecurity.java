package curso.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import curso.api.rest.service.ImplementacaoUserDetailsService;

/*Classe de Segurança
 *  Mapear URL, endereços, autorização e bloqueio de acesso a URL */

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;

	/* Configura as solicitações de acesso por HTTP */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* Ativando a proteção contra usuários que não estão validados por token */

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				/*
				 * Ativando a permissão para acesso a pagina inicial do sistema EX:
				 * teste.com.br/index.html
				 */
				.disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/index").permitAll()

				/* URL de Logout - Redirecinamento de pagina para index */
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

				/* Mapeia URL de Logout e invalida usuário */
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		/* Filtra as requisições de login para autenticação */
		
		/* Filtra demais requisições para verificar as previsões do TOKEN JWT no HEADER HTTP */
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* Service consulta usuário no BD */
		auth.userDetailsService(implementacaoUserDetailsService)

				/* Padrão de codificação de senha */

				.passwordEncoder(new BCryptPasswordEncoder());
		super.configure(auth);
	}

}
