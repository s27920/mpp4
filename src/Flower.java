public class Flower {
    private double[] values;
    private Cluster belongsTo;
    private String label;

    public void setBelongsTo(Cluster belongsTo) {
        this.belongsTo = belongsTo;
    }

    public Flower(double[] values, String label) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public double[] getValues() {
        return values;
    }

    public double getDist(double[] other){
        double dist = 0.0;
        for (int i = 0; i < values.length; i++) {
            dist+= Math.pow(values[i] - other[i], 2);
        }
        return dist;
    }

    public int migrate(Cluster[] clusters){
        Cluster toMigrateTo = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Cluster cluste :
                clusters) {
            double dist1 = getDist(cluste.getCentroid());
            if (dist1 < dist){
                toMigrateTo = cluste;
                dist = dist1;
            }
        }
        if (this.belongsTo == toMigrateTo){
            return 0;
        }
        takeOutPutIn(this.belongsTo, toMigrateTo);
        return 1;
    }

    public void takeOutPutIn(Cluster c1, Cluster c2){
        c1.removeFromCluster(this);
        c2.addToCluster(this);
    }
}
