public class App {
    public static void main(String[] args) throws Exception {

        if(args.length == 0){
            System.out.println("Hello World!");
            Maze m = new Maze(5, 5);
            //m.mazeGen();
            m.displayMaze();
        }
        else{
            int rows = Integer.parseInt(args[0]);
            int columns = Integer.parseInt(args[1]);
            Maze m = new Maze(rows, columns);
            //m.mazeGen();
            m.displayMaze();
        }
    }
}
