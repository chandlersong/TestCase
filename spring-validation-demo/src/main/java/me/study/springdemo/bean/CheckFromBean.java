package me.study.springdemo.bean;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckBean.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckFromBean {

    String message() default "Email address is already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
