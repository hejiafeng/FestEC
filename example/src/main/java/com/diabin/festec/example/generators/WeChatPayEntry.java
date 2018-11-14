package com.diabin.festec.example.generators;

import com.diabin.latte.annotations.PayEntryGenerator;
import com.diabin.latte.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.diabin.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public  interface WeChatPayEntry {
}
