package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImp implements RoleDAO {
    @Override
    public Role create(Role role) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement =ConnectionHolder.getConnection().prepareStatement("INSERT INTO ROLE(role) VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                int id=resultSet.getInt(1);
                role.setRoleId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role read(int position) {

        PreparedStatement preparedStatement =null;
        Role role=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT role FROM role WHERE role.id IS ?");
            preparedStatement.setInt(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            role=new Role();
            if(resultSet.next()){
                role.setRoleId(position);
                role.setRoleName(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role read(String roleName) {
        PreparedStatement preparedStatement=null;
        Role role = new Role();
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT * FROM role WHERE role.role = ?");
            preparedStatement.setString(1,roleName);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                role.setRoleId(resultSet.getInt(1));
                role.setRoleName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role[] readAll() {
        PreparedStatement preparedStatement=null;
        List<Role> list=new ArrayList<>();
        Role role=null;
        Role[] roles=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("SELECT *FROM role");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                role=new Role();
                role.setRoleId(resultSet.getInt(1));
                role.setRoleName(resultSet.getString(2));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        roles=new Role[list.size()];
        for(int i=0; i<roles.length; i++){
            roles[i]=list.get(i);
        }

        return roles;
    }

    @Override
    public Role getDefaultRole() {
        return read(3);
    }

    @Override
    public Role update(Role role) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("UPDATE role SET role=? WHERE role.id=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,role.getRoleName());
            preparedStatement.setInt(2, role.getRoleId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public int delete(int id) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("DELETE FROM role WHERE role.id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
