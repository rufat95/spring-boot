package az.texnoera.texnoera.controllers;

import az.texnoera.texnoera.entities.Course;
import az.texnoera.texnoera.requests.CourseRequest;
import az.texnoera.texnoera.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody CourseRequest courseRequest){
        return courseService.createCourse(courseRequest);
    }

    @GetMapping("/{courseId}")
    public Course findCourseById(@PathVariable Long courseId){
        return courseService.findByCourseId(courseId);
    }

}
