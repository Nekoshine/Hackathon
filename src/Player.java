public class Player {
    private String Pseudo;
    private int Age;
    private String Sex;
    private int moneyStrt;
    private int moneyEnd;
    private int healthStrt;
    private int healthEnd;
    private int id;


    public Player(String pseudo, int age, String sex) {
        Pseudo = pseudo;
        Age = age;
        Sex = sex;
    }

    public void setId(int nid) {
    	id=nid;
    }
    public int getId() {
    	return id;
    }
    public void setMoneyStrt(int money) {
        moneyStrt = money;
    }

    public void setHealthStrt(int health) {
        healthStrt = health;
    }

    public void setMoneyEnd(int money) {
        moneyEnd = money;
    }

    public void setVieFin(int health) {
        healthEnd = health;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public int getAge() {
        return Age;
    }

    public String getSexe() {
        return Sex;
    }

    public int getMoneyStrt() {
        return moneyStrt;
    }

    public int getMoneyEnd() {
        return moneyEnd;
    }

    public int getHealthStrt() {
        return healthStrt;
    }

    public int getHealthEnd() {
        return healthEnd;
    }
}
