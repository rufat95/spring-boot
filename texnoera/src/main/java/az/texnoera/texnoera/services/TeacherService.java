package az.texnoera.texnoera.services;

import az.texnoera.texnoera.entities.Teacher;
import az.texnoera.texnoera.entities.User;
import az.texnoera.texnoera.repositories.TeacherRepository;
import az.texnoera.texnoera.repositories.UserRepository;
import az.texnoera.texnoera.requests.TeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public Teacher createTeacher(TeacherRequest teacherRequest) {
        User user = new User();
        user.setFirstName(teacherRequest.getFirstName());
        user.setLastName(teacherRequest.getLastName());
        user.setEmail(teacherRequest.getEmail());
        user.setPassword(teacherRequest.getPassword());
        user.setUserRoles(teacherRequest.getUserRoles());

        User newUser = userRepository.save(user);

        Teacher teacher = new Teacher();
        teacher.setSalary(teacherRequest.getSalary());
        teacher.setUser(newUser);

        teacherRepository.save(teacher);

        return teacher;
    }

    public Teacher findByTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow();
    }
}
