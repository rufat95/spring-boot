package az.texnoera.texnoera.repositories;

import az.texnoera.texnoera.entities.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {
}
