package az.other.Other.requests;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long userId;
}
