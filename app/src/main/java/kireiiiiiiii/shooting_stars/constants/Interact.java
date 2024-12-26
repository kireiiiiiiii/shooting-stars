/*
 * Author: Matěj Šťastný
 * Date created: 9/11/2024
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

import kireiiiiiiii.shooting_stars.AppMain;
import kireiiiiiiii.shooting_stars.common.Links;

/**
 * Constants class with all possbile events that can be triggered by user
 * interaction. These events are then referenced in objects with the
 * {@code Interactable} interface
 * 
 */
public class Interact {

    public static final Runnable HOME = () -> {
        AppMain.game.onGoToMenu();
    };

    public static final Runnable OPTIONS = () -> {
        AppMain.game.onGoToOptions();
    };

    public static final Runnable LINKS = () -> {
        AppMain.game.onGoToLinks();
    };

    public static final Runnable GITHUB = () -> {
        Links.openURL(Links.GITHUB);
    };

    public static final Runnable INSTAGRAM = () -> {
        Links.openURL(Links.INSTAGRAM);
    };

    public static final Runnable NEXT_LAN = () -> {
        AppMain.game.onLanguageChange(true);
    };

    public static final Runnable PREV_LAN = () -> {
        AppMain.game.onLanguageChange(false);
    };

    public static final Runnable PAUSE = () -> {
        AppMain.game.onTogglePause();
    };

    public static final Runnable DEBUG = () -> {

    };

    public static final Runnable RESTART = () -> {
        AppMain.game.onGameRestart();
    };

    public static final Runnable START = () -> {
        AppMain.game.onGameStart();
    };

    public static final Runnable TARGET_INTERACTED = () -> {
        AppMain.game.onTargetHit(false);
    };
}
