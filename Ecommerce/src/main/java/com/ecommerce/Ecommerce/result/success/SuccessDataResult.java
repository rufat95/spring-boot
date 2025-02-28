package com.ecommerce.Ecommerce.result.success;

import com.ecommerce.Ecommerce.result.DataResult;

public class SuccessDataResult<T> extends DataResult<T> {
    public SuccessDataResult(String message, T data) {
        super(true, message, data);
    }
}
