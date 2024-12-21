package sorisoop.soridam.common.uuid;

import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


public class PrefixedUuidGenerator implements IdentifierGenerator {

	private final UuidPrefix value;

	public PrefixedUuidGenerator(PrefixedUuid config) {
		this.value = config.value();
	}

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		return value.getPrefix() + UUID.randomUUID();
	}

}

