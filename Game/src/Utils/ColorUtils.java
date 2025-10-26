package src.Utils;

import java.awt.*;
import java.util.Random;

public final class ColorUtils {
    private static final Random random = new Random();

    private ColorUtils() {}

    public static Color getRandomColor() {
        // A value from 0 to 255 for Red, Green, and Blue.
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    public static Color getRandomBrightColor() {
        final float hue = random.nextFloat();
        final float saturation = random.nextFloat() * 0.3f + 0.7f;
        final float brightness = random.nextFloat() * 0.2f + 0.8f;

        return Color.getHSBColor(hue, saturation, brightness);
    }
}