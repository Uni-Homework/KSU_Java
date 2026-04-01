package ExamTasks;

import java.util.LinkedList;
import java.util.List;

enum DictTypes { UNRESTRICTED, FIRST_LANG, SECOND_LANG }

public class TEx1_Dictionary {
    private List<String> keys;
    private List<String> values;
    private DictTypes type = DictTypes.FIRST_LANG;

    public TEx1_Dictionary (DictTypes dictionaryType) {
        keys = new LinkedList<>();
        values = new LinkedList<>();
    }

    public void ParseFile(String filename){
        // TODO
    }

    public void ReadPairs() {
        // TODO
    }

    public void FindValue(String key) {
        // TODO
    }

    public void AddValue(String value) {
        // TODO
    }
}
