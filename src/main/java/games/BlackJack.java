package games;

import org.slf4j.Logger;

import java.io.IOException;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды
    private static final int MAX_VALUE = 21;

    public static void main(String[] args) throws IOException {
        int sum1;
        int sum2;
        int last;

            initRound();
            sum1 = sum2 = 0;
            last = addCard2Player();
            sum1 += value(last);
            String player = "Вам выпала карта {}";
            log.info(player, CardUtils.toString(last));
            last = addCard2Player();
            sum1 += value(last);
            log.info(player, CardUtils.toString(last));
            while (sum1<20 && confirm("Берём ещё?")) {
                last = addCard2Player();
                sum1 += value(last);
                log.info(player, CardUtils.toString(last));
            }

            last = addCard2Player();
            sum2 += value(last);
            String computer = "Компьютеру выпала карта {}";
            log.info(computer, CardUtils.toString(last));
            last = addCard2Player();
            sum2 += value(last);
            log.info(computer, CardUtils.toString(last));
            while (sum2<18) {
                last = addCard2Player();
                sum2 += value(last);
                log.info(computer, CardUtils.toString(last));
            }
            log.info("Сумма ваших очков - {}, сумма компьютера - {}", sum1, sum2);
            if (sum2<=MAX_VALUE && (sum1>MAX_VALUE || sum1<sum2)) {
                log.info("Вы проиграли раунд! Теряете 10$");
                Choice.cash -= 10;
            }
            else if (sum1<=MAX_VALUE && (sum2>MAX_VALUE || sum2<sum1)) {
                log.info("Вы выиграли раунд! Получите 10$");
                Choice.cash += 10;
            } else {
                log.info("Ничья, каждый остается при своих!");
            }

        try {
            Choice.main(new String[0]);
        } catch (IOException e) {
            log.error(e.getStackTrace().toString(),e);
        }
    }

    static boolean confirm(String message) throws IOException {
        log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

    private static int addCard2Player() {
        return cards[cursor++];
    }

    private static void initRound() {
        log.info("У Вас {}$ ! Начинаем новый раунд!", Choice.cash);
        cards = CardUtils.getShaffledCards();
        cursor = 0;
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case ACE:
            default: return 11;
        }
    }
}
