package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MusicTypeDAOImp implements MusicTypeDAO {
    @Override
    public MusicType create(MusicType music) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement =ConnectionHolder.getConnection().prepareStatement("INSERT INTO music_type(type_name) VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, music.getTypeName());
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                music.setTypeId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return music;
    }

    @Override
    public MusicType read(int position) {
        PreparedStatement preparedStatement =null;
        MusicType musicType=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT type_name FROM music_type WHERE music_type.id = ?");
            preparedStatement.setInt(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            musicType = new MusicType();
            if(resultSet.next()){
                musicType.setTypeId(position);
                musicType.setTypeName(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }

    @Override
    public MusicType[] readAll() {
        PreparedStatement preparedStatement=null;
        List<MusicType> list=new ArrayList<>();
        MusicType musicType=null;
        MusicType[] musicTypes=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("SELECT *FROM music_type");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                musicType=new MusicType();
                musicType.setTypeId(resultSet.getInt(1));
                musicType.setTypeName(resultSet.getString(2));
                list.add(musicType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        musicTypes=new MusicType[list.size()];
        for(int i=0; i<musicTypes.length; i++){
            musicTypes[i]=list.get(i);
        }
        return musicTypes;
    }

    @Override
    public MusicType update(MusicType music) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("UPDATE music_type SET type_name=? WHERE music_type.id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,music.getTypeName());
            preparedStatement.setLong(2, music.getTypeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return music;
    }

    @Override
    public int delete(int id) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("DELETE FROM music_type WHERE music_type.id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
