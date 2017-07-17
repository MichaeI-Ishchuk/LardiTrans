package com.lardi_trans.controller;

import com.lardi_trans.domain.User;
import com.lardi_trans.dto.UserContactDTO;
import com.lardi_trans.dto.UserDTO;
import com.lardi_trans.service.UserContactService;
import com.lardi_trans.service.UserService;
import com.lardi_trans.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nata on 19.03.2017.
 */
@Controller
public class UserAccountController {

    @Autowired
    UserContactService userContactService;

    @Resource
    Environment environment;

    @RequestMapping(value = "/account", method = RequestMethod.GET)

    public ModelAndView getAccount(HttpSession session, @ModelAttribute("contact_registration") UserContactDTO userContactDTO) {

         User user = (User)session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();

        if (user!=null) {


            modelAndView.setViewName("account");
            modelAndView.addObject("userName", user.getName());
            modelAndView.addObject("userSurname", user.getSurname());

            //////////////


            return modelAndView;

        }
        else {
            modelAndView.setViewName("pb");
                 return modelAndView;}

    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String registrationHandler
            (
                    @ModelAttribute
                    @Validated
                    UserContactDTO userContactDTO,
                    BindingResult bindingResult, HttpSession session) {

        if(Validator.registration(bindingResult, session)){

            return "redirect:account";
        }
        User user = (User)session.getAttribute("user");
        String login = user.getLogin();
        userContactService.registrationUserContact(userContactDTO, login);
        session.setAttribute("error", environment.getProperty("reg.successful"));
        return "redirect:account";
    }

    @RequestMapping(value = "/account/exit", method = RequestMethod.GET, name = "exit")

    public String exitAccount(
            HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        session.invalidate();

        return "redirect:/account";

    }


//    @RequestMapping(value = "/account/addContact", method = RequestMethod.GET)
//    public @ResponseBody
//    UserContact getDomainInJsonFormat() {
//
//        UserContact userContact = new UserContact();
//        userContact.setName("sssssssssssssss");
//
//             return userContact;

//    }




}
