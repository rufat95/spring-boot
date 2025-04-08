package az.candyshop.CandyShop.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse {
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
}