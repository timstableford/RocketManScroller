package uk.co.tstableford.sidescroller;

public class Vector2D {
    private float x,y;
    public Vector2D(){
            this.setX(0);
            this.setY(0);
    }
    public float dX(Vector2D other){
            return other.x - this.x;
    }
    public float dY(Vector2D other){
            return other.y - this.y;
    }
    public Vector2D(float x, float y){
            this();
            this.setX(x);
            this.setY(y);
    }
    public float dot(Vector2D other) {
            float dot = (this.x*other.x + this.y*other.y);
            return dot;
    }

    public float perpDot(Vector2D other) {
            float pdot =  this.x*other.y-this.y*other.x;
            return pdot;
    }
    public float angle(Vector2D other){
            return (float)(Math.PI-Math.atan2(this.dX(other), this.dY(other)));
    }
    public float getMagnitude(){
            return (float)Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }
    public Vector2D multiply(Vector2D other){
            return new Vector2D(this.x*other.x, this.y*other.y);
    }
    public Vector2D scale(float scale){
            float x = this.x * scale;
            float y = this.y * scale;
            return new Vector2D(x, y);
    }
    public float distance(Vector2D other){
            float dx = other.x - this.x;
            float dy = other.y - this.y;
            return new Vector2D(dx, dy).getMagnitude();
    }
    public Vector2D add(Vector2D other){
            float x = this.x + other.x;
            float y = this.y + other.y;
            return new Vector2D(x, y);
    }
    public Vector2D subtract(Vector2D other){
            float x = this.x - other.x;
            float y = this.y - other.y;
            return new Vector2D(x, y);
    }
    public float getX() {
            return x;
    }
    public void setX(float x) {
            this.x = x;
    }
    public float getY() {
            return y;
    }
    public void setY(float y) {
            this.y = y;
    }
    @Override
    public String toString(){
    	return "["+this.x+", "+this.y+"]";
    }
}
