package org.example;

import org.example.part1.Order;
import org.example.part1.Product;
import org.example.part1.Profile;
import org.example.part1.User;
import org.example.part2.AbstractBaseEntity;
import org.example.part2.Customer;
import org.example.part2.Goods;
///+import org.example.part2.SingleBaseEntity;
//import org.example.part2.SingleBaseEntity;
import org.example.part3.Customers;
import org.example.part3.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        LazyInitializationEx();
    }

    public static void LazyInitializationEx() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml").
                addAnnotatedClass(Customers.class).
                addAnnotatedClass(Good.class);
        try {

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();


            Customers customers = new Customers();
            customers.setName("Nastya");

            Good good1 = new Good();
            good1.setPrice(3.14);
            good1.setName("tomato");

            Good good2 = new Good();
            good2.setPrice(2.2);
            good2.setName("cola");

            Set<Good> goods = new HashSet<>();
            good1.setCustomers(customers);
            good2.setCustomers(customers);
            goods.add(good1);
            goods.add(good2);

            customers.setGoods(goods);

            session.persist(customers);
            session.persist(good1);
            session.persist(good2);
            session.getTransaction().commit();
            //    System.out.println(session.find(Customers.class, customers.getId()));
            //     System.out.println("Saved customer ID: " + customers.getId());
            session.close();

            Session newSession = sessionFactory.openSession();
            Customers persistentCustomer = newSession.createQuery(
                            "SELECT c FROM Customers c JOIN FETCH c.goods WHERE c.id = :id", Customers.class)
                    .setParameter("id", customers.getId())
                    .getSingleResult();

            newSession.close();

            try {
                Set<Good> getGoods = persistentCustomer.getGoods();
                for (Good good : getGoods) {
                    System.out.println("Name: " + good.getName() + ", Price: " + good.getPrice());
                }
            } catch (Exception e) {
                System.out.println("LazyInitializationException occurred!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void part2() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml").
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Goods.class);

        try {
            SessionFactory sessionFactory = configuration.buildSessionFactory();

            Session session = sessionFactory.openSession();

            session.beginTransaction();
//
//        Customer customer1 = new Customer();
//            customer1.setName("John Doe");
//
//        Goods goods1 = new Goods();
//        goods1.setName("Laptop");
//        goods1.setPrice(1200.99);
//        goods1.setCustomer(customer1);
//
//        Goods goods2 = new Goods();
//        goods2.setName("Smartphone");
//        goods2.setPrice(699.99);
//        goods2.setCustomer(customer1);
//
//        Set<Goods> goodsSet = new HashSet<>();
//        goodsSet.add(goods1);
//        goodsSet.add(goods2);
//        customer1.setGoods(goodsSet);
//
//        session.persist(customer1);

//          Полиморфный запрос для получения всех сущностей типа SingleBaseEntity
            List<AbstractBaseEntity> entities = session.createQuery("FROM org.example.part2.AbstractBaseEntity", AbstractBaseEntity.class).getResultList();

            for (AbstractBaseEntity entity : entities) {
                if (entity instanceof Customer customer) {
                    System.out.println("Customer: " + customer.getName());
                } else if (entity instanceof Goods goods) {
                    System.out.println("Goods: " + goods.getName() + ", Price: " + goods.getPrice());
                }
            }


            session.getTransaction().commit();

            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.out.println("nooo");
        }
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