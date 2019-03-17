public class Game {
	private Question currentSituation;
    public Question getCurrentSituation() {
		return currentSituation;
	}

	private int money;
    private int health;
    private int turn;
    private Player player;

	public Game(Player nplayer) {
        money = 50;
        health = 50;
        turn=1;
        player = nplayer;
        player.setMoneyStrt(money);
        player.setHealthStrt(health);
        currentSituation = Database.getSituation(Database.getRandom(turn),turn);
    }

    public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
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
                player.setHealthEnd(health);
                player.setMoneyEnd(money);
                Database.insertPlayer(player);
            }else {
                currentSituation = Database.getSituation(Database.getRandom(turn), turn);
            }
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }
    }

    public boolean isEnded() {
	    return turn>10;
    }
}
