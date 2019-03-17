public class Answer{

	private int playerNumber;
	private int questionNumber;
	private int health;
	private int money;

	public Answer(int questionNumber,int playerNumber,int health,int money){
		this.playerNumber=playerNumber;
		this.questionNumber=questionNumber;
		this.health=health;
		this.money=money;
	}
	public int getPlayerNumber() {
		return playerNumber;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public int getHealth() {
		return health;
	}
	public int getMoney() {
		return money;
	}
}
