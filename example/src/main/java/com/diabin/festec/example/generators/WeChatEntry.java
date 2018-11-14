package com.diabin.festec.example.generators;


import com.diabin.latte.annotations.EntryGenerator;
import com.diabin.latte.wechat.templates.WXEntryTemplate;

//@EntryGenerator{
//    packageName = "com.diabin.festec.example",
//        entryTemplete = WXEntryTemplate.class
//}
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.diabin.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
