public class Question {
    private int id;
    private String question;
    private Choice rightChoice;
    private Choice leftChoice;
    private String image;
    
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
        game.setHealth(game.getHealth() + leftChoice.getHealthCost());
        game.setMoney(game.getMoney() + leftChoice.getMoneyCost());
        return leftChoice.getEnding();
    }

    public int chooseRight(Game game){
        game.setHealth(game.getHealth() + rightChoice.getHealthCost());
        game.setMoney(game.getMoney() + rightChoice.getMoneyCost());
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
