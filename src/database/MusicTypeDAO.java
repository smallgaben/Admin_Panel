package database;

/**
 * Created by miroslav on 13.11.15.
 */
public interface MusicTypeDAO {
    MusicType create(MusicType  music);
    MusicType read(int position);
    MusicType[] readAll();
    MusicType update(MusicType music);
    int delete(int id);

}
