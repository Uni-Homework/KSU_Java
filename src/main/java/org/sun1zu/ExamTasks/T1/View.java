// TODO: add abortion on nonexistent selection (instead of 0)
package org.sun1zu.ExamTasks.T1;

import org.sun1zu.ExamTasks.T1.Model.Dictionary;

import java.io.File;
import java.io.IOException;

public class View {
    private static int MenuWaitUserInput(String menu, int min, int max) {
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

    static String menu1 = "1. Create dict\n2. Edit dict\n3. Remove dict\n0. Exit";

    static void main() {
        IO.println("Dictionaries by sun1zu\n");

        while (true) {
            var input = MenuWaitUserInput(menu1, 0, 3);
            switch (input) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Dictionary dictionary = Dictionary.InitDictionary();
                    DictMenu(dictionary, "");
                    break;
                case 2:
                    var inp = FileSelect();
                    if (inp.equals("D3ADB33F")) break;
                    try {
                        var dict = Dictionary.ParseFile(inp);
                        DictMenu(dict, inp);
                    } catch (IOException e) {
                        IO.println("File not found or is corrupted!");
                    }
                    break;

                case 3:
                    var fname = FileSelect();
                    if (fname.equals("D3ADB33F")) break;
                    var file = new File(fname);
                    if (file.delete()) {
                        IO.println("Dictionary deleted successfully!");
                    } else {
                        IO.println("Error: file does not exist");
                    }
                    break;

                default:
                    IO.println("Unknown command!");
                    break;
            }
            IO.println();
        }
    }

    static String menu2 = "1. Read all pairs\n2. Delete by key\n3. Find value by key\n4. Add value\n0. Save and exit";

    /**
     * Menu for user to modify a dictionary
     *
     * @param dict     dictionary to modify
     * @param filename leave empty not to overwrite file
     */
    static void DictMenu(Dictionary dict, String filename) {
        while (true) {
            var input = MenuWaitUserInput(menu2, 0, 4);
            switch (input) {
                case 0:
                    if (filename.compareTo("") == 0) {
                        filename = IO.readln("Enter new filename: ");
                    }

                    try {
                        dict.WriteToFile(filename);
                    } catch (IOException e) {
                        IO.println("Error when writing to file!");
                        return;
                    }

                    IO.println("File saved successfully");
                    return;
                case 1:
                    dict.PrintPairs();
                    break;
                case 2:
                    var del_inp = IO.readln("Input a key for pair to delete: ");
                    dict.DeletePairByKey(del_inp);
                    break;
                case 3:
                    var find_inp = IO.readln("Input a key for pair to output: ");
                    dict.FindValue(find_inp);
                    break;
                case 4:
                    var key_inp = IO.readln("Input a key to add pair to a dict: ");
                    var val_inp = IO.readln("Input a value to add pair to a dict: ");
                    dict.AddValue(key_inp, val_inp);
            }
            IO.println();
        }
    }

    /**
     * Looks for all dict files in program working dir and lets the user select one of them
     *
     * @return User selected filename OR "D3ADB33F" if selection is "Back"
     */
    private static String FileSelect() {
        var fnames = Dictionary.GetDictFiles();

        String menu = "";
        int c = 1;
        for (var el : fnames) {
            menu = menu.concat(String.format("%d. %s\n", c++, el));
        }
        menu = menu.concat("0. Back");

        int sel = MenuWaitUserInput(menu, 0, fnames.size());
        if (sel == 0) return "D3ADB33F";
        return fnames.get(sel - 1);
    }
}
