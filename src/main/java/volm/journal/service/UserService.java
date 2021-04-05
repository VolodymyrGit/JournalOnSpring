package volm.journal.service;

public interface UserService {

    boolean authorized(String email, String password);
}
