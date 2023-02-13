package racingcar.view;

import java.util.List;
import java.util.Map;
import racingcar.constants.InputConstant;
import racingcar.constants.OutputConstant;

public class OutputView {
    public static void printNameInput() {
        System.out.println(InputConstant.CAR_NAME_INPUT_MESSAGE.getMessage());
    }

    public static void printCountInput() {
        System.out.println(InputConstant.TRY_COUNT_INPUT_MESSAGE.getMessage());
    }

    public static void printResultMessage() {
        System.out.println(OutputConstant.RESULT_MESSAGE.getMessage());
    }

    public static void printRoundResult(Map<String, Integer> roundResult) {
        for (String key : roundResult.keySet()) {
            Integer value = roundResult.get(key);
            printCarResult(key, value);
        }
    }

    public static void printCarResult(String carName, int distance) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(carName).append(OutputConstant.CAR_INFORMATION_DELIMITER.getMessage());
        stringBuilder.append(OutputConstant.DISTANCE_COMMAND.getMessage().repeat(distance));
        System.out.println(stringBuilder.toString());
    }

    public static void printWinners(List<String> winnerNames) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        String str = String.join(OutputConstant.WINNER_NAME_DELIMITER.getMessage(), winnerNames);
        stringBuilder.append(str).append(OutputConstant.WINNER_MESSAGE.getMessage());
        System.out.println(stringBuilder.toString());
    }

    public static void printNewLine() {
        System.out.println("");
    }
}
