package me.study.redis.entry;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Student")
public class Person {

    private String name;
}
