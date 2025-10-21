package src.Enum;

import java.awt.*;

public enum RarityEnum {
    //TODO: rename weight to range
    COMMON(60, Color.white), // 0-60
    RARE(30, Color.blue), // 60-90
    LEGENDARY(8.5f, Color.yellow), // 90-98.5
    INSANE(1.5f, Color.red); // 98.5-100

    //TODO: IMMEDIATE - fix how weights work
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
        setColor(color);
    }
}