import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("Nodes evaluated: " + totalSteps);
        System.out.println("Time Taken: " + timeTaken + "ms");
        System.out.println("Path Length: " + steps);
        System.out.print("Path: (");
        for(int i = 0; i < positions.size(); i++){
            System.out.print(positions.get(i));
            if(i != positions.size() - 1){
                System.out.print(", ");
            }
        }
        
        System.out.println(")");
    }

    public void export(String fileName){
        String content = steps + ":(";


        for(int i = 0; i < positions.size(); i++){
            content += positions.get(i);
            if(i != positions.size() - 1){
                content += ",";
            }
        }

        content += ")";

        try {
            // Create a FileWriter with the given file name
            FileWriter fileWriter = new FileWriter(fileName);

            // Create a BufferedWriter to write efficiently
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the string to the file
            bufferedWriter.write(content);

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("String has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
