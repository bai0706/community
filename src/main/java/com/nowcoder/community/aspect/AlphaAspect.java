package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    // （）分别表示方法的返回值（可以返回任何类型），包名， service业务包下的所有类， 所有方法， 所有参数
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut(){
    }

    // 通知包括5个方法：前，后， 返回，异常，环绕
    @Before("pointcut()")
    public void before(){
        System.out.println("before");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void AfterReturning(){
        System.out.println("afterReturning");
    }

    @AfterThrowing("pointcut()")
    public void AfterThrowing(){
        System.out.println("afterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        System.out.println("around before");

        // joinPoint连接点指 织入的部位
        // 调用目标组件的方法
        Object obj = joinPoint.proceed();

        System.out.println("around after");

        return obj;
    }
}
