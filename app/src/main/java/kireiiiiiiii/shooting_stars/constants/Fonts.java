/*
 * Author: Matěj Šťastný
 * Date created: 5/16/2024
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
 *
 */

package kireiiiiiiii.shooting_stars.constants;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Constants class with fonts, also handeling their loading.
 * 
 */
public class Fonts {

    /////////////////
    // Constants
    ////////////////

    public static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);

    /////////////////
    // Variables
    ////////////////

    public static Font headingFont = DEFAULT_FONT;
    public static Font textFont = DEFAULT_FONT;

    /////////////////
    // Setter
    ////////////////

    /**
     * Sets the font from it's name using the {@code InputStream} object.
     * 
     * @param fontType - type of the font. Will be formated, so the case doesn't
     *                 matter.
     * @return loaded {@code Font} object or the default font.
     */
    private static Font setFont(String fontType) {
        if (fontType.equals("defaultFont")) {
            return DEFAULT_FONT;
        }
        InputStream fontStream = getFontInputStream("/fonts/" + fontType);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            return DEFAULT_FONT;
        }
    }

    /////////////////
    // Accesors
    ////////////////

    public static Font heading() {
        return headingFont;
    }

    public static Font text() {
        return textFont;
    }

    /////////////////
    // Modifiers
    ////////////////

    public static void setFonts() {
        setHeadingFont(GameDialogue.headingFont);
        setTextFont(GameDialogue.textFont);
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Get's the {@code InputStream} of a font.
     * 
     * @param fontName - file name of the font.
     * @return an {@code InputStream} object.
     */
    private static InputStream getFontInputStream(String fontName) {
        InputStream resource = Fonts.class.getResourceAsStream(fontName);
        if (resource == null) {
            System.out.println("Input stream is null for font: " + fontName);
        }
        return resource;
    }

    private static void setHeadingFont(String fileName) {
        headingFont = setFont(fileName);
    }

    private static void setTextFont(String fileName) {
        textFont = setFont(fileName);
    }
}
