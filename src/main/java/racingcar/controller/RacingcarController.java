package racingcar.controller;

import java.util.LinkedHashMap;
import racingcar.service.CarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingcarController {
    private final CarService carService;
    private int tryCount;

    public RacingcarController(CarService carService) {
        this.carService = carService;
    }

    public void run() {
        start();
        play();
        finish();
    }

    private void start() {
        initializeCarNames();
        initializeTryCount();
    }

    private void initializeCarNames() {
        try {
            OutputView.printNameInput();
            carService.validateNameInput(InputView.readCarNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initializeCarNames();
        }
    }

    private void initializeTryCount() {
        try {
            OutputView.printCountInput();
            tryCount = InputView.readTryCount();
            carService.validateCountInput(tryCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initializeTryCount();
        }
    }

    private void play() {
        OutputView.printResultMessage();
        for (int i = 0; i < tryCount; i++) {
            LinkedHashMap<String, Integer> roundResult = carService.runRound();
            OutputView.printRoundResult(roundResult);
            OutputView.printNewLine();
        }
    }

    private void finish() {
        OutputView.printRoundResult(carService.finishRound());
        OutputView.printWinners(carService.finishGame());
    }
}
