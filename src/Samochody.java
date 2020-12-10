public class Samochody {

    private int id_samochody;
    private String nazwa, taknie;


    public Samochody(int id_samochody, String nazwa, String taknie) {
        this.id_samochody = id_samochody;
        this.nazwa = nazwa;
        this.taknie = taknie;
    }

    public int getId_samochody() {
        return id_samochody;
    }

    public void setId_samochody(int id_samochody) {
        this.id_samochody = id_samochody;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTaknie() {
        return taknie;
    }

    public void setTaknie(String taknie) {
        this.taknie = taknie;
    }
}


