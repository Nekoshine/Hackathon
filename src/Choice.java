public class Choice {
    protected int ending;
    protected double moneyCost;
    protected double healthCost;

    public Choice(int ending, int moneyCost, int healthCost) {
        this.ending = ending;
        this.moneyCost = moneyCost;
        this.healthCost = healthCost;
    }
}
