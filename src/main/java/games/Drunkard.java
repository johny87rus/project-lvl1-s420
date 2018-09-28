package games;

import org.apache.commons.math3.util.MathArrays;


public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length;
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playersCardsBeginCursors = new int[2];
    private static int[] playersCardsEndCursors = new int[2];


    public static void main(String[] args) {
        generateCards(playersCards);
        int[] player1Cards = playersCards[0];
        int[] player2Cards = playersCards[1];
        int player1Card;
        int player2Card;
        int sum1;
        int sum2;
        playersCardsBeginCursors[0]=0;
        playersCardsBeginCursors[1]=0;
        playersCardsEndCursors[0]=CARDS_TOTAL_COUNT/2;
        playersCardsEndCursors[1]=CARDS_TOTAL_COUNT/2;
        while(!gameOver()) {
            player1Card = player1Cards[playersCardsBeginCursors[0]];
            playersCardsBeginCursors[0]=incrementIndex(playersCardsBeginCursors[0]);
            player2Card = player2Cards[playersCardsBeginCursors[1]];
            playersCardsBeginCursors[1]=incrementIndex(playersCardsBeginCursors[1]);
            System.out.printf("Игрок №1 карта : %s, игрок №2 карта : %s.", toString(player1Card), toString(player2Card));
            if (getPar(player1Card).ordinal()>getPar(player2Card).ordinal()) {
                player1Cards[playersCardsEndCursors[0]]=player1Card;
                playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
                player1Cards[playersCardsEndCursors[0]]=player2Card;
                playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
                System.out.print("Выиграл игрок 1!");
            } else if (getPar(player1Card).ordinal()<getPar(player2Card).ordinal()) {
                player2Cards[playersCardsEndCursors[1]]=player2Card;
                playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
                player1Cards[playersCardsEndCursors[1]]=player1Card;
                playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
                System.out.print("Выиграл игрок 2!");
            } else {
                player1Cards[playersCardsEndCursors[0]]=player1Card;
                playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
                player2Cards[playersCardsEndCursors[1]]=player2Card;
                playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
                System.out.print("Спор - каждый остаётся при своих!");
            }
            sum1 = playersCardsBeginCursors[0]<=playersCardsEndCursors[0] ? playersCardsEndCursors[0]-playersCardsBeginCursors[0] : playersCardsEndCursors[0]-playersCardsBeginCursors[0]+CARDS_TOTAL_COUNT;
            sum2 = playersCardsBeginCursors[1]<=playersCardsEndCursors[1] ? playersCardsEndCursors[1]-playersCardsBeginCursors[1] : playersCardsEndCursors[1]-playersCardsBeginCursors[1]+CARDS_TOTAL_COUNT;
            System.out.printf("У игрока №1 %d карт, у игрока №2 %d карт%n", sum1, sum2);
            if (sum1==CARDS_TOTAL_COUNT || sum2==CARDS_TOTAL_COUNT) break;
        }
        System.out.println("Игра окончена!");
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

    private static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static void generateCards(int[][] cards) {
        int[] src = new int[CARDS_TOTAL_COUNT];
        for (int i=0; i<CARDS_TOTAL_COUNT; i++) {
            src[i]=i;
        }
        MathArrays.shuffle(src);
        for (int i=0; i<CARDS_TOTAL_COUNT; i++) {
            if (i<CARDS_TOTAL_COUNT/2) cards[0][i]=src[i];
            else cards[1][i-CARDS_TOTAL_COUNT/2]=src[i];
        }

    }

    private static boolean gameOver () {
        return false;
    }


}
