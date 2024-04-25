import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        List<Flower> flowers = parser.parse("iris_training.txt");
        int k = getInput();
        Cluster[] cluster = new Cluster[k];
        for (int i = 0; i < k; i++) {
            cluster[i] = new Cluster(new ArrayList<>());
            cluster[i].addToCluster(flowers.get(i));
        }
        for (int i = 3; i < flowers.size(); i++) {
            cluster[(int)(Math.random()*k)].addToCluster(flowers.get(i));
        }

        int counter;
        int iter = 1;
        do {
            counter = 0;
            for (Cluster c : cluster) {
                c.calculateCentroid();
            }
            for (Flower fl : flowers) {
                counter+=fl.migrate(cluster);
            }
            double dSquared = 0.0;
            for (Cluster c : cluster) {
                dSquared += c.getDSquared();
            }
            System.out.println("Iteration " + iter++ + " distance squared: " + dSquared);
        }   while (counter != 0);
        int cCounter = 1;
        for (Cluster cluster1: cluster) {
            System.out.println("\ncluster " + cCounter++ + ": " + Arrays.toString(cluster1.getCentroid()));
            double entropy = cluster1.getEntropy();
            System.out.println("entropy for " + cluster1.getLabel() + ": " + entropy);
        }
    }
    private static int getInput(){
        System.out.print("PLease input param k:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
