package Enum;

public enum RarityEnum {
    COMMON(60),
    RARE(25),
    LEGENDARY(10),
    INSANE(5);

    private float weight;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    RarityEnum(float weight) {
        setWeight(weight);
    }
}