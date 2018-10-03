package games;

import org.slf4j.Logger;

import java.io.IOException;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды
    private static final int MAX_VALUE = 21;
    private static int SUM1;
    private static int SUM2;

    public static void main(String[] args) throws IOException {

        initRound();
        while (SUM1<20) {
            if (cursor<2) {
                log.info("Вам выпала карта : {}", addCard2Player());
            } else if (confirm("Берём ещё?")) {
                log.info("Вам выпала карта : {}", addCard2Player());
            } else break;
        }
        log.info("Ход компьютера");
        while (SUM2<17) {
            log.info("Компьютеру выпала карта : {}", addCard2Computer());
        }

        log.info("Сумма ваших очков - {}, сумма компьютера - {}", SUM1, SUM2);
        int result = result();
        if (result==1) log.info("Вы выиграли раунд! Получите 10$");
        else if (result==-1) log.info("Вы проиграли раунд! Теряете 10$");
        else log.info("Ничья, каждый остается при своих!");

        try {
            Choice.main(new String[0]);
        } catch (IOException e) {
            log.error("Произошла ошибка ввода-вывода");
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

    private static String addCard2Player() {
        int temp = cards[cursor++];
        SUM1 += value(temp);
        return CardUtils.toString(temp);
    }
    private static String addCard2Computer() {
        int temp = cards[cursor++];
        SUM2 += value(temp);
        return CardUtils.toString(temp);
    }

    private static void initRound() {
        log.info("У Вас {}$ ! Начинаем новый раунд!", Choice.cash);
        cards = CardUtils.getShaffledCards();
        cursor = SUM1 = SUM2 = 0;
    }

    private static int result() {
        if (SUM2<=MAX_VALUE && (SUM1>MAX_VALUE || SUM1<SUM2)) {
            Choice.cash -= 10;
            return -1;
        }
        else if (SUM1<=MAX_VALUE && (SUM2>MAX_VALUE || SUM2<SUM1)) {
            Choice.cash += 10;
            return 1;
        } else {
            return 0;
        }
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
