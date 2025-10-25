package src.AssetManager.Sound;

public enum SoundEffectEnum {
    PADDLE_HIT("Game/Assets/Sound/paddle-hit.wav"),
    GOAL("Game/Assets/Sound/goal.wav"),
    POWER_UP_SELECT("Game/Assets/Sound/power-up.wav"),
    BRICK_HIT("Game/Assets/Sound/brick-hit.wav");

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
