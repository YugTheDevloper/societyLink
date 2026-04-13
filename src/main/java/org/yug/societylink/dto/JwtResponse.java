package org.yug.societylink.dto;

import lombok.Data;

@Data

public class JwtResponse {

    public String token;
    public String type="Bearer";

    public JwtResponse(String token){
        this.token=token;
    }


}
