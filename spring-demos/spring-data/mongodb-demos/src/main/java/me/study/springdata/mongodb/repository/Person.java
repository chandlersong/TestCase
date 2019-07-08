package me.study.springdata.mongodb.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@Document(collection = "test1")
public class Person {

    @Id
    private BigInteger id;

    private String name;
}
