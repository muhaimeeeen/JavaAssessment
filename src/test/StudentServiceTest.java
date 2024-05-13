package test;

import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class StudentServiceTest {
    StudentService studentService;
    // Initialize/Create a new student
    Student student = new Student("1234", "Richard", "test@gmail.com", new Date(01/01/2011));

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void subscribeStudent() {
        // Invoke the method to test
        studentService.subscribeStudent(student);

        // Checks if the studentId is equals to the initialized/created student
        assertTrue(studentService.isSubscribed("1234"));
    }

    @Test
    void isSubscribed() {
        studentService.subscribeStudent(student);

        // Checks if the studentId is equals to the initialized/created student
        assertTrue(studentService.isSubscribed("1234"));
    }

    @Test
    void isSubscribedNotEquals() {
        // Checks if the studentId is not equals to any initialized/created student
        assertFalse(studentService.isSubscribed("7891"));
    }
}