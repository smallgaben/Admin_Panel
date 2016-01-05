package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UserDaoImp implements UserDAO {
    @Override
    public User create(User user) {
        AdressDAO adressDAO=new AdressDAOImp();

        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("INSERT INTO  user(login,password,firstname,lastame,role_id) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFistName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setInt(5,user.getRole().getRoleId());
            preparedStatement.executeUpdate();

            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
               user.setId(resultSet.getInt(1));
            }
            adressDAO.create(user.getAdress(), user.getId());
            createUserMusicType(user,user.getMusicTypes());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User read(int position) {
        PreparedStatement preparedStatement=null;
        User user =new User();
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("SELECT login,password,firstname,lastame,role_id FROM user WHERE user.id=?");
            preparedStatement.setInt(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user.setId(position);
                user.setLogin(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setFistName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setAdress(new AdressDAOImp().read(position));
                user.setRole(new RoleDAOImp().read(resultSet.getInt(5)));
                user.setMusicTypes(getUserMusicType(user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User read(String login, String password){
        PreparedStatement preparedStatement=null;
        User user=null;
        try {
            preparedStatement= ConnectionHolder.getConnection().prepareStatement("SELECT *FROM user WHERE login=? AND password=? ");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFistName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setRole(new RoleDAOImp().read(resultSet.getInt(6)));
                user.setAdress(new AdressDAOImp().read(resultSet.getInt(1)));
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User[] readAll() {
        PreparedStatement preparedStatement=null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT *FROM user",PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(read(resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User[] rez=new User[users.size()];
        for(int i=0; i<users.size(); i++){
            rez[i]=users.get(i);
        }
        return rez;
    }

    @Override
    public User update(User user) {
       new  AdressDAOImp().update(user.getAdress(), user.getId());
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("UPDATE user SET login=?, password=?, firstname=?, lastame=?, role_id=? WHERE user.id=?");
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFistName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setInt(5,user.getRole().getRoleId());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.executeUpdate();
            updateUserMusicType(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(int position) {
        delete(read(position));
    }

    @Override
    public User update(int position) {
       return update(read(position));
    }

    @Override
    public void delete(User user) {
        PreparedStatement preparedStatement;
        deleteUserMusicType(user);
        new AdressDAOImp().delete(user.getId());
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("DELETE FROM user WHERE id=?");
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createUserMusicType(User user, Set<MusicType> musicTypes){
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO user_music_type VALUES (?,?)");
            for(MusicType m:musicTypes){
                preparedStatement.setLong(1,user.getId());
                preparedStatement.setLong(2, m.getTypeId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Set<MusicType> getUserMusicType(User user){
        PreparedStatement preparedStatement=null;
        Set<MusicType> musicTypes=new HashSet<>();
        MusicType musicType=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT music_type_id FROM user_music_type WHERE user_id=?");
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                musicType = new MusicTypeDAOImp().read(resultSet.getInt(1));
                musicTypes.add(musicType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicTypes;
    }

    static void updateUserMusicType(User user){
        deleteUserMusicType(user);
        createUserMusicType(user,user.getMusicTypes());
    }

    static void deleteUserMusicType(User user){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("DELETE FROM user_music_type WHERE user_id=?");
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
