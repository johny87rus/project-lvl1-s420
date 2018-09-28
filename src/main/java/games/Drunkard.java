package games;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    public static void main(String[] args) {
        int[] deck1 = new int[CARDS_TOTAL_COUNT];
        int[] deck2 = new int[CARDS_TOTAL_COUNT];
        generateDecks(deck1, deck2);
        int firstPlayerPointer = 0;
        int firstPlayerSumm = CARDS_TOTAL_COUNT/2;
        int secondPlayerPointer = 0;
        int secondPlayerSumm = CARDS_TOTAL_COUNT/2;
        System.out.println("Начинаем игру! Напоминаю правила, бубны > треф > червей > пик!");
        while (!haveWinner(firstPlayerSumm, secondPlayerSumm)) {
            System.out.println("Начинаем розыгрыш!");
            System.out.printf("Количество карту у первого игрока - %d, количество карт у второго игрока - %d%n", firstPlayerSumm, secondPlayerSumm);
            if (deck1[firstPlayerPointer]==-1) {
                firstPlayerPointer++;
                if (firstPlayerPointer==CARDS_TOTAL_COUNT) firstPlayerPointer=0;
                continue;
            }
            if (deck2[secondPlayerPointer]==-1) {
                secondPlayerPointer++;
                if (secondPlayerPointer==CARDS_TOTAL_COUNT) secondPlayerPointer=0;
                continue;
            }
            System.out.printf("Карта первого игрока - %s, карта второго игрока - %s%n", toString(deck1[firstPlayerPointer]), toString(deck2[secondPlayerPointer]));
            if (deck1[firstPlayerPointer]%PARS_TOTAL_COUNT>deck2[secondPlayerPointer]%PARS_TOTAL_COUNT) {
                System.out.printf("Выиграл первый игрок, т.к. %s > %s%n", toString(deck1[firstPlayerPointer]), toString(deck2[secondPlayerPointer]));
                addCard(deck1, firstPlayerPointer, deck1[firstPlayerPointer]);
                deck1[firstPlayerPointer]=-1;
                addCard(deck1, firstPlayerPointer, deck2[secondPlayerPointer]);
                firstPlayerSumm++;
                secondPlayerSumm--;
            } else if (deck1[firstPlayerPointer]%PARS_TOTAL_COUNT<deck2[secondPlayerPointer]%PARS_TOTAL_COUNT) {
                System.out.printf("Выиграл второй игрок, т.к. %s > %s%n", toString(deck2[secondPlayerPointer]), toString(deck1[firstPlayerPointer]));
                addCard(deck2, secondPlayerPointer, deck2[secondPlayerPointer]);
                deck2[secondPlayerPointer]=-1;
                addCard(deck2, secondPlayerPointer, deck1[firstPlayerPointer]);
                secondPlayerSumm++;
                firstPlayerSumm--;
            } else if (deck1[firstPlayerPointer]/PARS_TOTAL_COUNT>deck2[secondPlayerPointer]/PARS_TOTAL_COUNT) {
                System.out.printf("Выиграл первый игрок, т.к. %s > %s%n", toString(deck1[firstPlayerPointer]), toString(deck2[secondPlayerPointer]));
                addCard(deck1, firstPlayerPointer, deck1[firstPlayerPointer]);
                deck1[firstPlayerPointer]=-1;
                addCard(deck1, firstPlayerPointer, deck2[secondPlayerPointer]);
                firstPlayerSumm++;
                secondPlayerSumm--;
            } else {
                System.out.printf("Выиграл второй игрок, т.к. %s > %s%n", toString(deck2[secondPlayerPointer]), toString(deck1[firstPlayerPointer]));
                addCard(deck2, secondPlayerPointer, deck2[secondPlayerPointer]);
                deck2[secondPlayerPointer]=-1;
                addCard(deck2, secondPlayerPointer, deck1[firstPlayerPointer]);
                secondPlayerSumm++;
                firstPlayerSumm--;
            }
            firstPlayerPointer++;
            secondPlayerPointer++;
            if (firstPlayerPointer==CARDS_TOTAL_COUNT) firstPlayerPointer=0;
            if (secondPlayerPointer==CARDS_TOTAL_COUNT) secondPlayerPointer=0;
        }

        System.out.printf("Игра окончена, победил %s!!!%n", getWinner(firstPlayerSumm));

    }

    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static Suit getSuite(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuite(cardNumber);
    }

    /**
     * Метод для определения наличия значения-карты в массиве карт
     *
     * @param deck - массив карт
     * @param value - проверяемое значение-карта
     * @return
     */
    private static boolean isExist(int[] deck, int value) {
        for (int i=0; i<deck.length; i++) {
            if(deck[i]==value) return true;
        }
        return false;
    }

    /**
     *
     * Метод генерации пачек карт
     *
     * @param deck1 - массив карт игрока 1
     * @param deck2 - массив карт игрока 2
     */
    private static void generateDecks(int[] deck1, int[] deck2 ) {
        for (int i=0; i<CARDS_TOTAL_COUNT;i++) {
            deck1[i]=-1;
            deck2[i]=-1;
        }
        Random random = new Random();
        for (int i=0; i<CARDS_TOTAL_COUNT/2; i++) {
            int randomInt = random.nextInt(CARDS_TOTAL_COUNT);
            while (isExist(deck1, randomInt) || isExist(deck2, randomInt)) {
                randomInt = random.nextInt(CARDS_TOTAL_COUNT);
            }
            deck1[i]=randomInt;
        }
        for (int i=0; i<CARDS_TOTAL_COUNT/2; i++) {
            int randomInt = random.nextInt(CARDS_TOTAL_COUNT);
            while (isExist(deck1, randomInt) || isExist(deck2, randomInt)) {
                randomInt = random.nextInt(CARDS_TOTAL_COUNT);
            }
            deck2[i]=randomInt;
        }
    }

    /**
     * Метод определяет наличие победителя
     * @param firstPlayerSumm
     * @param secondPlayerSumm
     * @return
     */
    private static boolean haveWinner(int firstPlayerSumm, int secondPlayerSumm) {
        return firstPlayerSumm==CARDS_TOTAL_COUNT || secondPlayerSumm==CARDS_TOTAL_COUNT;
    }

    /**
     * Метод находит ближайший справа индекс, чтобы имитировать перенос карт в конец колоды
     * @param deck
     * @param startIndex
     * @return
     */
    private static int findEmptyIndex(int[] deck, int startIndex) {
        if (startIndex+1<deck.length) {
            for (int i=startIndex+1; i<deck.length; i++) {
                if (deck[i]==-1) return i;
            }
        } else {
            for (int i=0; i<=startIndex; i++) {
                if (deck[i]==-1) return i;
            }
        }
        return 0;
    }

    /**
     * Метод добавления карты
     * @param deck
     * @param startIndex
     * @param value
     */
    private static void addCard (int[] deck, int startIndex, int value) {
        deck[findEmptyIndex(deck, startIndex)]=value;
    }

    /**
     * Метод выводит победителя игры
     * @param first
     * @return
     */
    private static String getWinner(int first) {
        return first == 36 ? "первый игрок" : "второй игрок";
    }
}
