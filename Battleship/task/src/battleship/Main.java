package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int BOARD_LENGTH = 10;
    private static final char FOG_CELL = '~';
    private static final char SHIP_CELL = 'O';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'M';
    static Scanner scanner = new Scanner(System.in);

    private static char[][] playerField;

    private enum ShipName {
        AIRCRAFT_CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        private final String name;
        private final int size;

        ShipName(String name, int size) {
            this.name = name;
            this.size = size;
        }

        private String getName() {
            return name;
        }

        private int getSize() {
            return size;
        }
    }

    public static void start() {
            placeShips();
    }

    private static void createPlayerField() {
        playerField = new char[BOARD_LENGTH][BOARD_LENGTH];
        for (char[] row : playerField) {
            Arrays.fill(row, FOG_CELL);
        }
    }

    private static void printPlayerField(char[][] board) {
        System.out.print("  ");
        for (int i = 1; i <= BOARD_LENGTH; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        char letter = 'A';
        for (char[] row : board) {
            System.out.print(letter);
            for (char cell : row) {
                System.out.print(" " + cell);
            }
            System.out.println();
            letter++;
        }
    }

    private static void placeShips() {
        for (ShipName ship : ShipName.values()) {
                boolean validPlacement = false;
                while (!validPlacement) {
                    printPlayerField(playerField);
                    System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.getName(), ship.getSize());
                    String startInput = scanner.next().toUpperCase();
                    String endInput = scanner.next().toUpperCase();

                    try {
                        int[] startCoordinate = parseCoordinate(startInput);
                        int[] endCoordinate = parseCoordinate(endInput);

                        checkPlacement(startCoordinate, endCoordinate, ship.getSize(), ship);

                        validPlacement = true;
                    } catch (Exception e) {
                        System.out.println("Error! " + e.getMessage());
                    }
                }
        }
    }

    private static int[] parseCoordinate (String coordinate) {
        int column = Integer.parseInt(coordinate.substring(1)) - 1;
        int row = coordinate.charAt(0) - 'A';
        return new int[]{row, column};
    }

    private static void checkPlacement(int[] startCoordinate, int[] endCoordinate, int shipSize, ShipName ship) {
        int tempStartRow = startCoordinate[0];
        int tempStartColumn = startCoordinate[1];
        int tempEndRow = endCoordinate[0];
        int tempEndColumn = endCoordinate[1];

        int startRow = Math.min(tempStartRow, tempEndRow);
        int startColumn = Math.min(tempStartColumn, tempEndColumn);
        int endRow = Math.max(tempStartRow, tempEndRow);
        int endColumn = Math.max(tempStartColumn, tempEndColumn);

        int shipLength = Math.max(Math.abs(startRow - endRow), Math.abs(startColumn - endColumn)) + 1;

        if (startRow != endRow && startColumn != endColumn) {
            throw new IllegalArgumentException("Wrong ship location! Try again:");
        } else if (shipLength != shipSize) {
            throw new IllegalArgumentException("Wrong length of the " + ship.getName() + "! Try again:");
        } else if (isTooClose(startRow, startColumn, endRow, endColumn)) {
            throw new IllegalArgumentException("You placed it too close to another one. Try again:");
        } else {
            placeShip(startRow, startColumn, endRow, endColumn);
        }
    }

    private static boolean isTooClose(int startRow, int startColumn, int endRow, int endColumn) {
        for (int i = Math.max(0, startRow - 1); i <= Math.min(BOARD_LENGTH - 1, endRow + 1); i++) {
            for (int j = Math.max(0, startColumn - 1); j <= Math.min(BOARD_LENGTH - 1, endColumn + 1); j++) {
                if ((i >= startRow && i <= endRow) && (j >= startColumn && j <= endColumn)) {
                    continue;
                } else if (playerField[i][j] == SHIP_CELL) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void placeShip(int startRow, int startColumn, int endRow, int endColumn) {
        for (int x = Math.min(startRow, endRow); x <= Math.max(startRow, endRow); x++) {
            for (int y = Math.min(startColumn, endColumn); y <= Math.max(startColumn, endColumn); y++) {
                playerField[x][y] = SHIP_CELL;
            }
        }
    }

    public static void main(String[] args) {
        createPlayerField();
        start();
    }
}