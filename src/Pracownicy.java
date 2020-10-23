public class Pracownicy {
    private int id_pracownicy;
    private String imie;
    private String nazwisko;
    private int wiek;
    private String plec;

    public int getId_pracownicy() {
        return id_pracownicy;
    }

    public Pracownicy(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public void setId_pracownicy(int id_pracownicy) {
        this.id_pracownicy = id_pracownicy;
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

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }
}
