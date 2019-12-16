package com.jpco.app.jonesprintcompany.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"", "/", "index"})
    public String root() {
        return "index";
    }

    @GetMapping("/login-2")
    public String login(Model model) {
        return "login-2";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
  
    @GetMapping("/services")
    public String services() {
        return "services";
    }
    
    @GetMapping("/place-order")
    public String placeOrder() {
        return "place-order";
    }
    
    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }
    
    @GetMapping("/pricing")
    public String pricing() {
        return "pricing";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }   

    
    @GetMapping("/login-2baf7")
    public String account() {
        return "login-2baf7";
    }

}
