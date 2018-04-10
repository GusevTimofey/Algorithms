package GraphAlgs;

import java.util.*;

public class GraphAlgoritms {

    static Integer INF = Integer.MAX_VALUE;
    private static int[][] weightMatrix = {
            {0, 5, 4, INF, INF, INF, 3},
            {5, 0, 1, 2, INF, INF, INF},
            {4, 1, 0, INF, INF, INF, INF},
            {INF, 2, INF, 0, 3, INF, INF},
            {INF, INF, INF, 3, 0, 2, INF},
            {INF, INF, INF, INF, 2, 0, 1},
            {3, INF, INF, INF, INF, 1, 0}};

    public static class DFS {
        private int[][] adjacencyMatrix;
        private int vertexNum;
        private boolean[] isVisited;

        DFS(int[][] adjacencyMatrix) {
            vertexNum = adjacencyMatrix[0].length;
            this.adjacencyMatrix = adjacencyMatrix;
        }

        public void Run() {
            isVisited = new boolean[vertexNum];
            Stack<Integer> stack = new Stack<>();

            stack.push(0);

            while (!stack.empty()) {
                int node = stack.pop();
                if (isVisited[node])
                    continue;
                isVisited[node] = true;
                for (int i = vertexNum - 1; i >= 0; i--) {
                    if (adjacencyMatrix[node][i] == 1 && !isVisited[i]) {
                        stack.push(i);
                        isVisited[i] = false;
                    }
                }
                System.out.print(node + 1 + " ");
            }
            System.out.println();
        }
    }  //Depth First Search

    public static class BFS {
        private int[][] adjacencyMatrix;
        private int vertexNum;
        private boolean[] isVisited;

        BFS(int[][] adjacencyMatrix) {
            this.adjacencyMatrix = adjacencyMatrix;
            vertexNum = adjacencyMatrix[0].length;
        }

        public void Run() {
            isVisited = new boolean[vertexNum];
            Queue<Integer> queue = new LinkedList<>();

            queue.add(0);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (isVisited[node])
                    continue;
                isVisited[node] = true;
                for (int i = vertexNum - 1; i >= 0; i--) {
                    if (adjacencyMatrix[node][i] == 1 && !isVisited[i]) {
                        queue.add(i);
                        isVisited[i] = false;
                    }
                }
                System.out.print(node + 1 + " ");
            }
            System.out.println();
        }
    } //Breadth First Search

    public static class FloydWarshallAlg {

        public void Run() {
            for (int k = 0; k < weightMatrix.length; k++) {
                for (int i = 0; i < weightMatrix.length; i++) {
                    for (int j = 0; j < weightMatrix.length; j++) {
                        if (weightMatrix[i][k] < INF && weightMatrix[k][j] < INF)
                            if (weightMatrix[i][k] + weightMatrix[k][j] < weightMatrix[i][j])
                                weightMatrix[i][j] = weightMatrix[i][k] + weightMatrix[k][j];
                    }
                }
            }
            for (int[] aWeightMatrix : weightMatrix) {
                for (int j = 0; j < weightMatrix.length; j++) {
                    System.out.print(aWeightMatrix[j] + " ");
                }
                System.out.println();
            }
        }
    }      //O(v^3)

    public static class KruskalAlg {
        private int vertexNum;
        private int uNum;
        private int[][] tree;
        private int[] sets;
        private final int MAX = 10;
        private ArrayList<Edge> edges;
        private double cost;

        private class Edge implements Comparable<Edge> {
            int u;
            int v;
            double weight;

            // Конструктор
            Edge(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.weight = w;
            }

            // Компаратор
            @Override
            public int compareTo(Edge edge) {
                if (weight != edge.weight) return weight < edge.weight ? -1 : 1;
                return 0;
            }
        }

        KruskalAlg(int uNumA, int vNumA, int[][] matrix) {
            tree = new int[MAX][3];
            sets = new int[MAX];
            uNum = uNumA;
            vertexNum = vNumA;
            edges = new ArrayList<>();
            edges.add(new Edge(0, 0, 0));

            for (int i = 0; i < vertexNum; i++) {
                edges.add(new Edge(matrix[i][0], matrix[i][1], matrix[i][2]));
            }

            for (int i = 1; i <= uNum; i++) {
                sets[i] = i;
            }
            Collections.sort(edges);
        }

        private int Find(int vertex) {
            return sets[vertex];
        }

        private void Join(int v1, int v2) {
            if (v1 < v2) {
                for (int i = 0; i < sets.length; i++) {
                    if (sets[i] == v2)
                        sets[i] = v1;
                }
            } else {
                for (int i = 0; i < sets.length; i++) {
                    if (sets[i] == v1)
                        sets[i] = v2;
                }
            }
        }

        private void BuildingTree() {
            int k = uNum;
            cost = 0;
            int i, t = 1;
            for (i = 1; i <= k; i++) {
                if (Find(edges.get(i).u) != Find(edges.get(i).v)) {
                    tree[t][1] = edges.get(i).u;
                    tree[t][2] = edges.get(i).v;
                    cost += edges.get(i).weight;
                    Join(edges.get(t).u, edges.get(t).v);
                    t++;
                }
            }
        }

        public void Run() {
            BuildingTree();
            System.out.println("Вес: " + cost);
            System.out.println("Ребра минимального остовного дерева:");
            for (int i = 1; i < uNum; i++) {
                System.out.println(tree[i][1] + " - " + tree[i][2]);
            }
            System.out.println();
        }
    }   // (ElogE) E - num of vertex

    public static class DeikstraAlg {

        private static int INF = Integer.MAX_VALUE / 2;
        private int n; //количество вершин в орграфе
        private int m; //количествое дуг в орграфе
        private ArrayList adjacencyList[]; //список смежности
        private ArrayList weightList[]; //вес ребра в орграфе
        private boolean isVisited[]; //массив для хранения информации о пройденных и не пройденных вершинах
        private int dist[]; //массив для хранения расстояния от стартовой вершины
        private int pred[];//массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
        int start; //стартовая вершина, от которой ищется расстояние до всех других

        DeikstraAlg(int vNum, int uNum, int matrix[][], int start) {
            ReadData(vNum, uNum, matrix, start);
        }

        //процедура запуска алгоритма Дейкстры из стартовой вершины
        private void Deikstra(int s) {
            dist[s] = 0; //кратчайшее расстояние до стартовой вершины равно 0
            for (int iter = 0; iter < n; ++iter) {
                int v = -1;
                int distV = INF;
                for (int i = 0; i < n; ++i) {
                    if (isVisited[i]) {
                        continue;
                    }
                    if (distV < dist[i]) {
                        continue;
                    }
                    v = i;
                    distV = dist[i];
                }
                for (int i = 0; i < adjacencyList[v].size(); ++i) {
                    int u = (int) adjacencyList[v].get(i);
                    int weightU = (int) weightList[v].get(i);
                    if (dist[v] + weightU < dist[u]) {
                        dist[u] = dist[v] + weightU;
                        pred[u] = v;
                    }
                }
                isVisited[v] = true;
            }
        }

        private void printWay(int v) {
            if (v == -1) {
                return;
            }
            printWay(pred[v]);
            System.out.print((v + 1) + " ");
        }

        public void Run() {
            Deikstra(start);
            System.out.println("Минимальный путь из " + (start + 1) + " в j: ");
            for (int v = 0; v < n; ++v) {
                if (dist[v] != INF) {
                    System.out.print(dist[v] + " ");
                } else {
                    System.out.print("-1 ");
                }
            }
            System.out.println();
            for (int v = 0; v < n; ++v) {
                System.out.print((v + 1) + ": ");
                if (dist[v] != INF) {
                    printWay(v);
                }
                System.out.println();
            }
        }

        void ReadData(int uNum, int vNum, int matrix[][], int startA) {
            n = uNum;
            m = vNum;
            start = startA - 1;
            adjacencyList = new ArrayList[2 * n];
            for (int i = 0; i < 2 * n; ++i) {
                adjacencyList[i] = new ArrayList();
            }
//инициализация списка, в котором хранятся веса ребер
            weightList = new ArrayList[2 * n];
            for (int i = 0; i < 2 * n; ++i) {
                weightList[i] = new ArrayList();
            }
//считываем граф, заданный списком ребер
            for (int i = 0; i < m; ++i) {
                int u = matrix[i][0];
                int v = matrix[i][1];
                int w = matrix[i][2];
                u--;
                v--;
                adjacencyList[u].add(v);
                adjacencyList[v].add(u);
                weightList[u].add(w);
                weightList[v].add(w);
            }
            isVisited = new boolean[2 * n];
            Arrays.fill(isVisited, false);
            pred = new int[2 * n];
            Arrays.fill(pred, -1);
            dist = new int[2 * n];
            Arrays.fill(dist, INF);
        }
    } //0(ElogV)   O(V^2)

    public static class PrimAlg {
        private static int INF = Integer.MAX_VALUE / 2;
        private int n; //количество вершин в орграфе
        private int m; //количествое дуг в орграфе
        private ArrayList adjacencyList[]; //список смежности
        private ArrayList weightList[]; //вес ребра в орграфе
        private boolean isVisited[]; //массив для хранения информации о пройденных и не пройденных вершинах
        private int dist[]; //массив для хранения расстояния от стартовой вершины
        private int pred[];//массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
        int start; //стартовая вершина, от которой ищется расстояние до всех других

        PrimAlg(int vNum, int uNum, int matrix[][], int start) {
            ReadData(vNum, uNum, matrix, start);
        }

        //процедура запуска алгоритма Прима из стартовой вершины
        private void Prim(int s) {
            dist[s] = 0; //кратчайшее расстояние до стартовой вершины равно 0
            for (int iter = 0; iter < n; ++iter) {
                int v = -1;
                int distV = INF;
                for (int i = 0; i < n; ++i) {
                    if (isVisited[i]) {
                        continue;
                    }
                    if (distV < dist[i]) {
                        continue;
                    }
                    v = i;
                    distV = dist[i];
                }
                for (int i = 0; i < adjacencyList[v].size(); ++i) {
                    int u = (int) adjacencyList[v].get(i);
                    int weightU = (int) weightList[v].get(i);
                    if (weightU < dist[u] && !isVisited[u]) {
                        dist[u] = weightU;
                        pred[u] = v;
                    }
                }
                isVisited[v] = true;

            }
        }

        public void Run() {
            Prim(start);
            System.out.println("Минимальный путь из " + (start + 1) + " в j: ");
            for (int v = 0; v < n; ++v) {
                if (dist[v] != INF) {
                    System.out.print(dist[v] + " ");
                } else {
                    System.out.print("-1 ");
                }
            }
            System.out.println();
        }

        void ReadData(int uNum, int vNum, int matrix[][], int startA) {
            n = uNum;
            m = vNum;
            start = startA - 1;
            adjacencyList = new ArrayList[2 * n];
            for (int i = 0; i < 2 * n; ++i) {
                adjacencyList[i] = new ArrayList();
            }
//инициализация списка, в котором хранятся веса ребер
            weightList = new ArrayList[2 * n];
            for (int i = 0; i < 2 * n; ++i) {
                weightList[i] = new ArrayList();
            }
//считываем граф, заданный списком ребер
            for (int i = 0; i < m; ++i) {
                int u = matrix[i][0];
                int v = matrix[i][1];
                int w = matrix[i][2];
                u--;
                v--;
                adjacencyList[u].add(v);
                adjacencyList[v].add(u);
                weightList[u].add(w);
                weightList[v].add(w);
            }
            isVisited = new boolean[2 * n];
            Arrays.fill(isVisited, false);
            pred = new int[2 * n];
            Arrays.fill(pred, -1);
            dist = new int[2 * n];
            Arrays.fill(dist, INF);
        }
    }


}
