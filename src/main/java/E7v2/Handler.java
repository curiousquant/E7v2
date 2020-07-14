package E7v2;

public class Handler {
    gui g;
    Bag b;
    MultiDim m;
    public Handler(gui g,Bag b,MultiDim m){
        this.g = g;
        this.b = b;
        this.m = m;
    }
    
    public Handler(gui g,Bag b){
        this.g = g;
        this.b = b;
    }
    
    public gui getG() {
        return this.g;
    }

    public void setG(gui g) {
        this.g = g;
    }

    public Bag getB() {
        return this.b;
    }

    public void setB(Bag b) {
        this.b = b;
    }

    public MultiDim getM() {
    	return this.m;
    }
    
    public void setM(MultiDim m) {
    	this.m = m;
    }
    
}