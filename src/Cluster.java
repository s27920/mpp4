import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cluster {
    private List<Flower> thisCluster;
    private double[] centroid;
    private String label;

    public Cluster(List<Flower> thisCluster) {
        this.thisCluster = thisCluster;
    }

    public void addToCluster(Flower flower){
        thisCluster.add(flower);
        flower.setBelongsTo(this);
    }
    public void removeFromCluster(Flower flower){
        thisCluster.remove(flower);
    }

    public double getEntropy() {
        Map<String, Integer> belongs = new HashMap<>();
        for (Flower flower : thisCluster) {
            String label = flower.getLabel();
            belongs.put(label, belongs.getOrDefault(label, 0) + 1);
        }
        double sum = 0.0;
        int size = thisCluster.size();
        for (Map.Entry<String, Integer> entry : belongs.entrySet()) {
            double pi = entry.getValue() / (double) size;
            if (pi > 0) {
                sum += pi * Math.log(pi) / Math.log(2);
            }
        }
        setLabel(belongs);
        return -sum;
    }

    private void setLabel(Map<String , Integer> map){
        String label = "";
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max){
                max = entry.getValue();
                label = entry.getKey();
            }
        }
        this.label = label;
    }

    public void calculateCentroid(){
        double[] centroid = new double[Parser.size];
        int counter = 0;
        for (Flower flower :
                thisCluster) {
            counter++;
            double[] values = flower.getValues();
            for (int i = 0; i < values.length; i++) {
                centroid[i]+=values[i];
            }
        }
        for (int i = 0; i < centroid.length; i++) {
            centroid[i]/=counter;
        }
        this.centroid = centroid;
    }

    public double getDSquared(){
        double toReturn = 0.0;
        for (Flower fl :
                thisCluster) {
            toReturn += Math.pow(fl.getDist(centroid),2);
        }
        return toReturn;
    }

    public double[] getCentroid() {
        return centroid;
    }

    public String getLabel() {
        return label;
    }

}
