package com.jleo.jcontrol.access;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Component
public @interface Permission {
    /**
     * 特定用户可通过
     * 优先级：1
     * @return
     */
    String[] user() default {};

    /**
     * 特定角色的用户可通过
     * 优先级：2
     * @return
     */
    String[] role() default {};

    /**
     * 符合条件的用户是否可访问
     * @return
     */
    boolean pass() default true;
}
