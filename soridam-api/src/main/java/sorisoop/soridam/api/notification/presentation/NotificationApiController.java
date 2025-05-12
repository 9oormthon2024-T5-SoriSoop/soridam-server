package sorisoop.soridam.api.notification.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.notification.application.NotificationFacade;
import sorisoop.soridam.common.response.SliceResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Notification", description = "알림 API")
@RequestMapping("/api/notifications")
public class NotificationApiController {
	private final NotificationFacade notificationFacade;

	@GetMapping("/me")
	public ResponseEntity<SliceResponse> getMyNotifications(
		@RequestParam(required = false)
		@Parameter(description = "커서 페이징을 위한 마지막 즐겨찾기 Id", example = "1")
		String lastId,

		@RequestParam(defaultValue = "10")
		@Parameter(description = "가져올 데이터 개수", example = "10")
		int limit
	) {
		Long last = StringUtils.hasText(lastId) ? Long.parseLong(lastId) : null;
		SliceResponse response = notificationFacade.getMyNotifications(last, limit);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}/read")
	public ResponseEntity<Void> markAsRead(
		@Parameter(description = "읽을 알림의 ID", example = "1", required = true)
		@PathVariable Long id
	) {
		notificationFacade.markAsRead(id);
		return ResponseEntity.noContent().build();
	}
}
