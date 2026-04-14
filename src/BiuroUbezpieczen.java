import java.util.ArrayList;
public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;
    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }
    public void dodajPolise(Polisa polisa) {
        polisy.add(polisa);
    }
    public void wypiszRaport() {
        System.out.println("=== RAPORT BIURA: " + nazwa + " ===");
        for (Polisa polisa : polisy) {
            System.out.println(polisa);
        }
        System.out.println("===============================");
    }
    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa polisa : polisy) {
            suma += polisa.obliczSkladkeKoncowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }
    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa polisa : polisy) {
            suma += polisa.obliczSkladkeOdnowieniowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }
    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;
        for (Polisa polisa : polisy) {
            if (polisa.getPoziomRyzyka() >= 5) {
                licznik++;
            }
        }
        return licznik;
    }
    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa polisa : polisy) {
            if (polisa.getNumerPolisy().equals(numerPolisy)) {
                return polisa;
            }
        }
        return null;
    }
    public void wypiszTanszeNiz(double prog) {
        System.out.println("Polisy tansze niz " + prog + ":");
        for (Polisa polisa : polisy) {
            if (polisa.obliczSkladkeKoncowa() < prog) {
                System.out.println(polisa);
            }
        }
    }
}