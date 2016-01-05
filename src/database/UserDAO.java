package database;

public interface UserDAO{

    User create(User user);
    User read(int position);
    User read(String login, String password);
    User[] readAll();
    User update(User user);
    User update(int position);
    void delete(User user);
    void delete(int position);

}
