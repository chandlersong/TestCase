package org.commmstudy.hibernate.jpa.relationship.refernoid.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "JPA_PERSION_NOT_ID_REF")
public class Person {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID", nullable = false)
    private String id;

    @OneToMany(targetEntity = PersonName.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "NAME", referencedColumnName = "NameFlag")
    private List<PersonName> names;

    @Column(name = "NameFlag")
    private String nameFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PersonName> getNames() {
        return names;
    }

    public void setNames(List<PersonName> names) {
        this.names = names;
    }

    public String getNameFlag() {
        return nameFlag;
    }

    public void setNameFlag(String nameFlag) {
        this.nameFlag = nameFlag;
    }

}
