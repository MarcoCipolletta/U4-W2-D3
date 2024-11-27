package it.epicode.esercizio;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(13525235235532L, "Notte", "books", 101.0);
        Product product2 = new Product(21242142421L, "Giorno", "books", 50.0);
        Product product3 = new Product(324124343L, "Sole", "books", 300.0);
        Product product4 = new Product(43242341214123L, "Bosco", "books", 201.0);
        Product product5 = new Product(3483538938L, "Dado", "baby", 20.0);
        Product product6 = new Product(234234234234L, "Pezzo", "baby", 10.0);
        Product product7 = new Product(23423654234L, "Lancia", "baby", 76.30);
        Product product8 = new Product(234236596424L, "Freccia", "baby", 15.07);
        Product product9 = new Product(7654844234L, "Bracciale", "boys", 100.0);
        Product product10 = new Product(7654844234L, "Anello", "boys", 87.60);
        Product product11 = new Product(7654844234L, "Orologio", "boys", 560.0);
        Product product12 = new Product(7654844234L, "Macchina", "boys", 10000.0);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        products.add(product11);
        products.add(product12);

        List<Order> orders = new ArrayList<>();
        List<Product> productsOrder1 = new ArrayList<>();
        List<Product> productsOrder2 = new ArrayList<>();
        List<Product> productsOrder3 = new ArrayList<>();
        List<Product> productsOrder4 = new ArrayList<>();
        List<Product> productsOrder5 = new ArrayList<>();
        productsOrder1.add(product1);
        productsOrder1.add(product7);
        productsOrder1.add(product9);
        productsOrder1.add(product12);
        productsOrder1.add(product4);
        productsOrder2.add(product2);
        productsOrder2.add(product5);
        productsOrder2.add(product6);
        productsOrder2.add(product8);
        productsOrder2.add(product10);
        productsOrder3.add(product3);
        productsOrder3.add(product11);
        productsOrder4.add(product1);
        productsOrder4.add(product2);
        productsOrder4.add(product3);
        productsOrder4.add(product4);
        productsOrder4.add(product9);
        productsOrder5.add(product1);
        productsOrder5.add(product3);
        productsOrder5.add(product4);
        productsOrder5.add(product9);
        productsOrder5.add(product10);
        productsOrder5.add(product11);
        productsOrder5.add(product12);

        Customer customer1 = new Customer(12321L, "Giuseppe", 1);
        Customer customer2 = new Customer(126721L, "Frano", 2);
        Customer customer3 = new Customer(675756L, "Silvia", 3);
        Customer customer4 = new Customer(46364L, "Chiara", 2);
        Customer customer5 = new Customer(346756543L, "Luca", 1);


        Order order1 = new Order(9524889L, "inviata", LocalDate.now(), LocalDate.now().plusDays(3), productsOrder1, customer1);
        Order order2 = new Order(10987654L, "inviata", LocalDate.now(), LocalDate.now().plusDays(2), productsOrder2, customer3);
        Order order3 = new Order(23478909876L, "inviata", LocalDate.of(2021, 3, 12), LocalDate.now().plusDays(4), productsOrder3, customer2);
        Order order4 = new Order(154378909765L, "inviata", LocalDate.now(), LocalDate.now().plusDays(2), productsOrder4, customer4);
        Order order5 = new Order(16928364238L, "inviata", LocalDate.of(2021, 2, 20), LocalDate.now().plusDays(1), productsOrder5, customer5);


        //--------------------------------Es1---------------------------------


        List<Product> booksMajor100 = products.stream().filter(product -> {
            return product.getCategory().equals("books") && product.getPrice() > 100;
        }).toList();
        System.out.println("--------------Tutti i prodotti");
        products.forEach(product -> System.out.println(product.getName() + " " + product.getPrice() + " " + product.getCategory()));
        System.out.println("\nTutti i libri con prezzo superiore a 100");
        booksMajor100.forEach(product -> System.out.println(product.getName() + " " + product.getPrice() + " " + product.getCategory()));


        //--------------------------------Es2---------------------------------

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        List<Order> ordersWithProductForBaby = orders.stream()
                .filter(order -> {
                    return order.getProducts().stream().anyMatch(product -> product.getCategory().equalsIgnoreCase("baby"));
                })
                .toList();

        System.out.println("\n----------------------------Ordini con prodotti:");
        orders.forEach(order -> {
            System.out.println("Id ordine: " + order.getId() + "\n     Con questi prodotti:");
            order.getProducts().forEach(product -> System.out.println(product.getName() + " categoria: " + product.getCategory()));
        });
        System.out.println("\n-----------------------Ordini con prodotti per baby:");
        ordersWithProductForBaby.forEach(order -> {
            System.out.println("Id ordine: " + order.getId() + "\n     Con questi prodotti:");
            order.getProducts().forEach(product -> System.out.println(product.getName() + " categoria: " + product.getCategory()));
        });


        //--------------------------------Es3---------------------------------

        List<Product> productsForBoys = products.stream()

                .filter(product -> product.getCategory().equalsIgnoreCase("boys"))
                .map(product -> {
                    System.out.println("Prezzo prima: " + product.getPrice());
                    double newPrice = product.getPrice() * 0.9;
                    product.setPrice(newPrice);
                    System.out.println("Prezzo dopo: " + product.getPrice());
                    return product;

                })
                .toList();

        //--------------------------------Es4---------------------------------

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Product> productsOrderFromClienTier2InACertainDate = orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate))
                .flatMap(order -> order.getProducts().stream())
                .toList();


        System.out.println("\nProdotti ordinati da clienti di livello (tier) 2 tra l'01-Feb-2021 e l'01-Apr-2021:");
        productsOrderFromClienTier2InACertainDate.forEach(product -> System.out.println(" " + product.getName() + " - " + product.getPrice()));
    }
}