package com.hilatest.json.jackson;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilatest.Constant;

public class HelloWorld {

    @Test
    public void testHelloWorld() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        System.out.println(value);

        mapper.writeValue(new File(Constant.OutputPath, "result.json"), value);
        System.out.println(Constant.OutputPath.getAbsolutePath());
        // or:
        byte[] jsonBytes = mapper.writeValueAsBytes(value);
        System.out.println(jsonBytes);
        // or:
        String jsonString = mapper.writeValueAsString(value);
        System.out.println(jsonString);
    }
}

// Note: can use getters/setters as well; here we just use public fields directly:
class MyValue {
    public String name;
    public int age;

    // NOTE: if using getters/setters, can keep fields `protected` or `private`
    @Override
    public String toString() {
        return "MyValue [name=" + name + ", age=" + age + "]";
    }

}
