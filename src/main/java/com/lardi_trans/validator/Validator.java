package com.lardi_trans.validator;

import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

/**
 * Created by nata on 15.03.2017.
 */
public class Validator {

    public static boolean registration(BindingResult bindingResult, HttpSession session){
        if(bindingResult.getAllErrors().size() != 0) {
            session.setAttribute("error", bindingResult.getAllErrors()
                    .get(0).getDefaultMessage());
            return true;
        }
        return false;
    }
}
