package com.gva.cursomcd.resource.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

public class StandardError implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter 
    private Integer status;

    @Getter
    @Setter
    private String msg;

    @Setter
    @Getter
    private Long timeStamp;

    @Getter
    @Setter
    private String momento;


    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.momento = getTimeStamp(timeStamp);
    }


    public String getTimeStamp(Long timeStamp){
        LocalDateTime data = LocalDateTime.now();
        return String.valueOf(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

}