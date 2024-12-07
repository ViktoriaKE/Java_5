package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            SomeBean sb = new Injector().inject(new SomeBean());
            sb.foo();  // Выведет "AC" или "BC" в зависимости от содержимого config.properties
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
