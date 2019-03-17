public class Game {
    protected Question currentSituation;
    protected int money;
    protected int health;
    protected int turn;
    private Player player;

    public Game(Player nplayer) {
        money = 50;
        health = 50;
        turn=1;
        player = nplayer;
        player.setArgentDep(money);
        player.setVieDep(health);
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
                player.setVieFin(health);
                player.setArgentFin(money);
                Database.insertPlayer(player);
            }else {
                currentSituation = Database.getSituation(Database.getRandom(turn), turn);
            }
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }
    }
}
