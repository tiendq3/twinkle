package com.yorra.twinkle.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseMessage {

    private int status;

    private String reason;

    private String path;

    private String title;
}
