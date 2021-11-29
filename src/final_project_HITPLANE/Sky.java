package final_project_HITPLANE;

import java.awt.image.BufferedImage;
 
/**
 * 天空
 */
public class Sky extends FlyerObject{
 // 移动速度,y1
 private int y1;//第二张图片的y坐标
 private int speed;//移动速度
 
 public Sky(){//设置初始值（默认值）
 //此处的宽高用常量是因为天空的宽高和窗口是一致的，x轴和y轴为若不为0就和窗口不匹配了
 super(GameDriver.WIDTH,GameDriver.HEIGHT,0,0);//初始化图片坐标及宽，高
 speed = 1;//初始化移动速度
 y1 = -GameDriver.HEIGHT;//第二张图片设置在第一张图片上方
 }
 /**重写step方法（移动）*/
 public void step(){
 y += speed;//第一张图向下移动
 y1 += speed;//第二张图向下移动
 if (y >= GameDriver.HEIGHT){//若y>=窗口的高
 y = -GameDriver.HEIGHT;//将移动出去的第一张天空挪到窗口上方
 }
 if (y1 >= GameDriver.HEIGHT){//若第二张天空挪出窗口
 y1 = -GameDriver.HEIGHT;//将第二张天空挪到窗口上方
 }
 }
 /**重写getImage()获取对象图片*/
 @Override
 public BufferedImage getImage() {//10毫秒走一次
 return Images.sky;//返回天空图片即可
 
 }
 
 /**
 * 获取y1坐标
 */
 public int getY1(){
 return y1;//返回y1
 }
}
