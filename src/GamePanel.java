import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;


// 画布
public class GamePanel extends JPanel implements ActionListener {
    private JFrame frame = null;
    private GamePanel panel = null;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Card[][] cards = new Card[ROWS][COLS];
    private String gameFlag = "start";
    private int score = 0;
    private JLabel scoreLabel;
    private static final String SAVE_FILE = "2048_save.dat";

    public GamePanel(JFrame frame) {
        this.setLayout(null);
        this.setOpaque(false);
        this.frame = frame;
        this.panel = this;

        // 创建菜单
        createMenu();

        // 创建卡片
        initCard();

        // 随机创建一个卡片
        createRandomNum();

        // 创建键盘监听
        createKeyListener();
    }

    // 创建键盘监听
    private void createKeyListener() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(!"start".equals(gameFlag)){
                    return;
                }
                int key = e.getKeyCode();
                switch(key){
                    // 向上
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        moveCard(1);
                        break;
                    // 向右
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        moveCard(2);
                        break;
                    // 向下
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        moveCard(3);
                        break;
                    // 向左
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        moveCard(4);
                        break;

                }
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    // 根据方向移动卡片
    private void moveCard(int dir) {
        // 清理卡片的合并标记
        clearCard();
        if(dir == 1) {
            moveCardTop(true);
        }
        else if(dir == 2) {
            moveCardRight(true);
        }
        else if(dir == 3) {
            moveCardBotton(true);
        }
        else if(dir == 4) {
            moveCardLeft(true);
        }

        // 重新创建卡片
        createRandomNum();
        // 重绘画布
        repaint();
        // 判断游戏是否已经结束
        gameOverOrNot();
    }

    // 判断游戏是否已经结束
    private void gameOverOrNot(){
        if(isWin()){
            gameWin();
        }
        else if(cardIsFull()){

            if(moveCardTop(false)||moveCardRight(false)||moveCardBotton(false)||moveCardLeft(false)){
                // 只要有一个方向可以合并,就没有结束
                return;
            }
            else{
                gameOver();
            }
        }
    }

    private void gameWin() {
        // 计算此时卡片的最大值,并记录
        gameFlag = "end";
        // 弹出结束提示
        UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("思源宋体",Font.ITALIC,18)));
        UIManager.put("OptionPane.messageFront",new FontUIResource(new Font("思源宋体",Font.ITALIC,18)));
        JOptionPane.showMessageDialog(frame,"你成功了,太棒了");
    }

    // 游戏结束
    public void gameOver(){
        gameFlag = "end";
        // 弹出结束提示
        UIManager.put("OptionPane.buttonFont",new FontUIResource("思源宋体",Font.ITALIC,18));
        UIManager.put("OptionPane.messageFont",new FontUIResource("思源宋体",Font.ITALIC,18));
        JOptionPane.showMessageDialog(frame,"你失败了,请再接再厉");
    }

    private boolean isWin() {
        Card card;
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                card = cards[i][j];
                if(card.getNum() == 2048){
                    return true;
                }
            }
        }
        return false;
    }


    private void clearCard() {
        Card card;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                card = cards[i][j];
                card.setMerge(false);
            }
        }
    }

    private boolean moveCardLeft(boolean b) {
        Card card;
        boolean result = false;
        for(int i = 0;i<ROWS;i++){
            for(int j = 1;j<COLS;j++){
                card = cards[i][j];

                if(card.getNum() != 0){
                    // 只要卡片不是空白卡片,就要移动
                    if(card.moveLeft(cards,b)) {
                            result = true;
                    }
                }
            }
        }
        return result;
    }

    private boolean moveCardBotton(boolean b) {
        Card card;
        boolean result = false;
        for(int i = ROWS-2;i>=0;i--){
            for(int j = 0;j<COLS;j++){
                card = cards[i][j];

                if(card.getNum() != 0){
                    // 只要卡片不是空白卡片,就要移动
                    if(card.moveBotton(cards,b)) {
                            result = true;
                    }
                }
            }
        }
        return result;
    }

    private boolean moveCardRight(boolean b) {
        boolean result = false;
        Card card;
        for(int i = 0;i<ROWS;i++){
            for(int j = COLS-2;j>=0;j--){
                card = cards[i][j];

                if(card.getNum() != 0){
                    // 只要卡片不是空白卡片,就要移动
                    if(card.moveRight(cards,b)) {
                            result = true;
                    }
                }
            }
        }
        return result;
    }

    private boolean moveCardTop(boolean b) {
        boolean result = false;
        Card card;
        for(int i = 1;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                card = cards[i][j];

                if(card.getNum() != 0){
                    // 只要卡片不是空白卡片,就要移动
                    if(card.moveTop(cards,b)) {
                            result = true;
                    }
                }
            }
        }
        return result;
    }

    private void createRandomNum() {
        // 随机显示的数字是4或者2
        int num = 0;
        Random random = new Random();
        int n = random.nextInt(5)+1; // 取出1-5的随机数
        if(n == 1){
            num = 4;
        }
        else{
            num = 2;
        }

        // 如果格子满了,则不需要再去取了
        if(cardIsFull()){
            return;
        }
        // 取到卡片
        Card card = getRandomCard(random);

        // 设置卡片数字
        if(card!=null){
            card.setNum(num);
        }
    }

    private boolean cardIsFull() {
        Card card;
        for(int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                card = cards[i][j];
                if(card.getNum() == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private Card getRandomCard(Random random) {
        int i = random.nextInt(ROWS);
        int j = random.nextInt(COLS);
        Card card = cards[i][j];

        if(card.getNum() == 0){  // 如果是空白的卡片,则直接返回
            return card;
        }
        // 没找到,则递归继续找
        return getRandomCard(random);
    }

    private void initCard() {
        Card card;
        for(int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                card = new Card(i,j);
                cards[i][j] = card;
            }
        }
    }


    // 绘制卡片
    private void drawCard(Graphics g) {
        Card card;
        for(int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                card = cards[i][j];
                card.draw(g);
            }
        }
    }

    // 创建字体方法
    private Font createFont(){
        return new Font("思源宋体", Font.BOLD, 18);
    }


    private void createMenu() {
        // 创建字体
        Font tfont = createFont();


        // 创建JMemuBar(创建菜单)
        JMenuBar jmb = new JMenuBar();

        JMenu jMenu1 = new JMenu("游戏");
        jMenu1.setFont(tfont);
        // 创建子项
        JMenuItem jmi1 = new JMenuItem("新游戏");
        jmi1.setFont(tfont);
        JMenuItem jmi2 = new JMenuItem("退出");
        jmi2.setFont(tfont);
        // 添加子项
        jMenu1.add(jmi1);
        jMenu1.add(jmi2);

        JMenu jMenu2 = new JMenu("帮助");
        jMenu2.setFont(tfont);
        // 创建"帮助"下的子项
        JMenuItem jmi3 = new JMenuItem("操作帮助");
        jmi3.setFont(tfont);
        JMenuItem jmi4 = new JMenuItem("胜利条件");
        JMenuItem jmi5 = new JMenuItem("保存游戏");
        JMenuItem jmi6 = new JMenuItem("加载游戏进度");
        jmi4.setFont(tfont);
        jmi5.setFont(tfont);
        jmi6.setFont(tfont);

        // 添加子项
        jMenu2.add(jmi3);
        jMenu2.add(jmi4);
        jMenu2.add(jmi5);
        jMenu2.add(jmi6);

        JMenu jMenu3 = new JMenu("查看游戏分数");
        jMenu3.setFont(tfont);
        scoreLabel =  new JLabel("当前分数:0");
        scoreLabel.setFont(tfont);
        jMenu3.add(scoreLabel);

        jmb.add(jMenu1);
        jmb.add(jMenu2);
        jmb.add(jMenu3);
        frame.setJMenuBar(jmb);

        // 添加时间监听
        jmi1.addActionListener(this);
        jmi2.addActionListener(this);
        jmi3.addActionListener(this);
        jmi4.addActionListener(this);
        jmi5.addActionListener(this);
        jmi6.addActionListener(this);

        // 对每一个菜单子项设置指令
        jmi1.setActionCommand("restart");
        jmi2.setActionCommand("exit");
        jmi3.setActionCommand("help");
        jmi4.setActionCommand("win");
        jmi5.setActionCommand("save");
        jmi6.setActionCommand("load");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("restart")) {
            System.out.println("新游戏");
            restart();
        }
        if (command.equals("exit")) {
            System.out.println("退出游戏");
            Object[] options = {"确定","取消"};
            int res = JOptionPane.showOptionDialog(this,"你确定要退出游戏吗?",
                    "",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(res == 0){ // 确认退出
                System.exit(0);
            }
        }
        if (command.equals("help")) {
            System.out.println("游戏帮助");
            JOptionPane.showMessageDialog(null,"通过键盘的上下左右来移动,相同数字会合并",
                    "提示",JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("win")) {
            System.out.println("胜利条件");
            JOptionPane.showMessageDialog(null,"得到数字2048获得胜利,当没有空卡片则失败",
                    "提示",JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("score")) {
            JOptionPane.showMessageDialog(null,"游戏分数为:"+score,
                    "提示",JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("save")) {
            saveGame();
            JOptionPane.showMessageDialog(frame, "游戏已保存", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("load")) {
            if (loadGame()) {
                repaint();
                JOptionPane.showMessageDialog(frame, "游戏已加载", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // 新游戏
    private void restart() {
        Object[] options = {"确定","取消"};
        int res = JOptionPane.showOptionDialog(this,"你确定要重新开始游戏吗?",
                "",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(res == 0) { // 确认重新开始
            gameFlag = "start";
            initCard();
            createRandomNum();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制卡片
        drawCard(g);
    }

    // 修改分数更新方法
    public void addScore(int value) {
        score += value;
        updateScoreDisplay();
    }

    public void resetScore() {
        score = 0;
        updateScoreDisplay();
    }

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
