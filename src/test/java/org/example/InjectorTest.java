package org.example;

import org.junit.jupiter.api.Test;

class InjectorTest {

    @Test
    void testSuccessfulInjection() throws Exception {
        // Создаем объект, который будет иметь поле для внедрения
        TestClass testObject = new TestClass();
        Injector injector = new Injector();

        // Инжектируем зависимости
        injector.inject(testObject);

        // Просто выводим результат
        if (testObject.myService == null) {
            System.out.println("Test failed: Dependency was not injected.");
        } else {
            System.out.println("Test passed: Dependency was injected.");
        }
    }

    static class TestClass {
        @AutoInjectable
        private MyService myService;
    }

    interface MyService {
        void performAction();
    }

    static class MyServiceImpl implements MyService {
        @Override
        public void performAction() {
            // Реализация
        }
    }
}