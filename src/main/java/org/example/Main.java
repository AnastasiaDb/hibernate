package org.example;

import org.example.part1.Order;
import org.example.part1.Product;
import org.example.part1.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        User user = new User();
        user.setName("mya");

        Product product = new Product();
        product.setPrice(1.56);

        Order order = new Order();
        Set<Product> products = new HashSet<>();
        products.add(product);
        order.setProducts(products);

        order.setUser(user);
        order.setProducts(products);

        user.getOrders().add(order);

        session.persist(user);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}