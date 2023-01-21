package pl.kul.projekt.patient.email;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    private final Validator validator = new Validator();

    @ParameterizedTest
    @MethodSource("data")
    public void shouldValidateEmail(String email, Boolean expected) {
        assertEquals(validator.isValid(email), expected);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("przyklad@gmail.com", true),
                Arguments.of("przykl@ad@gmail.com", false),
                Arguments.of("przykl@ad@gmail.", false),
                Arguments.of("przy.klad@gm.ail.com", true),
                Arguments.of("przykladgm.ail.com", false),
                Arguments.of("@gmail.com", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        );
    }
}