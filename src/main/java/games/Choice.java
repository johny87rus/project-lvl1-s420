package games;

import org.slf4j.Logger;

import java.io.IOException;

public class Choice {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Choice.class);
    static final String LINE_SEPARATOR = System.getProperty("line.separator");
    static int cash = 100;

    public static void main(String[] args) throws IOException {
        if (cash > 0) {
            log.info("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\", 3 - \"очко\"");
            switch (getCharacterFromUser()) {
                case '1':
                    Slot.main(new String[0]);
                    break;
                case '2':
                    Drunkard.main(new String[0]);
                    break;
                case '3':
                    BlackJack.main(new String[0]);
                    break;
                default:
                    log.info("Игры с таким номером нет!");
            }
        }
        else log.info("К сожалению у вас кончились деньги, до свидания!");
    }


    static char getCharacterFromUser() throws IOException {
        byte[] input = new byte[1 + LINE_SEPARATOR.length()];
        if (System.in.read(input) != input.length)
            throw new RuntimeException("Пользователь ввёл недостаточное кол-во символов");
        return (char) input[0];
    }
}
