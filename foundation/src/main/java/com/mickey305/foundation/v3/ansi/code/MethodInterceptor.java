//package com.mickey305.foundation.v3.ansi.code;
//
//import com.mickey305.foundation.v3.annotation.validation.MethodInterceptorBinding;
//
//import javax.inject.Inject;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.Interceptor;
//import javax.interceptor.InterceptorBinding;
//import javax.interceptor.InvocationContext;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import javax.validation.executable.ExecutableValidator;
//import java.util.Set;
//
//@Interceptor
//@MethodInterceptorBinding
//public class MethodInterceptor {
//
//    @Inject
//    private Validator validator;
//
//    @AroundInvoke
//    public Object validateMethodInvocation(InvocationContext context)
//            throws Exception {
//        Set<ConstraintViolation<Object>> violations;
//        ExecutableValidator executableValidator = validator.forExecutables();
//        violations = executableValidator.validateParameters(
//                context.getElements(), context.getMethod(), context.getParameters());
//        processViolations(violations);
//        Object result = context.proceed();
//        violations = executableValidator.validateReturnValue(
//                context.getElements(), context.getMethod(), result);
//        processViolations(violations);
//        return result;
//    }
//
//    private void processViolations(Set<ConstraintViolation<Object>> violations) {
//        // nop
//    }
//
//}
