package lotto;

import lotto.Controller.Controller;

public class Application {
    public static void main(String[] args) {
        Controller gameController = new Controller();
        gameController.playingGame();
    }
}
