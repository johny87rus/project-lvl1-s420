package games;

import java.io.IOException;

public class Choice {
    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\"");
        switch (System.in.read()) {
            case '1': Slot.main(new String[0]); break;
            case '2': Drunkard.main(new String[0]); break;
            default: System.out.println("Игры с таким номером нет!");
        }
    }
}
