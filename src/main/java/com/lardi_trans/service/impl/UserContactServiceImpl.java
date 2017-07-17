package com.lardi_trans.service.impl;

import com.lardi_trans.domain.User;
import com.lardi_trans.domain.UserContact;
import com.lardi_trans.dto.UserContactDTO;
import com.lardi_trans.dto.UserDTO;
import com.lardi_trans.exception.RemoveUserContactException;
import com.lardi_trans.exception.UpdateUserContactException;
import com.lardi_trans.exception.UserServiceException;
import com.lardi_trans.repository.UserContactRepository;
import com.lardi_trans.repository.UserRepository;
import com.lardi_trans.service.UserContactService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by nata on 13.07.2017.
 */

@Service
@PropertySource("classpath:reg.properties")
public class UserContactServiceImpl implements UserContactService {
    @Resource
    Environment environment;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContactRepository userContactRepository;

    @Override
    public void registrationUserContact(UserContactDTO userContactDTO, String login) {


        User user = userRepository.getUserByLogin(login);

        UserContact userContact = new UserContact();
        userContact.setName(userContactDTO.getName());
        userContact.setSurname(userContactDTO.getSurname());
        userContact.setPatronymic(userContactDTO.getPatronymic());
        userContact.setPhoneMobile(userContactDTO.getPhoneMobile());
        userContact.setPhoneHome(userContactDTO.getPhoneHome());
        userContact.setAddress(userContactDTO.getAddress());
        userContact.setEmail(userContactDTO.getEmail());
        userContact.setUser(user);

        try{

            userContactRepository.save(userContact);

        }catch(Exception e){
            throw new UserServiceException(environment.getProperty("reg.exist"));


        }





    }

    @Override
    public List<UserContactDTO> getUserContactDTOByUser(UserDTO userDTO) {


        List<UserContact> list = userContactRepository.getUserContactByUser(userDTO.getLogin());


        return list.stream().map(userContact ->{

                    UserContactDTO userContactDTO = new UserContactDTO();
                    userContactDTO.setId(userContact.getId());
                    userContactDTO.setName(userContact.getName());
                    userContactDTO.setSurname(userContact.getSurname());
                    userContactDTO.setPatronymic(userContact.getPatronymic());
                    userContactDTO.setPhoneMobile(userContact.getPhoneMobile());
                    userContactDTO.setPhoneHome(userContact.getPhoneHome());
                    userContactDTO.setAddress(userContact.getAddress());
                    userContactDTO.setEmail(userContact.getEmail());


                    return userContactDTO;

                }
                ).collect(Collectors.toList());

    }

    @Override
    public List<UserContactDTO> getUserContactDTOByUserAndFilter(UserDTO userDTO, String text, String type) {

        List<UserContact> list = userContactRepository.getUserContactByUser(userDTO.getLogin());

        switch (type)

        {
        case "1":

            return list.stream()
                    .filter(userContact -> userContact.getName().matches("^" + text + ".*$") == true)
                    .map(userContact -> {
                                UserContactDTO userContactDTO = new UserContactDTO();
                                userContactDTO.setId(userContact.getId());
                                userContactDTO.setName(userContact.getName());
                                userContactDTO.setSurname(userContact.getSurname());
                                userContactDTO.setPatronymic(userContact.getPatronymic());
                                userContactDTO.setPhoneMobile(userContact.getPhoneMobile());
                                userContactDTO.setPhoneHome(userContact.getPhoneHome());
                                userContactDTO.setAddress(userContact.getAddress());
                                userContactDTO.setEmail(userContact.getEmail());
                                return userContactDTO;

                            }
                    ).collect(Collectors.toList());
        case "2":

            return list.stream()
                    .filter(userContact -> userContact.getSurname().matches("^" + text + ".*$") == true)
                    .map(userContact -> {
                                UserContactDTO userContactDTO = new UserContactDTO();
                                userContactDTO.setId(userContact.getId());
                                userContactDTO.setName(userContact.getName());
                                userContactDTO.setSurname(userContact.getSurname());
                                userContactDTO.setPatronymic(userContact.getPatronymic());
                                userContactDTO.setPhoneMobile(userContact.getPhoneMobile());
                                userContactDTO.setPhoneHome(userContact.getPhoneHome());
                                userContactDTO.setAddress(userContact.getAddress());
                                userContactDTO.setEmail(userContact.getEmail());
                                return userContactDTO;

                            }
                    ).collect(Collectors.toList());
        case "3":

            return list.stream()
                    .filter(userContact -> userContact.getPhoneMobile().matches("^\\" + text + ".*$") == true)
                    .map(userContact -> {
                                UserContactDTO userContactDTO = new UserContactDTO();
                                userContactDTO.setId(userContact.getId());
                                userContactDTO.setName(userContact.getName());
                                userContactDTO.setSurname(userContact.getSurname());
                                userContactDTO.setPatronymic(userContact.getPatronymic());
                                userContactDTO.setPhoneMobile(userContact.getPhoneMobile());
                                userContactDTO.setPhoneHome(userContact.getPhoneHome());
                                userContactDTO.setAddress(userContact.getAddress());
                                userContactDTO.setEmail(userContact.getEmail());
                                return userContactDTO;

                            }
                    ).collect(Collectors.toList());

        default: return null;
                }


    }

    @Override
    public void removeUserContact(Long id) {

        try {
            userContactRepository.delete(id);
        } catch (Exception e) {
            throw new RemoveUserContactException(environment.getProperty("reg.removeUserContact"));

        }
    }

    @Override
    public void updateUserContact(UserContactDTO userContactDTO) {

        try {
            userContactRepository.updateUserContact(userContactDTO);
        } catch (Exception e) {
            throw new UpdateUserContactException(environment.getProperty("reg.updateUserContact"));

        }


    }
}
