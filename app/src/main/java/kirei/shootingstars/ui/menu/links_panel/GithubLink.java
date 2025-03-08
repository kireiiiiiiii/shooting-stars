/*
 * Author: Matěj Šťastný
 * Date created: 6/16/2024
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

package kirei.shootingstars.ui.menu.links_panel;

import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import kirei.shootingstars.constants.Interact;
import kirei.shootingstars.constants.Textures;
import kirei.shootingstars.constants.WidgetTags;
import kirei.shootingstars.constants.ZIndexes;
import kirei.shootingstars.interfaces.Interactable;
import kirei.shootingstars.interfaces.Renderable;
import kirei.shootingstars.tools.ImageUtil;

/**
 * Button, that openes the GitHub repository in the browser.
 *
 */
public class GithubLink implements Renderable, Interactable {

    /////////////////
    // Constants
    ////////////////

    private final int[] SIZE = { 90, 90 };

    /////////////////
    // Variables
    ////////////////

    private int[] position;
    private boolean isVisible;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     *
     * @param owner    - owning {@code JPanel} object.
     * @param position - position.
     */
    public GithubLink(int[] position) {
        this.position = position;
        this.position[0] = this.position[0] + SIZE[0] / 2;
        this.position[1] = this.position[1] + SIZE[1] / 2;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {
        if (!this.isVisible) {
            return;
        }

        g.drawImage(ImageUtil.scaleImage(Textures.GITHUB_LOGO, SIZE[0], SIZE[1]), position[0], position[1], img);
    }

    @Override
    public int getZIndex() {
        return ZIndexes.POPUP_PANEL_BUTTONS;
    }

    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @Override
    public void hide() {
        this.isVisible = false;
    }

    @Override
    public void show() {
        this.isVisible = true;
    }

    @Override
    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(WidgetTags.LINKS);
        return tags;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public Runnable getInteraction() {
        return Interact.GITHUB;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}

// local lualine_nightfly = require("lualine.themes.nightfly")
// local new_colors = {
// blue = "#65D1FF",
// green = "#3EFFDC",
// violet = "#FF61EF",
// yellow = "#FFDA7B",
// black = "#000000",
// }

// lualine_nightfly.normal.a.bg = new_colors.blue
// lualine_nightfly.insert.a.bg = new_colors.green
// lualine_nightfly.visual.a.bg = new_colors.violet
// lualine_nightfly.command = {
// a = {
// gui = "bold",
// bg = new_colors.yellow,
// fg = new_colors.black,
// },
// }
