package com.snopkowski.elibrary.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TYPE", length = 15, unique = true, nullable = false)
    private String type = UserProfileType.USER.getUserProfileType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserProfile))
            return false;
        UserProfile other = (UserProfile) obj;
        if (id != other.id)
            return false;
        if (type == null) {
            return other.type == null;
        } else return type.equals(other.type);
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ",  type=" + type + "]";
    }


}
