public class Wypozyczenia {
    String imie, nazwisko, nazwa;
    int dlugosc_wypozyczenie;

    public Wypozyczenia(String imie, String nazwisko, String nazwa, int dlugosc_wypozyczenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwa = nazwa;
        this.dlugosc_wypozyczenie = dlugosc_wypozyczenie;
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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getDlugosc_wypozyczenie() {
        return dlugosc_wypozyczenie;
    }

    public void setDlugosc_wypozyczenie(int dlugosc_wypozyczenie) {
        this.dlugosc_wypozyczenie = dlugosc_wypozyczenie;
    }
}
