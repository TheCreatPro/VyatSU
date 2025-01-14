package com.vyatsu;  // shop

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;


public class MainClass {
    private static SessionFactory factory;

    public static void main(String[] args) {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду: ");
            String command = scanner.nextLine();
            if (command.startsWith("/showProductsByPerson")) {
                String name = command.split(" ")[1];
                showProductsByPerson(name);
            } else if (command.startsWith("/findPersonsByProductTitle")) {
                String title = command.split(" ")[1];
                findPersonsByProductTitle(title);
            } else if (command.startsWith("/removePerson")) {
                String name = command.split(" ")[1];
                removePerson(name);
            } else if (command.startsWith("/removeProduct")) {
                String title = command.split(" ")[1];
                removeProduct(title);
            } else if (command.startsWith("/buy")) {
                String[] parts = command.split(" ");
                buy(parts[1], parts[2]);
            } else if (command.startsWith("/addCustomer")) {
                String name = command.split(" ")[1];
                addCustomer(name);
            } else if (command.startsWith("/addProduct")) {
                String[] parts = command.split(" ");
                addProduct(parts[1], Double.parseDouble(parts[2]));
            } else {
                System.out.println("Неизвестная команда!");
            }
        }

    }


    private static void addCustomer(String name) {  // добавление покупашки
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = new Customer();
            customer.setName(name);
            session.save(customer);
            session.getTransaction().commit();
            System.out.println("Добавлен покупатель");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(String title, double price) {  // добавление продукта
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product();
            product.setTitle(title);
            product.setPrice(price);
            session.save(product);
            session.getTransaction().commit();
            System.out.println("Добавлен продукт");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void showProductsByPerson(String name) {  // выводит продукты, которые купил этот чел
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createQuery("from Customer where name = :name", Customer.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (customer != null) {

                // Дублирует продукты:
/*                ArrayList<Double> summa = new ArrayList<>(customer.getPurchases().size());
                { for (int i = 0; i < customer.getPurchases().size(); i++) summa.add(0.0);}


                for (int i = 0; i < customer.getPurchases().size(); i++) {
                    //System.out.println(summa.get(i));

                    summa.set(customer.getPurchases().get(i).getProduct().getId(), summa.get(customer.getPurchases().get(i).getProduct().getId()) + customer.getPurchases().get(i).getPriceAtPurchase());
                }
                System.out.println(summa);

                customer.getPurchases().forEach(p -> System.out.println(p.getProduct().getTitle() + " - " + summa.get(p.getProduct().getId())));
                // customer.getPurchases().forEach(p -> System.out.println(p.getProduct().getTitle() + " - " + p.getPriceAtPurchase()));
*/

                // В принципе это работает:
/*                Map<Integer, Double> productSums = new HashMap<>(); // Map для хранения сумм стоимости по каждому продукту

                // Подсчёт общей стоимости для каждого продукта
                customer.getPurchases().forEach(purchase -> {
                    int productId = purchase.getProduct().getId();
                    double price = purchase.getPriceAtPurchase();
                    productSums.put(productId, productSums.getOrDefault(productId, 0.0) + price);
                });

                // Вывод уникальных продуктов и их суммарной стоимости
                System.out.println("Продукты, купленные " + name + ":");
                productSums.forEach((productId, totalPrice) -> {
                    String productTitle = customer.getPurchases().stream()
                            .filter(p -> p.getProduct().getId() == productId)
                            .findFirst()
                            .map(p -> p.getProduct().getTitle())
                            .orElse("Unknown Product");
                    System.out.println(productTitle + " - " + totalPrice);
                });*/

                // словари для хранения суммы стоимости продуктов
                Map<Integer, String> productTitles = new HashMap<>();
                Map<Integer, Double> productSums = new HashMap<>();

                for (Purchase purchase : customer.getPurchases()) { // считаем сумму для каждого продукта
                    int productId = purchase.getProduct().getId();
                    String productTitle = purchase.getProduct().getTitle();
                    double price = purchase.getPriceAtPurchase();

                    if (!productTitles.containsKey(productId)) {  // если продукта нет в словарь, сохраняем его
                        productTitles.put(productId, productTitle);
                    }

                    if (productSums.containsKey(productId)) {
                        // добавляем стоимость к сумме
                        productSums.put(productId, productSums.get(productId) + price);
                    } else {
                        productSums.put(productId, price);
                    }
                }

                System.out.println(name + " купил:");
                for (int productId : productTitles.keySet()) {
                    System.out.println(productTitles.get(productId) + " - " + productSums.get(productId));
                }


            } else {
                System.out.println("Не найден покупатель с таким именем!");
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findPersonsByProductTitle(String title) {  // выводит покупателей, который купили этот продукт
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Purchase> purchases = session.createQuery("from Purchase where product.title = :title", Purchase.class)
                    .setParameter("title", title)
                    .getResultList();
            purchases.forEach(p -> System.out.println(p.getCustomer().getName()));
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removePerson(String name) {  // удаляет людей
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createQuery("from Customer where name = :name", Customer.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (customer != null) {
                session.remove(customer);
                System.out.println("Удалён покупатель");
            } else {
                System.out.println("Не найден покупатель с таким именем!");
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeProduct(String title) {  // удаляет продукты
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery("from Product where title = :title", Product.class)
                    .setParameter("title", title)
                    .uniqueResult();
            if (product != null) {
                session.remove(product);
                System.out.println("Удалён продукт");
            } else {
                System.out.println("Не найден продукт с таким названием!");
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buy(String customerName, String productTitle) {  // метод для регистрации покупок
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createQuery("from Customer where name = :name", Customer.class)
                    .setParameter("name", customerName)
                    .uniqueResult();
            Product product = session.createQuery("from Product where title = :title", Product.class)
                    .setParameter("title", productTitle)
                    .uniqueResult();

            if (customer != null && product != null) {
                Purchase purchase = new Purchase();
                purchase.setCustomer(customer);
                purchase.setProduct(product);
                purchase.setPriceAtPurchase(product.getPrice());
                session.save(purchase);
                System.out.println("Покупка зарегистрирована");
            } else {
                System.out.println("Не найден покупатель или продукт!");
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
