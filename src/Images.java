import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * 图片工具类
 */
public class Images {
// 公开的 静态的 图片数据类型 变量名
 /**
 * 对象图片
 */
 public static BufferedImage sky;//天空
 public static BufferedImage bullet;//子弹
 public static BufferedImage[] heros;//英雄机
 public static BufferedImage[] airs;//小敌机
 public static BufferedImage[] bairs;//大敌机
 public static BufferedImage[] bees;//小蜜蜂
 
 /**
 * 状态图片
 */
 public static BufferedImage start;//启动状态图
 public static BufferedImage pause;//暂停状态图
 public static BufferedImage gameover;//游戏结束状态图
 
 static {//初始化静态图片
 sky = readImage("background01.png");//天空
 bullet = readImage("bullet.png");//子弹
 heros = new BufferedImage[2];//英雄机图片数组
 heros[0] = readImage("hero0.png");//英雄机图片1
 heros[1] = readImage("hero1.png");//英雄机图片2
 airs = new BufferedImage[5];//小敌机图片数组
 bairs = new BufferedImage[5];//大敌机图片数组
 bees = new BufferedImage[5];//小蜜蜂图片数组
 airs[0] = readImage("airplane.png");//小敌机图片读取
 bairs[0] = readImage("bigairplane.png");//大敌机图片读取
 bees[0] = readImage("bee01.png");//小蜜蜂图片读取
 
 /**爆炸图迭代读取*/
 for (int i=1;i<5;i++){//遍历/迭代赋值
 airs[i] = readImage("bom"+i+".png");//小敌机图片数组其余元素赋值爆炸图
 bairs[i] = readImage("bom"+i+".png");//大敌机图片数组其余元素赋值爆炸图
 bees[i] = readImage("bom"+i+".png");//小蜜蜂图片数组其余元素赋值爆炸图
 }
 start = readImage("start.png");//启动状态图
 pause = readImage("pause.png");//暂停状态图
 gameover = readImage("gameover.png");//游戏结束状态图
 }
 
 /**
 * 读取图片
 * 此处的fileName：图片文件名
 *
 * try.....catch：异常的一种处理方法
 */
 public static BufferedImage readImage(String fileName){
 try{
 BufferedImage img = ImageIO.read(FlyerObject.class.getResource(fileName)); //读取与Flyer在同一个包中的图片
 return img;
 }catch(Exception e){
 e.printStackTrace();
 throw new RuntimeException();
 }
 }
}
 
