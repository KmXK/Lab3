package by.bsuir.vt3.server.service;

import by.bsuir.vt3.beans.AccountType;
import by.bsuir.vt3.beans.StudentFile;

import java.util.List;

public interface ServerService {
    String login(String username, String password);

    AccountType getAccountType(String authToken);

    void logout(String authToken);

    List<StudentFile> getAll();

    StudentFile get(int id);

    boolean add(StudentFile student);

    boolean edit(StudentFile changedStudent);
}
