package database;

/**
 * Created by miroslav on 12.11.15.
 */
public interface RoleDAO {

    Role create(Role role);
    Role read(int position);
    Role read(String roleName);
    Role[] readAll();
    Role getDefaultRole();
    Role update(Role role);
    int delete(int id);
}
