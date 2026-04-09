package org.sun1zu;

import org.sun1zu.ExamTasks.T1.Model.TEx1_Dictionary;
import org.sun1zu.Lab4.*;

import java.io.IOException;

import static org.sun1zu.ExamTasks.T1.Model.DictTypes.*;

public class Main {
    public static void main(String[] args) {
        // CheckLab4();
        CheckEx1();
    }

    static void CheckEx1() {
        IO.println("Выберите язык словаря [A: Язык 1, b: язык 2]");

        var input = IO.readln();

        var lang = FIRST_LANG;
        if(!input.isEmpty()) {
            if(input.charAt(0) == 'b')
                lang = SECOND_LANG;
        }

        var dict = new TEx1_Dictionary(lang);

        // Parsing
        try{
            dict.ParseFile("test.dict.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        dict.AddValue("12345", "First");
        dict.AddValue("54321", "Second");
        dict.AddValue("abcd", "Third");
        dict.AddValue("QTYW", "Fourth");

        dict.PrintPairs();
        dict.DeletePairByKey("54321");
        dict.DeletePairByKey("abcd");

        dict.FindValue("QTYW");
        dict.FindValue("abcd");
        dict.FindValue("54321");

        // Writing
        try {
            dict.WriteToFile("test.dict.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void CheckLab4() {
        IO.println("Solving Lightning");
        new T1_Lightning().Solve();

        IO.println("Solving BackwardString");
        new T2_BackwardString().Solve(IO.readln());

        IO.println("Solving Marathon");
        new T3_Marathon().Solve();

        IO.println("Solving Palindrome");
        IO.println(new T4_Palindrome().Solve(IO.readln()));

        IO.println("Solving Time");
        new T5_Time().Solve(Integer.parseInt(IO.readln()));
    }
}