package games;

import java.util.Random;

public class Slot {
    public static void main(String[] args) {
        final int DEFAULT_CASH = 100;
        final int DEFAULT_BET = 10;
        final int DEFAULT_WIN = 10000;
        final int DEFAULT_SIZE = 8;
        final int DEFAULT_START_COUNTER = 0;

        int cash = DEFAULT_CASH;
        int bet = DEFAULT_BET;
        int win = DEFAULT_WIN;
        int size = DEFAULT_SIZE;
        int firstCounter;
        int secondCounter;
        int thirdCounter;
        firstCounter = secondCounter = thirdCounter = DEFAULT_START_COUNTER;
        Random random = new Random();

        while(cash>0) {
            System.out.printf("У Вас %d$, ставка - %d$%n", cash, bet);
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + random.nextInt(size)) % size;
            secondCounter = (secondCounter + random.nextInt(size)) % size;
            thirdCounter = (thirdCounter + random.nextInt(size)) % size;
            System.out.printf("первый барабан - %d, второй - %d, третий - %d%n", firstCounter, secondCounter, thirdCounter);
            if (firstCounter==secondCounter && secondCounter==thirdCounter) {
                cash += win;
                System.out.printf("Поздравляем! Вы выиграли %d$ ! Ваш капитал теперь составляет: %d$%n", win, cash);
            } else {
                cash -= bet;
                System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$%n", bet, cash);
            }
        }
    }


}
