package org.sun1zu.ExamTasks.T1.Model;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
    public static final String file_ext = ".dict.txt";

    private final List<String> keys;
    private final List<String> values;
    private final DictTypes type;

    public Dictionary(DictTypes dictionaryType) {
        type = dictionaryType;

        keys = new LinkedList<>();
        values = new LinkedList<>();

//        IO.println("Created new dict of language " + GetLanguage());
    }

    /**
     * Use this method to init a dictionary (no language given)
     *
     * @return An empty dictionary with a language set by user
     */
    public static Dictionary InitDictionary() throws IndexOutOfBoundsException {
        IO.println("Select dictionary language: ");

        for (int i = 0; i < DictTypes.values().length; i++) {
            IO.println(String.format("%d. %s", i + 1, DictTypes.values()[i].getName()));
        }

        String sel;
        int isel = -1;
        while (true) {
            sel = IO.readln();
            isel = Integer.parseInt(sel);

            if (isel < 0 && isel > DictTypes.values().length) {
                IO.println("Selection invalid");
            } else break;
        }

        return new Dictionary(DictTypes.values()[isel - 1]);
    }

    /**
     * Creates a new dict based on JSON file contents.
     *
     * @param filename Name of the file to read data from
     * @throws IOException File is corrupted
     */
    public static Dictionary ParseFile(String filename) throws IOException {
        // IO.println("Parsing file " + filename);

        var fr = new FileReader(filename);
        String data = fr.readAllAsString();

        Object obj = JSONValue.parse(data);
        JSONObject jsonObject = (JSONObject) obj;

        // Extract language
        var type = DictTypes.valueOf((String) jsonObject.get("DictLang"));
        var dict = new Dictionary(type);
        jsonObject.remove("DictLang");

        // Extract data
        for (Object o : jsonObject.keySet()) {
            var key = (String) o;
            dict.AddValue(key, (String) jsonObject.get(key), false);
        }
        IO.println("Done! Found " + dict.keys.size() + " elements");
        IO.println("Language of the file: " + dict.type.getName());

        jsonObject.clear();
        fr.close();

        return dict;
    }

    public static List<String> GetDictFiles() {
        var res = new LinkedList<String>();
        try {
            var files = Files.list(Paths.get(".")).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(file_ext));
            var paths = files.toList();

            for (var path : paths)
                res.add(path.getFileName().toString());

            return res;

        } catch (IOException e) {
            IO.println("Failed to get files in the working dir!");
            return res;
        }
    }

    public void WriteToFile(String filename) throws IOException {
        var jsonObject = new JSONObject();

        var new_fname = filename;

        if (!filename.contains(file_ext)) new_fname += file_ext;

        var fw = new FileWriter(new_fname);

        jsonObject.put("DictLang", type.name());

        for (int i = 0; i < keys.size(); i++) {
            jsonObject.put(keys.get(i), values.get(i));
        }
        fw.write(jsonObject.toJSONString());
        fw.close();
        jsonObject.clear();
    }

    public void PrintPairs() {
        IO.println("Printing dict contents: ");
        for (int i = 0; i < keys.size(); i++) {
            IO.println(String.format("%s: %s", keys.get(i), values.get(i)));
        }
    }

    public void FindValue(String key) {
        IO.print("Looking for value by key " + key + "... ");
        int id = FindValueID(key);
        IO.println(id == -1 ? "No value found!" : "Found value: " + values.get(id));
    }

    public void DeletePairByKey(String key) {
        IO.print("Deleting pair by key " + key + "... ");
        int id = FindValueID(key);
        if (id == -1) {
            IO.println("No key found!");
            return;
        }

        IO.println(String.format("Deleted pair (%s: %s)", keys.get(id), values.get(id)));
        keys.remove(id);
        values.remove(id);
    }

    // It must follow the protection rules for a DictType
    public void AddValue(String key, String value, boolean detailed) {
        if (detailed) IO.print(String.format("Adding pair (%s, %s)... ", key, value));
        if (!LangCheck(key)) {
            if (detailed) {
                IO.println("Error: the key is invalid for this dictionary (language: " + type.getName() + ")");
                IO.println("Hint: " + type.getHint());
            }
            return;
        }

        var found = FindValueID(key);
        if (found == -1) {
            keys.add(key);
            values.add(value);
            if (detailed) IO.println("Pair added successfully!");
        } else {
            values.set(found, value);
            if (detailed) IO.println("Pair overwritten successfully!");
        }
    }

    public void AddValue(String key, String value) {
        AddValue(key, value, true);
    }

    private int FindValueID(String key) {
        for (int i = 0; i < keys.size(); i++) {
            if (key.equals(keys.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private boolean LangCheck(String s) {
        if (type == DictTypes.FIRST_LANG) {
            String eng_alp = "qwertyuiopasdfghjklzxcvbnm";

            for (int i = 0; i < s.length(); i++) {
                if (eng_alp.indexOf(Character.toLowerCase(s.charAt(i))) == -1) return false;
            }

            return s.length() == 4;
        } else if (type == DictTypes.SECOND_LANG) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }

            return s.length() == 5;
        }

        return true;
    }
}
