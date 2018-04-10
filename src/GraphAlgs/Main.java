package GraphAlgs;

public class Main {
    public static void main(String[] args) {
        int matrix[][] = {
                {1, 2, 7},
                {1, 6, 14},
                {1, 3, 9},
                {2, 4, 15},
                {2, 3, 10},
                {3, 4, 11},
                {3, 6, 2},
                {4, 5, 6},
                {5, 6, 9},
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
