import java.util.ArrayList;

public class Results {
    public String algorithm;
    public ArrayList<Integer> positions;
    public long timeTaken;
    public int totalSteps = 0;

    private int steps = 0;

    public Results(String algorithm){
        positions = new ArrayList<>();
        this.algorithm = algorithm;
    }

    public void addPosition(int position){
        positions.add(position);
        steps++;
    }

    public int getSteps(){
        return steps;
    }

    public void show(){
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Total Steps: " + totalSteps);
        System.out.println("Time Taken: " + timeTaken + "ms");
        System.out.println("Path Length: " + steps);
        System.out.print("Path: (");
        for(int i = 0; i < positions.size(); i++){
            System.out.print(positions.get(i) + ",");
        }
        System.out.println(")");
    }
}
