package org.example;

public class SomeBean {

    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();  // Выведет "A" или "B"
        field2.doSomething();  // Выведет "C"
    }
}