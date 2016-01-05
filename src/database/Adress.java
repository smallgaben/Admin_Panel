package database;

public class Adress {

    private int adressID;
    private String country;
    private String street;
    private long index;

    public Adress() {
    }

    public Adress( String country, String street, long index) {
        this.country = country;
        this.street = street;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "adressID=" + adressID +
                ", country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", index=" + index +
                '}';
    }

    public int getAdressID() {
        return adressID;
    }

    public void setAdressID(int adressID) {
        this.adressID = adressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }
}
