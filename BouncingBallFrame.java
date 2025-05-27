import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBallFrame extends JFrame {
    BallPanel panel;

    public BouncingBallFrame() {
        setTitle("Bouncing Ball Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String speedParam = JOptionPane.showInputDialog("Enter speed (e.g., 30):");
        int speed = 30;
        try {
            speed = Integer.parseInt(speedParam);
        } catch (Exception e) {}

        panel = new BallPanel(speed);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BouncingBallFrame();
    }
}

class BallPanel extends JPanel implements MouseListener {
    Ball ball1, ball2;
    int speed;

    public BallPanel(int speed) {
        this.speed = speed;
        ball1 = new Ball(this, 50, 50, 2, 3, 20, speed);
        ball2 = new Ball(this, 100, 80, 3, 2, 20, speed);
        Timer timer = new Timer(20, e -> {
            checkCollision();
            repaint();
        });
        timer.start();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball1.draw(g);
        ball2.draw(g);
    }

    private void checkCollision() {
        if (ball1.getBounds().intersects(ball2.getBounds())) {
            ball1.dx = -ball1.dx;
            ball1.dy = -ball1.dy;
            ball2.dx = -ball2.dx;
            ball2.dy = -ball2.dy;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ball1.getBounds().contains(e.getPoint())) {
            ball1.resetPosition();
        }
        if (ball2.getBounds().contains(e.getPoint())) {
            ball2.resetPosition();
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
