package com.lardi_trans.repository.impl;

import com.lardi_trans.dto.UserContactDTO;
import com.lardi_trans.repository.UserContactRepository;
import com.lardi_trans.repository.UserContactRepositoryCustom;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nata on 15.07.2017.
 */

@Component
public class UserContactRepositoryImpl implements UserContactRepositoryCustom{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateUserContact(UserContactDTO userContactDTO) {


        entityManager.createQuery("UPDATE com.lardi_trans.domain.UserContact u SET u.name= :name " +
                "u.surname= :surname " +
                "u.patronymic= :patronymic " +
                "u.phoneHome= :phoneHome " +
                "u.phoneMobile= :phoneMobile " +
                "u.address= :address " +
                "u.email= :email " +
                "WHERE u.id= :id")
                .setParameter("id", userContactDTO.getId())
                .setParameter("name", userContactDTO.getName())
               .setParameter("surname", userContactDTO.getSurname())
               .setParameter("patronymic", userContactDTO.getPatronymic())
                .setParameter("phoneHome", userContactDTO.getPhoneHome())
                .setParameter("phoneMobile", userContactDTO.getPhoneMobile())
                .setParameter("address", userContactDTO.getAddress())
                .setParameter("email", userContactDTO.getEmail())
                .executeUpdate();
    }
}
