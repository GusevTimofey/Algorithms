package ImprovedAlgsOnGraphs;

public class Main {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {{0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0}};

        AlgClass.TarianAlg tarianAlg = new AlgClass.TarianAlg(adjacencyMatrix);
        tarianAlg.Run();
    }
}
