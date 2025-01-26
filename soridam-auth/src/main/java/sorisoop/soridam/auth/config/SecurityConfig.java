package sorisoop.soridam.auth.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.common.CustomAccessDeniedHandler;
import sorisoop.soridam.auth.jwt.JwtAuthenticationEntryPoint;
import sorisoop.soridam.auth.jwt.JwtAuthenticationFilter;
import sorisoop.soridam.auth.jwt.application.JwtProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final JwtProvider jwtProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(SWAGGER_PATTERNS).permitAll()
				.requestMatchers(STATIC_RESOURCES_PATTERNS).permitAll()
				.requestMatchers(PERMIT_ALL_PATTERNS).permitAll()
				.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
				.anyRequest().permitAll()
			)
			.exceptionHandling(exceptions -> exceptions
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler)
			)
			.build();
	}
	private static final String[] SWAGGER_PATTERNS = {
		"/swagger-ui/**",
		"/actuator/**",
		"/v3/api-docs/**",
	};

	private static final String[] STATIC_RESOURCES_PATTERNS = {
		"/img/**",
		"/css/**",
		"/js/**"
	};

	private static final String[] PERMIT_ALL_PATTERNS = {
		"/error",
		"/favicon.ico",
		"/index.html",
		"/",
	};

	private static final String[] PUBLIC_ENDPOINTS = {
		"/api/users/signup",
		"/api/auth/**",
	};


	CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
			config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // 필요한 메서드만 허용
			config.setAllowedOriginPatterns(Arrays.asList("*")); // 특정 도메인 허용
			config.setAllowCredentials(true); // 인증 정보 포함 허용
			return config;
		};
	}

	public JwtAuthenticationFilter tokenAuthenticationFilter() {
		return new JwtAuthenticationFilter(jwtProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(
		BCryptPasswordEncoder bCryptPasswordEncoder
	) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return new ProviderManager(authProvider);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
