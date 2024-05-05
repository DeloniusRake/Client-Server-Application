package com.course_work.cs_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayIndex {

    @GetMapping("/index")
    public String displayIndex(Model model) {
        return "index";
    }

}
