package final_project_HITPLANE;
import java.awt.image.BufferedImage;

/**
 * Bullet class inherit the abstract FlyerObject class and represent the bullet shoot by the hero plane in this game
 */
public class Bullet extends FlyerObject{
	private final int speed;

	/**
	 * Constructor initialize a bullet object, take x,y as the bullet position
	 * @param x
	 * @param y
	 */
	 public Bullet(int x,int y) {
	 super(15,30,x,y);
	 speed = 3;
	 }

	/**
	 * let the bullet move from hero plane to the top of the game frame
	 */
	public void move(){
	 y -= speed;
	 }
	 
	 /**
	 * Override the getImage method,
	 * @return the image of the bullet if the state of the bullet is live,
	  * set the state of the bullet remove then return null if the state of the bullet is dead
	  * return null if the state of the bullet is remove
	 */
	 @Override
	 public BufferedImage getImage() {
	 if (isLive()){
	 return Images.bullet;
	 }else if (isDead()){
	 state = FlyerState.Remove;
	 }
	 return null;

	 }
	 
	 /**
	 * determine if the bullet is out of bounds, since the bullet move form downside to upside,
	  * when the y position is less than height of the bullet, the bullet is out of bounds
	 * @return true if the bullet is out of bounds, otherwise return false
	 */
	 public boolean isOutOfBounds(){
	 return y <= -height;
	 }
	}
