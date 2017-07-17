package com.lardi_trans.controller;

import com.lardi_trans.domain.User;
import com.lardi_trans.dto.UserContactDTO;
import com.lardi_trans.dto.UserDTO;
import com.lardi_trans.dto.rest_dto.ResponseRemoveUserContactDTO;
import com.lardi_trans.dto.rest_dto.ResponseUserContactDTO;
import com.lardi_trans.dto.rest_dto.RestUserContactDTO;
import com.lardi_trans.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nata on 13.07.2017.
 */


@RestController

public class RestUserAccountController {


    @Autowired
    UserContactService userContactService;


    @RequestMapping(value = "/account/getContacts" ,method = RequestMethod.GET)

    public ResponseEntity<ResponseUserContactDTO> getAllUserContactsById(HttpSession session, @RequestParam String text, @RequestParam String type)
    {

        User user = (User)session.getAttribute("user");

        if (user!=null)
        {

            List<UserContactDTO> listUserContactDTO;
            UserDTO userDTO = new UserDTO();

            userDTO.setLogin(user.getLogin());

            if(type.equals("0")) {

              listUserContactDTO = userContactService.getUserContactDTOByUser(userDTO);
                      }

            else {

               listUserContactDTO = userContactService.getUserContactDTOByUserAndFilter(userDTO,text,type);

                }



            ResponseUserContactDTO responseUserContactDTO = new ResponseUserContactDTO();



            List<RestUserContactDTO> list = new ArrayList<>();

            for (UserContactDTO dto: listUserContactDTO)
            {

                RestUserContactDTO restUser = new RestUserContactDTO();

                restUser.setName(dto.getName());
                restUser.setName(dto.getName());
                restUser.setSurname(dto.getSurname());
                restUser.setPatronymic(dto.getPatronymic());
                restUser.setPhoneMobile(dto.getPhoneMobile());
                restUser.setPhoneHome(dto.getPhoneHome());
                restUser.setAddress(dto.getAddress());
                restUser.setEmail(dto.getEmail());


                ResponseEntity<ResponseRemoveUserContactDTO> removeUserContact =
                        methodOn(RestUserAccountController.class)
                                .removeUserContact(dto.getId(), session);
                Link link = linkTo(removeUserContact).withRel("remove");

                ResponseEntity<ResponseRemoveUserContactDTO> updateUserContact =
                        methodOn(RestUserAccountController.class)
                                .updateUserContact(dto, session);
                Link link2= linkTo(updateUserContact).withRel("update");

                restUser.add(link);
                restUser.add(link2);
                list.add(restUser);
            }

            responseUserContactDTO.setList(list);
            responseUserContactDTO.setAuth("yes");
            return new ResponseEntity<>(responseUserContactDTO, HttpStatus.OK );
        }else {

            ResponseUserContactDTO responseUserContactDTO = new ResponseUserContactDTO();

            responseUserContactDTO.setAuth("no");

            return new ResponseEntity<>(responseUserContactDTO, HttpStatus.OK);
        }

    }


    @RequestMapping(value = "/account/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseRemoveUserContactDTO> removeUserContact(@PathVariable("id")Long id,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            ResponseRemoveUserContactDTO removeUserContactDTO = new ResponseRemoveUserContactDTO();
            removeUserContactDTO.setAuth("no");
            return new ResponseEntity<ResponseRemoveUserContactDTO>(removeUserContactDTO, HttpStatus.OK);
        }

        userContactService.removeUserContact(id);
        ResponseRemoveUserContactDTO removeUserContactDTO = new ResponseRemoveUserContactDTO();
        removeUserContactDTO.setAuth("yes");
        return new ResponseEntity<ResponseRemoveUserContactDTO>(removeUserContactDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/update", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseRemoveUserContactDTO> updateUserContact(@RequestBody UserContactDTO userContactDTO,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            ResponseRemoveUserContactDTO removeUserContactDTO = new ResponseRemoveUserContactDTO();
            removeUserContactDTO.setAuth("no");
            return new ResponseEntity<ResponseRemoveUserContactDTO>(removeUserContactDTO, HttpStatus.OK);
        }

        userContactService.updateUserContact(userContactDTO);
        ResponseRemoveUserContactDTO removeUserContactDTO = new ResponseRemoveUserContactDTO();
        removeUserContactDTO.setAuth("yes");
        return new ResponseEntity<ResponseRemoveUserContactDTO>(removeUserContactDTO, HttpStatus.OK);
    }
}
