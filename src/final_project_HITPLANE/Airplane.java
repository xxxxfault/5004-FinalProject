package final_project_HITPLANE;
import java.awt.image.BufferedImage;
 
/**
 * 小敌机
 */
public class Airplane extends FlyerObject implements EnemyScore{
 // 移动速度
 private int speed;
 
 public Airplane(){
 super(66,89);
 speed = 2;//小敌机的下落速度
 }
 /**重写step方法（移动）*/
 public void step(){
 y += speed;//y+表示向下
 }
 int index = 1;
 /**
 * 重写getImage()获取对象图片
 * @return
 */
 public BufferedImage getImage() {
 if (isLive()){//若活着 则返回airs[0]图片
 return Images.airs[0];
 }else if (isDead()){//若死了 则返回airs[1~4]图片
 BufferedImage img = Images.airs[index++];//获取爆破图
 if (index == Images.airs.length){//若index到了5 则表示到了最后一张
 state = REMOVE;//将当前状态修改为REMOVE删除的
 }
 return img;//返回爆炸图
 /*
 index = 1
 10M isLive返回true 则 return返回airs[0]图片
 20M isLive返回false 则 执行isDead返回true img = airs[1] index = 2 返回airs[1]图片
 30M isLive返回false 则 执行isDead返回true img = airs[2] index = 3 返回airs[2]图片
 40M isLive返回false 则 执行isDead返回true img = airs[3] index = 4 返回airs[3]图片
 50M isLive返回false 则 执行isDead返回true img = airs[4] index = 5 state修改为REMOVE 返回airs[4]图片
 60M isLive返回false 则 执行isDead返回false return返回null空值（不返回图片）
 */
 }
 return null;
 }
 
 /**
 * 重写getScore()方法
 * @return：分值
 */
 public int getScore(){
 return 1;
 }
}