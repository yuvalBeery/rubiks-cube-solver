package exceptions;

/**
 * exception that occurs when there is not 8 of each color on the cube that was scanned.
 */
public class IncorrectAmountOfColorsException extends Exception{
    public IncorrectAmountOfColorsException(int[] colorsCounter) {
        super("White: " + colorsCounter[0] + ", Blue: " + colorsCounter[1] + ", Red: " + colorsCounter[2] + ", Green: " + colorsCounter[3] + ", Orange: " + colorsCounter[4] + "Yellow: " + colorsCounter[5]);
    }
}

