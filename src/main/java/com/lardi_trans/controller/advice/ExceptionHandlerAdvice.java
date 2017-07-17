package com.lardi_trans.controller.advice;

import com.lardi_trans.exception.UserServiceException;
import com.lardi_trans.exception.VerifyUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpSession;

/**
 * Created by Adrien on 28.02.2017.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(UserServiceException.class)
    public String StudentServiceExceptionHandler(UserServiceException e,
            HttpSession session) {
        session.setAttribute("error", e.getMessage());
        return "redirect:registration";
    }

    @ExceptionHandler(VerifyUserException.class)
    public String verificationUserExceptionHandler(VerifyUserException e,
            HttpSession session) {
        session.setAttribute("error", e.getMessage());
        return "redirect:login";
    }

//    @ExceptionHandler(VerifyTeacherException.class)
//    public String verificationUserExceptionHandler(VerifyTeacherException e,
//            HttpSession session) {
//        session.setAttribute("error", e.getMessage());
//        return "redirect:teacher_login";
//    }
//
//
//
//
//
//
//    @ExceptionHandler(SubjectException.class)
//    public String StudentServiceExceptionHandler(SubjectException e,
//            HttpSession session) {
//        session.setAttribute("error", e.getMessage());
//        return "redirect:registration";
//    }
}