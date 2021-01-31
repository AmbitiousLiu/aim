package com.jleo.jcontrol.boot;

import com.jleo.jcontrol.access.InterceptorConfig;
import com.jleo.jcontrol.access.PermissionControl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启权限控制
 * 如果不使用此注解，虽然引入了包，但是没有启动拦截和增强
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({InterceptorConfig.class, PermissionControl.class})
public @interface EnableJControl {
}
