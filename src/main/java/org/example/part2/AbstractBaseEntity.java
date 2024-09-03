package org.example.part2;

import jakarta.persistence.*;



@Entity
@Table(name = "CustomerAndGoods")
@Inheritance
public abstract class AbstractBaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


