package org.example.part3;

import jakarta.persistence.*;
import org.example.part2.Goods;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    private Set<Good> goods = new HashSet<>();

    public Customers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }
}
