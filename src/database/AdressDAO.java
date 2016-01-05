package database;

public interface AdressDAO {

    Adress create(Adress adress, int id);
    Adress read(int position);
    Adress[] readAll();
    Adress update(Adress adress, int id);
    int delete(int id);

}
