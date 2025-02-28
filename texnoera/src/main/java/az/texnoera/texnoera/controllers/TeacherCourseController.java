package az.texnoera.texnoera.controllers;

import az.texnoera.texnoera.entities.TeacherCourse;
import az.texnoera.texnoera.requests.TeacherCourseRequest;
import az.texnoera.texnoera.services.TeacherCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/teacher_courses")
public class TeacherCourseController {
    private final TeacherCourseService teacherCourseService;

    @PostMapping
    public TeacherCourse createTeacherCourse(@RequestBody TeacherCourseRequest teacherCourseRequest){
        return teacherCourseService.createTeacherCourse(teacherCourseRequest);
    }

    @PutMapping("/{teacher_course_id}")
    public TeacherCourse updateTeacherCourse(@PathVariable(value = "teacher_course_id") Long teacherCourseId,
                                             @RequestBody TeacherCourseRequest teacherCourseRequest){
        return teacherCourseService.updateTeacherCourse(teacherCourseId, teacherCourseRequest);
    }

    @GetMapping("/{teacherCourseId}")
    public List<TeacherCourse> getTeacherCourse(@RequestParam(required = false) Long teacherCourseId){
        return teacherCourseService.getTeacherCourse(teacherCourseId);
    }
}
