package me.study.redis.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Student")
@AllArgsConstructor
public class Person {

    private String name;
}
