package org.sun1zu.Lab4;//3. Группа людей участвует в марафоне, их имена и время за которое они пробежали марафон вы можете увидеть ниже.
//Ваша задача найти человека, который быстрее всех пробежал дистанцию и вывести его имя и счет.
//String[] names = { "Вася", "Петр", "Александр", "Сергей", "Павел" };
//int[] times = { 341, 273, 278, 329, 445};

public class T3_Marathon {
    String[] names = {"Вася", "Петр", "Александр", "Сергей", "Павел"};
    int[] times = {341, 273, 278, 329, 445};

    public void Solve() {
        int minid = 0;
        for (int i = 0; i < times.length; i++) minid = times[minid] > times[i] ? i : minid;
        IO.println(names[minid]);
    }
}
