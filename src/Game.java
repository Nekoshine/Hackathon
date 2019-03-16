public class Game {
    private Question currentSituation;
    private double money;
    private double health;
    private int turn;

    public void setMoney(double money) {
        this.money = money;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMoney() {
        return money;
    }

    public double getHealth() {
        return health;
    }

    public Game() {
        money = 50;
        health = 50;
        turn=1;
        currentSituation = Database.getSituation(Database.getRandom(turn),turn);
    }

    public void chooseLeft(){
        nextSituation(currentSituation.chooseLeft(this));
    }

    public void chooseRight(){
        nextSituation(currentSituation.chooseRight(this));
    }

    private void nextSituation(int nextId) {
        if(nextId==0) {
            turn++;
            if(turn>10) {
                // todo: afficher la fin puis retour au menu principal
                System.out.println(String.format("money : %f ; health : %f",money,health));
            }
            currentSituation = Database.getSituation(Database.getRandom(turn),turn);
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }
    }
}
