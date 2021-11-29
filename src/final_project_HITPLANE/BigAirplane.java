package final_project_HITPLANE;
import java.awt.image.BufferedImage;
import java.util.Random;

 
/**
 * 大敌机
 */
public class BigAirplane extends FlyerObject implements EnemyScore{
// 移动速度
 private int speed;
 
 public BigAirplane(){//初始化默认属性
 super(203,211);//图片宽，高
 speed = 3;//移动速度
 }
 
 /**重写step方法（移动）*/
 public void step(){
 y += speed;//y+表示直线向下移动
 }
 
 int index = 1;
 @Override
 public BufferedImage getImage() {
 if (isLive()){//若活着 则返回airs[0]图片
 return Images.bairs[0];
 }else if (isDead()){//若死了 则返回airs[1~4]图片
 BufferedImage img = Images.bairs[index++];//获取爆破图
 if (index == Images.bairs.length){//若index到了5 则表示到了最后一张
 state = REMOVE;//将当前状态修改为REMOVE删除的
 }
 return img;
 }
 return null;
 }
 
 /**
 * 重写getScore()方法
 * @return：分值
 */
 public int getScore(){
 return 3;
 }
}