package src.AssetManager.Sound;

public enum SoundEffectEnum {
    PADDLE_HIT("Game/Assets/arcade-fx-288597.wav"),
    GOAL("./Game/Assets/arcade-fx-288597.wav"),
    POWER_UP_SELECT("./Game/Assets/arcade-fx-288597.wav"),
    BRICK_HIT("./Game/Assets/arcade-fx-288597.wav");

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    SoundEffectEnum(String path) {
        setPath(path);
    }
}
