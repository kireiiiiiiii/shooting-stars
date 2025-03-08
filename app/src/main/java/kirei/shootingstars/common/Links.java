/*
 * Author: Matěj Šťastný aka Kirei
 * Date created: 6/15/2024
 * Github link:  https://github.com/kireiiiiiiii/shooting-stars
 */

package kirei.shootingstars.common;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import kirei.shootingstars.constants.Logs;

public class Links {

    public static String INSTAGRAM = "https://www.instagram.com/_kireiiiiiiii";
    public static String GITHUB = "https://github.com/kireiiiiiiii/shooting-stars";

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
