package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

@Autowired
StudentService studentService;  // object of class StudentService

// adding student
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody() Student student){
        studentService.addStudent(student);
        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }
// adding teacher
    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody() Teacher teacher){
        studentService.addTeacher(teacher);
        return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }
// adding StudentList with particular teacher pair
    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair
        (@RequestParam("student") String student, @RequestParam("teacher") String teacher){
        studentService.addStudentTeacherPair(student,teacher);
        return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
    }
// get student by name
    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        // Assigning student by calling service layer method
        Student student = studentService.getStudentByName(name);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
// get teacher by name
    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        // Assigning student by calling service layer method
        Teacher teacher = studentService.getTeacherByName(name);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }
// get student list of particular teacher
    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        // Assigning list of student by calling service layer method
        List<String> students = studentService.getStudentsByTeacherName(teacher);
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }
// get all students list
    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        // Assigning list of student by calling service layer method
        List<String> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }
//Deleting a teacher and its students from the records
    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        studentService.deleteTeacherByName(teacher);
        return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
    }
// Delete all teachers and all students by them from the records
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        studentService.deleteAllTeachers();
        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
