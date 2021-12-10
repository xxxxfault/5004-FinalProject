package final_project_HITPLANE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Arrays;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * the panel for the entire game
 */
public class GamePanel extends JPanel {
    //initialize the panel and  objects
    private GameState state = GameState.Start;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;


    private Sky s = new Sky();
    private HeroPlane h = new HeroPlane();
    private FlyerObject[] enemies = {};
    private Bullet[] bt = {};
    private int score = 0;
    private int enterIndex = 0;
    private int shootIndex = 0;
    private AudioClip music;

    //main method to start the game
    public static void main(String[] args) throws Exception {

        GamePanel world = new GamePanel();

        JFrame frame = new JFrame("Hit Plane Game     🌟Record: " + world.paintRecord() + "🌟");
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        world.action();

    }


    /**
     * generate the enemy using the random number, when the number is less than 5, then generate bee object,
     * when the number is larger than 15 , generate the big enemy plane object, otherwise generate the enemy plane object
     */
    public FlyerObject generateEnemy() {
        Random rand = new Random();
        int type = rand.nextInt(20);
        if (type < 5) {
            return new Bee();
        } else if (type < 15) {
            return new EnemyPlane();
        } else {
            return new BigEnemyPlane();
        }
    }


    /**
     * let the enemy enter the game frame, put the newest generated enemy at the end of the enemy list
     */
    public void enemyEnter() {
        enterIndex++;
        if (enterIndex % 30 == 0) {
            FlyerObject fl = generateEnemy();
            enemies = Arrays.copyOf(enemies, enemies.length + 1);
            enemies[enemies.length - 1] = fl;
        }
    }


    /**
     * let the bullet shoot by the hero plane,  put the newest bullet at the end of the bullet list
     */
    public void shootBullet() {
        shootIndex++;
        if (shootIndex % 35 == 0) {
            Bullet[] bs = h.shoot();
            bt = Arrays.copyOf(bt, bt.length + bs.length);
            System.arraycopy(bs, 0, bt, bt.length - bs.length, bs.length);
        }
    }

    /**
     * let all objects except the hero plane move automatically in the frame
     */
    public void step() {
        s.move();
        for (FlyerObject enemy : enemies) enemy.move();
        for (Bullet bullet : bt) bullet.move();
    }

    /**
     * This method clear the enemies that are out of bound or have the remove state
     */
    public void outOfBounds() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].isOutOfBounds() || enemies[i].isRemove()) {
                enemies[i] = enemies[enemies.length - 1];
                enemies = Arrays.copyOf(enemies, enemies.length - 1);
            }
        }
        for (int i = 0; i < bt.length; i++) {
            if (bt[i].isOutOfBounds() || bt[i].isRemove()) {
                bt[i] = bt[bt.length - 1];
                bt = Arrays.copyOf(bt, bt.length - 1);
            }
        }
    }


    /**
     * check if the bullet of the hero plane hit enemy, if it does, set the bullet and enemy dead,
     * then determine the interface of the enemy and reward the specific type to the hero plane :
     */
    public void bulletBang() {
        for (int i = 0; i < bt.length; i++) {
            Bullet b = bt[i];
            for (int j = 0; j < enemies.length; j++) {
                FlyerObject f = enemies[j];
                if (b.isLive() && f.isLive() && f.isHit(b)) {
                    b.goDead();
                    f.goDead();
                    if (f instanceof EnemyScore) {
                        EnemyScore es = (EnemyScore) f;
                        score += es.getScore();
                    }
                    if (f instanceof EnemyAward) {
                        EnemyAward ea = (EnemyAward) f;
                        int type = ea.getAwardType();
                        switch (type) {
                            case EnemyAward.FIRE:
                                h.addFire();
                                break;
                            case EnemyAward.LIFE:
                                h.addLife();
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * check if the hero plane bang with the enemy
     * if the hit between them actually happen, set the enemy dead and subtract the hero plane's life with 1
     */
    private void heroBang() {
        for (int i = 0; i < enemies.length; i++) {
            FlyerObject f = enemies[i];
            if (f.isLive() && h.isLive() && f.isHit(h)) {
                f.goDead();
                h.subtractLife();
                h.clearFire();
            }
        }
    }

    /**
     * when hero plane's life is equal or below 0, game is over, and update the score in database
     *
     * @throws Exception
     */
    private void checkGameOver() throws Exception {

        if (h.getLife() <= 0) {

            state = GameState.GameOver;
            music.stop();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hit_plane?useSSL=false&serverTimezone=UTC", "root", "Xujiaqiano");

            Statement st = conn.createStatement();
            st.executeUpdate("insert into score values(" + score + ");");
        }
    }


    public void action() {
        MouseAdapter m = new MouseAdapter() {
            /**
             * when the game state is running, the hero plane should move with the mouse
             * @param e
             */
            @Override
            public void mouseMoved(MouseEvent e) {
                if (state == GameState.Running) {
                    int x = e.getX();
                    int y = e.getY();
                    h.moveTo(x, y);
                }
            }

            /**
             * when the mouse click, if the state of the game is start, switch to running,
             * if the state is game over, reinitialize and set the game state to start
             * @param e
             */
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case Start:
                        state = GameState.Running;
                        music();
                        break;
                    case Running:
                        state = GameState.Pause;
                        music.stop();
                        break;
                    case Pause:
                        state = GameState.Running;
                        music.loop();
                        break;
                    case GameOver:
                        /**
                         * reinitialize all information
                         */

                        score = 0;
                        s = new Sky();
                        h = new HeroPlane();
                        enemies = new FlyerObject[0];
                        bt = new Bullet[0];

                        state = GameState.Start;
                        break;
                }
            }

            /**
             * when the mouse exit the frame, if the state of the game is running, switch the state to pause
             * @param e
             */
            public void mouseExited(MouseEvent e) {
                if (state == GameState.Running) {
                    state = GameState.Pause;
                    music.stop();
                }
            }

            /**
             * when the mouse enter the frame, if the state of the game is pause, switch the state to running
             * @param e
             */
//            public void mouseEntered(MouseEvent e) {
//                if (state == GameState.Pause) {
//                    state = GameState.Running;
//                }
//            }
        };
        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        Timer timer = new Timer();
        int interval = 10;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == GameState.Running) {
                    enemyEnter();
                    shootBullet();
                    step();
                    outOfBounds();
                    bulletBang();
                    heroBang();

                    try {
                        checkGameOver();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                repaint();
            }
        }, interval, interval);
    }

    /**
     * Override the paint method, paint the images used in this game
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        //paint the background of the game
        g.drawImage(s.getImage(), s.x, s.y, s.width, s.height, null);
        g.drawImage(s.getImage(), s.x, s.getY1(), s.width, s.height, null);

        //paint the hero plane
        g.drawImage(h.getImage(), h.x, h.y, h.width, h.height, null);

        //paint the enemies:bee, enemy plane, big enemy plane
        for (int i = 0; i < enemies.length; i++) {
            FlyerObject f = enemies[i];
            g.drawImage(f.getImage(), f.x, f.y, f.width, f.height, null);

        }
        // paint the bullet
        for (int i = 0; i < bt.length; i++) {
            Bullet b = bt[i];
            g.drawImage(b.getImage(), b.x, b.y, b.width, b.height, null);
        }
        //display player's score , life and fire  in the top-left corner
        g.drawString("SCORE:" + score, 10, 25);
        g.drawString("LIFE:" + h.getLife(), 10, 45);
        g.drawString("FIRE:" + h.getFire(), 10, 65);

        //paint the images of game state
        switch (state) {
            case Start:
                g.drawImage(Images.start, 120, 300, 250, 250, null);
                break;
            case Pause:
                g.drawImage(Images.pause, 120, 300, 250, 250, null);
                break;
            case GameOver:
                g.drawImage(Images.gameOver, 120, 300, 250, 250, null);
                break;
        }
    }


    private int paintRecord() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hit_plane?useSSL=false&serverTimezone=UTC", "root", "Xujiaqiano");
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(score) as score from score;");
            while (res.next()) {
                int maxScore = res.getInt("score");
                return maxScore;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * initialize the background and play it
     */
    private void music() {
        try {
            File f = new File("src/final_project_HITPLANE/background_music.wav");
            URI uri = f.toURI();
            URL url = uri.toURL();

            music = Applet.newAudioClip(url);
            music.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
