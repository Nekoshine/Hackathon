public class Question {
    protected int id;
    protected String question;
    protected Choice rightChoice;
    protected Choice leftChoice;
    protected String image;
    
    public Question(int id, String question, Choice rightChoice, Choice leftChoice,String image) {
        this.id = id;
        this.question = question;
        this.rightChoice = rightChoice;
        this.leftChoice = leftChoice;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", rightChoice=" + rightChoice +
                ", leftChoice=" + leftChoice +
                '}';
    }

    public int chooseLeft(Game game){
        game.health += leftChoice.getHealthCost();
        game.money += leftChoice.getMoneyCost();
        return leftChoice.getEnding();
    }

    public int chooseRight(Game game){
        game.health += rightChoice.getHealthCost();
        game.money += rightChoice.getMoneyCost();
        return rightChoice.getEnding();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setName(String question) {
        this.question = question;
    }

    public Choice getRightChoice() {
        return rightChoice;
    }

    public void setRightChoice(Choice rightChoice) {
        this.rightChoice = rightChoice;
    }

    public Choice getLeftChoice() {
        return leftChoice;
    }

    public void setLeftChoice(Choice leftChoice) {
        this.leftChoice = leftChoice;
    }
}
