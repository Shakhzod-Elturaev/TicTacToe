import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static char[][] game = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static boolean res = false;
    static int c = 0;
    static int turn = 1;
    static char symbol;
    static char finalSymbol;
    static int winX = 0;
    static int winO = 0;
    static int computer = 0;
    static boolean player = false;

    public static void main(String[] args) {
        onlyGame();
    }

    public static void onlyGame(){
        while(true){
            System.out.println("1. Single player\t2. Multiplayer\t0. Exit");
            int gameType = scanner.nextInt();
            System.out.println();
            switch(gameType){
                case 1 -> {
                    main1:
                    while(true) {
                        System.out.println("1 - Play\t2. Score\t0. Back");
                        int playOrNot = scanner.nextInt();
                        System.out.println();
                        main:
                        while (true) {
                            switch (playOrNot) {
                                case 1 -> {
                                    System.out.println("Player -> x\tComputer -> o");
                                    show();
                                    System.out.println();
                                    while (true) {
                                        if (checking() == 1) {
                                            if(finalSymbol == 'x') {
                                                System.out.println("Winner is " + "(" + finalSymbol + ") Player");
                                                winX++;
                                            }
                                            else if (finalSymbol == 'o') {
                                                System.out.println("Winner is " + "(" + finalSymbol + ") Computer");
                                                winO++;
                                            }
                                            cleanBoard();
                                            break main;
                                        } else if (checking() == 2) {
                                            System.out.println("Draw");
                                            cleanBoard();
                                            break main;
                                        } else  playSingle();
                                    }
                                }
                                case 2 -> {
                                    System.out.println("The first player - " + winX + " : " + winO + " - Computer AI\n");
                                    break main;
                                }
                                case 0 -> {
                                    winX = 0;
                                    winO = 0;
                                    break main1;
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    main1:
                    while(true) {
                        System.out.println("1 - Play\t2. Score\t0. Back");
                        int playOrNot = scanner.nextInt();
                        System.out.println();
                        main:
                        while (true) {
                            switch (playOrNot) {
                                case 1 -> {
                                    System.out.println("Player 1 -> x\tPlayer 2 -> o");
                                    show();
                                    System.out.println();
                                    while (true) {
                                        if (checking() == 1) {
                                            if(finalSymbol == 'x') {
                                                System.out.println("Winner is " + "(" + finalSymbol + ") Player 1");
                                                winX++;
                                            }
                                            else if (finalSymbol == 'o') {
                                                System.out.println("Winner is " + "(" + finalSymbol + ") Player 2");
                                                winO++;
                                            }
                                            cleanBoard();
                                            break main;
                                        } else if (checking() == 2) {
                                            System.out.println("Draw");
                                            cleanBoard();
                                            break main;
                                        } else  play();
                                    }
                                }
                                case 2 -> {
                                    System.out.println("The first player - " + winX + " : " + winO + " - The second player\n");
                                    break main;
                                }
                                case 0 -> {
                                    winX = 0;
                                    winO = 0;
                                    break main1;
                                }
                            }
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Thank you for stopping by!!!!!");
                    return;
                }
            }
        }
    }

    public static void playSingle(){
        if(turn == 1) {
            System.out.print("Player 1, your turn : ");
            int firstPlayer = scanner.nextInt();
            player = true;
            symbol = 'x';
            insertElements(firstPlayer, symbol);
            show();
            System.out.println();
        }else if(turn == -1) {
            AI();
            System.out.println(computer);
            insertElements(computer, symbol);
            show();
            System.out.println();
        }
    }

    public static void AI(){
        System.out.print("Computer, your turn : ");
        computer = 0;
        player = false;
        symbol = 'o';
        if (game[0][0] == ' ' && ((game[0][1] == game[0][2] && game[0][1] == 'o') || (game[1][0] == game[2][0] && game[1][0] == 'o') || (game[1][1] == game[2][2] && game[1][1] == 'o'))) {
            computer = 1;
        } else if(game[0][1] == ' ' && ((game[1][1] == game[2][1] && game[1][1] == 'o') || (game[0][0] == game[0][2] && game[0][0] == 'o'))){
            computer = 2;
        } else if(game[0][2] == ' ' && ((game[0][0] == game[0][1] && game[0][0] == 'o') || (game[1][2] == game[2][2] && game[1][2] == 'o') || (game[1][1] == game[2][0] && game[1][1] == 'o'))){
            computer = 3;
        } else if(game[1][0] == ' ' && ((game[0][0] == game[2][0] && game[0][0] == 'o') || (game[1][1] == game[1][2] && game[1][1] == 'o'))){
            computer = 4;
        } else if(game[1][1] == ' ' && ((game[0][1] == game[2][1] && game[0][1] == 'o') || (game[1][0] == game[1][2] && game[1][0] == 'o') || (game[0][2] == game[2][0] && game[0][2] == 'o'))){
            computer = 5;
        } else if(game[1][2] == ' ' && ((game[0][2] == game[2][2] && game[0][2] == 'o') || (game[1][0] == game[1][1] && game[1][0] == 'o'))){
            computer = 6;
        } else if(game[2][0] == ' ' && ((game[1][1] == game[0][2] && game[1][1] == 'o') || (game[0][0] == game[1][0] && game[0][0] == 'o') || (game[2][1] == game[2][2] && game[2][1] == 'o'))){
            computer = 7;
        } else if(game[2][1] == ' ' && ((game[2][0] == game[2][2] && game[2][0] == 'o') || (game[0][1] == game[1][1] && game[0][1] == 'o'))){
            computer = 8;
        } else if(game[2][2] == ' ' && ((game[1][1] == game[0][0] && game[1][1] == 'o') || (game[0][2] == game[1][2] &&  game[0][2] == 'o') || (game[2][0] == game[2][1] && game[2][0] == 'o'))){
            computer = 9;
        } else {
            if (game[0][0] == ' ' && ((game[0][1] == game[0][2] && game[0][1] == 'x') || (game[1][0] == game[2][0] && game[1][0] == 'x') || (game[1][1] == game[2][2] && game[1][1] == 'x'))) {
                computer = 1;
            } else if(game[0][1] == ' ' && ((game[1][1] == game[2][1] && game[1][1] == 'x') || (game[0][0] == game[0][2] && game[0][0] == 'x'))){
                computer = 2;
            } else if(game[0][2] == ' ' && ((game[0][0] == game[0][1] && game[0][0] == 'x') || (game[1][2] == game[2][2] && game[1][2] == 'x') || (game[1][1] == game[2][0] && game[1][1] == 'x'))){
                computer = 3;
            } else if(game[1][0] == ' ' && ((game[0][0] == game[2][0] && game[0][0] == 'x') || (game[1][1] == game[1][2] && game[1][1] == 'x'))){
                computer = 4;
            } else if(game[1][1] == ' ' && ((game[0][1] == game[2][1] && game[0][1] == 'x') || (game[1][0] == game[1][2] && game[1][0] == 'x') || (game[0][2] == game[2][0] && game[0][2] == 'x'))){
                computer = 5;
            } else if(game[1][2] == ' ' && ((game[0][2] == game[2][2] && game[0][2] == 'x') || (game[1][0] == game[1][1] && game[1][0] == 'x'))){
                computer = 6;
            } else if(game[2][0] == ' ' && ((game[1][1] == game[0][2] && game[1][1] == 'x') || (game[0][0] == game[1][0] && game[0][0] == 'x') || (game[2][1] == game[2][2] && game[2][1] == 'x'))){
                computer = 7;
            } else if(game[2][1] == ' ' && ((game[2][0] == game[2][2] && game[2][0] == 'x') || (game[0][1] == game[1][1] && game[0][1] == 'x'))){
                computer = 8;
            } else if(game[2][2] == ' ' && ((game[1][1] == game[0][0] && game[1][1] == 'x') || (game[0][2] == game[1][2] &&  game[0][2] == 'x') || (game[2][0] == game[2][1] && game[2][0] == 'x'))){
                computer = 9;
            } else {
                computer = random.nextInt(9) + 1;
                insertElements(computer, symbol);
                while (!res) {
                    computer = random.nextInt(9) + 1;
                    insertElements(computer, symbol);
                }
            }
        }
    }
    public static int play(){
        if(turn == 1) {
            System.out.print("Player 1, your turn : ");
            int firstPlayer = scanner.nextInt();
            symbol = 'x';
            insertElements(firstPlayer, symbol);
            show();
            System.out.println();
        }else if(turn == -1) {
            System.out.print("Player 2, your turn : ");
            int secondPlayer = scanner.nextInt();
            symbol = 'o';
            insertElements(secondPlayer, symbol);
            show();
            System.out.println();
        }
        return checking();
    }

    public static void show(){
        for (char[] chars : game) {
            for (char aChar : chars) {
                System.out.print("[ " + aChar + " ]");
            }
            System.out.println();
        }
    }

    public static void insertElements(int number, char symbol){
        int rows = -1;
        int columns = -1;
        switch (number){
            case 1 ->{
                rows = 0;
                columns = 0;
            }
            case 2 ->{
                rows = 0;
                columns = 1;
            }
            case 3 ->{
                rows = 0;
                columns = 2;
            }
            case 4 ->{
                rows = 1;
                columns = 0;
            }
            case 5 ->{
                rows = 1;
                columns = 1;
            }
            case 6 ->{
                rows = 1;
                columns = 2;
            }
            case 7 ->{
                rows = 2;
                columns = 0;
            }
            case 8 ->{
                rows = 2;
                columns = 1;
            }
            case 9 ->{
                rows = 2;
                columns = 2;
            }
            default -> System.out.println("Unacceptable input. Please try again");
        }
        if(game[rows][columns] == ' '){
            game[rows][columns] = symbol;
            res = true;
            turn *= -1;
            c++;
        }
        else {
            res = false;
        }
    }

    public static int checking(){
        if(game[0][0] != ' ' && game[0][0] == game[0][1] &&game[0][0] == game[0][2]){
            finalSymbol = game[0][0];
            return 1;
        }else if(game[1][0] != ' ' && game[1][0] == game[1][1] &&game[1][0] == game[1][2]){
            finalSymbol = game[1][0];
            return 1;
        }else if(game[2][0] != ' ' && game[2][0] == game[2][1] &&game[2][0] == game[2][2]){
            finalSymbol = game[2][0];
            return 1;
        }else if(game[0][0] != ' ' && game[0][0] == game[1][0] &&game[0][0] == game[2][0]){
            finalSymbol = game[0][0];
            return 1;
        }else if(game[0][1] != ' ' && game[0][1] == game[1][1] &&game[0][1] == game[2][1]){
            finalSymbol = game[0][1];
            return 1;
        }else if(game[0][2] != ' ' && game[0][2] == game[1][2] &&game[0][2] == game[2][2]){
            finalSymbol = game[0][2];
            return 1;
        }else if(game[0][0] != ' ' && game[0][0] == game[1][1] &&game[0][0] == game[2][2]){
            finalSymbol = game[0][0];
            return 1;
        }
        else if(game[0][2] != ' ' && game[0][2] == game[1][1] &&game[0][2] == game[2][0]){
            finalSymbol = game[0][2];
            return 1;
        }
        if (c < 9) {
            return 0;
        } else return 2;
    }

    public static void cleanBoard(){
        for (char[] chars : game) {
            Arrays.fill(chars, ' ');
        }
        c = 0;
    }
}



