public class QuestionTmp {
    public String texte;
    public int id;
    public int categorie;

    public QuestionTmp(String texte, int id, int categorie) {
        this.texte = texte;
        this.id = id;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return texte;
    }
}

