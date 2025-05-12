package sorisoop.soridam.domain.notification.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.notification.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {
	private final NotificationRepository notificationRepository;
	private final FavoritePlaceQueryService favoritePlaceQueryService;

	public void sendToFavoriteUsers(ReviewCreateEvent event) {

	}
}
