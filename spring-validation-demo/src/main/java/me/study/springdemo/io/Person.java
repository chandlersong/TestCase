package me.study.springdemo.io;

import lombok.Data;
import me.study.springdemo.bean.CheckFromBean;

@Data
public class Person {

    @CheckFromBean
    private String name;
}
