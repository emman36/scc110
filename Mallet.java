public class Mallet extends Ball {

    private double xVelocity, yVelocity, speed;

	public Mallet(double x, double y, double diameter, String col, int layer, double speed) {
        super(x, y, diameter, col, layer);
        
        this.speed = speed;
    }

    public double getXVelocity(){
        return xVelocity;
    }

    public double getYVelocity(){
        return yVelocity;
    }

    public void setVelocity(boolean up, boolean down, boolean left, boolean right) {
        int xDirection = 0;
        int yDirection = 0;

        if(up) 
            yDirection -= 1;
        if(down) 
            yDirection += 1;
        if(left) 
            xDirection -= 1;
        if(right) 
            xDirection += 1;

        if (xDirection == 0 && yDirection == 0) {
            xVelocity = 0;
            yVelocity = 0;
        }

        if (xDirection != 0 && yDirection != 0) {
            xVelocity = (xDirection * speed) / Math.sqrt(2);
            yVelocity = (yDirection * speed) / Math.sqrt(2);
        } else {
            xVelocity = xDirection * speed;
            yVelocity = yDirection * speed;
        }
    }

    public void movement(double left, double right, double top, double bottom) {
        double nextXPosition = this.getXPosition() + xVelocity;
        double nextYPosition = this.getYPosition() + yVelocity;
    
        if (nextXPosition - this.getSize()/2 <= left || nextXPosition + this.getSize()/2 >= right) {
            xVelocity = -xVelocity;
        }
        
        if (nextYPosition - this.getSize()/2 <= top || nextYPosition + this.getSize()/2 >= bottom) {
            yVelocity = -yVelocity;
        }
    
        this.move(xVelocity, yVelocity);
    
        double halfSize = this.getSize() / 2;
        double xPosition = Math.min(Math.max(this.getXPosition(), left + halfSize), right - halfSize);
        double yPosition = Math.min(Math.max(this.getYPosition(), top + halfSize), bottom - halfSize);
    
        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
    
    }

}