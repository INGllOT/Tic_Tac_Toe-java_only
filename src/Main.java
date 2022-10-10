import java.util.Scanner;

public class Main {
    static boolean isY = true;
    public static void main(String[] args) {

        String empytField = "_________";
        String[] arrStr = empytField.split("");

        setFieldAndPrint(arrStr);

    }
    public static boolean check(String[] input) {
        String[][] matrix = new String[3][3];
        int a = 0;
        boolean gameIsNotFinished = false;
        boolean gameIsOver = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = input[a];
                a ++;
            }
        }

        for(String e : input) {
            if(e.equals("_")) {
                gameIsNotFinished = true;
            }
        }

        int X = checkMark("X", matrix);
        int O = checkMark("O", matrix);

        if(O > X) {
            System.out.println("O wins");
            gameIsOver = true;

        }else if (X > O) {
            System.out.println("X wins");
            gameIsOver = true;

//        }else if (checkIfImpossible(input) || (X == O && X != 0 && O != 0)) {
//            System.out.println("Impossible");

        } else if (gameIsNotFinished) {
            System.out.println("Game not finished");

        }  else if (O == X) {
            System.out.println("Draw");
            gameIsOver = true;
        }

        return gameIsOver;
    }

    public static String[][] playerMove(String[][] arr){
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int j = 0;


        for(;;) {
            boolean check = true;
            String[] coordinates = scanner.nextLine().split(" ");

            try {
                i = Integer.parseInt(coordinates[0]);
                j = Integer.parseInt(coordinates[1]);
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                check = false;
            }

            if (i > 3 || i < 1 || j > 3 || j < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                check = false;
            }

            try {
                if(arr[i - 1][j].equals("X") || arr[i - 1 ][j].equals("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    check = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("i " + i + " j " + j + " EXCEPTION !!!");
            }

            if(check) {
                if(isY){
                    arr[i - 1][j] = "X";
                    isY = false;
                    break;

                } else {
                    arr[i - 1][j] = "O";
                    isY = true;
                    break;
                }

            }
        }
        return arr;
    }
    public static void setFieldAndPrint(String[] arrStr) {
        String[][] field = new String[3][5];

        // marks
        int a = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                field[i][j] = arrStr[a];
                a ++;
            }
        }

        // left and right side
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(j == 0 || j == 4){
                    field[i][j] = "|";
                }
            }
            System.out.println();
        }

        for (;;) {

            // printing
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(field[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("---------");

            //  players move
            field = playerMove(field);

            // printing after player move
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(field[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("---------");

            String[] stringAfterMove = new String[9];
            int position = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 1; j < 4; j++) {
                    stringAfterMove[position] = field[i][j];
                    position ++;
                }
            }

            if (check(stringAfterMove)){
                break;
            }
        }
    }
    public static boolean checkIfImpossible(String[] arr) {
        int a = 0;
        int X = 1;
        int O = 1;
        boolean isX = true;
        boolean contains_ = false;
        for (String e : arr) {

            if(e.equals("_")){
                contains_ = true;
                a = 0;
            }

            if (e.equals("O")) {
                if (isX) {
                    isX = false;
                    a = 0;
                }
                if (!isX) {
                    a++;
                    O++;
                }
                if(a == 3) {
                    break;
                }
            }

            if(e.equals("X")) {
                if (!isX) {
                    isX = true;
                    a = 0;
                }
                if (isX) {
                    a++;
                    X ++;
                }
                if(a == 3) {
                    break;
                }
            }
        }

        System.out.println(X + " " + O + " " + a);
        if (!contains_) return false;
        if (a > 2 || ((X/O) >= 2) || ((O/X) >= 2)) return true;

        return false;
    }

    public static int checkMark(String str, String[][] matrix) {
        int win = 0;

        // left to right
        if(matrix[0][0].equals(str) && matrix[0][1].equals(str) && matrix[0][2].equals(str)){
            win ++;
        } else if (matrix[1][0].equals(str) && matrix[1][1].equals(str) && matrix[1][2].equals(str)) {
            win ++;
        } else if (matrix[2][0].equals(str) && matrix[2][1].equals(str) && matrix[2][2].equals(str)) {
            win ++;

            // up to down
        } else if (matrix[0][0].equals(str) && matrix[1][0].equals(str) && matrix[2][0].equals(str)) {
            win ++;
        } else if (matrix[0][1].equals(str) && matrix[1][1].equals(str) && matrix[2][1].equals(str)) {
            win ++;
        } else if (matrix[0][2].equals(str) && matrix[1][2].equals(str) && matrix[2][2].equals(str)) {
            win ++;

            //diagonally
        }else if (matrix[0][0].equals(str) && matrix[1][1].equals(str) && matrix[2][2].equals(str)) {
            win ++;
        }else if (matrix[0][2].equals(str) && matrix[1][1].equals(str) && matrix[2][0].equals(str)) {
            win ++;
        }

        return win;
    }

}


