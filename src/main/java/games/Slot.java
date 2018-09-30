package games;

import java.util.Random;

public class Slot {
    private static final int CASH = 100;
    private static final int BET = 10;
    private static final int WIN = 10000;
    private static final int SIZE = 8;
    private static final int START_COUNTER = 0;

    public static void main(String[] args) {

        int firstCounter;
        int secondCounter;
        int thirdCounter;
        firstCounter = secondCounter = thirdCounter = START_COUNTER;
        Random random = new Random();
        int cash = CASH;

        while(cash>0) {
            System.out.printf("У Вас %d$, ставка - %d$%n", CASH, BET);
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + random.nextInt(SIZE)) % SIZE;
            secondCounter = (secondCounter + random.nextInt(SIZE)) % SIZE;
            thirdCounter = (thirdCounter + random.nextInt(SIZE)) % SIZE;
            System.out.printf("первый барабан - %d, второй - %d, третий - %d%n", firstCounter, secondCounter, thirdCounter);
            if (firstCounter==secondCounter && secondCounter==thirdCounter) {
                cash += WIN;
                System.out.printf("Поздравляем! Вы выиграли %d$ ! Ваш капитал теперь составляет: %d$%n", WIN, cash);
            } else {
                cash -= BET;
                System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %d$%n", BET, cash);
            }
        }
    }


}
