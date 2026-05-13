package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class T7 {
    public static void main(String[] args) throws IOException {
        int currentYear = Year.now().getValue();
        int startYear = currentYear - 100;

        List<Integer> leapYears = getLeapYears(startYear, currentYear);

        Files.write(Path.of("leap_years.txt"),
                leapYears.stream().map(String::valueOf).toList());

        System.out.println("Високосные года за последние 100 лет:");
        System.out.println(leapYears);
        System.out.println("Всего: " + leapYears.size() + " лет");
        System.out.println("Результат записан в leap_years.txt");
    }

    public static List<Integer> getLeapYears(int startYear, int endYear) {
        List<Integer> leapYears = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++) {
            if (isLeapYear(year)) {
                leapYears.add(year);
            }
        }
        return leapYears;
    }

    public static boolean isLeapYear(int year) {
        // Григорианский календарь
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
