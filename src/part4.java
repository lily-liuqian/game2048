import javax.swing.*;
import java.io.*;

public class part4 {
    private JFrame frame = null;
    private GamePanel panel = null;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Card[][] cards = new Card[ROWS][COLS];
    private String gameFlag = "start";
    private int score = 0;
    private JLabel scoreLabel;
    private static final String SAVE_FILE = "2048_save.dat";

    private void updateScoreDisplay() {
        scoreLabel.setText("当前分数: " + score);
        frame.revalidate(); // 刷新菜单栏
    }

    // 存档功能
    public void saveGame() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            // 保存游戏状态和分数
            writer.println(gameFlag);
            writer.println(score);

            // 保存卡片状态
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    writer.print(cards[i][j].getNum() + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "保存游戏失败: " + e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 加载游戏进度
    public boolean loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            gameFlag = reader.readLine();
            score = Integer.parseInt(reader.readLine());
            updateScoreDisplay(); // 加载时更新分数显示

            for (int i = 0; i < ROWS; i++) {
                String[] values = reader.readLine().trim().split(" ");
                for (int j = 0; j < COLS; j++) {
                    cards[i][j].setNum(Integer.parseInt(values[j]));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
