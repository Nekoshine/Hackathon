public class Player {
    private String Pseudo;
    private int Age;
    private String Sexe;
    private int ArgentDep;
    private int ArgentFin;
    private int VieDep;
    private int VieFin;



    public Player(String pseudo, int age, String sexe) {
        Pseudo = pseudo;
        Age = age;
        Sexe = sexe;
    }

    public void setArgentDep(int argentDep) {
        ArgentDep = argentDep;
    }

    public void setVieDep(int vieDep) {
        VieDep = vieDep;
    }

    public void setArgentFin(int argentFin) {
        ArgentFin = argentFin;
    }

    public void setVieFin(int vieFin) {
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

    public int getArgentDep() {
        return ArgentDep;
    }

    public int getArgentFin() {
        return ArgentFin;
    }

    public int getVieDep() {
        return VieDep;
    }

    public int getVieFin() {
        return VieFin;
    }
}
