import java.util.*;

public class AStar {

    static class Node {

        Node parent;
        int position[];
        int f, g, h;

        Node(Node parent, int position[]) {
            this.parent = parent;
            this.position = position;
            this.f = this.g = this.h = 0;
        }

        boolean equals(Node temp) {
            return this.position[0] == temp.position[0] &&
                    this.position[1] == temp.position[1];
        }
    }

    private static boolean notValid(int nodePosition[],
            int n, int m) {
        return nodePosition[0] > n - 1 || nodePosition[0] < 0 ||
                nodePosition[1] > m - 1 || nodePosition[1] < 0;
    }

    private static List<int[]> A_star(int board[][],
            int src[], int dest[]) {

        Node srcNode = new Node(null, src);
        Node destNode = new Node(null, dest);

        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        openList.add(srcNode);

        while (openList.size() > 0) {

            Node currentNode = openList.get(0);
            int currentIndex = 0;

            for (int i = 0; i < openList.size(); i++)
                if (openList.get(i).f < currentNode.f) {
                    currentNode = openList.get(i);
                    currentIndex = i;
                }

            openList.remove(currentIndex);
            closedList.add(currentNode);

            if (currentNode.equals(destNode)) {

                List<int[]> path = new ArrayList<>();
                Node current = currentNode;

                while (current != null) {
                    path.add(current.position);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            List<Node> children = new ArrayList<>();
            int dirs[][] = new int[][] {
                    { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 },
                    { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }
            };

            for (int newPosition[] : dirs) {
                int nodePosition[] = new int[] {
                        currentNode.position[0] + newPosition[0],
                        currentNode.position[1] + newPosition[1]
                };

                if (notValid(nodePosition, board.length, board[0].length) ||
                        board[nodePosition[0]][nodePosition[1]] != 0) {
                    continue;
                }

                children.add(new Node(currentNode, nodePosition));
            }

            for (Node child : children) {

                for (Node closedChild : closedList)
                    if (closedChild.equals(child))
                        continue;

                child.g = currentNode.g + 1;
                child.h = (int) ((Math.pow(child.position[0] -
                        destNode.position[0], 2)
                        + (Math.pow(child.position[1] -
                                destNode.position[1], 2))));
                child.f = child.g + child.h;

                for (Node openNode : openList) {
                    if (child.equals(openNode) && child.g > openNode.g)
                        continue;
                }
                openList.add(child);
            }

            if (openList.size() > board.length * board.length *
                    board[0].length * board[1].length)
                break;
        }
        return new ArrayList<>();
    }

    public static void main(String args[]) {
        int board[][] = new int[][] {
                { 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 1, 0 },
                { 1, 0, 1, 0, 0, 0 }
        };
        int src[] = new int[] { 1, 0 };
        int dest[] = new int[] { 2, 5 };

        List<int[]> pathSrcToDest = A_star(board, src, dest);
        for (int i = 0; i < pathSrcToDest.size(); i++) {
            System.out.print("(" + pathSrcToDest.get(i)[0] +
                    ", " + pathSrcToDest.get(i)[1] + ") ");
        }
    }
}
