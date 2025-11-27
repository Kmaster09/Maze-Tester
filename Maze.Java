import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

    private char[][] grid;
    private boolean solvable;
    private int startR, startC;

    public Maze(String filename) throws IOException {
        grid = new char[20][20];
        loadMaze(filename);
        solvable = isSolvable();
    }

    private void loadMaze(String filename) throws IOException {
        Scanner input = new Scanner(new File(filename));

        for (int r = 0; r < 20; r++) {
            String line = input.nextLine();

            for (int c = 0; c < 20; c++) {
                grid[r][c] = line.charAt(c);

                if (grid[r][c] == 'S') {
                    startR = r;
                    startC = c;
                }
            }
        }

        input.close();
    }

    private boolean isSolvable() {
        boolean[][] visited = new boolean[20][20];
        return dfs(startR, startC, visited);
    }

    private boolean dfs(int r, int c, boolean[][] visited) {
        if (r < 0 || c < 0 || r >= 20 || c >= 20) return false;
        if (grid[r][c] == '#' || visited[r][c]) return false;

        if (grid[r][c] == 'E') return true;

        visited[r][c] = true;

        return dfs(r - 1, c, visited) ||
               dfs(r + 1, c, visited) ||
               dfs(r, c - 1, visited) ||
               dfs(r, c + 1, visited);
    }

    public String toString() {
        return "Maze: " + (solvable ? "YES" : "NO");
    }

    public static void main(String[] args) {
        try {
            Maze m = new Maze("maze.dat");
            System.out.println(m);
        } catch (IOException e) {
            System.out.println("Error loading maze file.");
        }
    }
}
