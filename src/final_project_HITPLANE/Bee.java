package final_project_HITPLANE;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyerObject implements EnemyAward{
 // x坐标移动速度,y坐标移动速度,
 private int xSpeed;//x坐标移动速度
 private final int ySpeed;//y坐标移动速度
 private final int awardType;//奖励类型
 
 public Bee(){//初始化属性
 super(48,50);//图片宽，高
 Random rand = new Random();
 awardType = rand.nextInt(2);//随机奖励值类型0~2之间（不包括2）0表示火力值，1表示生命值
 xSpeed = 1;//平行移动
 ySpeed = 2;//垂直移动
 }
 /**重写step方法（移动）*/
 public void step() {
 y += ySpeed;//y+：向下移动
 x += xSpeed;//x+：随机向左或是向右移动
 if (x <= 0 || x >= GameDriver.WIDTH - width) {
 xSpeed *= -1;//到达边界后反方向移动（正负为负，负负为正）
 }
 }
 
 int index = 1;
 public BufferedImage getImage() {
 if (isLive()){//若活着 则返回airs[0]图片
 return Images.bees[0];//返回小蜜蜂图
 }else if (isDead()){//若死了 则返回airs[1~4]图片
 BufferedImage img = Images.bees[index++];//获取爆破图
 if (index == Images.bees.length){//若index到了5 则表示到了最后一张
 state = REMOVE;//将当前状态修改为REMOVE删除的
 }
 return img;//返回爆炸图
 }
 return null;
 }
 
 /**
 * 重写getAwardType()方法
  */
 public int getAwardType(){
 return awardType;//返回奖励类型
 }
}