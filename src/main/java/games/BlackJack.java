package games;

import org.slf4j.Logger;

import java.io.IOException;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды
    private static final int MAX_VALUE = 21;
    private static int sum[];

    public static void main(String... args) throws IOException {

        initRound();
        while (sum[0]<20) {
            if (cursor<2 || confirm("Берём ещё?")) {
                log.info("Вам выпала карта : {}", addCard(0));
            } else break;
        }
        log.info("Ход компьютера");
        while (sum[1]<17) {
            log.info("Компьютеру выпала карта : {}", addCard(1));
        }

        log.info("Сумма ваших очков - {}, сумма компьютера - {}", sum[0], sum[1]);
        int result = result();
        if (result==1) log.info("Вы выиграли раунд! Получите 10$");
        else if (result==-1) log.info("Вы проиграли раунд! Теряете 10$");
        else log.info("Ничья, каждый остается при своих!");

        try {
            Choice.main();
        } catch (IOException e) {
            log.error("Произошла ошибка ввода-вывода");
        }
    }

    static boolean confirm(String message) throws IOException {
        log.info("{}  \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)", message);
        switch (Choice.getCharacterFromUser()) {
            case "Y":
            case "y": return true;
            default: return false;
        }
    }

    private static String addCard (int player) {
        int temp = cards[cursor++];
        sum[player] += value(temp);
        return CardUtils.toString(temp);
    }

    private static void initRound() {
        log.info("У Вас {}$ ! Начинаем новый раунд!", Choice.cash);
        cards = CardUtils.getShaffledCards();
        sum = new int[2];
        cursor = sum[0] = sum[1] = 0;
    }

    private static int result() {
        if (sum[1]<=MAX_VALUE && (sum[0]>MAX_VALUE || sum[0]<sum[1])) {
            Choice.cash -= 10;
            return -1;
        }
        else if (sum[0]<=MAX_VALUE && (sum[1]>MAX_VALUE || sum[1]<sum[0])) {
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
