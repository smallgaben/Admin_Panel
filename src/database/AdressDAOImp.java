package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdressDAOImp implements AdressDAO {
    @Override
    public Adress create(Adress adress, int id) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO adress VALUES(?,?,?,?) ");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,adress.getCountry());
            preparedStatement.setString(3,adress.getStreet());
            preparedStatement.setLong(4, adress.getIndex());
            preparedStatement.executeUpdate();
            adress.setAdressID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adress;
    }

    @Override
    public Adress read(int position) {
        PreparedStatement preparedStatement =null;
        Adress adress=null;
        try {
            preparedStatement = ConnectionHolder.getConnection().prepareStatement("SELECT country,street,zip FROM adress WHERE adress.id IS ?");
            preparedStatement.setInt(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            adress=new Adress();
            if(resultSet.next()){
                adress.setAdressID(position);
                adress.setCountry(resultSet.getString(1));
                adress.setStreet(resultSet.getString(2));
                adress.setIndex(resultSet.getLong(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adress;
    }

    @Override
    public Adress[] readAll() {
        PreparedStatement preparedStatement=null;
        List<Adress> list=new ArrayList<>();
        Adress adress=null;
        Adress[] adresses=null;

        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("SELECT *FROM adress");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                adress = new Adress();
                adress.setAdressID(resultSet.getInt(1));
                adress.setCountry(resultSet.getString(2));
                adress.setStreet(resultSet.getString(3));
                adress.setIndex(resultSet.getLong(4));
                list.add(adress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adresses=new Adress[list.size()];
        for(int i=0; i<adresses.length; i++){
            adresses[i]=list.get(i);
        }
        return adresses;
    }

    @Override
    public Adress update(Adress adress, int id) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("UPDATE adress SET id=?, country=?, street=?, zip=? WHERE adress.id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, adress.getCountry());
            preparedStatement.setString(3, adress.getStreet());
            preparedStatement.setLong(4, adress.getIndex());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adress;
    }

    @Override
    public int delete(int id){
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=ConnectionHolder.getConnection().prepareStatement("DELETE FROM adress WHERE adress.id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
