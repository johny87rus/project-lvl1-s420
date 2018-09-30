package games;

import java.io.IOException;

public class Choice {
    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\"");
        switch (getCharacterFromUser()) {
            case '1': Slot.main(new String[0]); break;
            case '2': Drunkard.main(new String[0]); break;
            default: System.out.println("Игры с таким номером нет!");
        }
    }


    static char getCharacterFromUser() throws IOException {
        byte[] input = new byte[1 + LINE_SEPARATOR.length()];
        if (System.in.read(input) != input.length)
            throw new RuntimeException("Пользователь ввёл недостаточное кол-во символов");
        return (char) input[0];
    }
}
