package by.bsuir.vt3.server.dao;

import by.bsuir.vt3.beans.AccountType;
import by.bsuir.vt3.beans.ArchiveProfile;

import java.util.List;

public interface ArchiveProfileDao {
    List<ArchiveProfile> getAll();

    String login(String username, String password);

    AccountType getAccType(String authToken);

    void logout(String authToken);
}
