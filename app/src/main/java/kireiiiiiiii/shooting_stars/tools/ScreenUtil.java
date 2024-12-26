/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
 * Github link: https://github.com/kireiiiiiiii/ShootingStars
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package kireiiiiiiii.shooting_stars.tools;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Class to get information from the user screen.
 * 
 */
public class ScreenUtil {

    /**
     * Calculates the desired size of the window depening on the dimensions of the
     * user screen.
     * 
     * @param dimentions - user's screen dimensions.
     * @return {@code double} position array.
     */
    public static double[] getAppWindowSize() {
        int[] screenDimentions = getScreenDimensions();
        double x = screenDimentions[0] * 0.9;
        double y = screenDimentions[1] * 0.9;
        double[] dimensions = { x, y };
        return dimensions;
    }

    /**
     * Gets the screen dimensions of the user's screen using {@code Toolkit}.
     * 
     * @return new {@code Position} object of the dimensions.
     */
    public static int[] getScreenDimensions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int[] dimensions = { screenWidth, screenHeight };
        return dimensions;
    }

}
