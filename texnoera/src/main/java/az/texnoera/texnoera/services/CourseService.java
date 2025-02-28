package az.texnoera.texnoera.services;

import az.texnoera.texnoera.entities.Course;
import az.texnoera.texnoera.repositories.CourseRepository;
import az.texnoera.texnoera.requests.CourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course createCourse(CourseRequest courseRequest) {

        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());

        return courseRepository.save(course);
    }

    public Course findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow();
    }
}
