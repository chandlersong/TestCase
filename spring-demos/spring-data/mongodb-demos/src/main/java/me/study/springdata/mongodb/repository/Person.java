package me.study.springdata.mongodb.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "person")
@TypeAlias("Person")
public class Person {

    @Id
    private BigInteger id;

    private String name;

    private String title;

    @DBRef
    private List<Address> addressList;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
