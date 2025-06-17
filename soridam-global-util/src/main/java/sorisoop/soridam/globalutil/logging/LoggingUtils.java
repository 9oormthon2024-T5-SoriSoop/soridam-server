package sorisoop.soridam.globalutil.logging;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingUtils {

	public static List<String> getArguments(JoinPoint joinPoint) {
		return Arrays.stream(joinPoint.getArgs())
			.map(LoggingUtils::getObjectFields)
			.toList();
	}

	public static String getObjectFields(Object obj) {
		if (Objects.isNull(obj)) {
			return "null";
		}

		StringBuilder result = new StringBuilder();
		Class<?> objClass = obj.getClass();
		result.append(objClass.getSimpleName()).append(" {");

		Field[] fields = objClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				result.append(fields[i].getName()).append(" = ")
					.append(fields[i].get(obj));
			} catch (IllegalAccessException e) {
				result.append(fields[i].getName()).append("=ACCESS_DENIED");
			}
			if (i < fields.length - 1) {
				result.append(", ");
			}
		}
		result.append("}");
		return result.toString();
	}

	public static String getParameterMessage(List<String> arguments) {
		if (arguments == null)
			return "";

		return String.join(" | ", arguments);
	}

	public static void logRequest(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =
			(authentication == null || Objects.equals(authentication.getName(), "anonymousUser")) ? "anonymous" :
				authentication.getName();
		String clientIp = HttpReqResUtils.getClientIpAddressIfServletRequestExist();
		String requestId = UUID.randomUUID().toString();

		MDC.put("requestId", requestId);
		MDC.put("userId", userId);
		MDC.put("clientIp", clientIp);
		MDC.put("uri", request.getRequestURI());
		MDC.put("method", request.getMethod());

		log.info("HTTP request started");
	}

	public static void logDuration(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		int status = response.getStatus();
		long startTime = (Long) request.getAttribute("startTime");
		long duration = System.currentTimeMillis() - startTime;

		MDC.put("status", String.valueOf(status));
		MDC.put("durationMs", String.valueOf(duration));

		if (ex == null) {
			log.info("HTTP request completed");
		} else {
			log.error("HTTP request failed", ex);
		}

		MDC.clear();
	}
}
