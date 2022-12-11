package by.bsuir.vt3.server.dao.impl;

import by.bsuir.vt3.beans.StudentFile;
import by.bsuir.vt3.beans.Students;
import by.bsuir.vt3.server.dao.StudentFileDao;
import jakarta.xml.bind.JAXBContext;

import java.io.FileReader;
import java.util.List;

public class StudentFileDaoImpl implements StudentFileDao {
    private List<StudentFile> students;

    @Override
    public List<StudentFile> getAll() {
        if (students == null) {
            try {
                JAXBContext context = JAXBContext.newInstance(Students.class);
                Students unmarshalledEntity = ((Students) context.createUnmarshaller().
                        unmarshal(new FileReader("./src/main/resources/students_db.xml")));
                students = unmarshalledEntity.getStudents();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return students;
    }

    @Override
    public StudentFile get(int studentId) {
        for (StudentFile student : getAll()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean add(StudentFile student) {
        return getAll().add(student);
    }

    @Override
    public boolean edit(StudentFile changedStudent) {
        for (int i = 0; i < getAll().size(); i++) {
            if (getAll().get(i).getId() == changedStudent.getId()) {
                getAll().set(i, changedStudent);
                return true;
            }
        }
        return false;
    }
}
