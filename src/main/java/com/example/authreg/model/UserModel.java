package com.example.authreg.model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private String midlename;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="users_class",
            joinColumns=@JoinColumn (name="users_id"),
            inverseJoinColumns=@JoinColumn(name="class_id"))
    private List<ClassModel> clas;



    public UserModel(){}

    public UserModel(long id, String name, String surname, List<ClassModel> clas, String midlename) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.clas = clas;
        this.midlename = midlename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String school) {
        this.surname = surname;
    }

    public List<ClassModel> getClas() {
        return clas;
    }

    public void setClas(List<ClassModel> clas) {
        this.clas = clas;
    }

    public String getMidlename() {
        return midlename;
    }

    public void setMidlename(String midlename) {
        this.midlename = midlename;
    }
}
