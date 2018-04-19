package GraphAlgs;

public class Main {
    public static void main(String[] args) {
        int matrix[][] = {
                {1, 2, 5},
                {2, 3, 14},
                {2, 4, 19},
                {3, 4, 15},
                {3, 5, 10},
                {4, 5, 11},
                {5, 6, 2},
                {6, 7, 6},
                {7, 4, 9},
        };

        int[][] adjacencyMatrix = {
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0}};

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Поиск в глубину: ");
        GraphAlgoritms.DFS dfs = new GraphAlgoritms.DFS(adjacencyMatrix);

        dfs.Run();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Поиск в ширину: ");
        GraphAlgoritms.BFS bfs = new GraphAlgoritms.BFS(adjacencyMatrix);
        bfs.Run();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Алгоритм Флойда-Уоршалла:");
        GraphAlgoritms.FloydWarshallAlg floydWarshallAlg = new GraphAlgoritms.FloydWarshallAlg();
        floydWarshallAlg.Run();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Алгоритм Краскала: ");
        GraphAlgoritms.KruskalAlg kruskalAlg = new GraphAlgoritms.KruskalAlg(6, 9, matrix);
        kruskalAlg.Run();
        System.out.println("---------------------------------------------------------------------------------------");
        GraphAlgoritms.DeikstraAlg deikstraAlg = new GraphAlgoritms.DeikstraAlg(6, 9, matrix, 1);
        System.out.println("Алгоритм Дейкстры: ");
        deikstraAlg.Run();
        System.out.println("---------------------------------------------------------------------------------------");
        GraphAlgoritms.PrimAlg primAlg = new GraphAlgoritms.PrimAlg(6, 9, matrix, 1);
        System.out.println("Алгоритм Прима: ");
        primAlg.Run();
        System.out.println("---------------------------------------------------------------------------------------");


    }
}
