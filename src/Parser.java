import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {
    static int size = 0;

    public List<Flower> parse(String filename){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            List<Flower> toReturn = new ArrayList<>();
            for (String line :
                    bufferedReader.lines().toList()
                 ) {
                String nline = line.replaceAll("[,]", ".").replaceAll("\\p{L}", "").replaceAll(" ", "").replaceAll("-", "");
                String[] arr = nline.split("\\s+");
                int length = arr.length;
                size = length;
                double[] hmm = new double[length];
                for (int i = 0; i < length; i++) {
                    hmm[i] = Double.parseDouble(arr[i]);
                }
                toReturn.add(new Flower(hmm, line.replaceAll("\\P{L}", "")));
            }
            return toReturn;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
