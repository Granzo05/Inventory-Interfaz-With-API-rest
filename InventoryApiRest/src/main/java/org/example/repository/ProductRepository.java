package org.example.repository;

import org.example.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {
    @Query("SELECT p FROM Products p WHERE p.code = :code")
    Optional<Products> findByCode(@Param("code") String code);
}
