import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciSequenceTest {
    static FibonacciSequence generator;
    @BeforeAll
    static void setUp(){
       generator = new FibonacciSequence();
    }
    @BeforeEach
    void cleanUp(){
        generator.clean();
    }
    @Test
    void shouldHaveEmptyListAfterCreation(){
        assertThat(generator.getSequence().values(), emptyCollectionOf(BigInteger.class));
    }
    @Test
    void shouldNotAcceptZeroAsArgument(){
        assertThrows(IllegalArgumentException.class, () -> generator.generate(0));
    }
    @Test
    void shouldNotAcceptNegativeArguments(){
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-4));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,10,550})
    void shouldGenerateExactNumberOfIterations(int iterations){
        //given
        generator.generate(iterations);
        //when
        Map<Integer,BigInteger> generatedSequence = generator.getSequence();
        //then
        assertThat(generatedSequence.size(), is(iterations));
    }
    @Test
    void shouldBeAbleToGetElementFromGeneratedSequence(){
        //given
        generator.generate(10);
        //when
        //then
        assertThat(generator.getElement(10), notNullValue());
    }
    @Test
    void shouldNotAllowToGetElementThatDoesNotExist(){
        //given
        generator.generate(10);
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> generator.getElement(11));
        assertThrows(IllegalArgumentException.class, () -> generator.getElement(-11));
    }
    @ParameterizedTest
    @MethodSource("createTestValuePairs")
    void shouldGenerateCorrectValues(int iteration, int value){
        //given
        generator.generate(20);
        //when
        BigInteger result = generator.getElement(iteration);
        //then
        assertThat(result, equalTo(BigInteger.valueOf((long)value)));
    }

    private static Stream<Arguments> createTestValuePairs(){
        return Stream.of(
                Arguments.of(5,3),
                Arguments.of(8,13),
                Arguments.of(15,377)
        );
    }

}
