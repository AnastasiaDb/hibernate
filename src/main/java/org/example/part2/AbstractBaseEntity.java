package org.example.part2;

import jakarta.persistence.*;

import javax.naming.Name;

@MappedSuperclass
 abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

@Entity
@Table(name = "CustomerAndGoods")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class JoinedBaseEntity extends AbstractBaseEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
