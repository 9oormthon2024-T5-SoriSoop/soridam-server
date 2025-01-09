package sorisoop.soridam.api.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.common.exception.CustomException;
import sorisoop.soridam.globalutil.logging.LoggingUtils;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {
	@Pointcut("execution(public * sorisoop.soridam..*(..)) && "
		+ "!execution(* sorisoop.soridam.api..application..*(..)) && "
		+ "!execution(* sorisoop.soridam.common..*(..)) && "
		+ "!@annotation(sorisoop.soridam.common.log.annotation.NoLogging) && "
		+ "!@annotation(org.springframework.boot.context.properties.ConfigurationProperties)"
	)
	private void logPointcut() {
	}

	@AfterThrowing(value = "logPointcut()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, CustomException exception) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();

		List<String> arguments = LoggingUtils.getArguments(joinPoint);
		String parameterMessage = LoggingUtils.getParameterMessage(arguments);

		log.error("[ERROR] POINT : {} || EXCEPTION : {} || ARGUMENTS : {}", className, exception.getCode().getCode(),
			parameterMessage);
		log.error("[ERROR] FINAL POINT : {}", exception.getStackTrace()[0]);
		log.error("[ERROR] MESSAGE : {}", exception.getMessage());
	}

}
