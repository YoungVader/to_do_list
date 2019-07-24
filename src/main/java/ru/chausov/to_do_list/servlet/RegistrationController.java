package ru.chausov.to_do_list.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
