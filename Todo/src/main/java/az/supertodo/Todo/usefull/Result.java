package az.supertodo.Todo.usefull;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {
    private List<T> data;
    private int page;
    private int size;
    private int totalPages;
}
