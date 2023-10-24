package com.group2.secotool_app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtils {

    public String normalizeText(String text){
        return text.trim().toLowerCase();
    }
}
