package enums;

import interfaces.Hashable;

import java.awt.*;

/**
 * Enum that represents the colors of the rubiks cube
 */
public enum CubeColor implements Hashable {
    WHITE("White",new Color(255, 255, 255, CubeColor.ALPHA_VALUE)),
    BLUE("Blue", new Color(0, 0, 255, CubeColor.ALPHA_VALUE)),
    RED("Red", new Color(255, 0, 0, CubeColor.ALPHA_VALUE)),
    GREEN("Green", new Color(0, 255, 0, CubeColor.ALPHA_VALUE)),
    ORANGE("Orange", new Color(255, 165, 0, CubeColor.ALPHA_VALUE)),
    YELLOW("Yellow", new Color(255, 255, 0, CubeColor.ALPHA_VALUE));

    /**
     * variable that sets the alpha value in the rgba color representation for each color.
     */
    private static final int ALPHA_VALUE = 100;
    /**
     * thae name of the color
     */
    private String name;
    /**
     * the Color value of each color
     */
    private Color color;

    CubeColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * @return the name of the color
     */
    public String getName() {
        return name;
    }

    /**
     * @return the color value of the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the ordinal value of a color as a char
     */
    public char charValue() {
        return (char) (this.ordinal() + 48);
    }

    @Override
    public int hash() {
        return ordinal();
    }
}

