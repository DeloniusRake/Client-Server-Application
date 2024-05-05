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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CatalogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);
    List<Product> productList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Manufacturer> manufacturerList = new ArrayList<>();
    DBProperties properties = DBProperties.getProperties();

    @GetMapping("/catalog")
    public String displayCatalog(@RequestParam(name = "categoryID", required = false) Integer categoryID, @RequestParam(name = "manufacturerID", required = false) Integer manufacturerID, Model model) {
        String title = "Продукция";
        try (Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUser(),
                properties.getPassword()
        )) {
            if (!Objects.isNull(categoryID)) {
                productList = ProductTableService.getInstance().getByCategoryID(connection, categoryID);
                Category category = new Category();
                category = CategoryTableService.getInstance().getById(connection, categoryID);
                title = category.getCategoryName();
            } else if (!Objects.isNull(manufacturerID)) {
                productList = ProductTableService.getInstance().getByManufacturerID(connection, manufacturerID);
                Manufacturer manufacturer = new Manufacturer();
                manufacturer = ManufacturerTableService.getInstance().getById(connection, manufacturerID);
                title = manufacturer.getManufacturerName();
            } else {
                productList = ProductTableService.getInstance().getAllRows(connection);
            }
            categoryList = CategoryTableService.getInstance().getAllRows(connection);
            manufacturerList = ManufacturerTableService.getInstance().getAllRows(connection);
        } catch (SQLException exception) {
            LOGGER.info(exception.getMessage(), exception);
        }
        model.addAttribute("title", title);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("manufacturerList", manufacturerList);
        model.addAttribute("productList", productList);
        return "catalog";
    }

    @PostMapping("/catalog")
    public void displayPartCatalog(@RequestParam(name = "searchQuery") String searchQuery, Model model) {
        try (Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUser(),
                properties.getPassword()
        )) {
            productList = ProductTableService.getInstance().getBySearchQuery(connection, searchQuery);
        } catch (SQLException exception) {
            LOGGER.info(exception.getMessage(), exception);
        }
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("manufacturerList", manufacturerList);
        if (!productList.isEmpty()) {
            model.addAttribute("productList", productList);
        }
        else {
            model.addAttribute("title", "По вашему запросу ничего не нашлось");
        }
    }

}
