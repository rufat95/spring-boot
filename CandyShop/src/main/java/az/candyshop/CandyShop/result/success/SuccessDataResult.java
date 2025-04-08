package az.candyshop.CandyShop.result.success;

import az.candyshop.CandyShop.result.DataResult;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(String message, T data){
        super(true, message, data);
    }
}
