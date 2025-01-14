package shop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

// Класс Покупатель
@Entity
@Table(name = "customers")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;


    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
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

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// Класс Товар
@Entity
@Table(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;

    public Product() {
    }

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}

// Класс покупки (для детализации)
@Entity
@Table(name = "purchases")
class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "purchase_price")
    private Double purchasePrice; // Цена на момент покупки

    public Purchase() {}

    public Purchase(Customer customer, Product product, Double purchasePrice) {
        this.customer = customer;
        this.product = product;
        this.purchasePrice = purchasePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer=" + customer.getName() +
                ", product=" + product.getTitle() +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}


public class MainClass {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            init();  // Инициализация фабрики сессий
            Scanner scanner = new Scanner(System.in);
            String command;

            while (true) {
                System.out.println("Введите команду (/help для списка команд):");
                command = scanner.nextLine();

                if (command.equals("/exit")) {
                    break;
                }

                processCommand(command, scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown(); // Закрытие фабрики сессий
        }
    }


    private static void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml") // Файл конфигурации Hibernate
                .addAnnotatedClass(Customer.class) // Класс покупателя
                .addAnnotatedClass(Product.class)   // Класс товара
                .addAnnotatedClass(Purchase.class) // Класс покупки
                .buildSessionFactory();
    }

    private static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }


    private static void processCommand(String command, Scanner scanner) {
        try {
            if (command.startsWith("/showProductsByPerson")) {
                showProductsByPerson(command);
            } else if (command.startsWith("/findPersonsByProductTitle")) {
                findPersonsByProductTitle(command);
            } else if (command.startsWith("/removePerson")) {
                removePerson(command);
            } else if (command.startsWith("/removeProduct")) {
                removeProduct(command);
            } else if (command.startsWith("/buy")) {
                buyProduct(command);
            } else if (command.equals("/help")) {
                printHelp();
            } else if (command.startsWith("/create")) {
                createEntities(scanner);
            }
            else {
                System.out.println("Неизвестная команда.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении команды: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Список доступных команд:");
        System.out.println("/showProductsByPerson <имя_покупателя> - Показать товары покупателя");
        System.out.println("/findPersonsByProductTitle <название_товара> - Найти покупателей товара");
        System.out.println("/removePerson <имя_покупателя> - Удалить покупателя");
        System.out.println("/removeProduct <название_товара> - Удалить товар");
        System.out.println("/buy <имя_покупателя> <название_товара> - Купить товар");
        System.out.println("/create - создать покупателей и продукты");
        System.out.println("/exit - Выход");
    }

    private static void createEntities(Scanner scanner) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Создаем покупателей
            Customer customer1 = new Customer("John Doe");
            Customer customer2 = new Customer("Jane Smith");
            Customer customer3 = new Customer("Bob Johnson");
            session.save(customer1);
            session.save(customer2);
            session.save(customer3);

            // Создаем товары
            Product product1 = new Product("Laptop", 1200.0);
            Product product2 = new Product("Smartphone", 800.0);
            Product product3 = new Product("Tablet", 300.0);
            Product product4 = new Product("Headphones", 100.0);
            session.save(product1);
            session.save(product2);
            session.save(product3);
            session.save(product4);


            session.getTransaction().commit();
            System.out.println("Покупатели и товары успешно созданы.");
        }
    }
    private static void showProductsByPerson(String command) {
        String customerName = command.substring("/showProductsByPerson".length()).trim();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Ищем покупателя по имени (регистронезависимый поиск)
            List<Customer> customers = session.createQuery("FROM Customer c WHERE LOWER(c.name) = LOWER(:name)", Customer.class)
                    .setParameter("name", customerName)
                    .getResultList();

            if (customers.isEmpty()) {
                System.out.println("Покупатель с именем '" + customerName + "' не найден.");
                return;
            }

            Customer customer = customers.get(0); // Берём первого найденного покупателя

            // Получаем список покупок покупателя и выводим информацию о товарах
            List<Purchase> purchases = customer.getPurchases();

            if (purchases.isEmpty()) {
                System.out.println("Покупатель '" + customerName + "' не совершал покупок.");
                return;
            }

            System.out.println("Товары, купленные покупателем '" + customerName + "':");
            for (Purchase purchase : purchases) {
                Product product = purchase.getProduct();
                System.out.println("  - " + product.getTitle() + " (Цена на момент покупки: " + purchase.getPurchasePrice() + ")");
            }

            session.getTransaction().commit();
        }
    }



    private static void findPersonsByProductTitle(String command) {
        String productTitle = command.substring("/findPersonsByProductTitle".length()).trim();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            //Ищем товар по названию
            List<Product> products = session.createQuery("FROM Product p WHERE LOWER(p.title) = LOWER(:title)", Product.class)
                    .setParameter("title", productTitle)
                    .getResultList();

            if(products.isEmpty()){
                System.out.println("Товар с названием: '" + productTitle + "' не найден.");
                return;
            }

            Product product = products.get(0);

            // Получаем все покупки данного товара
            List<Purchase> purchases = product.getPurchases();

            if (purchases.isEmpty()) {
                System.out.println("Товар '" + productTitle + "' еще никто не покупал.");
                return;
            }

            System.out.println("Покупатели товара '" + productTitle + "':");
            for (Purchase purchase : purchases) {
                Customer customer = purchase.getCustomer();
                System.out.println("  - " + customer.getName());
            }

            session.getTransaction().commit();
        }
    }


    private static void removePerson(String command) {
        String customerName = command.substring("/removePerson".length()).trim();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Находим покупателя (регистронезависимо)
            List<Customer> customers = session.createQuery("FROM Customer c WHERE LOWER(c.name) = LOWER(:name)", Customer.class)
                    .setParameter("name", customerName)
                    .getResultList();

            if (customers.isEmpty()) {
                System.out.println("Покупатель с именем '" + customerName + "' не найден.");
                session.getTransaction().rollback(); // Откат, если покупатель не найден
                return;
            }

            Customer customer = customers.get(0);
            session.delete(customer); // Удаление покупателя

            session.getTransaction().commit();
            System.out.println("Покупатель '" + customerName + "' удален.");
        }
    }



    private static void removeProduct(String command) {
        String productTitle = command.substring("/removeProduct".length()).trim();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            // Находим товар (регистронезависимо)
            List<Product> products = session.createQuery("FROM Product p WHERE LOWER(p.title) = LOWER(:title)", Product.class)
                    .setParameter("title", productTitle)
                    .getResultList();

            if(products.isEmpty()){
                System.out.println("Товар с названием '" + productTitle + "' не найден.");
                session.getTransaction().rollback();
                return;
            }
            Product product = products.get(0);
            session.delete(product); // Удаляем товар


            session.getTransaction().commit();
            System.out.println("Товар '" + productTitle + "' удален.");
        }
    }


    private static void buyProduct(String command) {
        String[] parts = command.substring("/buy".length()).trim().split("\\s+");
        if (parts.length != 2) {
            System.out.println("Неверный формат команды. Используйте: /buy <имя_покупателя> <название_товара>");
            return;
        }
        String customerName = parts[0];
        String productTitle = parts[1];

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            // 1. Находим покупателя
            List<Customer> customers = session.createQuery("FROM Customer c WHERE LOWER(c.name) = LOWER(:name)", Customer.class)
                    .setParameter("name", customerName)
                    .getResultList();

            if (customers.isEmpty()) {
                System.out.println("Покупатель с именем '" + customerName + "' не найден.");
                session.getTransaction().rollback(); // откат транзакции
                return;
            }
            Customer customer = customers.get(0);

            // 2. Находим товар
            List<Product> products = session.createQuery("FROM Product p WHERE LOWER(p.title) = LOWER(:title)", Product.class)
                    .setParameter("title", productTitle)
                    .getResultList();

            if(products.isEmpty()){
                System.out.println("Товар с названием '" + productTitle + "' не найден.");
                session.getTransaction().rollback();
                return;
            }
            Product product = products.get(0);

            // 3. Создаем запись о покупке
            Purchase purchase = new Purchase(customer, product, product.getPrice());
            session.save(purchase);

            session.getTransaction().commit();
            System.out.println("Покупатель '" + customerName + "' купил товар '" + productTitle + "'.");
        }
    }
}