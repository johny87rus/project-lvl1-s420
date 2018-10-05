package games;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.Random;

public class Slot {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);
    private static final int BET = 10;
    private static final int WIN = 10000;
    private static final int SIZE = 8;
    private static final int START_COUNTER = 0;

    public static void main(String... args) {

        int firstCounter;
        int secondCounter;
        int thirdCounter;
        firstCounter = secondCounter = thirdCounter = START_COUNTER;
        Random random = new Random();

            log.info("У Вас {}$, ставка - {}$", Choice.cash, BET);
            log.info("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + random.nextInt(SIZE)) % SIZE;
            secondCounter = (secondCounter + random.nextInt(SIZE)) % SIZE;
            thirdCounter = (thirdCounter + random.nextInt(SIZE)) % SIZE;
            log.info("первый барабан - {}, второй - {}, третий - {}", firstCounter, secondCounter, thirdCounter);
            if (firstCounter==secondCounter && secondCounter==thirdCounter) {
                Choice.cash += WIN;
                log.info("Поздравляем! Вы выиграли {}$ ! Ваш капитал теперь составляет: {}$", WIN, Choice.cash);
            } else {
                Choice.cash -= BET;
                log.info("Проигрыш {}$, ваш капитал теперь составляет: {}$", BET, Choice.cash);
            }
        try {
            Choice.main();
        } catch (IOException e) {
            log.error("Произошла ошибка ввода-вывода");
        }

    }


}
