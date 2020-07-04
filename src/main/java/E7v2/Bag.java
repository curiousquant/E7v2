package E7v2;

import java.util.ArrayList;
import java.util.HashMap;

public class Bag {

    private int row, column;
    int[][] inventory;
    private int wcnt = 0;
    private int bcnt = 0;
    private int cnnt = 0;
    private int hcnt = 0;
    private int ncnt = 0;
    private int rcnt = 0;
    private int cntbar;
    private int maxw = 0, maxb = 0, maxc = 0, maxh = 0, maxn = 0, maxr = 0;
    Handler handler;
    ArrayList<Equipment> w = new ArrayList<>();
    ArrayList<Equipment> r = new ArrayList<>();
    ArrayList<Equipment> n = new ArrayList<>();
    ArrayList<Equipment> h = new ArrayList<>();
    ArrayList<Equipment> ch = new ArrayList<>();
    ArrayList<Equipment> b = new ArrayList<>();

    HashMap<String,Hero> heroBag = new HashMap<>();

    ArrayList<Sets> sets = new ArrayList<>();

    public Bag(String path, String heroPath, Handler handler) {
        loadInventory(path);
        loadHeros(heroPath);
        this.handler = handler;
    }

    public Sets superCalcs(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);
        int cntbar = 1;
        double maxatk = 0;
        double atk = 0;
        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {
                                int atkE1 = (w.get(i).getSet().equals("atk")) ? 1 : 0;
                                int atkE2 = (r.get(j).getSet().equals("atk")) ? 1 : 0;
                                int atkE3 = (n.get(k).getSet().equals("atk")) ? 1 : 0;
                                int atkE4 = (h.get(l).getSet().equals("atk")) ? 1 : 0;
                                int atkE5 = (ch.get(m).getSet().equals("atk")) ? 1 : 0;
                                int atkE6 = (b.get(n1).getSet().equals("atk")) ? 1 : 0;

                                int desE1 = (w.get(i).getSet().equals("des")) ? 1 : 0;
                                int desE2 = (r.get(j).getSet().equals("des")) ? 1 : 0;
                                int desE3 = (n.get(k).getSet().equals("des")) ? 1 : 0;
                                int desE4 = (h.get(l).getSet().equals("des")) ? 1 : 0;
                                int desE5 = (ch.get(m).getSet().equals("des")) ? 1 : 0;
                                int desE6 = (b.get(n1).getSet().equals("des")) ? 1 : 0;

                                // atk set
                                if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    atk = 1.35 * ((1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd()) * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd()) * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd()) * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd()) * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd()) * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd())
                                                    * (b.get(n1).getC()));
                                }
                                // dest set
                                else if (desE1 + desE2 + desE3 + desE4 + desE5 + desE6 >= 4) {
                                    atk = (1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd() + .4 + 1.5)
                                            * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd() + .4 + 1.5)
                                                    * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd() + .4 + 1.5)
                                                    * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd() + .4 + 1.5)
                                                    * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd() + .4 + 1.5)
                                                    * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd() + .4 + 1.5)
                                                    * (b.get(n1).getC());
                                }
                                // broken set
                                else {
                                    atk = (1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd()) * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd()) * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd()) * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd()) * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd()) * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd()) * (b.get(n1).getC());
                                }

                                if (maxatk < atk) {
                                    maxatk = atk;
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);
                                }
                            }

                        }
                    }
                }
            }
        }

        System.out.println("atk score: " + maxatk);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        atk: " + maxW.getP_atk() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        atk: " + maxH.getP_atk() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        atk: " + maxCH.getP_atk() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        atk: " + maxN.getP_atk() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        atk: " + maxR.getP_atk() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        atk: " + maxB.getP_atk() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    public Sets superBellaCalcs(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);

        int cntbar = 1;
        double maxatk = 0;
        double atk = 0;
        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {
                                int atkE1 = (w.get(i).getSet().equals("atk")) ? 1 : 0;
                                int atkE2 = (r.get(j).getSet().equals("atk")) ? 1 : 0;
                                int atkE3 = (n.get(k).getSet().equals("atk")) ? 1 : 0;
                                int atkE4 = (h.get(l).getSet().equals("atk")) ? 1 : 0;
                                int atkE5 = (ch.get(m).getSet().equals("atk")) ? 1 : 0;
                                int atkE6 = (b.get(n1).getSet().equals("atk")) ? 1 : 0;

                                // atk set
                                if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    atk = 1.35 * ((1 + w.get(i).getP_atk())) + (1 + r.get(j).getP_atk())
                                            + (1 + n.get(k).getP_atk()) + (1 + h.get(l).getP_atk())
                                            + (1 + ch.get(m).getP_atk()) + (1 + b.get(n1).getP_atk());
                                }

                                // broken set
                                else {
                                    atk = (1 + w.get(i).getP_atk()) + (1 + r.get(j).getP_atk())
                                            + (1 + n.get(k).getP_atk()) + (1 + h.get(l).getP_atk())
                                            + (1 + ch.get(m).getP_atk()) + (1 + b.get(n1).getP_atk());
                                }

                                if (maxatk < atk) {
                                    maxatk = atk;
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);
                                }
                            }

                        }
                    }
                }
            }
        }

        System.out.println("atk score: " + maxatk);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        atk: " + maxW.getP_atk() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        atk: " + maxH.getP_atk() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        atk: " + maxCH.getP_atk() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        atk: " + maxN.getP_atk() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        atk: " + maxR.getP_atk() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        atk: " + maxB.getP_atk() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    // Double.parseDouble(customAtkTxt.getText()),Double.parseDouble(customHpTxt.getText()),
    // Double.parseDouble(customCritTxt.getText()),Double.parseDouble(customSpeedTxt.getText()),
    // Double.parseDouble(customDefTxt.getText()))
    public Sets superCustomCalcs(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b, Double catk, Double chp,
            Double cCrit, Double cSpeed, Double cDef, Double cEff, Double cCd, Hero hero) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);

        int heroatk = hero.getAtk();
        int herodef = hero.getDef();
        int herohp = hero.getHp();
        int herospd = hero.getSpd();
        int herocrit = hero.getCrit();
        int herocd = hero.getCritdmg();
        int heroeff = hero.getEff();
        int heroeffres = hero.getEffres();

        double maxatk = 0;
        double atk = 0,fatk=0;
        double crit = 0;
        double eff = 0;
        double spd = 0;
        double hp = 0,fhp=0;
        double def = 0,fdef=0;
        double dest = 0;
        cntbar = 1;
        // wcnt=5;
        // rcnt=5;
        // ncnt=5;
        // hcnt=5;
        // cnnt=5;
        // bcnt=5;

        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            // progressBar.setText(Integer.toString(getCntBar()));;

            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {

                                int atkE1 = (w.get(i).getSet().equals("atk")) ? 1 : 0;
                                int atkE2 = (r.get(j).getSet().equals("atk")) ? 1 : 0;
                                int atkE3 = (n.get(k).getSet().equals("atk")) ? 1 : 0;
                                int atkE4 = (h.get(l).getSet().equals("atk")) ? 1 : 0;
                                int atkE5 = (ch.get(m).getSet().equals("atk")) ? 1 : 0;
                                int atkE6 = (b.get(n1).getSet().equals("atk")) ? 1 : 0;

                                int critE1 = (w.get(i).getSet().equals("crit")) ? 1 : 0;
                                int critE2 = (r.get(j).getSet().equals("crit")) ? 1 : 0;
                                int critE3 = (n.get(k).getSet().equals("crit")) ? 1 : 0;
                                int critE4 = (h.get(l).getSet().equals("crit")) ? 1 : 0;
                                int critE5 = (ch.get(m).getSet().equals("crit")) ? 1 : 0;
                                int critE6 = (b.get(n1).getSet().equals("crit")) ? 1 : 0;

                                int desE1 = (w.get(i).getSet().equals("des")) ? 1 : 0;
                                int desE2 = (r.get(j).getSet().equals("des")) ? 1 : 0;
                                int desE3 = (n.get(k).getSet().equals("des")) ? 1 : 0;
                                int desE4 = (h.get(l).getSet().equals("des")) ? 1 : 0;
                                int desE5 = (ch.get(m).getSet().equals("des")) ? 1 : 0;
                                int desE6 = (b.get(n1).getSet().equals("des")) ? 1 : 0;

                                int effE1 = (w.get(i).getSet().equals("eff")) ? 1 : 0;
                                int effE2 = (r.get(j).getSet().equals("eff")) ? 1 : 0;
                                int effE3 = (n.get(k).getSet().equals("eff")) ? 1 : 0;
                                int effE4 = (h.get(l).getSet().equals("eff")) ? 1 : 0;
                                int effE5 = (ch.get(m).getSet().equals("eff")) ? 1 : 0;
                                int effE6 = (b.get(n1).getSet().equals("eff")) ? 1 : 0;

                                int hpE1 = (w.get(i).getSet().equals("hp")) ? 1 : 0;
                                int hpE2 = (r.get(j).getSet().equals("hp")) ? 1 : 0;
                                int hpE3 = (n.get(k).getSet().equals("hp")) ? 1 : 0;
                                int hpE4 = (h.get(l).getSet().equals("hp")) ? 1 : 0;
                                int hpE5 = (ch.get(m).getSet().equals("hp")) ? 1 : 0;
                                int hpE6 = (b.get(n1).getSet().equals("hp")) ? 1 : 0;

                                int defE1 = (w.get(i).getSet().equals("def")) ? 1 : 0;
                                int defE2 = (r.get(j).getSet().equals("def")) ? 1 : 0;
                                int defE3 = (n.get(k).getSet().equals("def")) ? 1 : 0;
                                int defE4 = (h.get(l).getSet().equals("def")) ? 1 : 0;
                                int defE5 = (ch.get(m).getSet().equals("def")) ? 1 : 0;
                                int defE6 = (b.get(n1).getSet().equals("def")) ? 1 : 0;

                                int spdE1 = (w.get(i).getSet().equals("spd")) ? 1 : 0;
                                int spdE2 = (r.get(j).getSet().equals("spd")) ? 1 : 0;
                                int spdE3 = (n.get(k).getSet().equals("spd")) ? 1 : 0;
                                int spdE4 = (h.get(l).getSet().equals("spd")) ? 1 : 0;
                                int spdE5 = (ch.get(m).getSet().equals("spd")) ? 1 : 0;
                                int spdE6 = (b.get(n1).getSet().equals("spd")) ? 1 : 0;

                                def =(w.get(i).getP_def()) + (r.get(j).getP_def()) + (n.get(k).getP_def())
                                        + (h.get(l).getP_def()) + (ch.get(m).getP_def()) + (b.get(n1).getP_def());

                                fdef = (w.get(i).getF_def()) + (r.get(j).getF_def()) + (n.get(k).getF_def())
                                + (h.get(l).getF_def()) + (ch.get(m).getF_def()) + (b.get(n1).getF_def());

                                if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 == 2) {
                                    def += 15;
                                } else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 == 4) {
                                    def += 30;
                                } else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 == 6) {
                                    def += 45;
                                }
                                
                                def = fdef + (1+def/100)*herodef;


                                hp =  (w.get(i).getP_hp()) + (r.get(j).getP_hp()) + (n.get(k).getP_hp())
                                        + (h.get(l).getP_hp()) + (ch.get(m).getP_hp()) + (b.get(n1).getP_hp());
                                fhp = (w.get(i).getF_hp()) + (r.get(j).getF_hp()) + (n.get(k).getF_hp())
                                + (h.get(l).getF_hp()) + (ch.get(m).getF_hp()) + (b.get(n1).getF_hp());
                                
                                if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 2) {
                                    hp += 15;
                                } else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 4) {
                                    hp += 30;
                                } else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 6) {
                                    hp += 45;
                                }

                                hp = fhp + (1+hp/100)*herohp;

                                if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 == 2) {
                                    crit = herocrit + 12 + (w.get(i).getC()) + (r.get(j).getC()) + (n.get(k).getC())
                                            + (h.get(l).getC()) + (ch.get(m).getC()) + (b.get(n1).getC());
                                } else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 == 4) {
                                    crit = herocrit + 24 + (w.get(i).getC()) + (r.get(j).getC()) + (n.get(k).getC())
                                            + (h.get(l).getC()) + (ch.get(m).getC()) + (b.get(n1).getC());
                                } else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 == 6) {
                                    crit = herocrit + 36 + (w.get(i).getC()) + (r.get(j).getC()) + (n.get(k).getC())
                                            + (h.get(l).getC()) + (ch.get(m).getC()) + (b.get(n1).getC());
                                } else {
                                    crit = herocrit + (w.get(i).getC()) + (r.get(j).getC()) + (n.get(k).getC()) + (h.get(l).getC())
                                            + (ch.get(m).getC()) + (b.get(n1).getC());
                                }

                                // atk set
                                fatk = (w.get(i).getF_atk()) + (r.get(j).getF_atk()) + (n.get(k).getF_atk())
                                + (h.get(l).getF_atk()) + (ch.get(m).getF_atk()) + (b.get(n1).getF_atk());

                                if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {    
                                   
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    atk = 35+((w.get(i).getP_atk()) + (r.get(j).getP_atk()) + (n.get(k).getP_atk())
                                            + (h.get(l).getP_atk()) + (ch.get(m).getP_atk()) + (b.get(n1).getP_atk()));
                                    atk = fatk+heroatk*(1+atk/100);
                                }
                                // broken set
                                else {
                                    atk = ((w.get(i).getP_atk()) + (r.get(j).getP_atk()) + (n.get(k).getP_atk())
                                            + (h.get(l).getP_atk()) + (ch.get(m).getP_atk()) + (b.get(n1).getP_atk()));
                                          
                                    
                                    atk = fatk+heroatk*(1+atk/100);
                                }
                                // dest set
                                if (desE1 + desE2 + desE3 + desE4 + desE5 + desE6 >= 4) {
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    dest = 35 + ((w.get(i).getCd()) + (r.get(j).getCd()) + (n.get(k).getCd())
                                            + (h.get(l).getCd()) + (ch.get(m).getCd()) + (b.get(n1).getCd()));
                                }
                                // broken set
                                else {
                                    dest = ((w.get(i).getCd()) + (r.get(j).getCd()) + (n.get(k).getCd())
                                            + (h.get(l).getCd()) + (ch.get(m).getCd()) + (b.get(n1).getCd()));
                                }

                                dest = dest + herocd;

                                if (spdE1 + spdE2 + spdE3 + spdE4 + spdE5 + spdE6 >= 4) {
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    spd = 1.25*herospd + (w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd()
                                            + h.get(l).getSpd() + ch.get(m).getSpd() + b.get(n1).getSpd());
                                }
                                // broken set
                                else {
                                    spd = herospd + w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd() + h.get(l).getSpd()
                                            + ch.get(m).getSpd() + b.get(n1).getSpd();
                                }

                                eff = (w.get(i).getEff()) + (r.get(j).getEff()) + (n.get(k).getEff())
                                        + (h.get(l).getEff()) + (ch.get(m).getEff()) + (b.get(n1).getEff());

                                if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 2) {
                                    eff += 20;
                                } else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 2) {
                                    eff += 40;
                                } else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 6) {
                                    eff += 60;
                                }
                                
                                eff=eff+heroeff;

                                if (atk >= catk && hp >= chp && crit >= cCrit && spd >= cSpeed && def >= cDef
                                        && eff >= cEff && dest >= cCd) {
                                    
                                    //System.out.println(atk);  
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);
                                }
                            }

                        }
                    }
                }
            }
        }

        System.out.println("atk score: " + maxatk);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        atk: " + maxW.getP_atk() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        atk: " + maxH.getP_atk() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        atk: " + maxCH.getP_atk() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        atk: " + maxN.getP_atk() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        atk: " + maxR.getP_atk() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        atk: " + maxB.getP_atk() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    public Sets superHpCalcs(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);

        int cntbar = 1;
        double maxhp = 0;
        double hp = 0;
        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {
                                int hpE1 = (w.get(i).getSet().equals("hp")) ? 1 : 0;
                                int hpE2 = (r.get(j).getSet().equals("hp")) ? 1 : 0;
                                int hpE3 = (n.get(k).getSet().equals("hp")) ? 1 : 0;
                                int hpE4 = (h.get(l).getSet().equals("hp")) ? 1 : 0;
                                int hpE5 = (ch.get(m).getSet().equals("hp")) ? 1 : 0;
                                int hpE6 = (b.get(n1).getSet().equals("hp")) ? 1 : 0;

                                // hp set
                                if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 2) {
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    hp = 1.20 * ((1 + w.get(i).getP_hp())) + (1 + r.get(j).getP_hp())
                                            + (1 + n.get(k).getP_hp()) + (1 + h.get(l).getP_hp())
                                            + (1 + ch.get(m).getP_hp()) + (1 + b.get(n1).getP_hp());
                                } else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 4) {
                                    hp = 1.40 * ((1 + w.get(i).getP_hp())) + (1 + r.get(j).getP_hp())
                                            + (1 + n.get(k).getP_hp()) + (1 + h.get(l).getP_hp())
                                            + (1 + ch.get(m).getP_hp()) + (1 + b.get(n1).getP_hp());
                                } else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 6) {
                                    hp = 1.60 * ((1 + w.get(i).getP_hp())) + (1 + r.get(j).getP_hp())
                                            + (1 + n.get(k).getP_hp()) + (1 + h.get(l).getP_hp())
                                            + (1 + ch.get(m).getP_hp()) + (1 + b.get(n1).getP_hp());
                                }
                                // broken set
                                else {
                                    hp = (1 + w.get(i).getP_hp()) + (1 + r.get(j).getP_hp()) + (1 + n.get(k).getP_hp())
                                            + (1 + h.get(l).getP_hp()) + (1 + ch.get(m).getP_hp())
                                            + (1 + b.get(n1).getP_hp());
                                }

                                if (maxhp < hp) {
                                    maxhp = hp;
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("hp score: " + maxhp);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        hp: " + maxW.getP_hp() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        hp: " + maxH.getP_hp() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        hp: " + maxCH.getP_hp() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        hp: " + maxN.getP_hp() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        hp: " + maxR.getP_hp() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        hp: " + maxB.getP_hp() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    public Sets superCalcsHunt(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);

        double maxatk = 0;
        double atk = 0;
        double spd = 0;
        double maxspd = 0;
        int cntbar = 1;
        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {
                                int atkE1 = (w.get(i).getSet().equals("atk")) ? 1 : 0;
                                int atkE2 = (r.get(j).getSet().equals("atk")) ? 1 : 0;
                                int atkE3 = (n.get(k).getSet().equals("atk")) ? 1 : 0;
                                int atkE4 = (h.get(l).getSet().equals("atk")) ? 1 : 0;
                                int atkE5 = (ch.get(m).getSet().equals("atk")) ? 1 : 0;
                                int atkE6 = (b.get(n1).getSet().equals("atk")) ? 1 : 0;

                                int spdE1 = (w.get(i).getSet().equals("spd")) ? 1 : 0;
                                int spdE2 = (r.get(j).getSet().equals("spd")) ? 1 : 0;
                                int spdE3 = (n.get(k).getSet().equals("spd")) ? 1 : 0;
                                int spdE4 = (h.get(l).getSet().equals("spd")) ? 1 : 0;
                                int spdE5 = (ch.get(m).getSet().equals("spd")) ? 1 : 0;
                                int spdE6 = (b.get(n1).getSet().equals("spd")) ? 1 : 0;

                                int desE1 = (w.get(i).getSet().equals("des")) ? 1 : 0;
                                int desE2 = (r.get(j).getSet().equals("des")) ? 1 : 0;
                                int desE3 = (n.get(k).getSet().equals("des")) ? 1 : 0;
                                int desE4 = (h.get(l).getSet().equals("des")) ? 1 : 0;
                                int desE5 = (ch.get(m).getSet().equals("des")) ? 1 : 0;
                                int desE6 = (b.get(n1).getSet().equals("des")) ? 1 : 0;

                                int effE1 = (w.get(i).getSet().equals("eff")) ? 1 : 0;
                                int effE2 = (r.get(j).getSet().equals("eff")) ? 1 : 0;
                                int effE3 = (n.get(k).getSet().equals("eff")) ? 1 : 0;
                                int effE4 = (h.get(l).getSet().equals("eff")) ? 1 : 0;
                                int effE5 = (ch.get(m).getSet().equals("eff")) ? 1 : 0;
                                int effE6 = (b.get(n1).getSet().equals("eff")) ? 1 : 0;

                                // atk set
                                if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    atk = 1.35 * ((1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd()) * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd()) * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd()) * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd()) * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd()) * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd())
                                                    * (b.get(n1).getC()));
                                }
                                // dest set
                                else if (desE1 + desE2 + desE3 + desE4 + desE5 + desE6 >= 4) {
                                    atk = (1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd() + .4 + 1.5)
                                            * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd() + .4 + 1.5)
                                                    * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd() + .4 + 1.5)
                                                    * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd() + .4 + 1.5)
                                                    * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd() + .4 + 1.5)
                                                    * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd() + .4 + 1.5)
                                                    * (b.get(n1).getC());
                                }
                                // broken set
                                else {
                                    atk = (1 + w.get(i).getP_atk()) * (1 + w.get(i).getCd()) * (w.get(i).getC())
                                            + (1 + r.get(j).getP_atk()) * (1 + r.get(j).getCd()) * (r.get(j).getC())
                                            + (1 + n.get(k).getP_atk()) * (1 + n.get(k).getCd()) * (n.get(k).getC())
                                            + (1 + h.get(l).getP_atk()) * (1 + h.get(l).getCd()) * (h.get(l).getC())
                                            + (1 + ch.get(m).getP_atk()) * (1 + ch.get(m).getCd()) * (ch.get(m).getC())
                                            + (1 + b.get(n1).getP_atk()) * (1 + b.get(n1).getCd()) * (b.get(n1).getC());
                                }

                                if (spdE1 + spdE2 + spdE3 + spdE4 + spdE5 + spdE6 >= 4) {
                                    // System.out.println(spdE1+spdE2+spdE3+spdE4+spdE5+spdE6);
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    spd = 1.25 * (w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd()
                                            + h.get(l).getSpd() + ch.get(m).getSpd() + b.get(n1).getSpd());
                                }
                                // broken set
                                else {
                                    spd = w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd() + h.get(l).getSpd()
                                            + ch.get(m).getSpd() + b.get(n1).getSpd();
                                }

                                int eff = w.get(i).getEff() + r.get(j).getEff() + n.get(k).getEff() + h.get(l).getEff()
                                        + ch.get(m).getEff() + b.get(n1).getEff();
                                if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 2) {
                                    eff += 20;
                                } else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 2) {
                                    eff += 40;
                                } else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 6) {
                                    eff += 60;
                                }
                                if (maxatk < atk && spd >= (45 + 10) && eff >= 65) {

                                    maxatk = atk;
                                    maxspd = spd;
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);
                                }
                            }

                        }
                    }
                }
            }
        }
        System.out.println("atk score: " + maxatk);
        System.out.println("spd score: " + maxspd);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        atk: " + maxW.getP_atk() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        atk: " + maxH.getP_atk() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        atk: " + maxCH.getP_atk() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        atk: " + maxN.getP_atk() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        atk: " + maxR.getP_atk() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        atk: " + maxB.getP_atk() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    public Sets superSpeedCalcs(ArrayList<Equipment> w, ArrayList<Equipment> h, ArrayList<Equipment> ch,
            ArrayList<Equipment> n, ArrayList<Equipment> r, ArrayList<Equipment> b) {
        Equipment maxW = w.get(0);
        Equipment maxR = r.get(0);
        Equipment maxN = n.get(0);
        Equipment maxH = h.get(0);
        Equipment maxCH = ch.get(0);
        Equipment maxB = b.get(0);
        int cntbar = 1;
        double maxspd = 0;
        double spd = 0;
        for (int i = 0; i < wcnt; i++) {
            handler.getG().drawProgress(Math.ceil(400 * ((double) cntbar / (wcnt))));
            System.out.println(Math.ceil(400 * ((double) cntbar / (wcnt))));
            // handler.getG().drawProgress2(cntbar);

            cntbar++;
            for (int j = 0; j < rcnt; j++) {
                for (int k = 0; k < ncnt; k++) {
                    for (int l = 0; l < hcnt; l++) {
                        for (int m = 0; m < cnnt; m++) {
                            for (int n1 = 0; n1 < bcnt; n1++) {
                                int spdE1 = (w.get(i).getSet().equals("spd")) ? 1 : 0;
                                int spdE2 = (r.get(j).getSet().equals("spd")) ? 1 : 0;
                                int spdE3 = (n.get(k).getSet().equals("spd")) ? 1 : 0;
                                int spdE4 = (h.get(l).getSet().equals("spd")) ? 1 : 0;
                                int spdE5 = (ch.get(m).getSet().equals("spd")) ? 1 : 0;
                                int spdE6 = (b.get(n1).getSet().equals("spd")) ? 1 : 0;

                                // atk set
                                if (spdE1 + spdE2 + spdE3 + spdE4 + spdE5 + spdE6 >= 4) {
                                    // System.out.println(spdE1+spdE2+spdE3+spdE4+spdE5+spdE6);
                                    // atk =
                                    // 1.35*(w.get(i).getA_score()+r.get(j).getA_score()+n.get(k).getA_score()+h.get(l).getA_score());
                                    spd = 1.25 * (w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd()
                                            + h.get(l).getSpd() + ch.get(m).getSpd() + b.get(n1).getSpd());
                                }
                                // broken set
                                else {
                                    spd = w.get(i).getSpd() + r.get(j).getSpd() + n.get(k).getSpd() + h.get(l).getSpd()
                                            + ch.get(m).getSpd() + b.get(n1).getSpd();
                                }

                                if (maxspd < spd) {
                                    maxspd = spd;
                                    maxW = w.get(i);
                                    maxR = r.get(j);
                                    maxN = n.get(k);
                                    maxH = h.get(l);
                                    maxCH = ch.get(m);
                                    maxB = b.get(n1);

                                    setMaxw(i);
                                    setMaxr(j);
                                    setMaxn(k);
                                    setMaxh(l);
                                    setMaxc(m);
                                    setMaxb(n1);

                                }
                            }

                        }
                    }
                }
            }
        }
        System.out.println("spd score: " + maxspd);
        System.out.println("    weapon id: " + maxW.getPk());
        System.out.println("        atk: " + maxW.getP_atk() + " spd: " + maxW.getSpd() + " crit: " + maxW.getC()
                + " critD: " + maxW.getCd());
        System.out.println("    head id: " + maxH.getPk());
        System.out.println("        atk: " + maxH.getP_atk() + " spd: " + maxH.getSpd() + " crit: " + maxH.getC()
                + " critD: " + maxH.getCd());
        System.out.println("    chest id: " + maxCH.getPk());
        System.out.println("        atk: " + maxCH.getP_atk() + " spd: " + maxCH.getSpd() + " crit: " + maxCH.getC()
                + " critD: " + maxCH.getCd());
        System.out.println("    necklace id: " + maxN.getPk());
        System.out.println("        atk: " + maxN.getP_atk() + " spd: " + maxN.getSpd() + " crit: " + maxN.getC()
                + " critD: " + maxN.getCd());
        System.out.println("    ring id: " + maxR.getPk());
        System.out.println("        atk: " + maxR.getP_atk() + " spd: " + maxR.getSpd() + " crit: " + maxR.getC()
                + " critD: " + maxR.getCd());
        System.out.println("    boot id: " + maxB.getPk());
        System.out.println("        atk: " + maxB.getP_atk() + " spd: " + maxB.getSpd() + " crit: " + maxB.getC()
                + " critD: " + maxB.getCd());

        w.remove(getMaxw());
        r.remove(getMaxr());
        n.remove(getMaxn());
        h.remove(getMaxh());
        ch.remove(getMaxc());
        b.remove(getMaxb());
        wcnt--;
        bcnt--;
        cnnt--;
        hcnt--;
        ncnt--;
        rcnt--;
        Sets s = new Sets(maxW, maxH, maxCH, maxN, maxR, maxB);
        getSets().add(s);
        return s;
    }

    public void calcScore() {
        for (int i = 0; i < wcnt; i++) {
            w.get(i).atk_score();
        }
        for (int i = 0; i < rcnt; i++) {
            r.get(i).atk_score();
        }
        for (int i = 0; i < ncnt; i++) {
            n.get(i).atk_score();
        }
        for (int i = 0; i < hcnt; i++) {
            h.get(i).atk_score();
        }
        for (int i = 0; i < cnnt; i++) {
            ch.get(i).atk_score();
        }
        for (int i = 0; i < bcnt; i++) {
            b.get(i).atk_score();
        }
    }

    private void loadHeros(String path) {
        String file = Utils.loadFileAsString(path);
        String[] token = file.split("\\s+");
        String name="";
        String txt = "";
        int atk=0, def=0, hp=0, spd=0, crit=0, critdmg=0, eff=0, effres=0;
        for (int i = 0; i < token.length / 9; i++) {
            for (int j = 0; j < 9; j++) {
                txt = token[i * 9 + j];
                if (j == 0) {
                    name = txt;
                } else if (j == 1) {
                    atk = Utils.parseInt(txt);
                } else if (j == 2) {
                    def = Utils.parseInt(txt);
                } else if (j == 3) {
                    hp = Utils.parseInt(txt);
                } else if (j == 4) {
                    spd = Utils.parseInt(txt);
                } else if (j == 5) {
                    crit = Utils.parseInt(txt);
                } else if (j == 6) {
                    critdmg = Utils.parseInt(txt);
                } else if (j == 7) {
                    eff = Utils.parseInt(txt);
                } else if (j == 8) {
                    effres = Utils.parseInt(txt);
                }
            }
            heroBag.put(name,new Hero(name,atk,def,hp,spd,crit,critdmg,eff,effres));
            //System.out.println(name+atk+def+hp+spd+crit+critdmg+eff+effres);
        }
    }

    private void loadInventory(String path) {

        String file = Utils.loadFileAsString(path);
        String[] token = file.split("\\s+");
        String txt = "";
        String type = "";
        String set = "";
        int f_atk = 0, f_def = 0, f_hp = 0, p_atk = 0, p_def = 0, p_hp = 0, c = 0, cd = 0, spd = 0, eff = 0, effres = 0;

        row = Utils.parseInt(token[0]);
        column = Utils.parseInt(token[1]);

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                txt = token[(x * column + y + 2)];
                if (y == 0) {
                    type = txt;
                } else if (y == 1) {
                    f_atk = Utils.parseInt(txt);
                } else if (y == 2) {
                    f_def = Utils.parseInt(txt);
                } else if (y == 3) {
                    f_hp = Utils.parseInt(txt);
                } else if (y == 4) {
                    p_atk = Utils.parseInt(txt);
                } else if (y == 5) {
                    p_def = Utils.parseInt(txt);
                } else if (y == 6) {
                    p_hp = Utils.parseInt(txt);
                } else if (y == 7) {
                    c = Utils.parseInt(txt);
                } else if (y == 8) {
                    cd = Utils.parseInt(txt);
                } else if (y == 9) {
                    spd = Utils.parseInt(txt);
                } else if (y == 10) {
                    eff = Utils.parseInt(txt);
                } else if (y == 11) {
                    effres = Utils.parseInt(txt);
                } else if (y == 12) {
                    set = txt;
                    if (type.equals("weapon")) {
                        w.add(new Weapon(wcnt + 1, wcnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff,
                                effres, set));
                        wcnt++;
                    } else if (type.equals("boot")) {
                        b.add(new Boot(bcnt + 1, bcnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff, effres,
                                set));
                        bcnt++;
                    } else if (type.equals("chest")) {
                        ch.add(new Chest(cnnt + 1, cnnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff,
                                effres, set));
                        cnnt++;
                    } else if (type.equals("head")) {
                        h.add(new Head(hcnt + 1, hcnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff, effres,
                                set));
                        hcnt++;
                    } else if (type.equals("neck")) {
                        n.add(new Necklace(ncnt + 1, ncnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff,
                                effres, set));
                        ncnt++;
                    } else if (type.equals("ring")) {
                        r.add(new Ring(rcnt + 1, rcnt, f_atk, f_def, f_hp, p_atk, p_def, p_hp, c, cd, spd, eff, effres,
                                set));
                        rcnt++;
                    }
                }
            }
        }
    }

    // public Equipment maxHelment(){
    // int max_idx=0;
    // double max_score=0;
    // double score = 0;

    // for (int i=0;i<hcnt;i++){
    // score = h.get(i).atk_score();
    // if (score>=max_score){
    // max_idx = i;
    // max_score = score;
    // }
    // }
    // return h.get(max_idx);
    // }

    public HashMap<String,Hero> getHeroBag() {
        return this.heroBag;
    }

    public void setHeroBag(HashMap<String,Hero> heroBag) {
        this.heroBag = heroBag;
    }


    public int getCntBar() {
        return this.cntbar;
    }

    public void setCntBar(int cntbar) {
        this.cntbar = cntbar;
    }

    public ArrayList<Sets> getSets() {
        return this.sets;
    }

    public int getMaxw() {
        return this.maxw;
    }

    public void setMaxw(int maxw) {
        this.maxw = maxw;
    }

    public int getMaxb() {
        return this.maxb;
    }

    public void setMaxb(int maxb) {
        this.maxb = maxb;
    }

    public int getMaxc() {
        return this.maxc;
    }

    public void setMaxc(int maxc) {
        this.maxc = maxc;
    }

    public int getMaxh() {
        return this.maxh;
    }

    public void setMaxh(int maxh) {
        this.maxh = maxh;
    }

    public int getMaxn() {
        return this.maxn;
    }

    public void setMaxn(int maxn) {
        this.maxn = maxn;
    }

    public int getMaxr() {
        return this.maxr;
    }

    public void setMaxr(int maxr) {
        this.maxr = maxr;
    }

    public ArrayList<Equipment> getW() {
        return this.w;
    }

    public void setW(ArrayList<Equipment> w) {
        this.w = w;
    }

    public ArrayList<Equipment> getR() {
        return this.r;
    }

    public void setR(ArrayList<Equipment> r) {
        this.r = r;
    }

    public ArrayList<Equipment> getN() {
        return this.n;
    }

    public void setN(ArrayList<Equipment> n) {
        this.n = n;
    }

    public ArrayList<Equipment> getH() {
        return this.h;
    }

    public void setH(ArrayList<Equipment> h) {
        this.h = h;
    }

    public ArrayList<Equipment> getCh() {
        return this.ch;
    }

    public void setCh(ArrayList<Equipment> ch) {
        this.ch = ch;
    }

    public ArrayList<Equipment> getB() {
        return this.b;
    }

    public void setB(ArrayList<Equipment> b) {
        this.b = b;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public int getWcnt() {
        return this.wcnt;
    }

    public void setWcnt(int wcnt) {
        this.wcnt = wcnt;
    }

    public int getBcnt() {
        return this.bcnt;
    }

    public void setBcnt(int bcnt) {
        this.bcnt = bcnt;
    }

    public int getCnnt() {
        return this.cnnt;
    }

    public void setCnnt(int cnnt) {
        this.cnnt = cnnt;
    }

    public int getHcnt() {
        return this.hcnt;
    }

    public void setHcnt(int hcnt) {
        this.hcnt = hcnt;
    }

    public int getNcnt() {
        return this.ncnt;
    }

    public void setNcnt(int ncnt) {
        this.ncnt = ncnt;
    }

    public int getRcnt() {
        return this.rcnt;
    }

    public void setRcnt(int rcnt) {
        this.rcnt = rcnt;
    }

}