package com.codegym.controler;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @GetMapping("/login")
    public String signup(Model model){
        model.addAttribute("user",new User() );
        return "signup";
    }
    @PostMapping("/signup")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model){
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "signup";
        }
        else {
            user.getName();
            model.addAttribute("user",user);
//            ModelAndView model=new ModelAndView("signup");
//            model.addObject("user", user);

            return "login";
        }

    }
}
