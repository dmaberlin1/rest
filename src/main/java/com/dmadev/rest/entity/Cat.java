package com.dmadev.rest.entity;

import jakarta.persistence.*;
import lombok.*;

// @Datadata - getter/setter/ required args constructor/tostring/equals-hashcode
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private int age;
    private int weight;


    public Cat(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Cat() {
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
