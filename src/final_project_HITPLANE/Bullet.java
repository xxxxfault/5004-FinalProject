package final_project_HITPLANE;
import java.awt.image.BufferedImage;

public class Bullet extends FlyerObject{
	private int speed;
	 public Bullet(int x,int y) {//子弹有多个，每个子弹的初始坐标都不同，所以要写活
	 super(15,30,x,y);
	 speed = 3;//初始移动速度
	 }
	 /**重写step方法（移动)*/
	 public void step(){
	 y -= speed;//y-：表示直线向上移动
	 }
	 
	 /**
	 * 重写getImage()获取对象图片
	 * @return
	 */
	 @Override
	 public BufferedImage getImage() {//10毫秒走一次
	 if (isLive()){//若活着则返回bullet图片
	 return Images.bullet;
	 }else if (isDead()){//若死了则将state修改为REMOVE
	 state = FlyerState.Remove;
	 }
	 return null;//死了的和删除的都返回null空值
	 /**
	 * 若活着 则返回bullet图片
	 * 若死了 则修改REMOVE 再返回空值
	 * 若删除 则返回空值
	 */
	 }
	 
	 /**
	 * 判断子弹是否越界
	 * @return
	 */
	 public boolean isOutOfBounds(){
	 return y <= -height;//若子弹的y轴坐标小于自己的高则说明移动到了窗口外部
	 }
	}
