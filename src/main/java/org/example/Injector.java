package org.example;

import java.io.InputStream;
import java.util.Properties;
import java.lang.reflect.Field;
import java.io.FileNotFoundException;

/**
 * Класс для внедрения зависимостей в объект с использованием рефлексии.
 * <p>
 * Класс использует настройки, указанные в файле конфигурации {@code config.properties}, чтобы
 * автоматически внедрить зависимости в поля объекта, помеченные аннотацией {@code @AutoInjectable}.
 * </p>
 *
 * <p>
 * Файл {@code config.properties} должен содержать соответствия между интерфейсами и классами их реализаций:
 * {@code <интерфейс>=<реализация>}.
 * </p>
 */
public class Injector {

    /**
     * Внедряет зависимости в объект, помеченные аннотацией {@code @AutoInjectable}.
     *
     * @param object Объект, в который будут внедрены зависимости.
     * @param <T> Тип объекта, в который внедряются зависимости.
     * @return Объект с внедренными зависимостями.
     * @throws Exception Если произошла ошибка при загрузке конфигурации, рефлексии или создании объектов.
     * @throws FileNotFoundException Если файл {@code config.properties} не найден.
     */
    public <T> T inject(T object) throws Exception {
        // Загружаем файл настроек
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("config.properties не найден в ресурсах.");
        }

        Properties properties = new Properties();
        properties.load(inputStream);

        // Получаем все поля объекта
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // Проверяем, помечено ли поле аннотацией AutoInjectable
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                // Получаем имя интерфейса, которое должно быть внедрено
                String interfaceName = field.getType().getName();

                // Получаем класс реализации из конфигурационного файла
                String implementationClassName = properties.getProperty(interfaceName);
                if (implementationClassName != null) {
                    // Загружаем класс реализации
                    Class<?> implClass = Class.forName(implementationClassName);
                    Object implementation = implClass.getDeclaredConstructor().newInstance();

                    // Инициализируем поле
                    field.setAccessible(true);
                    field.set(object, implementation);
                }
            }
        }

        return object;
    }
}