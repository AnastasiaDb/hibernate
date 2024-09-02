package org.example;

import org.example.part1.Order;
import org.example.part1.Product;
import org.example.part1.Profile;
import org.example.part1.User;
import org.example.part2.Customer;
import org.example.part2.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        part2();
    }

    public static void part2() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Goods.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("John Doe");

        Goods goods1 = new Goods();
        goods1.setName("Laptop");
        goods1.setPrice(1200.99);
        goods1.setCustomer(customer);

        Goods goods2 = new Goods();
        goods2.setName("Smartphone");
        goods2.setPrice(699.99);
        goods2.setCustomer(customer);

        Set<Goods> goodsSet = new HashSet<>();
        goodsSet.add(goods1);
        goodsSet.add(goods2);
        customer.setGoods(goodsSet);

        session.persist(customer);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

    public static void part1() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml").
                addAnnotatedClass(Order.class).
                addAnnotatedClass(Product.class).
                addAnnotatedClass(Profile.class).
                addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

//        User user = new User();
//        user.setName("mya");
//
//        Product product = new Product();
//        product.setPrice(1.56);
//
//        Order order = new Order();
//        Set<Product> products = new HashSet<>();
//        products.add(product);
//        order.setProducts(products);
//
//        order.setUser(user);
//        order.setProducts(products);
//
//        user.getOrders().add(order);
//
//        session.persist(user);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}