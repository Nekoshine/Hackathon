public class Story {
    private int id;
    private String name;
    private String listeSituation ;
    private int[] listeQuestion;
    
    public Story(String name,String listeSituation){
    	this.name=name;
    	this.listeSituation=listeSituation;
    	this.setListeQuestion(listeSituation);
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

    public String getListeSituation() {
        return listeSituation;
    }

    public void setListeSituationString(int[] listeSituationInt) {
    	String liste = new String();
    	for(int i=0;i<listeSituationInt.length;i++) {
    		liste = listeSituationInt[i]+" ";
    	}
    	//System.out.println(liste);
    	this.listeSituation=liste;
    }

    public int[] getListeSituationInt(String listeSituationInt) {
    	String[] cuted = listeSituationInt.split(",");
    	int[] listeSituation = new int[cuted.length];
    	for(int i=0;i<listeSituation.length;i++) {
    		listeSituation[i] = Integer.parseInt(cuted[i]);
    	}
    	return listeSituation;
    }

    public void setListeQuestion(String liste){
        this.listeQuestion = getListeSituationInt(liste);
    }

    public int getNumQuestion(int i){
        return this.listeQuestion[i-1];
    }
    // int[] coucou = story.getListeSituationInt(story.getListeSituation));
}
