package database;

import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private String login,password,fistName,lastName;
    private Adress adress;
    private Role role;
    private Set<MusicType> musicTypes;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adress=" + adress +
                ", role=" + role +
                ", musicTypes=" + musicTypes +
                '}'+'\n';

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(Set<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }
}
