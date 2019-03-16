public class Choice {
    protected Situation starting;
    protected Situation ending;
    protected double moneyCost;
    protected double healthCost;

    public Choice(Situation starting, Situation ending, int moneyCost, int healthCost) {
        this.starting = starting;
        this.ending = ending;
        this.moneyCost = moneyCost;
        this.healthCost = healthCost;
    }
}
