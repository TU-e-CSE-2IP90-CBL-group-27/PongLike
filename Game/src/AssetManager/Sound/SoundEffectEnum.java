package src.AssetManager.Sound;

public enum SoundEffectEnum {
    PADDLE_HIT("Game/Assets/paddle-hit.wav"),
    GOAL("Game/Assets/goal.wav"),
    POWER_UP_SELECT("./Game/Assets/power-up.wav"),
    BRICK_HIT("./Game/Assets/brick-hit.wav");

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
