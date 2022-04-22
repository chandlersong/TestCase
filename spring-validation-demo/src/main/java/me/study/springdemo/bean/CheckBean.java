package me.study.springdemo.bean;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Log4j2
@Component
public class CheckBean implements ConstraintValidator<CheckFromBean, String> {

    private List<String> cache;

    @PostConstruct
    public void setup() {
        log.info("initial from spring content");
        cache = Lists.newArrayList();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.info("check value:{}", s);
        log.info("it's bean from spring,{}", cache != null);
        return true;
    }
}
