package com.grubneac.CafeDemoCRM.model;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
