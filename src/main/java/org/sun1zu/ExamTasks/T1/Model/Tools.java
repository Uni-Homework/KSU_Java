package org.sun1zu.ExamTasks.T1.Model;

import java.util.List;

public class Tools {
    /**
     * Console menu for a user to select an int value for given menu.
     *
     * @param menu Repeated string that will drop as hint for each input attempt
     * @param min  Minimum range for a valid input
     * @param max  Maximum range for a valid input
     * @return Selected menu item as int
     */
    public static int MenuWaitUserInput(String menu, int min, int max) {
        while (true) {
            IO.println(menu);
            var selection = IO.readln();

            if (!selection.isEmpty()) {
                int menuSelection;
                try {
                    menuSelection = Integer.parseInt(String.valueOf(selection.charAt(0)));
                } catch (NumberFormatException ex) {
                    IO.println("Invalid input!");
                    continue;
                }
                if (menuSelection < min || menuSelection > max) {
                    IO.println("Please select a valid menu item!");
                    continue;
                }
                return menuSelection;
            }
        }
    }

    /**
     * Menu for a user to select an object from list of objects.
     *
     * @param objects List of objects to select from
     * @return Selected object OR null if selected "Back"
     */
    public static Object MenuWaitUserInput(List<?> objects) {
        int min = 0;
        int max = objects.size();

        String menu = "";
        int c = 1;
        for (var ob : objects) {
            menu = menu.concat(String.format("%d. %s\n", c++, ob.toString()));
        }
        menu = menu.concat("0. Back");

        int ans = MenuWaitUserInput(menu, min, max);
        if (ans == min) return null;   // null indicates "back" selection
        return objects.get(ans - 1);
    }
}
