package com.lardi_trans.controller;

import com.lardi_trans.domain.User;
import com.lardi_trans.dto.UserDTO;
import com.lardi_trans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import ua.ishchuk.service.StudentService;
import com.lardi_trans.validator.Validator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nata on 11.03.2017.
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Resource
    Environment environment;


    @RequestMapping(value = "/login", method = RequestMethod.GET, name = "userLogin")
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user_login");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    public String verifyStudent(@RequestParam String login,
            @RequestParam String password,
            HttpSession session, HttpServletResponse response, HttpServletRequest request)
    {
           User user = userService.verify(login, password);
           session.setAttribute("user", user);
        session.setAttribute("userURL", "account/getContacts");

            return "redirect:account";

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET, name = "userRegistration")
    public ModelAndView getRegistrationPage
            (@ModelAttribute("user_registration") UserDTO userDTO)
           {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user_registration");
        return mv;
    }

   @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationHandler
            (
             @ModelAttribute("user_registration")
               @Validated
             UserDTO userDTO,
             BindingResult bindingResult, HttpSession session) {

         if(Validator.registration(bindingResult, session)){

            return "redirect:registration";
        }

      userService.registrationUser(userDTO);
       session.setAttribute("error", environment.getProperty("reg.successful"));
        return "redirect:/";
    }

}
