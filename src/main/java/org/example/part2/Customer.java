package org.example.part2;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends AbstractBaseEntity {

    public Customer() {
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Goods> goods = new HashSet<>();

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }


}
