package org.commmstudy.hibernate.jpa.relationship.refernoid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "JPA_PERSION_NAME_NOT_ID_REF")
public class PersonName {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "MEANING")
    private String meaning;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonName other = (PersonName) obj;
        if (meaning == null) {
            if (other.meaning != null)
                return false;
        } else if (!meaning.equals(other.meaning))
            return false;
        return true;
    }

}
