import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {


    public GameFrame() {

        // 第一步:创建游戏窗口
        // 设定标题
        setTitle("2048");
        // 设定窗体大小
        setSize(370,420);
        // 设定默认背景颜色
        getContentPane().setBackground(new Color(66,136,83));
        // 关闭窗口时进程结束
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中
        setResizable(false); // 设置窗口大小固定

    }
}
