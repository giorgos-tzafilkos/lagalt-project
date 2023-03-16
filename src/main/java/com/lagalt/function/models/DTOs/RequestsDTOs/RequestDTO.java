package com.lagalt.function.models.DTOs.RequestsDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    // Constructor
    public RequestDTO(int request_id, String request_text, int user_id, String user_name){
        this.request_id = request_id;
        this.request_text = request_text;
        this.user_id = user_id;
        this.user_name = user_name;
    }
    private int request_id;
    private String request_text;
    private int user_id;
    private String user_name;
}
