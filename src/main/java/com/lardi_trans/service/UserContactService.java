package com.lardi_trans.service;

import com.lardi_trans.dto.UserContactDTO;
import com.lardi_trans.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nata on 13.07.2017.
 */

public interface UserContactService {

    public void registrationUserContact(UserContactDTO userContactDTO, String login);

   public List<UserContactDTO> getUserContactDTOByUser(UserDTO user);
    public List<UserContactDTO> getUserContactDTOByUserAndFilter(UserDTO user, String text, String type);

     public void removeUserContact(Long id);
    public void updateUserContact(UserContactDTO userContactDTO);
}
