package final_project_HITPLANE;
import java.awt.image.BufferedImage;

public class HeroPlane extends FlyerObject {
	 // 命数，火力值
	 private int life;//命数
	 private int fire;//火力
	 
	 /**
	 * 初始化英雄机坐标机具体数据
	 */
	 public HeroPlane() {
	 super(97,139,140,400);//宽，高，及初始坐标
	 fire = 0;//初始火力值 0：单倍火力
	 life = 3;//初始生命值
	 }
	 /**重写step方法（移动）*/
	 public void step(){//每10毫秒走一次
	 //因为英雄机是跟随鼠标移动的，而鼠标是在窗口上的所以这里就没有写具体的方法，而是在窗口类中去用鼠标的具体坐标计算出英雄机的移动位置
	 }
	 
	 int index = 0;//下标
	 /**重写getImage()获取对象图片*/
	 @Override
	 public BufferedImage getImage() {//每10毫秒走一次
	 return Images.heros[index++ % Images.heros.length];//heros[0]和heros[1]来回切换
	 /**
	 *过程
	 *index = 0
	 *10M 返回heros[0] index = 1
	 *20M 返回heros[1] index = 2
	 *30M 返回heros[0] index = 3
	 *40M 返回heros[1] index = 4
	 *50M 返回heros[0] index = 5
	 *60M 返回heros[1] index = 6
	 *...........
	 */
	 }
	 /**
	 * 英雄机发射子弹（生成子弹对象）
	 */
	 public Bullet[] shoot(){
	 int xStep = this.width/4;//子弹x坐标
	 int yStep = 5;//子弹y坐标
	 System.out.println(this.x+"\t"+this.y);
	 if (fire>0){//双倍火力
	 Bullet[] bs = new Bullet[3];//2发子弹
	 bs[0] = new Bullet(this.x+1*xStep,this.y-yStep);//子弹坐标1
	 bs[1] = new Bullet(this.x+3*xStep,this.y-yStep);//子弹坐标2
	 bs[2] = new Bullet(this.x+2*xStep,this.y-yStep);
	 fire -= 2;//发射一次双倍活力，则火力值-2
	 return bs;
	 } else {//单倍火力
	 Bullet[] bs = new Bullet[1];//1发子弹
	 bs[0] = new Bullet(this.x+2*xStep,this.y-yStep);//x:英雄机的x+2/4英雄机的宽，y:英雄机的y-
	 return bs;
	 }
	 }
	 
	 /**
	 * 英雄机移动
	 */
	 public void moveTo(int x,int y){//形参列表：鼠标的x坐标，y坐标
	 this.x = x - this.width/2;//英雄机的x = 鼠标的x减1/2英雄机的宽
	 this.y = y - this.height/2;//英雄机的y = 鼠标的y减1/2英雄机的高
	 }
	 /**
	 * 英雄机增生命值
	 */
	 public void addLife(){
	 life++;//生命值+1
	 }
	 
	 /**
	 * 获取英雄机生命值
	 * @return
	 */
	 public int getLife(){
	 return life;//返回生命值
	 }
	 
	 /**
	 * 英雄机减少生命值
	 */
	 public void subtractLife(){
	 life--;//生命值减1
	 }
	 /**
	 * 英雄机增火力值
	 */
	 public void addFier(){
	 fire += 40;//火力值+40
	 }
	 
	 /**
	 * 清空火力值
	 */
	 public void clearFier(){
	 fire = 0;//火力值归零
	 }

}
