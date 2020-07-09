package E7v2;

public class AllResults {
    double[][][][][][]allatk, alldef,allhp;
    
    public AllResults(double[][][][][][] allatk,double[][][][][][] alldef,double[][][][][][] allhp){
        this.allatk=allatk;
        this.alldef = alldef;
        this.allhp = allhp;
    }

    public double[][][][][][] getAllatk() {
        return this.allatk;
    }

    public void setAllatk(double[][][][][][] allatk) {
        this.allatk = allatk;
    }

    public double[][][][][][] getAlldef() {
        return this.alldef;
    }

    public void setAlldef(double[][][][][][] alldef) {
        this.alldef = alldef;
    }

    public double[][][][][][] getAllhp() {
        return this.allhp;
    }

    public void setAllhp(double[][][][][][] allhp) {
        this.allhp = allhp;
    }
    
}