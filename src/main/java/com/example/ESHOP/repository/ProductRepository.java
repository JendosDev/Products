package com.example.ESHOP.repository;

import com.example.ESHOP.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Service
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT ALL * FROM product", (rs, rowNum) -> {
            return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("partNo"),
                    rs.getString("description"),
                    rs.getBoolean("isForSale"),
                    rs.getBigDecimal("price"));
        });
    }

    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id=?", new Object[]{id}, (rs, rowNum) -> {
            return new Product(
                    rs.getInt("id"),
                    rs.getString("partNo"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBoolean("isForSale"),
                    rs.getBigDecimal("price"));
        });
    }

    public void addProduct(Product product) {
        jdbcTemplate.update("INSERT INTO product (part_no, name, description, is_for_sale, price) VALUES ('"
                + product.getPartNo()
                + "','" + product.getName()
                + "','" + product.getDescription()
                + "','" + product.isForSale()
                + "','" + product.getPrice());
    }

    public Product updatePriceById(Product product) {
        BigDecimal price = new BigDecimal(String.valueOf(product.getPrice()));
        jdbcTemplate.execute("UPDATE product SET price =" + price + " WHERE id = " + product.getId());
        return product;
    }

    public void deleteOutOfSaleProducts() {
        jdbcTemplate.execute("DELETE FROM product WHERE isForSale = "+ 0);
    }

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ESHOP",
            "root",
            "Heslo123!");

    Statement statement = connection.createStatement();

    ResultSet resultSet = statement.executeQuery("select * from product");


    public ProductRepository() throws SQLException {

    }
}


