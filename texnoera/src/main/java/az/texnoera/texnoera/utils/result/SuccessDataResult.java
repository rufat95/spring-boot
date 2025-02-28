package az.texnoera.texnoera.utils.result;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(String message, T data){
        super(true, message, data);
    }

}
