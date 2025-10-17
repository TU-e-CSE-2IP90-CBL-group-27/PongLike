package src.Enum;

public enum RarityEnum {
    COMMON(60),
    RARE(30 ),
    LEGENDARY(8.5f),
    INSANE(1.5f);

    private float weight;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void increaseWeight(double weight) {
        this.weight += weight;
    }
    RarityEnum(float weight) {
        setWeight(weight);
    }
}