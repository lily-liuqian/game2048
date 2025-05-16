import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class key extends JFrame implements KeyListener {
    // 按下上键操作
    public void keyUpOperation(int[][] board,int row,int col){
        for(int c = 0;c<col;c++){
            for(int r = 1;r<row;r++){
                if(board[r][c]!=0) {
                    for (int r1 = r - 1; r1 >= 0; r1--) {
                        if (board[r1][c] != 0) {
                            board[r1][c] = board[r1+1][c];
                            board[r][c] = 0;
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
    }
    //按下下键操作
    public void keyDownOperation(int[][] board,int row,int col){

    }
    // 按下左键操作
    public void keyLeftOperation(int[][] board,int row,int col){

    }
    // 按下右键操作
    public void keyRightOperation(int[][] board,int row,int col){

    }



    // 创建窗口
    public key(){
        // 窗口标题
        setTitle("监听用户输入");
        // 窗口大小
        setSize(400,400);
        // 设置窗口显示位置
        setLocationRelativeTo(null);
        // 关闭窗口同时退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口是否显示可见
        setVisible(true);
        // 给窗口添加一个键盘监听
        addKeyListener(this);

    }
    @Override
    // 键按下时
    public void keyTyped(KeyEvent e) {
        // 获取键盘按下的键
        char c = e.getKeyChar();
        System.out.println("按下键:"+c);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // 操作键盘按下的键
        int c = e.getKeyCode();
        switch (c){
            case KeyEvent.VK_LEFT:
                System.out.println("按下左键");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("按下右键");
                break;
            case KeyEvent.VK_UP:
                System.out.println("按下上键");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("按下下键");

                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Enter");
                break;
            default:
                System.out.println("按下其他键");
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        // 获取键盘按下的键
        char c = e.getKeyChar();
        System.out.println("松开键:"+c);
    }
}

