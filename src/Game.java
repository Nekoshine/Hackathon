public class Game {
    protected Situation currentSituation;
    protected double money;
    protected double health;
    protected int turn;

    public Game() {
        turn = 1;
        money = 0.5;
        health = 0.5;
    }

    public void chooseLeft(){
        currentSituation = currentSituation.chooseLeft(this);
        turn++;
    }

    public void chooseRight(){
        currentSituation = currentSituation.chooseRight(this);
        turn++;
    }

}
