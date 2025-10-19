package src.Enum;

import java.awt.*;

public enum RarityEnum {
    COMMON(60, Color.white),
    RARE(30, Color.blue),
    LEGENDARY(8.5f, Color.yellow),
    INSANE(1.5f, Color.red);

    private float weight;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void increaseWeight(double weight) {
        this.weight += weight;
    }
    RarityEnum(float weight, Color color) {
        setWeight(weight);
    }
}