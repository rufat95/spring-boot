package az.candyshop.CandyShop.result.success;

import az.candyshop.CandyShop.result.PaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessPageDataset<T> {
    private boolean success;
    private String message;
    private T data;
    private PaginationResponse pagination;
}