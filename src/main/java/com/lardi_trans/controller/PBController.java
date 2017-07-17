package com.lardi_trans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nata on 11.03.2017.
 */

@Controller
public class PBController {

@RequestMapping(value = "/", method = RequestMethod.GET)
ModelAndView getNameFile()

{
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("pb");
    return modelAndView;

}

}
