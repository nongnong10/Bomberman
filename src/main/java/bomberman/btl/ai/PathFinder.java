package bomberman.btl.ai;

import bomberman.btl.main.GamePanel;

import java.util.ArrayList;
import java.util.Map;

public class PathFinder {
    public GamePanel gamePanel;
    public Node[][] nodes;
    public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();


    public Node startNode, goalNode, currentNode;
    public boolean goalReached = false;
    public int step = 0;

    public PathFinder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        instantiateNodes();
    }

    public void instantiateNodes() {
        nodes = new Node[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                nodes[i][j] = new Node(j, i);
            }
        }
    }

    public void resetNodes() {
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                nodes[i][j].checked = false;
                nodes[i][j].open = false;
                nodes[i][j].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void getCost(Node node) {
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //F cost
        node.fCost = node.gCost + node.hCost;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        startNode = nodes[startRow][startCol];
        goalNode = nodes[goalRow][goalCol];
        currentNode = startNode;
        openList.add(currentNode);

        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                //CHECK TILE
                int tileNum = gamePanel.tileManager.mapTileNum[i][j];
                if (gamePanel.tileManager.tile[tileNum].collision == true) {
                    nodes[i][j].solid = true;
                }

                //CHECK INTERACTIVE TILE
                for (int ind = 0; ind < gamePanel.interactiveTiles.length; ++ind) {
                    if (gamePanel.interactiveTiles[ind] != null && gamePanel.interactiveTiles[ind].destructible == true) {
                        int row = gamePanel.interactiveTiles[ind].worldY / gamePanel.tileSize;
                        int col = gamePanel.interactiveTiles[ind].worldX / gamePanel.tileSize;
                        nodes[row][col].solid = true;
                    }
                }

                getCost(nodes[i][j]);
            }
        }
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void tracePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }

    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int row = currentNode.row;
            int col = currentNode.col;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row > 0) {
                openNode(nodes[row - 1][col]);
            }
            if (row + 1 < gamePanel.maxScreenRow) {
                openNode(nodes[row + 1][col]);
            }
            if (col > 0) {
                openNode(nodes[row][col - 1]);
            }
            if (col + 1 < gamePanel.maxScreenCol) {
                openNode(nodes[row][col + 1]);
            }

            //FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); ++i) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            if (openList.isEmpty()) {
                break;
            }

            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                tracePath();
            }
            step++;
        }
        return goalReached;
    }
}
