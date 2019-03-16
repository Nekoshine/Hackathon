public class Choice {
    private int ending;
    private int moneyCost;
    private int healthCost;
    private String text;

    public Choice(int ending, int moneyCost, int healthCost, String text) {
        this.ending = ending;
        this.moneyCost = moneyCost;
        this.healthCost = healthCost;
        this.text= text;
    }

	public int getEnding() {
		return ending;
	}

	public int getMoneyCost() {
		return moneyCost;
	}

	public int getHealthCost() {
		return healthCost;
	}

	public String getText() {
		return text;
	}
}
