package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.service.CarService;

class CarTest {
    @Nested
    @DisplayName("자동차 이름 길이 테스트")
    class TestNameLength {
        @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
        @ValueSource(strings = {"", "abcdef", "pobiiiiii"})
        @DisplayName("자동차 이름의 길이가 0 이하거나 5보다 클 경우 예외 발생")
        void Should_ThrowException_When_NameMoreThan6(String name) {
            assertThatThrownBy(() -> new Car(name, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 자동차 이름의 길이는 1부터 5 사이여야 합니다.");
        }

        @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
        @ValueSource(strings = {"a", "abc", "abcde"})
        @DisplayName("자동차 이름의 길이가 1 이상 5 이하인 경우 성공")
        void Should_Success_When_NameLessThan5(String name) {
            assertDoesNotThrow(() -> new Car(name, 0));
        }
    }

    @Nested
    @DisplayName("자동차 이름 형식 테스트")
    class TestNameFormat {
        @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
        @ValueSource(strings = {" ", "abc!", "123* "})
        @DisplayName("자동차 이름이 영숫자가 아닌 경우 예외 발생")
        void Should_ThrowException_When_NameIsNotEnglishAndNumber(String name) {
            assertThatThrownBy(() -> new Car(name, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 자동차 이름은 영숫자로 이루어져야 합니다.");
        }

        @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
        @ValueSource(strings = {"a1", "a2cd", "ab34e"})
        @DisplayName("자동차 이름이 영숫자인 경우 성공")
        void Should_Success_When_NameIsEnglishAndNumber(String name) {
            assertDoesNotThrow(() -> new Car(name, 0));
        }
    }

    @Nested
    @DisplayName("전진 테스트")
    class TestForward {
        CarService carService = new CarService();
        Car car = new Car("test", 0);

        @ParameterizedTest(name = "{displayName} {index} ==> engine : ''{0}''")
        @ValueSource(ints = {0, 2, 3})
        @DisplayName("랜덤 값이 4보다 작은 경우 멈춤")
        void Should_Success_When_EngineLessThan4(int engine) {
            carService.runForward(car, engine);
            assertThat(car.getDistance()).isEqualTo(0);
        }

        @ParameterizedTest(name = "{displayName} {index} ==> engine : ''{0}''")
        @ValueSource(ints = {4, 7})
        @DisplayName("랜덤 값이 4 이상인 경우 전진")
        void Should_Success_When_EngineMoreThan4(int engine) {
            carService.runForward(car, engine);
            assertThat(car.getDistance()).isEqualTo(1);
        }
    }
}
