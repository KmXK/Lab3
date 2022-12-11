package by.bsuir.vt3.server.service.impl;

import by.bsuir.vt3.beans.AccountType;
import by.bsuir.vt3.beans.StudentFile;
import by.bsuir.vt3.server.dao.DaoFactory;
import by.bsuir.vt3.server.service.ServerService;

import java.util.List;

public class ServerServiceImpl implements ServerService {
    @Override
    public String login(String username, String password) {
        var archiveProfileDao = DaoFactory.getInstance().getArchiveProfileDao();
        return archiveProfileDao.login(username, password);
    }

    @Override
    public AccountType getAccountType(String authToken) {
        if (authToken == null) {
            return null;
        }
        var archiveProfileDao = DaoFactory.getInstance().getArchiveProfileDao();
        return archiveProfileDao.getAccType(authToken);
    }

    @Override
    public void logout(String authToken) {
        var archiveProfileDao = DaoFactory.getInstance().getArchiveProfileDao();
        archiveProfileDao.logout(authToken);
    }

    @Override
    public List<StudentFile> getAll() {
        var studentFileDao = DaoFactory.getInstance().getStudentFileDao();
        return studentFileDao.getAll();
    }

    @Override
    public StudentFile get(int id) {
        var studentFileDao = DaoFactory.getInstance().getStudentFileDao();
        return studentFileDao.get(id);
    }

    @Override
    public boolean add(StudentFile student) {
        var studentFileDao = DaoFactory.getInstance().getStudentFileDao();
        return studentFileDao.add(student);
    }

    @Override
    public boolean edit(StudentFile changedStudent) {
        var studentFileDao = DaoFactory.getInstance().getStudentFileDao();
        return studentFileDao.edit(changedStudent);
    }
}
