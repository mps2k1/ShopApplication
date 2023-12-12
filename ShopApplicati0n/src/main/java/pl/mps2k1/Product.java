package pl.mps2k1;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
    private String name;
    private int quantity;
    private double price;
}
