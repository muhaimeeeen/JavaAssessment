package test;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class CourseServiceTest {
    CourseService courseService;
    // Initialize a new course for testing
    Course course = new Course("TEST-COURSE", "Test Course", 6, new Module("TEST-MODULE", "Test Module", "Description"));

    @BeforeEach
    void setUp() {
        // Create an instance of CourseService
        courseService = new CourseService();
    }

    @Test
    void registerCourse() {
        // Invoke the method to test
        courseService.registerCourse(course);

        // Checking if course is not null and if it is equal to the test course that was initialized
        assertNotNull(courseService.getCourse("TEST-COURSE"));
        assertEquals(course, courseService.getCourse("TEST-COURSE"));

    }

    @Test
    void getCourse() {
        // Checks if the given value is equals to null
        assertNull(courseService.getCourse("TEST-COURSE-2"));

    }
}