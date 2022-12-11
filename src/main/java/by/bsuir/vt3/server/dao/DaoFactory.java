package by.bsuir.vt3.server.dao;

import by.bsuir.vt3.server.dao.impl.ArchiveProfileDaoImpl;
import by.bsuir.vt3.server.dao.impl.StudentFileDaoImpl;
import lombok.Getter;

@Getter
public final class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final StudentFileDao studentFileDao = new StudentFileDaoImpl();
    private final ArchiveProfileDao archiveProfileDao = new ArchiveProfileDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}

