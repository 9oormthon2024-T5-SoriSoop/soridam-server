package sorisoop.soridam.infra.config;

import java.util.Arrays;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int processors = Runtime.getRuntime().availableProcessors();
		executor.setCorePoolSize(processors);
		executor.setMaxPoolSize(processors * 2);
		executor.setQueueCapacity(50);
		executor.setKeepAliveSeconds(60);
		executor.setThreadNamePrefix("AsyncExecutor-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (ex, method, params) -> {
			System.err.println("[Async Error] " + ex.getMessage());
			System.err.println("→ Method: " + method.getName());
			Arrays.stream(params).forEach(p -> System.err.println("→ Param: " + p));
		};
	}
}

