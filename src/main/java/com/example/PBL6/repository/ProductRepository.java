package com.example.PBL6.repository;

import com.example.PBL6.persistance.product.Category;
import com.example.PBL6.persistance.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query
    Optional<Product> findProductByName(String name);

    Page<Product> findProductByCategory(Category category, Pageable pageable);

    @Query
    Product getById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products p " +
            "       SET p.price = :newPrice, " +
            "           p.description = :newDescription, " +
            "           p.categories_id = :newCategoryId, " +
            "           p.image = :newImage " +
            "       WHERE p.id = :productId", nativeQuery = true)
    void updateProduct(
            @Param("productId") Integer productId,
            @Param("newPrice") Double newPrice,
            @Param("newDescription") String newDescription,
            @Param("newCategoryId") Integer newCategoryId,
            @Param("newImage") String newImage
    );

    @Query(value = "SELECT p.id AS product_id,\n" +
            "       p.name AS product_name,\n" +
            "       COUNT(oi.id) AS order_count\n" +
            "  FROM products p\n" +
            "  JOIN product_variants pv \n" +
            "    ON p.id = pv.product_id\n" +
            "  JOIN order_items oi \n" +
            "    ON pv.id = oi.product_variant_id\n" +
            " GROUP BY p.id\n" +
            " ORDER BY order_count DESC;", nativeQuery = true)
    List<Object[]> getBestSeller();
}
