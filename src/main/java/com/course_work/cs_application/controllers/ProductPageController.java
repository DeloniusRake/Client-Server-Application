package com.course_work.cs_application.controllers;

import com.course_work.cs_application.models.Category;
import com.course_work.cs_application.models.Manufacturer;
import com.course_work.cs_application.models.Product;
import com.course_work.cs_application.services.CategoryTableService;
import com.course_work.cs_application.services.ManufacturerTableService;
import com.course_work.cs_application.services.ProductTableService;
import com.course_work.cs_application.utilits.DBProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class ProductPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPageController.class);

    @GetMapping("/product_page")
    public String displayProductPage(@RequestParam(name = "productID") int productID, Model model) {
        DBProperties properties = DBProperties.getProperties();
        Product product = new Product();
        Category category = new Category();
        Manufacturer manufacturer = new Manufacturer();
        try (Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUser(),
                properties.getPassword()
        )) {
            product = ProductTableService.getInstance().getById(connection, productID);
            category = CategoryTableService.getInstance().getById(connection, product.getCategoryID());
            manufacturer = ManufacturerTableService.getInstance().getById(connection, product.getManufacturerID());
        }
        catch (SQLException exception) {
            LOGGER.info(exception.getMessage(), exception);
        }
        model.addAttribute("product", product);
        model.addAttribute("category", category);
        model.addAttribute("manufacturer", manufacturer);
        return "product_page";
    }

}
