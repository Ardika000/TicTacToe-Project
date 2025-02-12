import java.util.Scanner;

public class apps{
    
    public static Scanner scanner = new Scanner(System.in);
    public static char map[][] = {
        {'_','_','_'},
        {'_','_','_'},
        {'_','_','_'}
    };
    public static int turn = 1;
    public static int countTurn = 1;
    
    public static void printMap(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                System.out.print(map[i][j]);
                if(j != 2){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static boolean fillMap(int baris,int kolom, int player){
        if(player == 1 && map[baris][kolom] == '_'){
            countTurn++;
            map[baris][kolom] = 'O';
            return true;
        }

        if (player == 2 && map[baris][kolom] == '_'){
            countTurn++;
            map[baris][kolom] = 'X';
            return true;
        }
        return false;

    }

    public static boolean checkWin(){
        // Cek Baris
        for(int i = 0; i<3; i++){
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2] && map[i][0] != '_') return true;
        }
        
        // Cek Kolom
        for(int i = 0; i<3; i++){
            if(map[0][i] == map[1][i] && map[1][i] == map[2][i] && map[0][i] != '_') return true;
        }

        if(map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[1][1] != '_') return true;

        if(map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[1][1] != '_') return true;

        return false;
    }
    
    public static void startGame(){

        do{
            int player;
            
            if(turn == 1){
                player = turn;
                turn = 2;
            }else{
                player = turn;
                turn = 1;
            }
            
            int inputKolom;
            int inputBaris;
            do{
                System.out.print("Player "+player+" move : ");
                inputKolom = scanner.nextInt();
                inputBaris = scanner.nextInt();
                if(inputBaris > 2 || inputBaris < 0 || inputKolom>2 || inputKolom < 0){
                    System.out.println("Input out of range!");
                }
            }while(inputBaris > 2 || inputBaris < 0 || inputKolom>2 || inputKolom < 0);

            boolean valid = fillMap(inputBaris, inputKolom,player);
            if(valid == false){
                turn = (turn== 1) ? 2:1;
            }

            printMap();

            boolean isWin = checkWin();
            if(isWin == true){
                System.out.println("Player "+player+" is the winner!");
                return;
            }else if(isWin == false && countTurn == 9){
                System.out.println("It's Draw !!");
                return;
            }

        }while(true);

    }

    public static void resetGame(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                map[i][j] = '_';
            }
        }
        countTurn = 1;
        if(turn == 2) turn = 1;
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to tic tac toe game");
        startGame();

        System.out.print("Do you want to play again [y/n]?");
        scanner.nextLine();
        String inp = scanner.nextLine();
        while(inp.equals("y")){
            resetGame();
            startGame();
            scanner.nextLine();
            System.out.print("Do you want to play again [y/n]?");
            inp = scanner.nextLine();
        }
        System.out.println("Good Bye !!");
    }
}
