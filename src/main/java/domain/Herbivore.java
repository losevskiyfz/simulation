package domain;

public class Herbivore extends Creature {
    @Override
    public String view() {
        return "🐰";
    }

    public void reduceHealth(int damage) {
        hp -= damage;
    }
}
