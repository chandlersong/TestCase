package org.example.go;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private String iD;
    private String name;
    private String age;
    private Timestamp time;
}
