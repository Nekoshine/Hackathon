public class Situation {
    protected int id;
    protected String question;
    protected Choice rightChoice;
    protected Choice leftChoice;
    protected String image;
    
    public Situation(int id, String question, Choice rightChoice, Choice leftChoice,String image) {
        this.id = id;
        this.question = question;
        this.rightChoice = rightChoice;
        this.leftChoice = leftChoice;
        this.image = image;
        
    }

    @Override
    public String toString() {
        return "Situation{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", rightChoice=" + rightChoice +
                ", leftChoice=" + leftChoice +
                '}';
    }

    public Situation chooseLeft(Game game){
        game.health += leftChoice.healthCost;
        game.money += leftChoice.moneyCost;
        return leftChoice.ending;
    }

    public Situation chooseRight(Game game){
        game.health += rightChoice.healthCost;
        game.money += rightChoice.moneyCost;
        return rightChoice.ending;
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
