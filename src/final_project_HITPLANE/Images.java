package final_project_HITPLANE;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
	 heros = new BufferedImage[2];//英雄机图片数组
	 airs = new BufferedImage[5];//小敌机图片数组
	 bairs = new BufferedImage[5];//大敌机图片数组
	 bees = new BufferedImage[5];//小蜜蜂图片数组
	 try {
		 start = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("start.png")));//启动状态图
		 pause = ImageIO.read(FlyerObject.class.getResource("pause.png"));//暂停状态图
		 gameover = ImageIO.read(FlyerObject.class.getResource("gameover.png"));//游戏结束状态图
		sky = ImageIO.read(FlyerObject.class.getResource("background01.png"));
		bullet = ImageIO.read(FlyerObject.class.getResource("bullet.png"));;//子弹
		 heros[0] = ImageIO.read(FlyerObject.class.getResource("hero0.png"));//英雄机图片1
		 heros[0] = ImageIO.read(FlyerObject.class.getResource("hero1.png"));//英雄机图片2 
		 airs[0] = ImageIO.read(FlyerObject.class.getResource("airplane.png"));//小敌机图片读取
		 bairs[0] = ImageIO.read(FlyerObject.class.getResource("bigairplane.png"));//大敌机图片读取
		 bees[0] = ImageIO.read(FlyerObject.class.getResource("bee01.png"));//小蜜蜂图片读取
		 for (int i=1;i<5;i++){//遍历/迭代赋值
			 airs[i] = ImageIO.read(FlyerObject.class.getResource("bom"+i+".png"));//小敌机图片数组其余元素赋值爆炸图
			 bairs[i] = ImageIO.read(FlyerObject.class.getResource("bom"+i+".png"));//大敌机图片数组其余元素赋值爆炸图
			 bees[i] = ImageIO.read(FlyerObject.class.getResource("bom"+i+".png"));//小蜜蜂图片数组其余元素赋值爆炸图
			 }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}