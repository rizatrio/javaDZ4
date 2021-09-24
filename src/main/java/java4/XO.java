package java4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class XO {

    private static char[][] map;
    private static final char X_DOT = 'X';
    private static final char O_DOT = 'O';
    private static final char EMPTY_DOT = '•';
    private static final int mapSize = 3;
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWin(X_DOT)) {
                System.out.println("YOU WIN");
                break;
            }
            if (isDraw()) {
                System.out.println("DRAW");
                break;
            }
            System.out.println("---------");
            computerTurn();
            printMap();
            if (isWin(O_DOT)) {
                System.out.println("YOU LOSE");
                break;
            }
            if (isDraw()) {
                System.out.println("DRAW");
                break;
            }
        }
    }

    private static boolean isWin(char dot) {
        int checkHorDot;
        int checkVerDot;
        int diagMain;
        int diagReverse;
        for (int i = 0; i < map.length; i++) {
            checkHorDot = 0;
            checkVerDot = 0;
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == dot) { // проверка победу
                    checkHorDot++;
                }
                if (map[j][i] == dot) { // проверяем вертикальные линии на возможную победу
                    checkVerDot++;
                }

            }
            if (checkHorDot == mapSize || checkVerDot == mapSize) {
                return true;
            }
        }

        diagMain = 0; diagReverse = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i][i] == dot) {
                diagMain++;
            }
            if (map[i][map.length - i - 1] == dot) {
                diagReverse++;
            }
        }
        if (diagMain == mapSize || diagReverse == mapSize) {
            return true; //проверка по диагоналям
        }

        return  false;
    }

    public static void humanTurn() {
        int xCoordinate;
        int yCoordinate;
        System.out.println("Введите координаты в формате \"x пробел y\"");
        do {
            xCoordinate = -1;
            yCoordinate = -1;
            if (SC.hasNextInt()) {
                xCoordinate = SC.nextInt();
            }
            if (SC.hasNextInt()) {
                yCoordinate = SC.nextInt();
            }
            SC.nextLine();
        } while (!isValidHumanTurn(xCoordinate, yCoordinate));
    }

    public static void computerTurn() {
        int xCoordinate;
        int yCoordinate;
        Random random = new Random();
        do {
            xCoordinate = random.nextInt(mapSize);
            yCoordinate = random.nextInt(mapSize);
        } while (!isValidComputerTurn(xCoordinate, yCoordinate));
    }

    private static boolean isValidHumanTurn(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 1 || yCoordinate < 1 ||
                xCoordinate > mapSize || yCoordinate > mapSize) {
            System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
            return false;
        }
        if (map[xCoordinate - 1][yCoordinate - 1] == EMPTY_DOT) {
            map[xCoordinate - 1][yCoordinate - 1] = X_DOT;
            return true;
        }
        System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
        return false;
    }

    private static boolean isValidComputerTurn(int xCoordinate, int yCoordinate) {
        if (map[xCoordinate][yCoordinate] == EMPTY_DOT) {
            map[xCoordinate][yCoordinate] = O_DOT;
            return true;
        }
        return false;
    }

    public static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void initMap() {
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            Arrays.fill(map[i], EMPTY_DOT);
        }
    }

    private static boolean isDraw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }
}

