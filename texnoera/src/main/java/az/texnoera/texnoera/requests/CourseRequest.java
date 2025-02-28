package az.texnoera.texnoera.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseRequest {
    private String name;
    private Integer duration;
    private String description;
}
