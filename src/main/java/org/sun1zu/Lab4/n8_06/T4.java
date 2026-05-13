package org.sun1zu.Lab4.n8_06;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class T4 {
    public static void main(String[] args) throws IOException {
        List<String> luckyTickets = new ArrayList<>();

        for (int i = 0; i <= 999999; i++) {
            String ticket = String.format("%06d", i);

            int leftSum = (ticket.charAt(0) - '0') + (ticket.charAt(1) - '0') + (ticket.charAt(2) - '0');
            int rightSum = (ticket.charAt(3) - '0') + (ticket.charAt(4) - '0') + (ticket.charAt(5) - '0');

            if (leftSum == rightSum) {
                luckyTickets.add(ticket);
            }
        }

        Files.write(Path.of("lucky_tickets.txt"), luckyTickets);
        System.out.println("Найдено счастливых билетов: " + luckyTickets.size());
        System.out.println("Результат записан в lucky_tickets.txt");
    }
}
