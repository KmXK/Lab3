package by.bsuir.vt3.server.dao;

import by.bsuir.vt3.beans.StudentFile;

import java.util.List;

public interface StudentFileDao {
    List<StudentFile> getAll();

    StudentFile get(int studentId);

    boolean add(StudentFile student);

    boolean edit(StudentFile changedStudent);
}
