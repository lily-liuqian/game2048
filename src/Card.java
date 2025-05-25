import java.awt.*;

public class Card {
    private int x = 0;  // x坐标
    private int y = 0;  // y坐标
    private int weight = 80; // 宽
    private int height = 80;  //高
    private int i = 0; // 下标i
    private int j = 0;  // 下标j

    private int start = 5;  // 偏移量
    private int num = 0;  // 数字
    private boolean merge = false;  // 是否合并,如果已经合并则不能再合并
    private int score = 0;

    public Card(int i,int j) {
        this.i = i;
        this.j = j;
    }

    // 根据i,j计算x,y坐标
    private void cal(){
        this.x = start+j*weight+(j+1)*5;  // (j+1)*5 -- 每个卡片之间的间隔
        this.y = start+i*height+(i+1)*5;

    }

    // 卡片的绘制
    public void draw(Graphics g) {
        cal();
        // 根据数字获取对应的颜色
        Color color = getColor();

        Color ocolor = g.getColor();
        // 设置新颜色
        g.setColor(color);
        g.fillRoundRect(x,y,weight,height,4,4);

        // 绘制数字
        if(num!=0){
            // 设置数字颜色
            g.setColor(new Color(125,78,51));
            Font font = new Font("思源宋体",Font.BOLD,30);
            g.setFont(font);
            String text = num+"";
            int ty = y+50;
            int textLen = getWordWidth(font,text,g);
            int tx = x+(weight-textLen)/2;
            g.drawString(text, tx,ty);
        }


        // 还原旧颜色
        g.setColor(ocolor);
    }

    // 得到该字体字符串得长度
    public static int getWordWidth(Font font,String content,Graphics g){
        FontMetrics metrics = g.getFontMetrics(font);
        int width = 0;
        for(int i=0;i<content.length();i++){
            width+=metrics.charWidth(content.charAt(i));
        }
        return width;
    }


    // 获取颜色
    private Color getColor() {
        Color color = null;
        // 根据num来设置颜色
        switch(num){
            case 2:
                color = new Color(238,244,234);
                break;
            case 4:
               color = new Color(200, 245, 130);
               break;
           case 8:
               color = new Color(120, 218, 55);
               break;
           case 16:
               color = new Color(85, 185, 19);
               break;
           case 32:
               color = new Color(67, 173, 94);
                   break;
            case 64:
                color = new Color(79, 154, 28);
                break;
            case 128:
                color = new Color(57, 145, 79);
                break;
            case 256:
                color = new Color(53, 136, 73);
                break;
            case 512:
                color = new Color(48, 121, 66);
                break;
            case 1024:
                color = new Color(45, 114, 61);
                break;
            case 2048:
                color = new Color(41, 100, 55);
                break;
            default:  // 默认颜色
                color = new Color(92, 151, 117);
                break;
        }
        return color;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return this.score;
    }
    // 向上移动的方法
    public boolean moveTop(Card[][] cards,boolean b) {
        // 设定递归的退出条件
        if(i == 0){
                return false;
            }
        // 上一个卡片
        Card prev = cards[i-1][j];
        // 要交换上去的
        if(prev.getNum() == 0){
            if(b) {
                prev.num = this.num;
                this.num = 0;
                prev.moveTop(cards, b);
            }
            return true;
        }else if(prev.getNum() == num&& !prev.merge){
            // 要合并的
            if(b) {
                prev.merge = true;
                prev.num = this.num * 2;
                this.num = 0;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public void setMerge(boolean b) {
        this.merge = b;
    }

    public boolean moveRight(Card[][] cards,boolean b) {
        // 设定递归的退出条件
        if(j == 3){
            return false;
        }
        // 上一个卡片
        Card prev = cards[i][j+1];
        // 要交换上去的
        if(prev.getNum() == 0){
            if(b) {
                prev.num = this.num;
                this.num = 0;
                prev.moveRight(cards, b);
            }
            return true;
        }else if(prev.getNum() == num&& !prev.merge){
            if(b) {
                // 要合并的
                prev.merge = true;
                prev.num = this.num * 2;
                this.num = 0;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean moveBotton(Card[][] cards,boolean b) {
        // 设定递归的退出条件
        if(i == 3){
            return false;
        }
        // 上一个卡片
        Card prev = cards[i+1][j];
        // 要交换上去的
        if(prev.getNum() == 0){
            if(b) {
                prev.num = this.num;
                this.num = 0;
                prev.moveBotton(cards, b);
            }
            return true;
        }else if(prev.getNum() == num&& prev.merge == false){
            if(b) {
                // 要合并的
                prev.merge = true;
                prev.num = this.num * 2;
                this.num = 0;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean moveLeft(Card[][] cards,boolean b) {
        // 设定递归的退出条件
        if(j == 0){
            return false;
        }
        // 上一个卡片
        Card prev = cards[i][j-1];
        // 要交换上去的
        if(prev.getNum() == 0){
            if(b) {
                prev.num = this.num;
                this.num = 0;
                prev.moveLeft(cards, b);
            }
            return true;
        }else if(prev.getNum() == num&& !prev.merge){
            if(b) {
                // 要合并的
                prev.merge = true;
                prev.num = this.num * 2;
                this.num = 0;
            }
            return true;
        }
        else{
            return false;
        }
    }

}
