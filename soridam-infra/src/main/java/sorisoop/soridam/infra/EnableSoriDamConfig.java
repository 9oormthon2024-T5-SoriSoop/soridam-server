package sorisoop.soridam.infra;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Target(TYPE)
@Retention(RUNTIME)
@Import(SoriDamConfigImportSelector.class)
public @interface EnableSoriDamConfig {
	SoriDamConfigGroup[] value();
}

