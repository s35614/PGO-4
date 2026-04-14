public class Polisa {

    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka,

                  double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {

        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;

        liczbaUtworzonychPolis++;

    }

    public String getNumerPolisy() {
        return numerPolisy;
    }
    public int getPoziomRyzyka() {
        return poziomRyzyka;

    }
    public double obliczSkladkeKoncowa() {
        double skladka = skladkaBazowa;
        skladka += poziomRyzyka * 120;
        if (wartoscPojazdu > 60000) {

            skladka += 150;

        }
        if (czyMaAlarm) {
            skladka *= 0.9;
        }
        if (czyBezszkodowyKlient) {
            skladka *= 0.85;
        }

        skladka += OPLATA_ADMINISTRACYJNA;
        if (skladka < skladkaBazowa) {
            skladka = skladkaBazowa;

        }

        return Math.round(skladka * 100.0) / 100.0;

    }

    public double obliczSkladkeOdnowieniowa() {
        double skladka = obliczSkladkeKoncowa();
        if (poziomRyzyka == 4) {
            skladka *= 1.10;
        } else if (poziomRyzyka >= 5) {
            skladka *= 1.20;
        }
        if (wartoscPojazdu > 60000) {
            skladka += 150;
        }

        if (czyBezszkodowyKlient) {
            skladka *= 0.92;
        }
        if (czyMaAlarm) {
            skladka *= 0.95;
        }

        double minProg = obliczSkladkeKoncowa() * 0.90;
        double maxProg = obliczSkladkeKoncowa() * 1.25;

        if (skladka < minProg) {
            skladka = minProg;

        }
        if (skladka > maxProg) {
            skladka = maxProg;
        }
        return Math.round(skladka * 100.0) / 100.0;

    }

    public String pobierzPodsumowanieRyzyka() {
        if (poziomRyzyka <= 2) {
            return "Niskie ryzyko";

        } else if (poziomRyzyka <= 4) {
            return "Srednie ryzyko";
        } else {
            return "Wysokie ryzyko";
        }

    }

    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonychPolis;

    }

    @Override
    public String toString() {
        return "Polisa{" +
                "numerPolisy='" + numerPolisy + '\'' +
                ", klient='" + klient + '\'' +
                ", skladkaBazowa=" + skladkaBazowa +
                ", poziomRyzyka=" + poziomRyzyka +
                ", wartoscPojazdu=" + wartoscPojazdu +
                ", czyMaAlarm=" + czyMaAlarm +
                ", czyBezszkodowyKlient=" + czyBezszkodowyKlient +
                ", skladkaKoncowa=" + obliczSkladkeKoncowa() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polisa polisa = (Polisa) o;
        return numerPolisy.equals(polisa.numerPolisy);
    }
}
