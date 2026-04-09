package org.sun1zu.ExamTasks.T1.Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TEx1_Dictionary {
    private List<String> keys;
    private List<String> values;
    private DictTypes type = DictTypes.FIRST_LANG;

    public TEx1_Dictionary (DictTypes dictionaryType) {
        type = dictionaryType;

        keys = new LinkedList<>();
        values = new LinkedList<>();

        IO.println("Created new dict of language " + GetLanguage());
    }

    /**
     * Parses file into current dictionary object.
     * WARNING! Overwrites all contents of current dictionary.
     * @param filename Name of the file to read data from
     * @throws IOException File is corrupted
     */
    public void ParseFile(String filename) throws IOException {
        IO.println("Parsing file " + filename);

        var fr = new FileReader(filename);
        String data = fr.readAllAsString();

        Object obj = JSONValue.parse(data);
        JSONObject jsonObject = (JSONObject) obj;

        IO.println("File opened successfully! Clearing...");
        Clear();

        // Extract language
        type = (DictTypes) DictTypes.valueOf((String) jsonObject.get("DictLang"));
        jsonObject.remove("DictLang");
        IO.println("Language of the file: " + GetLanguage());

        // Extract data
        for (Object o : jsonObject.keySet()) {
            var key = (String) o;
            AddValue(key, (String) jsonObject.get(key));
        }
        IO.println("Done! Found " + keys.size() + " elements");

        jsonObject.clear();
        fr.close();
    }

    public void WriteToFile(String filename) throws IOException {
        var jsonObject = new JSONObject();
        var fw = new FileWriter(filename);

        jsonObject.put("DictLang", type.name());

        for (int i=0; i<keys.size(); i++) {
            jsonObject.put(keys.get(i), values.get(i));
        }
        fw.write(jsonObject.toJSONString());
        fw.close();
        jsonObject.clear();
    }

    public void PrintPairs() {
        IO.println("Printing dict contents: ");
        for (int i=0; i< keys.size(); i++) {
            IO.println(String.format("%s: %s", keys.get(i), values.get(i)));
        }
    }

    private int FindValueID(String key) {
        for (int i=0; i<keys.size(); i++) {
            if (key.equals(keys.get(i))) {
                return i;
            }
        }
        return -1;
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

    private String GetLanguage() {
        if (type == DictTypes.FIRST_LANG) {
            return "Язык 1";
        }
        else if (type == DictTypes.SECOND_LANG) {
            return "Язык 2";
        }
        else if (type == DictTypes.UNRESTRICTED) {
            return "Язык без ограничений";
        }
        return "Error: unknown language!";
    }

    // It must follow the protection rules for a DictType
    public void AddValue(String key, String value) {
        IO.print(String.format("Adding pair (%s, %s)... ", key, value));
        if (!LangCheck(key)) {
            IO.println("Error: the key is invalid for this dictionary (language: " + GetLanguage() + ")");
            return;
        }
        IO.println("Pair added successfully!");

        keys.add(key);
        values.add(value);
    }

    private boolean LangCheck(String s) {
        if(type == DictTypes.FIRST_LANG) {
            String eng_alp = "qwertyuiopasdfghjklzxcvbnm";

            for (int i=0; i<s.length(); i++) {
                if (eng_alp.indexOf(Character.toLowerCase(s.charAt(i))) == -1) return false;
            }

            return s.length() == 4;
        }
        else if (type == DictTypes.SECOND_LANG) {
            for(int i=0; i<s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }

            return s.length() == 5;
        }

        return true;
    }

    public void Clear(){
        keys.clear();
        values.clear();
    }
}
