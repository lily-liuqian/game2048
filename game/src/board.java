import java.util.Random;

class board {
    private final int row = 4;
    private final int col = 4;
    private int[][] board = new int[row][col];
    // 获取游戏盘面的长
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int[][] getBoard(){
        return board;
    }

    // 显示菜单
    public void menu(){
        System.out.println("*****************************");
        System.out.println("*****  欢迎来到2048小游戏  *****");
        System.out.println("***** 请输入您的操作(0-1): *****");
        System.out.println("*****       1.play      *****");
        System.out.println("*****       0.exit      *****");
        System.out.println("*****************************");
    }


    // 初始化游戏盘面
    public void InitBoard(int[][] board) {
        for(int r = 0;r<row;r++){
            for(int c = 0;c<col;c++){
                board[r][c]=0;
                this.board[r][c]=board[r][c];
            }
        }
    }

    // 打印游戏盘面
    public void PrintBoard(int[][] board) {
        for(int r = 0;r<row;r++){
            for(int c = 0;c<col;c++) {
                if (c < col - 1) {
                    System.out.print(board[r][c]);
                    System.out.print("  |");
                } else {
                    System.out.print(board[r][c]);
                }
            }
            System.out.println();
            if(r<row-1) {
                for (int c = 0; c < col; c++) {
                    if (c < col - 1) {
                        System.out.print("---|");
                    } else {
                        System.out.print("---");
                    }
                }
                System.out.println();
            }
        }
    }

    // 随即在键盘上生成一个2或4
    public void CreateNums(int[][] board) {
        Random r = new Random();
        int row_num = r.nextInt(row);
        int col_num = r.nextInt(col);
        int ran_num = r.nextInt(2);
        if(ran_num==0) {
            board[row_num][col_num] = 2;
        }
        if(ran_num==1) {
            board[row_num][col_num] = 4;
        }
        this.board[row_num][col_num] = board[row_num][col_num];
        PrintBoard(board);
    }
}
