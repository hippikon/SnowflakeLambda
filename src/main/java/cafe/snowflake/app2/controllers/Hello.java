package cafe.snowflake.app2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Hello {

    @GetMapping("/index")
    public String showUserList(Model model) {

        model.addAttribute("users", List.of());
        return "index";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @GetMapping("/health1")
    public String oidcHealth() {
        return "Healthy";
    }


//    @GetMapping(value = "/login")
//    public String login() {
//        //return login.html located in /resources/templates
//        return "login";
//    }
}
