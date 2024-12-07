package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, используемая для пометки полей, которые должны быть автоматически
 * инжектированы через {@link Injector}.
 * <p>
 * Поля, помеченные этой аннотацией, будут автоматически инжектированы с помощью
 * реализации, указанной в конфигурационном файле {@code config.properties}.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME) // Аннотация будет доступна на этапе выполнения
@Target(ElementType.FIELD) // Аннотация может быть применена только к полям
public @interface AutoInjectable {
    // Эта аннотация не требует дополнительных полей
}