package E7v2;
public abstract class Equipment implements Comparable<Equipment>{
    protected int pk,id,f_atk,f_def,f_hp,p_atk,p_def,p_hp,c,cd,spd,eff,effres;
    protected String set;
    protected double a_score;
    
    public Equipment(int pk,int id,int f_atk,int f_def, int f_hp, 
            int p_atk, int p_def, int p_hp, int c,int cd,int spd,int eff, int effres,String set){
        this.pk=pk;
        this.id=id;
        this.f_atk=f_atk;
        this.f_def=f_def;
        this.f_hp=f_hp;
        this.p_atk=p_atk;
        this.p_def=p_def;
        this.p_hp=p_hp;
        this.c = c;
        this.cd = cd;
        this.spd = spd;
        this.a_score=0;
        this.eff = eff;
        this.effres=effres;
        this.set = set;
    }

    public int compareTo(Equipment other) {
        if(this.getA_score() > other.getA_score())
            return 1;
        else if (this.getA_score() == other.getA_score())
            return 0 ;
        return -1 ;
    }

    public double atk_score(){
        this.a_score = this.f_atk*(1+this.p_atk*.01)*this.c*.01*this.cd*.01
                    + this.f_atk*(1+this.p_atk*.01)*(1-this.c*.01);
        //System.out.println(this.a_score);
        return this.a_score;
    }

    public String getSet() {
        return this.set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public double getA_score(){
        return this.a_score;
    }

    public void  setA_score(double a_score){
        this.a_score = a_score;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getC() {
        return this.c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getCd() {
        return this.cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getF_atk() {
        return this.f_atk;
    }

    public void setF_atk(int f_atk) {
        this.f_atk = f_atk;
    }

    public int getF_def() {
        return this.f_def;
    }

    public void setF_def(int f_def) {
        this.f_def = f_def;
    }

    public int getF_hp() {
        return this.f_hp;
    }

    public void setF_hp(int f_hp) {
        this.f_hp = f_hp;
    }

    public int getP_atk() {
        return this.p_atk;
    }

    public void setP_atk(int p_atk) {
        this.p_atk = p_atk;
    }

    public int getP_def() {
        return this.p_def;
    }

    public void setP_def(int p_def) {
        this.p_def = p_def;
    }

    public int getP_hp() {
        return this.p_hp;
    }

    public void setP_hp(int p_hp) {
        this.p_hp = p_hp;
    }
    public int getSpd() {
        return this.spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
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

    public int getPk() {
        return this.pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
    
}