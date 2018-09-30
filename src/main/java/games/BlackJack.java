package games;

import java.io.IOException;

public class BlackJack {

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

    private static int[] playersMoney = {100, 100};

    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;

    public static void main(String[] args) throws IOException {
        int sum1;
        int sum2;
        int last;
        while(playersMoney[0]>0 && playersMoney[1]>0) {
            initRound();
            sum1 = sum2 = 0;
            last = addCard2Player(0);
            sum1 += value(last);
            System.out.printf(" Вам выпала карта %s%n", CardUtils.toString(last));
            last = addCard2Player(0);
            sum1 += value(last);
            System.out.printf(" Вам выпала карта %s%n", CardUtils.toString(last));
            while (sum1<20 && confirm("Берём ещё?")) {
                last = addCard2Player(0);
                sum1 += value(last);
                System.out.printf(" Вам выпала карта %s%n", CardUtils.toString(last));
            };

            last = addCard2Player(1);
            sum2 += value(last);
            System.out.printf("Компьютеру выпала карта %s%n", CardUtils.toString(last));
            last = addCard2Player(1);
            sum2 += value(last);
            System.out.printf("Компьютеру выпала карта %s%n", CardUtils.toString(last));
            while (sum2<18) {
                last = addCard2Player(1);
                sum2 += value(last);
                System.out.printf("Компьютер решил взять еще и ему выпала карта %s%n", CardUtils.toString(last));
            }
            System.out.printf("Сумма ваших очков - %d, сумма компьютера - %d%n", sum1, sum2);
            if (sum2<22 && (sum1>=22 || sum1<sum2)) {
                System.out.println("Вы проиграли раунд! Теряете 10$");
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
            }
            else if (sum1<22 && (sum2>=22 || sum2<sum1)) {
                System.out.println("Вы выиграли раунд! Получите 10$");
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
            } else {
                System.out.println("Ничья, каждый остается при своих!");
            }
        }

        if (playersMoney[0] > 0)
            System.out.println("Вы выиграли! Поздравляем!");
        else
            System.out.println("Вы проиграли! Соболезнуем!");
    }

    static boolean confirm(String message) throws IOException {
        System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

    private static int addCard2Player(int player) {
        int card;
        card = playersCards[player][playersCursors[player]] = cards[cursor];
        cursor += 1;
//        playersCursors[player] += 1;
        return card;
    }

    private static void initRound() {
        System.out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - " + playersMoney[1] + "$. Начинаем новый раунд!");
        cards = CardUtils.getShaffledCards();
        cursor=0;
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
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
