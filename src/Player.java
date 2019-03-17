public class Player {
    private String Pseudo;
    private int Age;
    private String Sexe;
    private double ArgentDep;
    private double ArgentFin;
    private double VieDep;
    private double VieFin;



    public Player(String pseudo, int age, String sexe,) {
        Pseudo = pseudo;
        Age = age;
        Sexe = sexe;
    }

    public void setArgentDep(double argentDep) {
        ArgentDep = argentDep;
    }

    public void setVieDep(double vieDep) {
        VieDep = vieDep;
    }

    public void setArgentFin(double argentFin) {
        ArgentFin = argentFin;
    }

    public void setVieFin(double vieFin) {
        VieFin = vieFin;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public int getAge() {
        return Age;
    }

    public String getSexe() {
        return Sexe;
    }

    public double getArgentDep() {
        return ArgentDep;
    }

    public double getArgentFin() {
        return ArgentFin;
    }

    public double getVieDep() {
        return VieDep;
    }

    public double getVieFin() {
        return VieFin;
    }
}
