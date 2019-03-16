public class Game {
    protected Situation currentSituation;
    protected Categorie categorie;
    protected double money;
    protected double health;
    protected int turn;

    public Game() {
        money = 0.5;
        health = 0.5;
        turn=0;
    }

    public void chooseLeft(){
        currentSituation = currentSituation.chooseLeft(this);
        if(currentSituation==null) {

        }
    }

    public void chooseRight(){
        currentSituation = currentSituation.chooseRight(this);
    }

}
