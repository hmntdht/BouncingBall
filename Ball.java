import java.awt.*;
import java.util.Random;

public class Ball implements Runnable {
    int x, y, dx, dy, radius;
    Color color;
    Component container;
    Thread thread;
    int speed;

    public Ball(Component container, int x, int y, int dx, int dy, int radius, int speed) {
        this.container = container;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.speed = speed;
        this.color = randomColor();
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            x += dx;
            y += dy;

            if (x < 0 || x > container.getWidth() - radius) {
                dx = -dx;
                color = randomColor();
            }
            if (y < 0 || y > container.getHeight() - radius) {
                dy = -dy;
                color = randomColor();
            }

            container.repaint();
            try {
                Thread.sleep(1000 / speed);
            } catch (InterruptedException e) {}
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius, radius);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radius, radius);
    }

    private Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public void resetPosition() {
        x = container.getWidth() / 2;
        y = container.getHeight() / 2;
    }
}
