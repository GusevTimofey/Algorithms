package ImprovedAlgsOnGraphs;

import java.util.Stack;

public class AlgClass {
    public static class TarianAlg {
        private int[][] adjacencyMatrix;
        private int[] colour;
        private int vertexNum;

        public TarianAlg(int[][] adjacencyMatrix) {
            this.adjacencyMatrix = adjacencyMatrix;
            this.vertexNum = adjacencyMatrix.length;
        }

        public void Run() {
            colour = new int[vertexNum];
            Stack<Integer> stack = new Stack<>();

            stack.push(0);

            while (!stack.empty()) {
                int node = stack.peek();
                if (colour[node] == 2)
                    continue;
                colour[node] = 1;
                for (int i = vertexNum - 1; i >= 0; i--) {
                    if (adjacencyMatrix[node][i] == 1 && colour[i] == 0) {
                        stack.push(i);
                        colour[i] = 1;
                    }

                }

                int otherNode = stack.peek();
                for (int i = vertexNum - 1; i >=0 ; i--) {
                    if(adjacencyMatrix[otherNode][i] == 1 && colour[i] == 0)
                        return;
                    else{
                        node = stack.pop();
                        colour[node] = 2;
                    }
                }

//                colour[node] = 2;
                System.out.print(node + 1 + " ");
            }
            System.out.println();
        }
    }
}
