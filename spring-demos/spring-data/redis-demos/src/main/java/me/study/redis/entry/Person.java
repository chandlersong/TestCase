package me.study.redis.entry;

import lombok.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Person")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private long id = RandomUtils.nextLong();

    @NonNull
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
