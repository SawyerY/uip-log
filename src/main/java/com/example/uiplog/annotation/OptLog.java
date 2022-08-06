package com.example.uiplog.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface OptLog {
    String value() default "";

    boolean valid() default true;

    Position logsPostion() default Position.Action;

    public static enum Position {
        Action,
        Service,
        DAO;

        private Position() {
        }
    }
}
