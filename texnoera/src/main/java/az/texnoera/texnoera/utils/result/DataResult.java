package az.texnoera.texnoera.utils.result;

import lombok.Getter;

@Getter
public class DataResult<T> extends Result{
    private final T data;

    public DataResult(boolean success, String message, T data){
        super(true, message);
        this.data = data;
    }
}
