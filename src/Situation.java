public class Situation {
    protected int id;
    protected String name;
    protected Choice rightChoice;
    protected Choice leftChoice;

    public Situation(int id, String name, Choice rightChoice, Choice leftChoice) {
        this.id = id;
        this.name = name;
        this.rightChoice = rightChoice;
        this.leftChoice = leftChoice;
    }

    @Override
    public String toString() {
        return "Situation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rightChoice=" + rightChoice +
                ", leftChoice=" + leftChoice +
                '}';
    }

    public Situation chooseLeft(Game game){
        game.health += leftChoice.healthCost;
        game.money += leftChoice.moneyCost;
        return leftChoice.nextSituation;
    }

    public Situation chooseRight(Game game){
        game.health += rightChoice.healthCost;
        game.money += rightChoice.moneyCost;
        return rightChoice.nextSituation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
