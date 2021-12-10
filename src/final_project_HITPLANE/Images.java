package final_project_HITPLANE;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;


/**
 * This class represent all images of objects, that will be composited in flyer objects to get the image
 */
public class Images {

    public static BufferedImage sky;
    public static BufferedImage bullet;

    public static BufferedImage hero;
    public static BufferedImage[] enemyPlane = new BufferedImage[5];
    public static BufferedImage[] bigEnemyPlane = new BufferedImage[5];
    public static BufferedImage[] bee = new BufferedImage[5];
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameOver;


    // initialize the images of the objects
    static {

        try {
            start = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("start.png")));
            pause = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("pause.png")));
            gameOver = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("gameOver.png")));

            sky = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("Sky.png")));
            bullet = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("Bullet.png")));
            hero = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("HeroPlane1.png")));

            enemyPlane[0] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("EnemyPlane.png")));
            bigEnemyPlane[0] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("BigEnemyPlane.png")));
            bee[0] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("Bee.png")));

            for (int i = 1; i < 5; i++) {
                enemyPlane[i] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("bom" + i + ".png")));
                bigEnemyPlane[i] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("bom" + i + ".png")));
                bee[i] = ImageIO.read(Objects.requireNonNull(FlyerObject.class.getResource("bom" + i + ".png")));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}