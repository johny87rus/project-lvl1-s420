package games;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {
    public static void main(String[] args) {
        final int DEFAULT_CASH = 100;
        final int DEFAULT_BET = 10;
        final int DEFAULT_WIN = 10000;
        final int SIZE = 7;
        final int START_COUNTER = 0;

        int cash = DEFAULT_CASH;
        int bet = DEFAULT_BET;
        int win = DEFAULT_WIN;
        int size = SIZE;
        int firstCounter, secondCounter, thirdCounter;
        firstCounter = secondCounter = thirdCounter = START_COUNTER;

        while(cash>0) {
            System.out.printf("У Вас %d$, ставка - %d$%n", cash, bet);
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
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
