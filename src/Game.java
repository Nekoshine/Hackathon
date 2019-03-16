public class Game {
    protected Situation currentSituation;
    protected Categorie categorie;
    protected double money;
    protected double health;
    protected int turn;

    public Game() {
        money = 50;
        health = 50;
        turn=1;
        currentSituation = Database.getSituation(Database.getRandom(turn),turn);
    }

    public void chooseLeft(){
        int nextId = currentSituation.chooseLeft(this);
        if(nextId==0) {
            turn++;
            currentSituation = Database.getSituation(Database.getRandom(turn),turn);
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }
    }

    public void chooseRight(){
        int nextId = currentSituation.chooseRight(this);
        if(nextId==0) {
            turn++;
            if(turn>10) {
                // todo: afficher la fin

            }
            currentSituation = Database.getSituation(Database.getRandom(turn),turn);
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }

    }
}
