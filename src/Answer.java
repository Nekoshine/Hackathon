public class Answer{

	private int answerNumber;
	private int playerNumber;
	private int questionNumber;
	private int health;
	private int money;
	private int status;

	public Answer(int answerNumber,int questionNumber,int playerNumber,int health,int money,int status){
		this.answerNumber=answerNumber;
		this.playerNumber=playerNumber;
		this.questionNumber=questionNumber;
		this.health=health;
		this.money=money;
		this.status=status;
	}
	public int getAnswerNumber() {
		return answerNumber;
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
	public int getStatus() {
		return status;
	}
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
