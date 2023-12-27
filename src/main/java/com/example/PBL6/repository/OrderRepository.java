package com.example.PBL6.repository;

import com.example.PBL6.persistance.order.Order;
import com.example.PBL6.persistance.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query
    List<Order> getOrdersByUser(User user);

    @Query(value = "UPDATE orders\n" +
                   "   SET status = :status \n" +
                   " WHERE id = :orderId", nativeQuery = true)
    @Modifying
    void updateStatusOrder(Integer orderId, String status);
}
