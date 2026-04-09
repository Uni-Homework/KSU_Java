package org.sun1zu.ExamTasks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;

public class TEx1_Dictionary {
    private List<String> keys;
    private List<String> values;
    private DictTypes type = DictTypes.FIRST_LANG;
    private JSONObject jsonObject;

    public TEx1_Dictionary (DictTypes dictionaryType) {
        keys = new LinkedList<>();
        values = new LinkedList<>();

        jsonObject = new JSONObject();

        IO.println("Created new dict of language " + GetLanguage());
    }

    public void ParseFile(String filename){
        // TODO
    }
    public void WriteToFile(String filename) throws IOException {
        var fw = new FileWriter(filename);
        jsonObject.put("DictLang", type);
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
}
