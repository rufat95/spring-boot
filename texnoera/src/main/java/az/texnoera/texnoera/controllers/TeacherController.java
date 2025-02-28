package az.texnoera.texnoera.controllers;

import az.texnoera.texnoera.entities.Teacher;
import az.texnoera.texnoera.requests.TeacherRequest;
import az.texnoera.texnoera.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public Teacher createTeacher(@RequestBody @Valid TeacherRequest teacherRequest){
        return teacherService.createTeacher(teacherRequest);
    }

    @GetMapping("/{teacherId}")
    public Teacher getOneTeacherById(@PathVariable Long teacherId){
        return teacherService.findByTeacherById(teacherId);
    }
}
