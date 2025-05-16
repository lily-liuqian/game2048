import java.util.Scanner;

public class Main {
    // 2048游戏实现

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            board b = new board();
            int input = 0;
            int row = b.getRow();
            int col = b.getCol();
            int[][] board = new int[row][col];  // 创建游戏盘面
            do{
                b.menu();
                input = sc.nextInt();
                switch (input) {
                    case 1:
                        b.InitBoard(board);
                        b.PrintBoard(board);
                        key ck = new key();
                        b.CreateNums(board);
                        break;
                    case 0:
                        System.out.println("感谢您的游玩");
                        break;
                    default:
                        System.out.println("输入错误,请重新选择");
                        break;
                }
            }while(input!=0);
        }
    }
