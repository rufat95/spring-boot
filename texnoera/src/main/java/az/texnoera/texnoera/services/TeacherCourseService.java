package az.texnoera.texnoera.services;

import az.texnoera.texnoera.entities.Course;
import az.texnoera.texnoera.entities.Teacher;
import az.texnoera.texnoera.entities.TeacherCourse;
import az.texnoera.texnoera.enums.StatusCode;
import az.texnoera.texnoera.exceptions.BaseException;
import az.texnoera.texnoera.repositories.TeacherCourseRepository;
import az.texnoera.texnoera.requests.TeacherCourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherCourseService {
    private final TeacherCourseRepository teacherCourseRepository;
    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherCourse createTeacherCourse(
            TeacherCourseRequest teacherCourseRequest) {

        Teacher teacher = teacherService.findByTeacherById(teacherCourseRequest.getTeacherId());
        Course course = courseService.findByCourseId(teacherCourseRequest.getCourseId());
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setTeacher(teacher);
        teacherCourse.setCourse(course);

        return teacherCourseRepository.save(teacherCourse);
    }


    public TeacherCourse updateTeacherCourse(Long teacherCourseId,
                                             TeacherCourseRequest teacherCourseRequest) {
        TeacherCourse teacherCourse = teacherCourseRepository.findById(teacherCourseId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND,
                        StatusCode.TEACHER_COURSE_NOT_FOUND));

        Teacher teacher = teacherService.findByTeacherById(teacherCourseRequest.getTeacherId());
        Course course = courseService.findByCourseId(teacherCourseRequest.getCourseId());

        teacherCourse.setTeacher(teacher);
        teacherCourse.setCourse(course);

        return teacherCourseRepository.save(teacherCourse );
    }

    public List<TeacherCourse> getTeacherCourse(Long teacherCourseId) {

        return teacherCourseRepository.findAll();
    }
}
