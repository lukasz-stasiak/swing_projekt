public class Klient {

    private int id_klient;
    private String imie, nazwisko;

    public Klient(int id_klient, String imie, String nazwisko) {
        this.id_klient = id_klient;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getId_klient() {
        return id_klient;
    }

    public void setId_klient(int id_klient) {
        this.id_klient = id_klient;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
