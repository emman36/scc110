import java.awt.geom.Point2D;

public class mainClass {

    public static void main(String[] args) {

        GameArena game = new GameArena(700,400,true);

        Rectangle rectangle = new Rectangle(50,50, 600, 300, "WHITE");
        game.addRectangle(rectangle);

        Puck puck = new Puck(350, 200, 16, "BLACK", 4, 0.78);
        game.addBall(puck);

        Ball innerMiddle = new Ball(350, 200, 78, "WHITE",3);
        game.addBall(innerMiddle);

        Ball outerMiddle = new Ball(350, 200, 80, "BLUE",2);
        game.addBall(outerMiddle);

        Mallet mallet1 = new Mallet(550, 200, 50, "BLUE", 4, 5);
        game.addBall(mallet1);

        Mallet mallet2 = new Mallet(150, 200, 50, "BLUE", 4, 5);
        game.addBall(mallet2);

        Line line1 = new Line(50, 50, 650, 50, 20, "BLUE", 6);
        game.addLine(line1);

        Line line2 = new Line(50, 50, 50,350, 20, "BLUE", 6);
        game.addLine(line2);

        Line line3 = new Line(50, 350, 650, 350, 20, "BLUE", 6);
        game.addLine(line3);

        Line line4 = new Line(650, 50, 650, 350, 20, "BLUE", 6);
        game.addLine(line4);

        Line line5 = new Line(637, 125, 637, 275, 6, "GREY", 5);
        game.addLine(line5);

        Line line6 = new Line(63, 125, 63, 275, 6, "GREY", 5);
        game.addLine(line6);

        Line line7 = new Line(350, 50, 350, 350, 1, "BLUE", 1);
        game.addLine(line7);

        while (true) {

            mallet1.setVelocity(game.upPressed(), game.downPressed(), game.leftPressed(), game.rightPressed());
            mallet1.movement(50, 650, 50, 350);

            mallet2.setVelocity(game.letterPressed('W'), game.letterPressed('S'), game.letterPressed('A'), game.letterPressed('D'));
            mallet2.movement(50, 650, 50, 350);


            puck.movement(50, 650, 50, 350, mallet1, mallet2);

            double distance1 = Point2D.distance(mallet1.getXPosition(), mallet1.getYPosition(), puck.getXPosition(), puck.getYPosition());

            if (distance1 <= (mallet1.getSize() + puck.getSize()) / 2) {
                double[] collision1 = Collissions.deflect(mallet1.getXPosition(), mallet1.getYPosition(), mallet1.getXVelocity(), mallet1.getYVelocity(), puck.getXPosition(), puck.getYPosition(), puck.getXVelocity(), puck.getYVelocity());
                puck.setXVelocity(collision1[2]);
                puck.setYVelocity(collision1[3]);
            }
            
            double distance2 = Point2D.distance(mallet2.getXPosition(), mallet2.getYPosition(), puck.getXPosition(), puck.getYPosition());
            
            if (distance2 <= (mallet2.getSize() + puck.getSize()) / 2) {
                double[] collision2 = Collissions.deflect(mallet2.getXPosition(), mallet2.getYPosition(), mallet2.getXVelocity(), mallet2.getYVelocity(), puck.getXPosition(), puck.getYPosition(), puck.getXVelocity(), puck.getYVelocity());
                puck.setXVelocity(collision2[2]);
                puck.setYVelocity(collision2[3]);
            }

            game.pause();
            
        }
    }
}
