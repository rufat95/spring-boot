package az.other.Other.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PostUpdateRequest {
    String title;
    String text;
}
