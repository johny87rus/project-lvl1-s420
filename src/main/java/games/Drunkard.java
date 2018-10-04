package games;

import org.apache.commons.math3.util.MathArrays;
import org.slf4j.Logger;

import java.io.IOException;

import static games.CardUtils.CARDS_TOTAL_COUNT;
import static games.CardUtils.getPar;

public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playersCardsBeginCursors = new int[2];
    private static int[] playersCardsEndCursors = new int[2];
    private static int sum1;
    private static int sum2;
    private static int player1Card;
    private static int player2Card;
    private static int flag;

    public static void main(String... args) {
        log.info("У вас {}$, ставка 10$", Choice.cash);
        generateCards(playersCards);
        int result;
        while(!gameOver()) {
            takeCards();
            log.info("Игрок №1 карта : {}, игрок №2 карта : {}. ", CardUtils.toString(player1Card), CardUtils.toString(player2Card));
            result = result();
            if (result == 1) {
                log.info("Выиграл игрок 1!");
            } else if (result == 2) {
                log.info("Выиграл игрок 2!");
            } else {
                log.info("Спор - каждый остаётся при своих!");
            }
            calculateSums();
            log.info(" У игрока №1 {} карт, у игрока №2 {} карт", sum1, sum2);
        }
        if (sum1>sum2) {
            Choice.cash += 10;
            log.info("Игра окончена! Вы выиграли 10$! В вашем кошельке {}$!", Choice.cash);
        }
        else {
            Choice.cash -= 10;
            log.info("Игра окончена! Вы проиграли 10$! В вашем кошельке {}$!", Choice.cash);
        }
        try {
            Choice.main(new String[0]);
        } catch (IOException e) {
            log.error("Произошла ошибка ввода-вывода");
        }
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
        sum1 = sum2 = CARDS_TOTAL_COUNT/2;
        playersCardsBeginCursors[0]=0;
        playersCardsBeginCursors[1]=0;
        playersCardsEndCursors[0]=CARDS_TOTAL_COUNT/2;
        playersCardsEndCursors[1]=CARDS_TOTAL_COUNT/2;

    }

    private static boolean gameOver() {
        return sum1!=CARDS_TOTAL_COUNT && sum2!=CARDS_TOTAL_COUNT ? false : true;
    }

    private static void takeCards() {
        player1Card = playersCards[0][playersCardsBeginCursors[0]];
        playersCardsBeginCursors[0]=incrementIndex(playersCardsBeginCursors[0]);
        player2Card = playersCards[1][playersCardsBeginCursors[1]];
        playersCardsBeginCursors[1]=incrementIndex(playersCardsBeginCursors[1]);
    }

    private static int result() {
        if (getPar(player1Card).ordinal()>getPar(player2Card).ordinal() && !(getPar(player2Card)== CardUtils.Par.SIX && getPar(player1Card)== CardUtils.Par.ACE) || getPar(player1Card)== CardUtils.Par.SIX && getPar(player2Card)== CardUtils.Par.ACE) {
            playersCards[0][playersCardsEndCursors[0]]=player1Card;
            playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
            playersCards[0][playersCardsEndCursors[0]]=player2Card;
            playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
            flag=1;
            return 1;
        } else if (getPar(player1Card).ordinal()<getPar(player2Card).ordinal() || getPar(player2Card)== CardUtils.Par.SIX && getPar(player1Card)== CardUtils.Par.ACE) {
            playersCards[1][playersCardsEndCursors[1]]=player2Card;
            playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
            playersCards[1][playersCardsEndCursors[1]]=player1Card;
            playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
            flag=2;
            return 2;
        } else {
            playersCards[0][playersCardsEndCursors[0]]=player1Card;
            playersCardsEndCursors[0]=incrementIndex(playersCardsEndCursors[0]);
            playersCards[1][playersCardsEndCursors[1]]=player2Card;
            playersCardsEndCursors[1]=incrementIndex(playersCardsEndCursors[1]);
            flag=0;
            return 0;
        }
    }

    private static void calculateSums() {
        sum1 = playersCardsBeginCursors[0]<playersCardsEndCursors[0] ? playersCardsEndCursors[0]-playersCardsBeginCursors[0] : playersCardsEndCursors[0]-playersCardsBeginCursors[0]+CARDS_TOTAL_COUNT;
        sum2 = playersCardsBeginCursors[1]<playersCardsEndCursors[1] ? playersCardsEndCursors[1]-playersCardsBeginCursors[1] : playersCardsEndCursors[1]-playersCardsBeginCursors[1]+CARDS_TOTAL_COUNT;
        if (sum1==sum2 && sum2==CARDS_TOTAL_COUNT) {
            sum1 = flag==1 ? sum1 : 0;
            sum2 = flag==2 ? sum2 : 0;
        }
    }
}
