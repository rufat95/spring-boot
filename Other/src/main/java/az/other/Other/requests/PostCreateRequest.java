package az.other.Other.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long user_id;
}
