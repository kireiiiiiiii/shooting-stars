/*
 * Author: Matěj Šťastný
 * Date created: 6/14/2024
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

package kireiiiiiiii.shooting_stars.ui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Container;
import java.util.ArrayList;

import kireiiiiiiii.shooting_stars.constants.Colors;
import kireiiiiiiii.shooting_stars.constants.Fonts;
import kireiiiiiiii.shooting_stars.constants.GameDialogue;
import kireiiiiiiii.shooting_stars.constants.WidgetTags;
import kireiiiiiiii.shooting_stars.constants.ZIndexes;
import kireiiiiiiii.shooting_stars.interfaces.Renderable;
import kireiiiiiiii.shooting_stars.tools.FontUtil;

/**
 * Pause screen {@code Renderable}.
 * 
 */
public class PauseScreen implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color TEXT_COLOR = Colors.MAIN_TEXT;

    /////////////////
    // Variables
    ////////////////

    private int[] size;
    private boolean visible;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default contructor.
     * 
     * @param panelSize - determines the size of the screen, commonly same as the
     *                  owning {@code JPanel}.
     */
    public PauseScreen(int[] panelSize) {
        this.size = panelSize;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {

        if (!visible) {
            return;
        }

        FontMetrics fm;
        int[] textPos;
        int x;
        int y;
        String text = GameDialogue.pause;

        g.setColor(TEXT_COLOR);
        g.setFont(Fonts.heading().deriveFont(Font.PLAIN, 80));
        fm = g.getFontMetrics();
        textPos = FontUtil.getCenteredPos(size[0], size[1], fm, text);
        x = textPos[0];
        y = textPos[1];
        g.drawString(text, x, y);
    }

    @Override
    public int getZIndex() {
        return ZIndexes.SCREENS;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void hide() {
        this.visible = false;
    }

    @Override
    public void show() {
        this.visible = true;
    }

    @Override
    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(WidgetTags.PAUSE);
        return tags;
    }

}
