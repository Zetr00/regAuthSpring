package com.example.authreg.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "book")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Description is required")
    private String description;
    @ManyToOne
    @JoinColumn(name = "class_id") // Указывает на столбец, который связывает сущности
    private ClassModel clas;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private ProductModel product;

    public BookModel(){}

    public BookModel(int id, String name, String author, ClassModel clas, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.clas = clas;
        this.description = description;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ClassModel getClas() {
        return clas;
    }

    public void setClas(ClassModel clas) {
        this.clas = clas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
