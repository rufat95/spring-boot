package az.texnoera.texnoera.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherCourseRequest {
    private Long teacherId;
    private Long courseId;
}
