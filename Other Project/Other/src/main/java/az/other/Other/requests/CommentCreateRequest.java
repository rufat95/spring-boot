package az.other.Other.requests;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentCreateRequest {
    private Long id;
    private Long userId;
    private Long postId;
    private String text;
}
