import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class FibonacciSequenceBackup {

    void backup(Map<Integer, BigInteger> sequence){
        File file = new File(specifyFilePath(sequence.size()));
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(Map.Entry<Integer, BigInteger> entry : sequence.entrySet()){
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    String specifyFilePath(int specifier) {
        String path = "C://Users/barto/IdeaProjects/FibonacciSequence/src/main/resources/fibonacci";
        path += "-"+specifier+".txt";
        return path;
    }
}
