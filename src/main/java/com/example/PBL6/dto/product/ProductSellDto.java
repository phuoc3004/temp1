package com.example.PBL6.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellDto {
    private Integer id;
    private String name;
    private Integer sellQuantity;
}
