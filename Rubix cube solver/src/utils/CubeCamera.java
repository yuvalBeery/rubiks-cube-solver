package utils;

import enums.CubeColor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class CubeCamera extends JFrame {
    //dimensions finals
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int SQR_SIZE = 70;
    private static final int STROKE_WIDTH = 2;
    private static final int PREV_STROKE_WIDTH = 2;
    private static final int PREV_SQR_SIZE = 35;

    //grid location finals
    private static final int STARTING_SQR_X = (int) (WIDTH/2 - 1.5*SQR_SIZE - STROKE_WIDTH*2);
    private static final int STARTING_SQR_Y = (int) (HEIGHT/2 - 1.5*SQR_SIZE - STROKE_WIDTH*2);
    private static final int STARTING_PREV_SQR_X = (int) (6*WIDTH/7 - 1.5*SQR_SIZE - STROKE_WIDTH*2);
    private static final int STARTING_PREV_SQR_Y = (int) (6*HEIGHT/7 - 1.5*SQR_SIZE - STROKE_WIDTH*2);

    //camera variables
    private VideoCapture capture = new VideoCapture(0);
    private Mat frame = new Mat();

    //cube faces variables
    private int curFace = 0;
    public String[] faces = new String[6];

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public CubeCamera() {
        super("Camera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    curFace++;
                    if (curFace < 7)
                        paint(getGraphics());
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (curFace != 0)
                        curFace--;
                    paint(getGraphics());
                }
            }
        });

        while(curFace < 7) {
            paint(getGraphics());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        capture.release();
        dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (capture.read(frame)) {
            Core.flip(frame, frame, 1);
            BufferedImage img = (BufferedImage) toBufferedImage(frame);
            g.drawImage(img, 0, 0, null);
            drawGrid(g, img);
        }

        String text = "";
        if (curFace < 6)
            text = CubeColor.values()[curFace].getName() + " face";
        else if (curFace == 6)
            text = "Press space to proceed";
        Font font = new Font("TimesNewRoman", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.BLACK);
        int stringWidth = g.getFontMetrics().stringWidth(text);
        int midX = (getWidth() - stringWidth) / 2;
        g.drawString(text, midX, 75);

        if (curFace > 0 && curFace < 7)
            drawPrevGrid(g);
    }

    private Image toBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);

        return image;
    }

    private void drawGrid(Graphics g, BufferedImage img) {
        Graphics2D g2d = (Graphics2D) g;
        CubeColor color;
        char[] face = new char[8];

        //draw sqr1
        color = getSqrColor(img, STARTING_SQR_X, STARTING_SQR_Y);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        face[2] = (char) (color.ordinal() + 48);

        //draw aqr2
        color = getSqrColor(img, STARTING_SQR_X + SQR_SIZE, STARTING_SQR_Y);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        face[1] = (char) (color.ordinal() + 48);

        //draw aqr3
        color = getSqrColor(img, STARTING_SQR_X + 2*SQR_SIZE, STARTING_SQR_Y);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X+2*SQR_SIZE, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+2*SQR_SIZE, STARTING_SQR_Y, SQR_SIZE, SQR_SIZE);
        face[0] = (char) (color.ordinal() + 48);

        //draw sqr4
        color = getSqrColor(img, STARTING_SQR_X, STARTING_SQR_Y + SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);
        face[4] = (char) (color.ordinal() + 48);

        //draw aqr5
        color = getSqrColor(img, STARTING_SQR_X + SQR_SIZE, STARTING_SQR_Y + SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);

        //draw aqr6
        color = getSqrColor(img, STARTING_SQR_X + 2*SQR_SIZE, STARTING_SQR_Y + SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X+2*SQR_SIZE, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+2*SQR_SIZE, STARTING_SQR_Y+SQR_SIZE, SQR_SIZE, SQR_SIZE);
        face[3] = (char) (color.ordinal() + 48);

        //draw sqr7
        color = getSqrColor(img, STARTING_SQR_X, STARTING_SQR_Y + 2*SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        face[7] = (char) (color.ordinal() + 48);

        //draw aqr8
        color = getSqrColor(img, STARTING_SQR_X + SQR_SIZE, STARTING_SQR_Y + 2*SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+SQR_SIZE, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        face[6] = (char) (color.ordinal() + 48);

        //draw aqr9
        color = getSqrColor(img, STARTING_SQR_X + 2*SQR_SIZE, STARTING_SQR_Y + 2*SQR_SIZE);
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_SQR_X + 2*SQR_SIZE, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.drawRect(STARTING_SQR_X+2*SQR_SIZE, STARTING_SQR_Y+2*SQR_SIZE, SQR_SIZE, SQR_SIZE);
        face[5] = (char) (color.ordinal() + 48);

        if (curFace < 6)
            faces[curFace] = new String(face);
    }

    private void drawPrevGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CubeColor color;

        //draw sqr1
        color = CubeColor.values()[faces[curFace-1].charAt(2) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr2
        color = CubeColor.values()[faces[curFace-1].charAt(1) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr3
        color = CubeColor.values()[faces[curFace-1].charAt(0) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr4
        color = CubeColor.values()[faces[curFace-1].charAt(4) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr5
        color = CubeColor.values()[curFace-1];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr6
        color = CubeColor.values()[faces[curFace-1].charAt(3) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y + PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr7
        color = CubeColor.values()[faces[curFace-1].charAt(7) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr8
        color = CubeColor.values()[faces[curFace-1].charAt(6) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + PREV_SQR_SIZE, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);

        //draw sqr9
        color = CubeColor.values()[faces[curFace-1].charAt(5) - 48];
        g2d.setColor(color.getColor());
        g2d.fillRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(PREV_STROKE_WIDTH));
        g2d.drawRect(STARTING_PREV_SQR_X + 2*PREV_SQR_SIZE, STARTING_PREV_SQR_Y + 2*PREV_SQR_SIZE, PREV_SQR_SIZE, PREV_SQR_SIZE);
    }

    private CubeColor getSqrColor(BufferedImage img, int startingX, int startingY) {
        int r = 0, g = 0, b = 0;
        Color color;
        for (int i = 20; i < SQR_SIZE - 20; i++) {
            for (int j = 20; j < SQR_SIZE - 20; j++) {
                color = new Color(img.getRGB(startingX + i, startingY + j));
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
            }
        }

        r /= (SQR_SIZE - 40) * (SQR_SIZE - 40);
        g /= (SQR_SIZE - 40) * (SQR_SIZE - 40);
        b /= (SQR_SIZE - 40) * (SQR_SIZE - 40);
        return getEstimatedColor(r, g, b);
    }

    private CubeColor getEstimatedColor(int r, int g, int b) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(r, g, b, hsb);

        if (hsb[1] < 0.3)
            return CubeColor.WHITE;
        float deg = hsb[0] * 360;
        if (deg >= 180 && deg <= 240)
            return CubeColor.BLUE;
        else if ((deg >= 330 && deg <= 360) || (deg >= 0 && deg <= 12))
            return CubeColor.RED;
        else if (deg >= 120 && deg <= 180)
            return CubeColor.GREEN;
        else if (deg >= 12 && deg <= 35)
            return CubeColor.ORANGE;
        else if (deg >= 35 && deg <= 90)
            return CubeColor.YELLOW;

        return CubeColor.WHITE;
    }
}
