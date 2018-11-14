package com.diabin.festec.example.generators;

import com.diabin.latte.annotations.AppRegisterGenerator;
import com.diabin.latte.wechat.templates.AppRegisterTemplate;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.diabin.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
