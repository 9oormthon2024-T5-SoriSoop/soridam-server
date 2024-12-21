package sorisoop.soridam.common.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import sorisoop.soridam.infra.uuid.PrefixedUuid;
import sorisoop.soridam.infra.uuid.UuidPrefix;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

	@Id
	@PrefixedUuid(UuidPrefix.DEFAULT)
	String id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	public String extractUuid() {
		return id.substring(id.indexOf("_") + 1);
	}
}