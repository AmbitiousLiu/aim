package com.jleo.jcontrol.access;

import com.jleo.jcontrol.bean.Exception.JControlException;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.role.service.RoleService;
import com.jleo.jcontrol.session.Conversation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jleo
 * @date 2020/12/19
 */
@Aspect
public class PermissionControl {

    @Autowired
    private Conversation conversation;
    @Autowired
    private RoleService roleService;

    @Pointcut("execution(@com.jleo.jcontrol.access.Permission * *.*(..))")
    public void targetMethod() {}

    @Around("targetMethod()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        if (!conversation.isLogin(request)) {
            return new CodeResult(JControlConstant.CODE_RESULT_ERROR, JControlConstant.JCONTROL_CONSOLE_NAME + "must login at method:" + method.getName());
        }

        String userId = conversation.getUserId(request);
        List<String> roles = new ArrayList<>();
        roleService.getRoleByUserId(userId).forEach(roleDO -> {
            roles.add(roleDO.getName());
        });

        List<String> perUser = Arrays.stream(permission.user()).collect(Collectors.toList());
        List<String> perRole = Arrays.stream(permission.role()).collect(Collectors.toList());
        if (!perUser.isEmpty() || !perRole.isEmpty()) {
            boolean userPass = perUser.contains(userId);
            List<String> perRoleTemp = new ArrayList<String>(){{addAll(perRole);}};
            perRoleTemp.retainAll(roles);
            boolean rolePass = !perRoleTemp.isEmpty();

            if (permission.pass()) {
                if (userPass) {
                    return proceedingJoinPoint.proceed();
                }
                if (perRole.isEmpty()) {
                    return new CodeResult(JControlConstant.CODE_RESULT_ERROR, JControlConstant.JCONTROL_CONSOLE_NAME + "no access at method:" + method.getName());
                }
                if (rolePass) {
                    return proceedingJoinPoint.proceed();
                }
                return new CodeResult(JControlConstant.CODE_RESULT_ERROR, JControlConstant.JCONTROL_CONSOLE_NAME + "no access at method:" + method.getName());
            } else {
                if (userPass) {
                    return new CodeResult(JControlConstant.CODE_RESULT_ERROR, JControlConstant.JCONTROL_CONSOLE_NAME + "no access at method:" + method.getName());
                }
                if (perRole.isEmpty()) {
                    return proceedingJoinPoint.proceed();
                }
                if (rolePass) {
                    return new CodeResult(JControlConstant.CODE_RESULT_ERROR, JControlConstant.JCONTROL_CONSOLE_NAME + "no access at method:" + method.getName());
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
