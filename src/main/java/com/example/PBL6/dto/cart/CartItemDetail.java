package com.example.PBL6.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDetail {
    private Integer id;
    private Integer productId;
    private Integer productVariantId;
    private String productName;
    private String image;
    private String color;
    private String size;
    private Double price;
    private Integer quantity;
}
