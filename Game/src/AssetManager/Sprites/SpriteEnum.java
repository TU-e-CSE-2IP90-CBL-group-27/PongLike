package src.AssetManager.Sprites;

public enum SpriteEnum {
    SPEED_KATANA("./Game/Assets/Sprites/speed-katana.png"),
    ATTACK_AXE("./Game/Assets/Sprites/attack-axe.png"),
    LARGE_BRICK("./Game/Assets/Sprites/large-brick.png"),
    FIRE_WALL("./Game/Assets/Sprites/fire-wall.png"),
    COLUMN("./Game/Assets/Sprites/column.png"),
    BALL_BOMB("./Game/Assets/Sprites/ball-bomb.png"),
    OPEN("./Game/Assets/Sprites/open.png"),
    WALLS("./Game/Assets/Sprites/walls.png"),
    SKULL("./Game/Assets/Sprites/rskull.png"),
    RED("./Game/Assets/Sprites/red.png");

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    SpriteEnum(String path) {
        setPath(path);
    }
}
