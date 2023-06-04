import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean res = true;
        int  n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    res = false;
                    break;
                }
            }
        }
        if (res) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}