package com.example.springspark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    private final SparkService sparkService;

    public WebController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @GetMapping("/")
    public String showQueryForm(Model model) {
        model.addAttribute("sql", "show databases");
        return "home";
    }

    @PostMapping("/")
    public String runQuery(@RequestParam("sql") String sql, Model model) {
        model.addAttribute("sql", sql);
        try {
            Dataset<Row> result = sparkService.getSpark().sql(sql);
            StringBuilder sb = new StringBuilder();
            result.show(20, false);
            for (Row row : result.takeAsList(20)) {
                sb.append(row.mkString(", ")).append("<br/>");
            }
            model.addAttribute("output", sb.toString());
        } catch (Exception e) {
            model.addAttribute("output", "Error: " + e.getMessage());
        }
        return "home";
    }

    @GetMapping("/spark-version")
    @ResponseBody
    public String getSparkVersion() {
        return "Spark Version: " + sparkService.getSpark().version();
    }
}
