package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
            }
        }
        while ( option != 7 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        // Find student. use findStudent in student ervice
        // if student is found. Check against the approvedCourses
        // scanner to ask which course they want to grade
        // Obtain the course from courseService, Verify the course exits in courseService
        // Take course code from course and check if student isAttendingCourse (student.isAttendingCourse) returns true(is found in approvedCourses)
        // Prompt user(scanner) to enter grade
        // else tell user course not found with the student
        int grade = 0;
        System.out.println("Insert student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        // If student is found
        if (student != null) {
            System.out.println("Grading: " + student + "\n");
            // If student has enrolled into course(s)
            if (!student.getApprovedCourses().isEmpty()) {
                System.out.println("Course(s) enrolled:");
                for (Course course : student.getApprovedCourses()) {
                    System.out.println(course);
                }
                System.out.println("\nInsert course code to grade: ");
                String courseCode = scanner.next();
                CourseService courseService = new CourseService();
                Course course = courseService.getCourse(courseCode);
                // If student is attending the course to be graded
                if(student.isAttendingCourse(courseCode)){
                    System.out.println("Please enter grade");
                    grade = scanner.nextInt();
                }else {
                    System.out.println("Student is not attending this course");
                    return;
                }
            }
        }
    }


    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
