package pl.mps2k1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopService implements ProductRepository{
    private final DBConnector dbConnector;
    private final List<Product> products;

    public ShopService(DBConnector dbConnector, List<Product> products) {
        this.dbConnector = dbConnector;
        this.products = products;
        this.connection = null;

    }
    private Connection connection;

    @Override
    public Product saveProduct(Product product) {
        if (connection==null){
            dbConnector.DBConnect();
        }
        if (connection!=null) {


            try {
                String query = "INSERT INTO product (name, quantity, price) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getQuantity());
                preparedStatement.setDouble(3, product.getPrice());


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dodano nowy produkt:  "+new Product(product.getName(), product.getQuantity(), product.getPrice()));
        return new Product(product.getName(), product.getQuantity(), product.getPrice());

    }

    @Override
    public void getAllProducts() {
        List<Product> products = new ArrayList<>();
        if (connection==null){
        dbConnector.DBConnect();
    }
        if (connection!=null) {
            try {
                String query = "SELECT * FROM product";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");
                    Product product = new Product(name, quantity, price);
                    products.add(product);
                }
                products.stream()
                        .forEach(System.out::println);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addToShoppingCart(String name, int quantity) {   if (connection == null) {
        dbConnector.DBConnect();
    }
if (connection!=null) {
    try {
        String query = "SELECT * FROM product WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int productId = resultSet.getInt("id");
            String productName = resultSet.getString("name");
            double productPrice = resultSet.getDouble("price");

            Product product = new Product(productName, quantity, productPrice);

            String query2 = "INSERT INTO shoppingcart (name, quantity, price) VALUES (?, ?,?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setString(1, product.getName());
            preparedStatement2.setInt(2, product.getQuantity());
            preparedStatement2.setDouble(3, quantity * productPrice);

            System.out.println("Produkt dodany do koszyka: " + product);
        } else {
            System.out.println("Produkt o nazwie " + name + " nie został znaleziony.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    }

    @Override
    public void getTotalPrice() {
        double totalPrice = 0;
        {
            if (connection == null) {
                dbConnector.DBConnect();
            }
            if (connection != null) {

                try {
                    String query = "SELECT price FROM shoppingcart";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        double productPrice = resultSet.getDouble("price");
                        totalPrice += productPrice;
                    }
                    System.out.println("Całkowita cena w koszyku zakupów: " + totalPrice);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }}