package FlappyBirdProject5;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {
    public static FlappyBird flappyBird;
    public final int WIDTH = 800, HEIGHT = 800;
    public Renderer renderer;
    public Rectangle bird;
    public Random rand;
    public ArrayList<Rectangle> columns;
    public boolean gameOver, started;
    public int ticks, yMotion, score;


    //constructor
    public FlappyBird(){
        JFrame jf = new JFrame();
        Timer timer = new Timer(40,this);
        renderer = new Renderer();
        rand = new Random();

        jf.add(renderer);
        jf.setTitle("Flappy Bird");
        jf.setSize(WIDTH,HEIGHT);
        // "this" is the FlappyBird which is also a MouseListener and a KeyListener
        // "this" here is in the context of referring to the MouseListener
        // addMouseListener takes in a Mouselistener object
        jf.addMouseListener(this);
        jf.addKeyListener(this);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        columns = new ArrayList<>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();

    }


    public void addColumn(boolean start) {
        int width = 100;
        int space =0;
        int height =0;

        if (score <20 ) {
            space = 300;
            height = 50 + rand.nextInt(300);
        }
        else if (score <60) {
            space = 200;
            height = 50 + rand.nextInt(200);
        }
        else if (score <90) {
            space = 100;
            height = 50 + rand.nextInt(100);
        }

        if(start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height -120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size()-1) * 300, 0, width, HEIGHT - height - space));
        }

        else {
            columns.add(new Rectangle(columns.get(columns.size()-1).x +600, HEIGHT - height -120, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x,0, width, HEIGHT - height - space));
        }
    }

    public void jump(){
        if (gameOver) {

//            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;

        }
        if (!started) {
            started = true;
        }
        else if (!gameOver) {
            if(yMotion >0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }

    }
    public void paintColumn(Graphics g, Rectangle column) {

        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void repaint(Graphics g) {
        g.setColor(Color.CYAN.darker());
        g.fillRect(0,0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT-120, WIDTH, 120);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT-120, WIDTH, 20);

        g.setColor(Color.red);
        g.fillOval(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column: columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.white.darker());
        g.setFont(new Font("Calibri", 2, 100));

        if (!started) {
            g.drawString("Click to start!", 75, HEIGHT/2 -50);
        }
        if (gameOver) {
            g.drawString("Game Over!", 100, HEIGHT/2 -50);
        }
        if (!gameOver && started)
        {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }

    public static void main(String[] args) {
        flappyBird = new FlappyBird();

    }

//    public static void playMusic(String filepath) {
//        InputStream music;
//        try {
//            music = new FileInputStream(new File(filepath));
//            AudioStream audios = new AudioStream(music);
//            AudioPlayer.player.start(audios);
//        }
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error");
//        }
//    }
    public void sound(String f) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(f));

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
            // If you want to stop the sound, then use clip.stop();
        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ticks++;
        int speed =10;

        if (started) {
            for(int i=0; i<columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if (ticks %2 == 0 && yMotion <15) {
                yMotion += 2;
            }
            for(int i=0; i<columns.size(); i++) {
                Rectangle column = columns.get(i);
                if(column.x + column.width <0) {
                    columns.remove(column);
                    if (column.y ==0){
                        addColumn(false);
                    }
                }
            }



            bird.y += yMotion;

            for( Rectangle column: columns) {
                if (column.y == 0 && bird.x + bird.width / 2 > column.x  + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {
                    score++;
                    flappyBird.sound("ding-36029.wav");
                }

                if (column.intersects(bird)) {
                    gameOver = true;
                    if(bird.x <= column.x) {
                        bird.x = column.x - bird.width;
                    }
                    else {
                        if(column.y != 0) {
                            bird.y = column.y - bird.height;
                        }
                        else if (bird.y < column.height) {
                            bird.y = column.height;
                        }
                    }
                }
            }
            if (bird.y > HEIGHT - 120 || bird.y <0) {

                gameOver = true;
            }
            if (bird.y + yMotion >= HEIGHT - 120) {
                bird.y = HEIGHT - 120 - bird.height;
                gameOver = true;
            }
        }


        renderer.repaint();

    }



    @Override
    public void mouseClicked(MouseEvent e) {
            jump();
            flappyBird.sound("wing-flap.wav");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
            flappyBird.sound("wing-flap.wav");
        }
    }


}

//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.fillOval(0,0,10, 10);
//        //g.drawOval(0,0,100, 10);
//    }

//
//    JPanel jp = new JPanel();
//        jp.paintComponents(g -> {g.setColor(Color.RED);
//                g.fillOval(0,0,10, 10)});
//
//
//                jf.addKeyListener(new KeyListener() {
//@Override
//public void keyTyped(KeyEvent e) {
//        System.out.println("keychar" + e.getKeyChar());
//        }
//
//@Override
//public void keyPressed(KeyEvent e) {
//
//        }
//
//@Override
//public void keyReleased(KeyEvent e) {
//
//        }
//        });
//        jf.add(m, BorderLayout.CENTER);
//        jf.setVisible(true);
