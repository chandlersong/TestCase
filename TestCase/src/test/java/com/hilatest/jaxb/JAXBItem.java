package com.hilatest.jaxb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlSeeAlso({ MapElements.class })
public class JAXBItem {
    private String name;
    private int age;
    private int id;

    private Map<String, String> map1 = new HashMap<String, String>();

    private Map<String, String> map2 = new HashMap<String, String>();

    private List<String> list1 = new ArrayList<String>();

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "map1")
    public Map<String, String> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, String> map1) {
        this.map1 = map1;
    }

    public String map1Put(String key, String value) {
        return map1.put(key, value);
    }

    public Map<String, String> getMap2() {
        return map2;
    }

    @XmlElement(name = "map2")
    @XmlJavaTypeAdapter(MapAdapter.class)
    public void setMap2(Map<String, String> map2) {
        this.map2 = map2;
    }

    public String map2Put(String key, String value) {
        return map2.put(key, value);
    }

    @XmlElement(name = "abc")
    @XmlElementWrapper(name = "list1")
    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public boolean list1add(String e) {
        return list1.add(e);
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ",name=" + name + ",age=" + age + "]";
    }
}
