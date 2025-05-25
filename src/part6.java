import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class part6 {
    // 判断游戏是否已经结束
//    private void gameOverOrNot(){
//        if(isWin()){
//            gameWin();
//        }
//        else if(cardIsFull()){
//
//            if(moveCardTop(false)||moveCardRight(false)||moveCardBotton(false)||moveCardLeft(false)){
//                // 只要有一个方向可以合并,就没有结束
//                return;
//            }
//            else{
//                gameOver();
//            }
//        }
//    }
//
//    private void gameWin() {
//        // 计算此时卡片的最大值,并记录
//        gameFlag = "end";
//        // 弹出结束提示
//        UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("思源宋体",Font.ITALIC,18)));
//        UIManager.put("OptionPane.messageFront",new FontUIResource(new Font("思源宋体",Font.ITALIC,18)));
//        JOptionPane.showMessageDialog(frame,"你成功了,太棒了");
//    }
//
//    // 游戏结束
//    public void gameOver(){
//        gameFlag = "end";
//        // 弹出结束提示
//        UIManager.put("OptionPane.buttonFont",new FontUIResource("思源宋体",Font.ITALIC,18));
//        UIManager.put("OptionPane.messageFont",new FontUIResource("思源宋体",Font.ITALIC,18));
//        JOptionPane.showMessageDialog(frame,"你失败了,请再接再厉");
//    }
//
//    private boolean isWin() {
//        Card card;
//        for(int i = 0; i < ROWS; i++){
//            for(int j = 0; j < COLS; j++){
//                card = cards[i][j];
//                if(card.getNum() == 2048){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

}
