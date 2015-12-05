package com.beginner.jsr330;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {
    public String hello() {
        return "spring singleton";
    }
}