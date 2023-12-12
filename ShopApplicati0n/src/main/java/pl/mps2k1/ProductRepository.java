package pl.mps2k1;

public interface ProductRepository {
    Product saveProduct(Product product);
    void getAllProducts();
    void addToShoppingCart(String name, int quantity);
    void getTotalPrice();
}
