package com.example.authreg.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "class")
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable (name="users_class",
            joinColumns=@JoinColumn (name="class_id"),
            inverseJoinColumns=@JoinColumn(name="users_id"))
    private List<UserModel> users;

    @OneToMany (mappedBy = "clas", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<BookModel> hero;

    public ClassModel(){}

    public ClassModel(long id, String name, List<UserModel> users, Collection<BookModel> hero) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.hero = hero;
    }

    public ClassModel(String name) {
        this.name = name;
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

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<UserModel> getUser() {
        return users;
    }

    public void setUser(List<UserModel> users) {
        this.users = users;
    }

    public Collection<BookModel> getHero() {
        return hero;
    }

    public void setHero(Collection<BookModel> hero) {
        this.hero = hero;
    }
}
