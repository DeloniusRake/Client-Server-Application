package com.course_work.cs_application.utilits;

import com.course_work.cs_application.models.Category;
import com.course_work.cs_application.models.Manufacturer;
import com.course_work.cs_application.models.Product;
import com.course_work.cs_application.services.CategoryTableService;
import com.course_work.cs_application.services.ManufacturerTableService;
import com.course_work.cs_application.services.ProductTableService;
import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataBaseInit {

    private static DataBaseInit INSTANCE;

    private DataBaseInit() {}

    private boolean IsDBFilled(@NonNull Connection connection) throws SQLException {
        return !ManufacturerTableService.getInstance().getAllRows(connection).isEmpty();
    }

    public void init(@NonNull Connection connection) throws SQLException {
        DBinit(connection);
    }

    private void DBinit(@NonNull Connection connection) throws SQLException {
        createTableIfNotExists(connection, "categories");
        createTableIfNotExists(connection, "manufactures");
        createTableIfNotExists(connection, "products");
        if (!IsDBFilled(connection)) {
            FillTable(connection, "manufactures");
            FillTable(connection, "categories");
            FillTable(connection, "products");
        };

    }

    private void createTableIfNotExists(@NonNull Connection connection, String tableTittle) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS ";
        switch (tableTittle) {
            case "categories":query += "categories(category_id SERIAL PRIMARY KEY, category_name VARCHAR(256) NOT NULL)"; break;
            case "manufactures":query += "manufactures(manufacturer_id SERIAL PRIMARY KEY, manufacturer_name VARCHAR(256) NOT NULL)"; break;
            case "products":query += "products(" +
                    "product_id SERIAL PRIMARY KEY, " +
                    "category_id int NOT NULL REFERENCES categories(category_id), " +
                    "manufacturer_id int NOT NULL REFERENCES manufactures(manufacturer_id), " +
                    "product_name VARCHAR(256) NOT NULL, " +
                    "img VARCHAR(400) NOT NULL, " +
                    "description text, " +
                    "price real NOT NULL, " +
                    "weight real)"; break;
        }
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        }
    }

    private void FillTable(@NonNull Connection connection, String tableTitle) throws SQLException {
        switch (tableTitle) {
            case "manufactures": {
                List<Manufacturer> manufactures = new ArrayList<>();
                for(int i = 0; i < 4; i++) {
                    manufactures.add(new Manufacturer());
                }
                manufactures.get(0).setManufacturerID(1);
                manufactures.get(0).setManufacturerName("Егорьевская колбасно-гастрономическая фабрика");
                manufactures.get(1).setManufacturerID(2);
                manufactures.get(1).setManufacturerName("Останкино");
                manufactures.get(2).setManufacturerID(3);
                manufactures.get(2).setManufacturerName("Мясницкий Ряд");
                manufactures.get(3).setManufacturerID(4);
                manufactures.get(3).setManufacturerName("Черкизово");
                for (Manufacturer manufacturer : manufactures) {
                    ManufacturerTableService.getInstance().insert(connection, manufacturer);
                }
                break;
            }
            case "categories": {
                List<Category> categories = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    categories.add(new Category());
                }
                categories.get(0).setCategoryID(1);
                categories.get(0).setCategoryName("Окорок");
                categories.get(1).setCategoryID(2);
                categories.get(1).setCategoryName("Грудинка");
                categories.get(2).setCategoryID(3);
                categories.get(2).setCategoryName("Буженина");
                categories.get(3).setCategoryID(4);
                categories.get(3).setCategoryName("Колбаса");
                for (Category category : categories) {
                    CategoryTableService.getInstance().insert(connection, category);
                }
                break;
            }
            case "products": {
                List<Product> products = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    products.add(new Product());
                }
                products.get(0).setProductID(1);
                products.get(0).setProductName("Хамон сырокопченый Егорьевская КГФ, нарезка, 55 г");
                products.get(0).setCategoryID(1);
                products.get(0).setManufacturerID(1);
                products.get(0).setImg("img/p1.jpg");
                products.get(0).setDescription("Хамон - испанский национальный деликатес, сыровяленый свиной окорок. Наша красивая и вкусная нарезка подходит для изысканной мясной тарелки. Отбираются только лучшие мясные куски. Мы нарезаем тончайшие слайсы, прозрачные, нежные и вкусные.");
                products.get(0).setPrice(140);
                products.get(0).setWeight(55);
                products.get(1).setProductID(2);
                products.get(1).setProductName("Грудинка варено-копченая Особая Останкино, 300 г");
                products.get(1).setCategoryID(2);
                products.get(1).setManufacturerID(2);
                products.get(1).setImg("img/p2.jpg");
                products.get(1).setDescription("Для изготовления копчёной грудинки \"Особой\" выбирается самую сочную мясную часть с плотным шпиком. Необычное сочетание чеснока, чёрного перца и сахара делают вкус грудинки пикантным и уникальным. Грудинка может стать прекрасным дополнением праздничного стола или одним из ингредиентов множества блюд, таких как салаты, супы, яичница или бутерброды и каждое из них получится неизменно вкусным. Останкинский мясоперерабатывающий комбинат - лидер на рынке мясных продуктов и полуфабрикатов. Вся продукция производится на новейшем немецком и австрийском оборудовании по самым современным международным стандартам и проходит многоуровневый контроль качества, что обеспечивает безопасность и гарантирует насыщенный, приятный вкус.");
                products.get(1).setPrice(199);
                products.get(1).setWeight(300);
                products.get(2).setProductID(3);
                products.get(2).setProductName("Буженина копчено-вареная По-домашнему Егорьевская КГФ, нарезка, 115 г");
                products.get(2).setCategoryID(3);
                products.get(2).setManufacturerID(1);
                products.get(2).setImg("img/p3.jpg");
                products.get(2).setDescription("Буженина – традиционное блюдо славянской кухни - запеченное мясо, чаще всего свиной окорок без кости. Оно готовится, практически, по рецептам 16 века. Отбираются только лучшие мясные куски: свежие, правильной округлой формы, равномерно покрытые слоем сала, имеющие сальные прожилки. Мясо натирают смесью красного перца и соли, шпигуют чесноком и салом. Затем запекают «по- домашнему» медленно, не забывая при этом поливать мясо образующимся соком. В результате такой тепловой обработки буженина приобретает классический вид: плотную нежную текстуру, аппетитную душистую корочку.");
                products.get(2).setPrice(183);
                products.get(2).setWeight(115);
                products.get(3).setProductID(4);
                products.get(3).setProductName("Буженина запеченная По-Егорьевски Егорьевская КГФ, нарезка, 180 г");
                products.get(3).setCategoryID(3);
                products.get(3).setManufacturerID(1);
                products.get(3).setImg("img/p4.jpg");
                products.get(3).setDescription("Буженина – традиционное блюдо славянской кухни - запеченное мясо, чаще всего свиной окорок без кости. Оно готовится, практически, по рецептам 16 века. Отбираются только лучшие мясные куски: свежие, правильной округлой формы, равномерно покрытые слоем сала, имеющие сальные прожилки. Мясо натирают смесью красного перца и соли, шпигуют чесноком и салом. Затем запекают «по- домашнему» медленно, не забывая при этом поливать мясо образующимся соком. В результате такой тепловой обработки буженина приобретает классический вид: плотную нежную текстуру, аппетитную душистую корочку.");
                products.get(3).setPrice(309);
                products.get(3).setWeight(180);
                products.get(4).setProductID(5);
                products.get(4).setProductName("Окорок копчено-вареный, Мясницкий ряд, 300 г");
                products.get(4).setCategoryID(1);
                products.get(4).setManufacturerID(3);
                products.get(4).setImg("img/p5.jpg");
                products.get(4).setDescription("Нежная отборная свинина с прослойкой жира, придающей дополнительную сочность. Продукт коптится на натуральных буковых опилках. Окорок имеет особенно нежную мраморную и сочную структуру, обладает натуральным мясным ароматом и нежной мягкой корочкой.");
                products.get(4).setPrice(209);
                products.get(4).setWeight(300);
                products.get(5).setProductID(6);
                products.get(5).setProductName("Колбаса сырокопченая Салями Фламенко Черкизово Премиум, нарезка, 100 г");
                products.get(5).setCategoryID(4);
                products.get(5).setManufacturerID(4);
                products.get(5).setImg("img/p6.jpg");
                products.get(5).setDescription("Изделие колбасное мясное сырокопченое. Колбаса \"Салями Фламенко\". Охлажденный продукт. Модифицированная атмосфера.");
                products.get(5).setPrice(159);
                products.get(5).setWeight(100);
                products.get(6).setProductID(7);
                products.get(6).setProductName("Колбаса сырокопченая Юбилейная Останкино, 250 г");
                products.get(6).setCategoryID(4);
                products.get(6).setManufacturerID(2);
                products.get(6).setImg("img/p7.jpg");
                products.get(6).setDescription("Колбаса сырокопченая \"Юбилейная\" вырабатывается по классической технологии на новейшем немецком и австрийском оборудовании. В состав продукта входят свинина, шпик, филе куриных грудок в сочетании натуральных специй и пряностей. Копчение холодным дымом с последующей сушкой позволяет получить деликатесный продукт, выдерживающий длительное хранение и отличающийся отменными вкусовыми качествами. Сырокопченая колбаса \"Юбилейная\" ТМ \"Останкино\" не только украсит праздничный стол в виде классической нарезки, но и подойдет для приготовления канапе: Из ломтиков хлеба вырезать с помощью формочки небольшие кружочки, сырокопченую колбасу нарезать небольшими ровными полосками, творожный сыр с зеленью выложить на колбаные ломтики и свернуть в тугие рулетики. Положить по одному рулетики на кружок хлеба и закрепить коктейльной шпажкой, также можно добавить оливки. Уверены, такие «канапе» на шпажках подойдут к коктейлям, и будут отличным блюдом для фуршета и просто уютного вечера.");
                products.get(6).setPrice(199);
                products.get(6).setWeight(250);
                products.get(7).setProductID(8);
                products.get(7).setProductName("Колбаса сырокопченая Сальчичон с розовым перцем Черкизово Премиум, нарезка, 85 г");
                products.get(7).setCategoryID(4);
                products.get(7).setManufacturerID(4);
                products.get(7).setImg("img/p8.jpg");
                products.get(7).setDescription("Вкус отборного мяса, подчеркнутый приправами, особой рецептурой и копчением — в лучших традициях «Сальчичона».");
                products.get(7).setPrice(145);
                products.get(7).setWeight(85);
                products.get(8).setProductID(9);
                products.get(8).setProductName("Колбаса сырокопченая Милано Мясницкий ряд, нарезка, 90 г");
                products.get(8).setCategoryID(4);
                products.get(8).setManufacturerID(3);
                products.get(8).setImg("img/p9.jpg");
                products.get(8).setDescription("Настоящий деликатес, который отсылает нас к горячему солнцу Испании, раскрывая яркий характер.");
                products.get(8).setPrice(109);
                products.get(8).setWeight(90);
                for (Product product : products) {
                    ProductTableService.getInstance().insert(connection, product);
                }
                break;
            }
        }
    }

    public static DataBaseInit getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new DataBaseInit();
        }
        return INSTANCE;
    }

}
