package com.snopkowski.elibrary.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "PUBLISHER")
public class PublisherDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PublisherDao))
            return false;
        PublisherDao other = (PublisherDao) obj;
        if (id != other.id)
            return false;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "UserDao [id=" + id + ", name=" + name + "]";
    }


}
