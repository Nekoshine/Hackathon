public class Choice {
    protected Situation nextSituation;
    protected double moneyCost;
    protected double healthCost;

    public Choice(Situation nextSituation, int moneyCost, int healthCost) {
        this.nextSituation = nextSituation;
        this.moneyCost = moneyCost;
        this.healthCost = healthCost;
    }
}
