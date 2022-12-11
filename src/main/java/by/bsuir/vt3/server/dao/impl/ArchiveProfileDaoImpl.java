package by.bsuir.vt3.server.dao.impl;

import by.bsuir.vt3.beans.AccountType;
import by.bsuir.vt3.beans.ArchiveProfile;
import by.bsuir.vt3.beans.Profiles;
import by.bsuir.vt3.server.dao.ArchiveProfileDao;
import jakarta.xml.bind.JAXBContext;

import java.io.FileReader;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArchiveProfileDaoImpl implements ArchiveProfileDao {

    private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789" +
            "abcdefghijklmnopqrstuvwxyz";
    private final SecureRandom rand = new SecureRandom();
    private List<ArchiveProfile> profiles;
    private final Map<String, ArchiveProfile> loggedUsers = new HashMap<String, ArchiveProfile>();

    @Override
    public List<ArchiveProfile> getAll() {
        if (profiles == null) {
            try {
                JAXBContext context = JAXBContext.newInstance(Profiles.class);
                Profiles unmarshalledEntity = ((Profiles) context.createUnmarshaller().
                        unmarshal(new FileReader("./src/main/resources/profiles_db.xml")));
                profiles = unmarshalledEntity.getProfiles();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return profiles;
    }

    @Override
    public String login(String username, String password) {
        if (profiles == null) {
            this.getAll();
        }
        for (ArchiveProfile profile : profiles) {
            if (profile.getUsername().equals(username) &&
                    profile.getPassword().equals(password)) {
                String authToken = rand.ints(8, 0, chars.length()).
                        mapToObj(i -> "" + chars.charAt(i)).collect(Collectors.joining());
                loggedUsers.put(authToken, profile);
                return authToken;
            }
        }
        return null;
    }

    @Override
    public AccountType getAccType(String authToken) {
        try {
            if (loggedUsers.containsKey(authToken)) {
                return loggedUsers.get(authToken).getAccType();
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    @Override
    public void logout(String authToken) {
        loggedUsers.remove(authToken);
    }
}
