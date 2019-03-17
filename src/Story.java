public class Story {
    private int id;
    private String name;
    private int[] listeSituation = new int[10]; //liste de numéros de question

    public Story(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumSituation(int i) {
        return listeSituation[i-1];
    }

    public void setListeSituation(int[] listeSituation) {
        this.listeSituation = listeSituation;
    }
}
