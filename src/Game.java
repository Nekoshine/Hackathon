public class Game {
	private Question currentSituation;
    public Question getCurrentSituation() {
		return currentSituation;
	}

	private int money;
    private int health;
    private int countEventMoney = 0;
    private int countEventHealth = 0;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    private int turn;
    private Player player;
    private Story story;

	public Game(Player nplayer, Story nstory) {
        money = 50;
        health = 50;
        turn=1;
        story = nstory;
        player = nplayer;
        player.setMoneyStrt(money);
        player.setHealthStrt(health);
        if(story.getNumQuestion(turn) == 0) {
            currentSituation = Database.getSituation(Database.getRandom(turn), turn);
        } else {
            currentSituation = Database.getSituation(story.getNumQuestion(turn), turn);
        }
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
            if (isEnded()) {
                // todo: afficher la fin puis retour au menu principal
                player.setHealthEnd(health);
                player.setMoneyEnd(money);
                Database.insertPlayer(player);
            } else {
                if ((money<10)&&(countEventMoney ==0)) {
                    currentSituation = Database.getSituation(Database.getRandom(12),12);
                    turn--;
                    countEventMoney = 1;
                } else if ((health<10)&&(countEventHealth ==0)) {
                    currentSituation = Database.getSituation(Database.getRandom(13),13);
                    turn--;
                    countEventHealth = 1;
                } else {


                }
                if(story.getNumQuestion(turn) == 0) {
                    currentSituation = Database.getSituation(Database.getRandom(turn), turn);
                } else {
                    currentSituation = Database.getSituation(story.getNumQuestion(turn), turn);
                }
            }
        } else {
            currentSituation = Database.getSituation(nextId, 11);
        }
    }

    public boolean isEnded() {
	    return turn>10;
    }
}
