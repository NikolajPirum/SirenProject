package org.example.backend.exception;


import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Date timeStamp;
    private String message;
    private String description;

}
