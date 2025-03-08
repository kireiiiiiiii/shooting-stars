/*
 * Author: Matěj Šťastný aka Kirei
 * Date created: 6/15/2024
 * Github link: https://github.com/kireiiiiiiii/shooting-stars
 */

package kirei.shootingstars.constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

public class Logs {

    // Logs ----------------------------------------------------------------------

    public static final String APP_START = "App start";

    public static final String GAME_START = "Game start";
    public static final String GAME_RESTART = "Game restart";
    public static final String GAME_OVER = "Game ended";
    public static final String GAME_PAUSE = "Game paused";
    public static final String GAME_RESUMED = "Game resumed";

    public static final String TAGRET_HIT = "Target hit";
    public static final String TAGRET_NOT_HIT = "Target not hit";

    public static final String TOPSCORE_FILE_LOAD = "\"" + Files.TOP_SCORE_FILENAME + "\" loaded";
    public static final String TOPSCORE_FILE_SAVED = "\"" + Files.TOP_SCORE_FILENAME + "\" saved";

    public static final String TIMER_INTEARION = "Timer executed";

    public static final String LANGUAGE_SET = "Language set to " + GameDialogue.languageName;

    // Colors --------------------------------------------------------------------

    private static final String APP_LOG_COLOR = TerminalColors.BACKGROUND_RED;
    private static final String GAME_LOG_COLOR = TerminalColors.BACKGROUND_GREEN;
    private static final String TARGET_LOG_COLOR = TerminalColors.BACKGROUND_BLUE;
    private static final String FILE_LOG_COLOR = TerminalColors.BACKGROUND_PURPLE;
    private static final String TIMER_LOG_COLOR = TerminalColors.BACKGROUND_YELLOW;
    private static final String SETTINGS_SET_COLOR = TerminalColors.BACKGROUND_WHITE;

    // Logging -------------------------------------------------------------------

    private static ArrayList<String> logs = new ArrayList<String>();

    public static void log(String logInput) {
        String logColor;

        if (logInput.equals(APP_START)) {
            logColor = APP_LOG_COLOR;
        } else if (logInput.equals(GAME_START) || logInput.equals(GAME_RESTART) || logInput.equals(GAME_OVER) || logInput.equals(GAME_PAUSE) || logInput.equals(GAME_RESUMED)) {
            logColor = GAME_LOG_COLOR;
        } else if (logInput.equals(TAGRET_HIT) || logInput.equals(TAGRET_NOT_HIT)) {
            logColor = TARGET_LOG_COLOR;
        } else if (logInput.equals(TOPSCORE_FILE_LOAD) || logInput.equals(TOPSCORE_FILE_SAVED)) {
            logColor = FILE_LOG_COLOR;
        } else if (logInput.equals(TIMER_INTEARION)) {
            logColor = TIMER_LOG_COLOR;
        } else if (logInput.equals(LANGUAGE_SET)) {
            logColor = SETTINGS_SET_COLOR;
        } else {
            logColor = "";
        }

        String log = LocalTime.now().toString() + " | " + logInput;
        logs.add(log);
        System.out.println(logColor + log + TerminalColors.RESET);
        try {
            listToFile(logs, Files.LOG_FILE);
        } catch (IOException e) {
            System.out.println("FATAL - could not save log file");
        }
    }

    // Log file ------------------------------------------------------------------

    /**
     * Wrties the inside of an ArrayList into a file, fully clearing the file
     * beforehand. Puts every element of the list on a separate line
     *
     * @param list - ArrayList of Strings
     * @param file - target file
     * @throws IOException FileWriter exception
     */
    public static void listToFile(ArrayList<String> list, File file) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
        fw = new FileWriter(file, true);
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i));
            if (i != list.size() - 1) {
                fw.write("\n");
            }
        }
        fw.close();
    }

}
