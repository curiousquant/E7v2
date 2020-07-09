package E7v2;

public class FinalHero {
 
    double atk,def,hp,spd,crit,cd,eff,effres;
    String name,bname;
    public FinalHero(String name,double atk, double def, double hp, double spd,
        double crit, double cd, double eff, double effres,String bname){
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.spd = spd;
        this.crit = crit;
        this.cd = cd;
        this.eff = eff;
        this.effres = effres;
        this.bname = bname;
    }

    public double getAtk() {
        return this.atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return this.def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public double getHp() {
        return this.hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getSpd() {
        return this.spd;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public double getCrit() {
        return this.crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public double getCd() {
        return this.cd;
    }

    public void setCd(double cd) {
        this.cd = cd;
    }

    public double getEff() {
        return this.eff;
    }

    public void setEff(double eff) {
        this.eff = eff;
    }

    public double getEffres() {
        return this.effres;
    }

    public void setEffres(double effres) {
        this.effres = effres;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBname() {
        return this.bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

}