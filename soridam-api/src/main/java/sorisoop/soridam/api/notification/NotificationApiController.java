package sorisoop.soridam.api.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.common.response.SliceResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationApiController {
	private final NotificationFacade notificationFacade;

	@GetMapping("/me")
	public ResponseEntity<SliceResponse<NotificationResponse>> getMyNotifications(Long lastId, int limit) {
		SliceResponse response = notificationFacade.getMyNotifications(lastId, limit);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}/read")
	public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
		notificationFacade.markAsRead(id);
		return ResponseEntity.noContent().build();
	}
}
