package student_dao;

import student_model.Student;

import java.util.Date;
import java.util.List;

public interface StudentDAL {

    void saveStudents(List<Student> students);

    void saveStudent(Student student);

    Student getStudentByRollNo(long rollNo);
    List<Student> getStudentsByPercentage(double gte, double lte);
    List<Student> getStudentsByDOB(Date after);

    List<Student> findStudentByNameLike(String name);
}
