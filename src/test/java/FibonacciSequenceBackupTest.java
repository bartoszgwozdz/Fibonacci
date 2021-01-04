import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FibonacciSequenceBackupTest {
    FibonacciSequenceBackup fibonacciSequenceBackup;

    @BeforeEach
    void setUp(){
        fibonacciSequenceBackup = new FibonacciSequenceBackup();
    }
    @Test
    void shouldSpecifyFileName(){
        //given
        //when
        String specFilePath = fibonacciSequenceBackup.specifyFilePath(createExampleMapWithSize10().size());
        //then
        assertThat(specFilePath, is("C://Users/barto/IdeaProjects/FibonacciSequence/src/main/resources/fibonacci-10.txt"));
    }

    @Test
    void shouldBackUpGivenMapAsFileWithSpecifiedName(){
        //given
        File file = new File("C://Users/barto/IdeaProjects/FibonacciSequence/src/main/resources/fibonacci-10.txt");
        //when
        fibonacciSequenceBackup.backup(createExampleMapWithSize10());
        //then
        assertTrue(file.exists());
    }
    @Test
    void shouldBackUpGivenMapInProperFormat(){
        //given
        Map<Integer, BigInteger> exampleMapMap = createExampleMapWithSize10();
        File file = new File("C://Users/barto/IdeaProjects/FibonacciSequence/src/main/resources/fibonacci-10.txt");
        //when
        fibonacciSequenceBackup.backup(exampleMapMap);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            assertThat(reader.lines().count(), is((long)exampleMapMap.size()));
        }catch (IOException e){
            e.printStackTrace();
        }
        //then
        assertTrue(file.exists());
    }

    private Map<Integer, BigInteger> createExampleMapWithSize10(){
        Map<Integer, BigInteger> result = new HashMap<>();
        for(int i =0; i<10; i++){
            result.put(i, BigInteger.valueOf((i+3)<<16));
        }
        return result;
    }

    @AfterAll
    static void cleanUp(){
        File file = new File("C://Users/barto/IdeaProjects/FibonacciSequence/src/main/resources/fibonacci-10.txt");
        file.delete();
    }
}
