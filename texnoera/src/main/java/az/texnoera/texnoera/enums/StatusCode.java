package az.texnoera.texnoera.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    USER_NOT_FOUND(1001, "User not found!"),
    USER_NOT_LOGIN(1002, "Email or password incorrect"),
    TEACHER_COURSE_NOT_FOUND(1003, "Teacher_Course not found !"),
    ANY_ERROR(1100, "Any error !");

    private Integer code;
    private String message;
}
