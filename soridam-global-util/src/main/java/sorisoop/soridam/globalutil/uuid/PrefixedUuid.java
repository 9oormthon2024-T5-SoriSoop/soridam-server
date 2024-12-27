package sorisoop.soridam.globalutil.uuid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;

@IdGeneratorType(PrefixedUuidGenerator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrefixedUuid {
	UuidPrefix value() default UuidPrefix.DEFAULT;
}
