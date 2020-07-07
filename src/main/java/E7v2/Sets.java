package E7v2;
public class Sets {
   
    Equipment weapon, head, chest, neck, ring, boot;
    public Sets(Equipment weapon,Equipment head,Equipment chest,Equipment neck,Equipment ring,Equipment boot){
        this.weapon = weapon;
        this.head = head;
        this.chest = chest;
        this.neck = neck;
        this.ring = ring;
        this.boot = boot;
        
    }

    public Equipment getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    public Equipment getHead() {
        return this.head;
    }

    public void setHead(Equipment head) {
        this.head = head;
    }

    public Equipment getChest() {
        return this.chest;
    }

    public void setChest(Equipment chest) {
        this.chest = chest;
    }

    public Equipment getNeck() {
        return this.neck;
    }

    public void setNeck(Equipment neck) {
        this.neck = neck;
    }

    public Equipment getRing() {
        return this.ring;
    }

    public void setRing(Equipment ring) {
        this.ring = ring;
    }

    public Equipment getBoot() {
        return this.boot;
    }

    public void setBoot(Equipment boot) {
        this.boot = boot;
    }
    

    
}