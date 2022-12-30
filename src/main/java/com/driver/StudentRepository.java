package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String, Student> studentDB;
    HashMap<String, Teacher> teacherDB;
    HashMap<String, List<String>> teacher_StudentList;

    public StudentRepository() {
        this.studentDB = new HashMap<String, Student>();
        this.teacherDB = new HashMap<String, Teacher>();
        this.teacher_StudentList = new HashMap<String, List<String>>();
    }
    // adding student
    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    //adding teacher
    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }
// New student-teacher pair adding
    public void addStudentTeacherPair(String studentN, String teacherN)
    {
        if (studentDB.containsKey(studentN) && teacherDB.containsKey(teacherN))
        {
            List<String> temp = new ArrayList<>();
            if(teacher_StudentList.containsKey(teacherN))
            {
                temp = teacher_StudentList.get(teacherN);
            }
            temp.add(studentN);
            teacher_StudentList.put(teacherN,temp);
        }
    }
// get student by name
    public Student getStudentByName(String name)
    {
        return studentDB.get(name);
    }
// get Teacher by name
   public Teacher getTeacherByName(String name)
   {
       return teacherDB.get(name);
   }
// get student list of particular teacher
    public List<String> getStudentsByTeacherName(String name)
    {
        List<String> stuList = new ArrayList<>();

        if(teacher_StudentList.containsKey(name))
          stuList = teacher_StudentList.get(name);

        return stuList;
    }
// get all student list
    public List<String> getAllStudents()
    {
        return new ArrayList<>(studentDB.keySet());
    }
// Deleting a teacher and its students from the records
    public void deleteTeacherByName(String name)
    {
        if(teacher_StudentList.containsKey(name))
        {
            List<String> list = teacher_StudentList.get(name);
            for(String s: list)
            {
                if(studentDB.containsKey(s))
                    studentDB.remove(s);
            }
            teacher_StudentList.remove(name);
        }
        teacherDB.remove(name);
    }
// Delete all teachers and all students by them from the records
    public void deleteAllTeachers()
    {
        for(String Tname: teacherDB.keySet())
        {
            deleteTeacherByName(Tname);
        }
    }
}
