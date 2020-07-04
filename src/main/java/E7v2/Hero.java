package E7v2;

public class Hero {
    String name;
    int atk, def, hp, spd, crit, critdmg, eff, effres;
    Handler h;
    public Hero(String name,int atk, int def, int hp, int spd, int crit, int critdmg, int eff, int effres){
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.spd = spd;
        this.crit = crit;
        this.critdmg = critdmg;
        this.eff = eff;
        this.effres = effres;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return this.def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpd() {
        return this.spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getCrit() {
        return this.crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getCritdmg() {
        return this.critdmg;
    }

    public void setCritdmg(int critdmg) {
        this.critdmg = critdmg;
    }

    public int getEff() {
        return this.eff;
    }

    public void setEff(int eff) {
        this.eff = eff;
    }

    public int getEffres() {
        return this.effres;
    }

    public void setEffres(int effres) {
        this.effres = effres;
    }

    public Handler getH() {
        return this.h;
    }

    public void setH(Handler h) {
        this.h = h;
    }



    
}