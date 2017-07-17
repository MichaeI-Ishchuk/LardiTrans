package com.lardi_trans.dto.rest_dto;

import com.lardi_trans.dto.AuthDTO;

/**
 * Created by nata on 15.07.2017.
 */
public class ResponseRemoveUserContactDTO extends AuthDTO {

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    private String successful;

}
