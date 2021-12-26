package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/moreWater").setViewName("moreWater");
        registry.addViewController("/lessWater").setViewName("lessWater");
        registry.addViewController("/rightWater").setViewName("rightWater");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
//Return Form        
return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(PersonForm personForm) {

        System.out.println("-------");
        System.out.println(personForm);
        System.out.println("-------");

        int minimumWaterToConsume = 8;
        int maximumWaterToConsume = 12;
        try {
            //Connection conn = DriverManager.getConnection(url, "db_user@testdb", "StrongPassword");
            String url = "jdbc:mysql://dateb.mysql.database.azure.com:3306/testdb?useSSL=true&requireSSL=false";
            Connection conn = DriverManager.getConnection(url, "Neeti@dateb", "NETZEE@1503");
            String selectSql = "SELECT * FROM todo";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            if (!resultSet.next()) {
                System.out.println("NO DATA");
            } else {
                minimumWaterToConsume = resultSet.getInt("water");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (personForm.getWaterConsumed() <= minimumWaterToConsume) {
            return "redirect:/lessWater";
        } else if (personForm.getWaterConsumed() >= maximumWaterToConsume) {
            return "redirect:/moreWater";
        } else {
            return "redirect:/rightWater";
        }
    }
}
