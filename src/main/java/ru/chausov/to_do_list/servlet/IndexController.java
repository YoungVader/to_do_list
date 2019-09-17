package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("")
    public ModelAndView greeting(Principal authUser, Map<String, Object> model) {
        try {
            model.put("username", authUser.getName());
        } catch (NullPointerException exception) {

        }

        return new ModelAndView("greeting", model);
    }

    @GetMapping("/index")
    public ModelAndView index(Principal authUser, Map<String, Object> model) {
        model.put("username", authUser.getName());

        return new ModelAndView("index", model);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
