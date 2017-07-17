package com.lardi_trans.dto.rest_dto;

import com.lardi_trans.dto.AuthDTO;

import java.util.List;

/**
 * Created by nata on 13.07.2017.
 */
public class ResponseUserContactDTO extends AuthDTO {

    private List<RestUserContactDTO> listUserContact;

    public List<RestUserContactDTO> getList() {
        return listUserContact;
    }

    public void setList(
            List<RestUserContactDTO> listUserContact) {
        this.listUserContact = listUserContact;
    }
}
