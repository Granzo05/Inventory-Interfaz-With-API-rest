package org.example.repository;

import org.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAll();
    @Query("SELECT p FROM Product p WHERE p.code = :code")
    Optional<Product> findByCode(@Param("code") String code);
}
