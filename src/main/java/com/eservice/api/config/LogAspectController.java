package com.eservice.api.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: tao.zhang@hankun-tech.com
 * @user: Silent
 * @date: Created in 2019/11/28 9:53
 * @description: 描述
 */

@Aspect
@Component
public class LogAspectController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Description: 定义切入点
     * @Title: pointCut
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:17
     */
    //被注解CustomAopAnnotation表示的方法
    //@Pointcut("@annotation(com.only.mate.springboot.annotation.CustomAopAnnotation")
    @Pointcut("execution(public *  com.eservice.api.web..*(..))")

//    @Pointcut("execution(public * com.didispace.web..*.*(..))")
    public void pointCut(){
        logger.info(" 【注解：PointCut】 不会进！！！ ");
    }

    /**
     * @Description: 定义前置通知
     * @Title: before
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:23
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info(" [AOP] URL : " + request.getRequestURL().toString());
     ///   logger.info("【注解：Before】HTTP_METHOD : " + request.getMethod());
        logger.info(" [AOP] IP : " + request.getRemoteAddr());
     ///   logger.info("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info(" [AOP] ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * @Description: 后置返回通知
     * @Title: afterReturning
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:30
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void afterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
     //   logger.info("[AOP] Return: " + ret);
    }

    /**
     * @Description: 后置异常通知
     * @Title: afterThrowing
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:37
     * @param jp
     */
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterThrowing(JoinPoint jp,Exception e){
        logger.info("[AOP] 方法异常时执行....." + e.getMessage());
    }

    /**
     * @Description: 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     * @Title: after
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:48
     * @param jp
     */
    @After("pointCut()")
    public void after(JoinPoint jp){
     ///   logger.info("【注解：After】方法最后执行.....");
    }

    /**
     * @Description: 环绕通知,环绕增强，相当于MethodInterceptor
     * @Title: around
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:56
     * @param pjp
     * @return
     */
/*    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        logger.info("【注解：Around . 环绕前】方法环绕start.....");
        try {
            //如果不执行这句，会不执行切面的Before方法及controller的业务方法
            Object o =  pjp.proceed();
            logger.info("【注解：Around. 环绕后】方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
