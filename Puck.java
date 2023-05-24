public class Puck extends Ball {

    private double xVelocity, yVelocity, friction;

    public Puck(double x, double y, double diameter, String col, int layer, double friction) {
        super(x, y, diameter, col, layer);
        this.friction = friction;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
    }

    public void movement(double left, double right, double top, double bottom, Mallet mallet1, Mallet mallet2) {
        double lineThickness = 10; 
        this.move(xVelocity, yVelocity);
        xVelocity *= friction;
        yVelocity *= friction;

        double xPos = this.getXPosition();
        double yPos = this.getYPosition();
        double sizeHalf = this.getSize() / 2;

        if (xPos - sizeHalf < left + lineThickness || xPos + sizeHalf > right - lineThickness) {
            xVelocity *= -1;
            this.setXPosition(xPos < left ? left + lineThickness + sizeHalf : right - lineThickness - sizeHalf);
        }
        
        if (yPos - sizeHalf < top + lineThickness || yPos + sizeHalf > bottom - lineThickness) {
            yVelocity *= -1;
            this.setYPosition(yPos < top ? top + lineThickness + sizeHalf : bottom - lineThickness - sizeHalf);
        }

        Mallet[] mallets = {mallet1, mallet2};

        for (Mallet mallet : mallets) {
            double xDistance = this.getXPosition() - mallet.getXPosition();
            double yDistance = this.getYPosition() - mallet.getYPosition();
            double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)) - mallet.getSize()/2 - sizeHalf;

            if (distance <= 0) {
                double[] norm = Collissions.normalizeVector(new double[]{xDistance, yDistance});
                double totalRadius = mallet.getSize()/2 + sizeHalf;
                double totalVelocity = Math.sqrt(Math.pow(this.xVelocity,2) + Math.pow(this.yVelocity,2));

                this.xVelocity = totalVelocity * norm[0];
                this.yVelocity = totalVelocity * norm[1];
                this.setXPosition(mallet.getXPosition() + norm[0] * totalRadius);
                this.setYPosition(mallet.getYPosition() + norm[1] * totalRadius);
            }
        }
    }
}





