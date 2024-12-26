/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
 * Github link:  https://github.com/kireiiiiiiii/ShootingStars
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

package kireiiiiiiii.shooting_stars.common;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import kireiiiiiiii.shooting_stars.constants.Logs;

/**
 * Constants class with {@code URL} links referenced in the project.
 * 
 */
public class Links {

    /////////////////
    // Constants
    ////////////////

    public static String INSTAGRAM = "https://www.instagram.com/_kireiiiiiiii";
    public static String GITHUB = "https://github.com/kireiiiiiiii/ShootingStars";

    /////////////////
    // Methods
    ////////////////

    /**
     * Opens an {@code URL} link in the browser.
     * 
     * @param url - target link.
     */
    public static void openURL(String url) {
        try {
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            } else {
                Logs.log("ERROR - Internet browsing not supported!");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
