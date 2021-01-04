import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FibonacciSequence {
    private static Map<Integer,BigInteger> sequence;
    private FibonacciSequenceBackup fibonacciSequenceBackup;

    public FibonacciSequence() {
        sequence = new HashMap<>();
        fibonacciSequenceBackup= new FibonacciSequenceBackup();
    }

    public void generate(int iterations) {
        BigInteger lastIteration;
        BigInteger beforeLastIteration;
        BigInteger sum;
        if(iterations <=0){
            throw new IllegalArgumentException("Cannot generate 0 iterations");
        }
        for(int i=0; i<iterations; i++){
            if(i==0){
                sequence.put(0,BigInteger.ZERO);
            } else if(i==1){
                sequence.put(1,BigInteger.ONE);
            } else {
                lastIteration = sequence.get(i-1);
                beforeLastIteration = sequence.get(i-2);
                sum = lastIteration.add(beforeLastIteration);
                sequence.put(i, sum);
            }
        }
    }

    public Map<Integer, BigInteger> getSequence() {
        return this.sequence;
    }

    public BigInteger getElement(int i) {
        if(sequence.size() < i || i<0){
            throw new IllegalArgumentException("Wrong argument. Element doesn't exist.");
        }
        return sequence.get(i-1);
    }

    public void clean() {
        sequence.clear();
    }

    public boolean backup() {
        if(sequence.isEmpty()){
            return false;
        }else{
            fibonacciSequenceBackup.backup(sequence);
            return true;
        }
    }
}
