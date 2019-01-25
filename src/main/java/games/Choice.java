package games;

import org.slf4j.Logger;

import java.io.IOException;

public class Choice {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Choice.class);
    static final String LINE_SEPARATOR = System.getProperty("line.separator");
    static int cash = 100;

    public static void main(String... args) throws IOException {
        if (cash > 0) {
            log.info("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\", 3 - \"очко\"");
            switch (getCharacterFromUser()) {
                case "1":
                    Slot.main();
                    break;
                case "2":
                    Drunkard.main();
                    break;
                case "3":
                    BlackJack.main();
                    break;
                default:
                    log.info("Игры с таким номером нет!");
            }
        }
        else log.info("К сожалению у вас кончились деньги, до свидания!");
    }


    static String getCharacterFromUser() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int symbol;
        while ( (symbol = System.in.read()) != LINE_SEPARATOR.getBytes()[0]) {
            stringBuilder.append((char) symbol);
        }
        return stringBuilder.toString();
    }
}
