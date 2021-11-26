import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.Buffer;
//定时器
import java.util.Timer;
//定时器任务
import java.util.TimerTask;
//打开随机类
import java.util.Random;
//扩容类
import java.util.Arrays;
/**
 * 世界测试类(整个游戏窗口)
 */
public class World extends JPanel{
 public static final int WIDTH = 400;//窗口宽
 public static final int HEIGHT = 700;//窗口高
 
 public static final int START = 0;//启动状态
 public static final int RUNNING = 1;//运行状态
 public static final int PAUSE = 2;//暂停状态
 public static final int GAME_OVER = 3;//游戏结束状态
 private int state = START;//当前状态默认是启动状态
}
