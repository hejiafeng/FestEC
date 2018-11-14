package com.diabin.latte.annotations;

public @interface AppRegisterGenerator {
    String packageName();
    Class<?> registerTemplate();
}
