public class MazeStatistics {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.out.println("Usage: java MazeStatistics <maze file> <tries>");
            System.exit(1);
            return;
        }
        Maze m = Maze.readMaze(args[0]);
        int tries = Integer.parseInt(args[1]);

        System.out.println("Average time for DFS: " + averagePerfDFS(m, tries));
        System.out.println("Average time for BFS: " + averagePerfBFS(m, tries));
    }

    public static double averagePerfDFS(Maze m, int runs) {

        //We collect all the solving times and make an average to limit randomness

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < runs; i++){
            MazeSolver.solveMazeDFS(m);
        }

        long endTime = System.currentTimeMillis();

        double d = (endTime - startTime) * 1.0 / runs;
        
        return d;
    }

        public static double averagePerfBFS(Maze m, int runs) {

        //We collect all the solving times and make an average to limit randomness

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < runs; i++){
            MazeSolver.solveMazeBFS(m);
        }

        long endTime = System.currentTimeMillis();

        double d = (endTime - startTime) * 1.0 / runs;
        
        return d;
    }   
}
