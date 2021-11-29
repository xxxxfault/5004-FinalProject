package final_project_HITPLANE;
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
public class GameDriver extends JPanel{
 public static final int WIDTH = 500;//窗口宽
 public static final int HEIGHT = 700;//窗口高
 
 public static final int START = 0;//启动状态
 public static final int RUNNING = 1;//运行状态
 public static final int PAUSE = 2;//暂停状态
 public static final int GAME_OVER = 3;//游戏结束状态
 private int state = START;//当前状态默认是启动状态
 
 /**
 * 声明每个类具体的对象
 * 如下为：窗口中所看到的对象
 */
 private Sky s = new Sky();//天空对象
 private HeroPlane h = new HeroPlane();//英雄机对象
 private FlyerObject[] enemies ={};//敌人对象，分别是大敌机，小敌机，小蜜蜂所以写成了数组
 private Bullet[] bt ={};//子弹也是有很多的所以写成了数组
 
 /**
 * 生成敌人对象（小敌机，大敌机，小蜜蜂）
 */
 public FlyerObject nextOne(){
 Random rand = new Random();
 int type = rand.nextInt(20);//0-19之间的随机数
 if (type < 5){//当随机数小于5
 return new Bee();//返回小蜜蜂
 }else if (type < 13){//当随机数小于13
 return new Airplane();//返回小敌机
 }else{//大于十三则
 return new BigAirplane();//返回大敌机
 }
 }
 
 private int enterIndex = 0;
 /**
 * 敌人（大敌机，小敌机，小蜜蜂）入场
 */
 public void enterAction() {//每10毫秒走一次
 enterIndex++;
 if (enterIndex%40 == 0 ){//四百毫秒走一次
	 FlyerObject fl = nextOne();//获取敌人对象
 enemies = Arrays.copyOf(enemies,enemies.length+1);//扩容（每产生一个敌人数组就扩容1）
 enemies[enemies.length-1] = fl;//将生成的敌人fl放置enemies数组的末尾
 }
 }
 
 int shootIndex = 0;
 /**
 * 子弹入场
 */
 public void shootAction(){//10毫秒走一次
 shootIndex++;
 if (shootIndex%30 == 0){//每300毫秒走一次
 Bullet[] bs = h.shoot();//获取子弹数组对象
 bt = Arrays.copyOf(bt,bt.length+bs.length);//扩容子弹数组（每入场一个子弹就加一个元素）
 System.arraycopy(bs,0,bt,bt.length-bs.length,bs.length);//数组的追加
 }
 }
 
 /**
 * 让除去英雄机外的所有对象（小敌机，大敌机，小蜜蜂，子弹，天空）移动
 */
 public void setpAction() {//每10毫秒走一次
 s.step();//天空移动
 for (int i=0;i<enemies.length;i++) {//遍历所有敌人
 enemies[i].step();//敌人移动
 }
 for (int i=0;i<bt.length;i++){//遍历所有子弹
 bt[i].step();//子弹移动
 }
 }
 
 /**
 * 重写outOfBoundsAction（方法）
 */
 public void outOfBoundsAction() {//每10毫秒走一次
 for (int i=0;i<enemies.length;i++){//遍历所有敌人
 if (enemies[i].isOutOfBounds() || enemies[i].isRemove()){
 enemies[i] = enemies[enemies.length-1];//最后一个敌人和越界敌人替换
 enemies = Arrays.copyOf(enemies,enemies.length-1);//缩容
 }
 }
 for (int i=0;i<bt.length;i++){//迭代所有子弹
 if (bt[i].isOutOfBounds() || bt[i].isRemove()){
 bt[i] = bt[bt.length-1];//用最后一个子弹替换出界的子弹
 bt = Arrays.copyOf(bt,bt.length-1);//缩容
 }
 }
 }
 private int score = 0;//玩家的得分
 /**
 * 子弹与敌人的碰撞
 */
 public void bulletBangAction() {//每10毫秒走一次
 for (int i=0;i<bt.length;i++){//遍历所有子弹
 Bullet b = bt[i];//获取每一个子弹
 for (int j=0;j<enemies.length;j++){//迭代每一个敌人
	 FlyerObject f = enemies[j];//获取每一个敌人
 if (b.isLive() && f.isLive() && f.isHit(b)){//若子弹活着的，敌人活着的，并且两个对象相撞
 b.goDead();//子弹当前状态修改为死亡
 f.goDead();//敌人当前状态修改为死亡
 if (f instanceof EnemyScore) {//判断死亡的敌人类型能否强转为得分接口类型
 EnemyScore es = (EnemyScore) f;//将死亡敌人向下造型
 score += es.getScore();//调用具体的敌人对象的得分接口的getScore()加分方法
 }
 if (f instanceof EnemyAward){//判断死亡的敌人类型能否强转为奖励值接口类型
 EnemyAward ea = (EnemyAward) f;//将死亡敌人强转为奖励值接口类型
 int type = ea.getAwardType();//将具体的奖励值赋值给type
 switch (type){
 case EnemyAward.FIRE://火力值
 h.addFier();//返回增加火力值
 break;
 case EnemyAward.LIFE://生命值
 h.addLife();//返回增加生命值
 break;
 }
 }
 }
 }
 }
 }
 
 /**
 * 英雄机与敌人的碰撞
 */
 private void heroBangAction() {//每10毫秒走一次
 for (int i=0;i<enemies.length;i++){//迭代所有敌人
	 FlyerObject f = enemies[i];//获取每个敌人
 if (f.isLive() && h.isLive() && f.isHit(h)){//判断碰撞
 f.goDead();//敌人死亡
 h.subtractLife();//英雄机减生命值
 h.clearFier();//英雄机清空火力值
 }
 }
 }
 
 /**
 * 检测游戏结束
 */
 private void checkGameOverAction() {//每10毫秒走一次
 if (h.getLife() <= 0) {//若英雄机生命值为0或小于0
 state = GAME_OVER;//将状态修改为GAME_OVER游戏结束状态
 }
 }
 /**
 * 启动程序的执行
 */
 public void action() {//测试代码
 MouseAdapter m = new MouseAdapter() {
 /**
 * 重写mouseMoved()鼠标移动事件
 * @param e
 */
 @Override
 public void mouseMoved(MouseEvent e) {
 if (state == RUNNING){//仅在运行状态下执行
 int x = e.getX();//获取鼠标的x坐标
 int y = e.getY();//获取鼠标的y坐标
 h.moveTo(x,y);//接收鼠标具体坐标
 }
 }
 
 /**
 * 重写mouseClicked() 鼠标点击事件
 * @param e
 */
 public void mouseClicked(MouseEvent e){
 switch (state){//根据当前状态做不同的处理
 case START://启动状态时
 state = RUNNING;//鼠标点击后改成运行状态
 break;
 case GAME_OVER://游戏结束状态时
 /**
 * 清理战场（将所有数据初始化）
 */
 score = 0;//总分归零
 s = new Sky();//天空初始化所有属性
 h = new HeroPlane();//英雄机初始化所有属性
 enemies = new FlyerObject[0];//敌人初始化所有属性
 bt = new Bullet[0];//子弹初始化所有属性
  
 state = START;//鼠标点击后修改为启动状态
 break;
 }
 }
 
 /**
 * 鼠标移出窗口事件
 * @param e
 */
 public void mouseExited(MouseEvent e){
 if (state == RUNNING){//若状态为运行
 state = PAUSE;//则将当前状态修改为暂停
 }
 }
 
 /**
 * 鼠标的进入窗口事件
 * @param e
 */
 public void mouseEntered(MouseEvent e){
 if (state == PAUSE){//若当前状态为暂停
 state = RUNNING;//则将当前状态修改为运行
 }
 }
 };
 this.addMouseListener(m);
 this.addMouseMotionListener(m);
 Timer timer = new Timer();//定时器对象
 int interval = 10;//定时的间隔（此间隔是以毫秒为单位）
 timer.schedule(new TimerTask() {
 @Override
 public void run() {//定时干的事（每10毫秒自动执行此方法当中的所有方法）
 if (state == RUNNING){//只在运行状态下执行
 enterAction();//敌人（大敌机，小敌机，小蜜蜂）入场
 shootAction();//子弹入场
 setpAction();//飞行物移动
 outOfBoundsAction();//删除越界的敌人
 bulletBangAction();//子弹与敌人的碰撞
 heroBangAction();//英雄机与敌人的碰撞
 checkGameOverAction();//检测游戏结束
 }
 repaint();//重新调用paint()方法（重画）
 }
 }, interval, interval);//定时计划表
 }
 
 /**
 * 重写paint方法，在窗口中画图片
 * @param g:画笔
 */
 public void paint(Graphics g){//每10毫秒走一次
 g.drawImage(s.getImage(), s.x, s.y,500,700, null);//画天空
 g.drawImage(s.getImage(), s.x, s.getY1(),500,700, null);//画第二张天空
 g.drawImage(h.getImage(),h.x,h.y,150,150,null);//画英雄机
 for (int i=0;i<enemies.length;i++){//遍历所有敌人
	 FlyerObject f = enemies[i];//获取每一个敌人
	 if( f instanceof Airplane ) {
		 g.drawImage(f.getImage(),f.x,f.y,150,150,null);
	 }
	 else if (f instanceof BigAirplane) {
		 g.drawImage(f.getImage(),f.x,f.y,250,250,null);
	 }
	 else {
		 g.drawImage(f.getImage(),f.x,f.y,70,70,null);
	 }

	 
	 
 //画敌人
 }
 for (int i = 0; i<bt.length; i++){//遍历所有子弹
 Bullet b = bt[i];//获取所有子弹
 g.drawImage(b.getImage(),b.x,b.y,15,30,null);//画子弹
 }
 g.drawString("SCORE:"+score,10,25);//在窗口右上角画分数
 g.drawString("HP:"+h.getLife(),10,45);//在窗口右上角画出英雄机的生命值
 switch (state){//画状态图
 case START:
 g.drawImage(Images.start,120,300,250,250,null);//启动状态图
 break;
 case PAUSE:
 g.drawImage(Images.pause,120,300,250,250,null);//暂停图
 break;
 case GAME_OVER:
 g.drawImage(Images.gameover,120,300,250,250,null);//游戏结束图
 break;
 }
 }
 
 /**
 * 主执行方法
 * @param args
 */
 public static void main(String[] args) {
 JFrame frame = new JFrame();
 GameDriver world = new GameDriver();
 frame.add(world);
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setSize(WIDTH,HEIGHT);
 frame.setLocationRelativeTo(null);
 frame.setVisible(true);//1）设置窗口可见 2）尽快调用paint()方法
 
 world.action();//启动程序的执行
 }
}
/**
 * 1.问:为什么要将引用设计再main方法的外面?
 * 答:因为若将引用设计在main中,则引用只能在main中使用,其他方法都不能访问,
 * 为了能在其他方法中也能访问这些引用,所以将引用设计在main外
 *
 * 2.问:为什么要单独创建action方法来测试?
 * 答:因为main方法时static的,在main方法中是无法访问引用的,
 * 所以需要单独创建非static的方法来测试
 *
 * 3.问:为什么在main中要先创建world对象,然后再调用action()方法?
 * 答:因为main方法是static的,再main中是无法调用action()方法的
 * 所以要先创建world对象,然后再调用action()方法
 */