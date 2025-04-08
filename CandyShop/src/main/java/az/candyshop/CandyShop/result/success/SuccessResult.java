package az.candyshop.CandyShop.result.success;

import az.candyshop.CandyShop.result.Result;

public class SuccessResult extends Result {

    public SuccessResult(String message){
        super(true, message);
    }
}
