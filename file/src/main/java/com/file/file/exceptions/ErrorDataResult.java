package com.file.file.exceptions;

import lombok.Getter;
import java.util.List;

@Getter
public class ErrorDataResult extends ErrorResult{
    private final List<String> validation;

    public ErrorDataResult(Integer code, String message, List<String> validation) {
        super(code, message);
        this.validation = validation;
    }
}
