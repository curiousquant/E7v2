package E7v2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class gui extends JPanel implements ActionListener {
    protected JTextField f, weapon, head, chest, neck, ring, boot, customAtkTxt, customHpTxt, customCritTxt,
            customSpeedTxt, customDefTxt, customEffTxt, customCdTxt, heroAtkTxt, heroDefTxt, heroHpTxt, heroSpdTxt,
            heroCritTxt, heroCdTxt, heroEffTxt, heroEffResTxt;
    
    protected JTextArea a,batchTxt;
    protected JLabel customAtkLabel, customHpLabel, customCritLabel, customSpeedLabel, customDefLabel, customEffLabel,
            customCdLabel, customLabel, heroAtkLabel, heroDefLabel, heroHpLabel, heroSpdLabel, heroCritLabel,
            heroCdLabel, heroEffLabel, heroEffResLabel;
    JButton button;
    private JLabel lweapon, lhead, lchest, lneck, lring, lboot, total;
    private JLabel patk, pdef, php, pcrit, pcritdmg, speed, eff, effres, fatk,fdef,fhp,set, pk;
    JComboBox<String> heroCb;
    Bag b;
    Map<String, JTextField> items, sumItems;
    Map<String, Double> history;
    JButton setButton, spdButton, resetButton, wyvernButton, bellaButton, hpButton, critButton, xlsxButton,
            saveButton, loadButton, stopButton, batchButton,brusierButton,bellav2Button,lifeButton;
    protected ExportXLSX xlsx;
    int cnt,cnt2;
    Handler h;
    private static final int RECT_X = 500;
    private static final int RECT_Y = 400;
    private static final int RECT_WIDTH = 400;
    private static final int RECT_HEIGHT = 20;
    Rectangle border;
    Rectangle progress;
    Thread threadcustom,threadbella,threadspd,threadatk,threadhp,threadwyvern;
    int threadCnt=1;
    Map<Integer,FinalHero> fhero;
    boolean running;
    public gui() {
        super(new GridBagLayout());

        border = new Rectangle(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
        running = false;
        cnt = 0;
        cnt2 = 0;
        fhero = new HashMap<>();
        history = new HashMap<>();
        sumItems = new HashMap<>();
        h = new Handler(this, new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\bag.txt",
                "C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\herobag.txt", h));
        b = new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\bag.txt",
                "C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\herobag.txt", h);
        
        addLabels();
        addHeros();
        addTextStuff();
        addButtons();
    }
    public void calcHeroUI() {
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns, "patk", "pdef", "php", "speed", "pcrit", "pcritdmg", "eff", "effres", "fatk","fdef","fhp", "set");
        Hero selectedHero = b.getHeroBag().get(heroCb.getSelectedItem().toString());
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");

        int atkE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("atk")) ? 1 : 0;

        int critE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("crit")) ? 1 : 0;

        int desE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("des")) ? 1 : 0;

        int effE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("eff")) ? 1 : 0;

        int hpE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("hp")) ? 1 : 0;

        int defE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("def")) ? 1 : 0;

        int spdE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        
    //atk set
        if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {
            heroAtkTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(8)).getText())+
            selectedHero.getAtk() * (1 + (Double.parseDouble(sumItems.get(columns.get(0)).getText())+35) / 100))));
        }
        else{
            heroAtkTxt.setText(String.valueOf(Math.floor((Double.parseDouble(sumItems.get(columns.get(8)).getText())+
            selectedHero.getAtk() * (1 + Double.parseDouble(sumItems.get(columns.get(0)).getText()) / 100)))));
        }
    //def set
        if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 == 6) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+45) / 100))));
        }
        else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 >= 4) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+30) / 100))));
        }
        else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 >= 2) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+15) / 100))));
        }
        else{
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())) / 100))));
        }
    //hp set
        if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 6) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+45) / 100))));
        }
        else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 >= 4) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+30) / 100))));
        }
        else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 >= 2) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+15) / 100))));
        }
        else{
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
                selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())) / 100))));
        }

    //speed
        if (spdE1 + spdE2 + spdE3 + spdE4 + spdE5 + spdE6 >= 4) {
            heroSpdTxt.setText(String.valueOf(
                Math.floor((1.25*selectedHero.getSpd() + (Double.parseDouble(sumItems.get(columns.get(3)).getText()))))));
        }
        else{
            heroSpdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getSpd() + (Double.parseDouble(sumItems.get(columns.get(3)).getText())))));
        }

    //crit
        if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 == 6) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+36))));
        }
        else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 >= 4) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+24))));
        }
        else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 >= 2) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+12))));
        }
        else{
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())))));
        }
    //crit dmg
        if (desE1 + desE2 + desE3 + desE4 + desE5 + desE6 >= 4) {
            heroCdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCritdmg() + (Double.parseDouble(sumItems.get(columns.get(5)).getText())+35))));
        }
        else{
            heroCdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCritdmg() + (Double.parseDouble(sumItems.get(columns.get(5)).getText())))));
        }
    //eff
        if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 6) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+60))));
        }
        else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 >= 4) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+40))));
        }
        else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 >= 2) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+20))));
        }
        else{
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())))));
        }

        heroEffResTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEffres() + (Double.parseDouble(sumItems.get(columns.get(7)).getText())))));
        //System.out.println(selectedHero.getEff());
        
        // getFhero().put(getCnt2(),new FinalHero(selectedHero.getName(),Double.parseDouble(heroAtkTxt.getText()),
        //                         Double.parseDouble(heroDefTxt.getText()),
        //                         Double.parseDouble(heroHpTxt.getText()),Double.parseDouble(heroSpdTxt.getText()),
        //                         Double.parseDouble(heroCritTxt.getText()),Double.parseDouble(heroCdTxt.getText()),
        //                         Double.parseDouble(heroEffTxt.getText()),Double.parseDouble(heroEffResTxt.getText())));
        //cnt2++;
        //drawProgress(400);
    }

    public void calcHero(String bName) {
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns, "patk", "pdef", "php", "speed", "pcrit", "pcritdmg", "eff", "effres", "fatk","fdef","fhp", "set");
        Hero selectedHero = b.getHeroBag().get(heroCb.getSelectedItem().toString());
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");

        int atkE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("atk")) ? 1 : 0;
        int atkE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("atk")) ? 1 : 0;

        int critE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("crit")) ? 1 : 0;
        int critE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("crit")) ? 1 : 0;

        int desE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("des")) ? 1 : 0;
        int desE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("des")) ? 1 : 0;

        int effE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("eff")) ? 1 : 0;
        int effE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("eff")) ? 1 : 0;

        int hpE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("hp")) ? 1 : 0;
        int hpE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("hp")) ? 1 : 0;

        int defE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("def")) ? 1 : 0;
        int defE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("def")) ? 1 : 0;

        int spdE1 = (items.get(rows.get(0)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE2 = (items.get(rows.get(1)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE3 = (items.get(rows.get(2)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE4 = (items.get(rows.get(3)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE5 = (items.get(rows.get(4)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        int spdE6 = (items.get(rows.get(5)+columns.get(11)).getText().equals("spd")) ? 1 : 0;
        
    //atk set
        if (atkE1 + atkE2 + atkE3 + atkE4 + atkE5 + atkE6 >= 4) {
            heroAtkTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(8)).getText())+
            selectedHero.getAtk() * (1 + (Double.parseDouble(sumItems.get(columns.get(0)).getText())+35) / 100))));
        }
        else{
            heroAtkTxt.setText(String.valueOf(Math.floor((Double.parseDouble(sumItems.get(columns.get(8)).getText())+
            selectedHero.getAtk() * (1 + Double.parseDouble(sumItems.get(columns.get(0)).getText()) / 100)))));
        }
    //def set
        if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 == 6) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+45) / 100))));
        }
        else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 >= 4) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+30) / 100))));
        }
        else if (defE1 + defE2 + defE3 + defE4 + defE5 + defE6 >= 2) {
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())+15) / 100))));
        }
        else{
            heroDefTxt.setText(String.valueOf(Math.floor(Double.parseDouble(sumItems.get(columns.get(9)).getText())+
            selectedHero.getDef() * (1 + (Double.parseDouble(sumItems.get(columns.get(1)).getText())) / 100))));
        }
    //hp set
        if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 == 6) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+45) / 100))));
        }
        else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 >= 4) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+30) / 100))));
        }
        else if (hpE1 + hpE2 + hpE3 + hpE4 + hpE5 + hpE6 >= 2) {
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
            selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())+15) / 100))));
        }
        else{
            heroHpTxt.setText(String.valueOf(Math.floor( + Double.parseDouble(sumItems.get(columns.get(10)).getText())+
                selectedHero.getHp() * (1 + (Double.parseDouble(sumItems.get(columns.get(2)).getText())) / 100))));
        }

    //speed
        if (spdE1 + spdE2 + spdE3 + spdE4 + spdE5 + spdE6 >= 4) {
            heroSpdTxt.setText(String.valueOf(
                Math.floor((1.25*selectedHero.getSpd() + (Double.parseDouble(sumItems.get(columns.get(3)).getText()))))));
        }
        else{
            heroSpdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getSpd() + (Double.parseDouble(sumItems.get(columns.get(3)).getText())))));
        }

    //crit
        if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 == 6) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+36))));
        }
        else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 >= 4) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+24))));
        }
        else if (critE1 + critE2 + critE3 + critE4 + critE5 + critE6 >= 2) {
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())+12))));
        }
        else{
            heroCritTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCrit() + (Double.parseDouble(sumItems.get(columns.get(4)).getText())))));
        }
    //crit dmg
        if (desE1 + desE2 + desE3 + desE4 + desE5 + desE6 >= 4) {
            heroCdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCritdmg() + (Double.parseDouble(sumItems.get(columns.get(5)).getText())+35))));
        }
        else{
            heroCdTxt.setText(String.valueOf(
                Math.floor(selectedHero.getCritdmg() + (Double.parseDouble(sumItems.get(columns.get(5)).getText())))));
        }
    //eff
        if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 == 6) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+60))));
        }
        else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 >= 4) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+40))));
        }
        else if (effE1 + effE2 + effE3 + effE4 + effE5 + effE6 >= 2) {
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())+20))));
        }
        else{
            heroEffTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEff() + (Double.parseDouble(sumItems.get(columns.get(6)).getText())))));
        }

        heroEffResTxt.setText(String.valueOf(
                Math.floor(selectedHero.getEffres() + (Double.parseDouble(sumItems.get(columns.get(7)).getText())))));
        //System.out.println(selectedHero.getEff());
        
        getFhero().put(getCnt2(),new FinalHero(selectedHero.getName(),Double.parseDouble(heroAtkTxt.getText()),
                                Double.parseDouble(heroDefTxt.getText()),
                                Double.parseDouble(heroHpTxt.getText()),Double.parseDouble(heroSpdTxt.getText()),
                                Double.parseDouble(heroCritTxt.getText()),Double.parseDouble(heroCdTxt.getText()),
                                Double.parseDouble(heroEffTxt.getText()),Double.parseDouble(heroEffResTxt.getText()),
                                bName));
        cnt2++;
        drawProgress(400);
        running=false;
    }

    public void saveStuff() {
        // customAtkTxt, customHpTxt, customCritTxt,customSpeedTxt, customDefTxt
        try {
            List<String> lines = new ArrayList<>();
            lines.add(customAtkTxt.getText());
            lines.add(customHpTxt.getText());
            lines.add(customCritTxt.getText());
            lines.add(customSpeedTxt.getText());
            lines.add(customDefTxt.getText());
            lines.add(customEffTxt.getText());
            Path file = Paths.get("save1.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
            lines.clear();
        } catch (Exception e) {

        }
    }

    protected void paintComponent(Graphics g, double p) {
        Graphics2D g2 = (Graphics2D) g;

        // super.paintComponent(g); //lose all stuff
        // revalidate();

        progress = new Rectangle(RECT_X, RECT_Y, (int) (p), RECT_HEIGHT);
        g2.draw(border);

        g2.setColor(Color.BLUE);
        g2.draw(progress);
        g2.fill(progress);

        // g2.fillRect(RECT_X, RECT_Y, (int)(p), RECT_HEIGHT);
        // System.out.println((int)(p));
        
        if (p >= 400) {
            revalidate();
            repaint();
        }
    }

    public void drawProgress(double p) {
        // getProgressBar().setText(Double.toString(p));
        paintComponent(super.getGraphics(), p);

        // getProgressBar().setText(p);
        // System.out.println(p);
    }

    public void loadBatch(){
        String file1 = batchTxt.getText();//Utils.loadFileAsString("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\batchRun.txt");
        String[] token1 = file1.split("[,|\n]");//(\w*)[^\"]");
        for(int i=0; i<token1.length/2;i++){
            System.out.println(token1[2*i]);
            System.out.println(token1[2*i+1]);
            
            String heroName = token1[2*i];
            String buttonName = token1[2*i+1];
            heroCb.setSelectedItem(heroName);

            Component[] components = this.getComponents();
            
            for (Component component : components) {
                
                if (component instanceof JButton) {
                    JButton button1 = (JButton) component;
                    
                    //try{
                        if (button1.getActionCommand().equals(buttonName)){
                            System.out.println("running button:"+button1.getActionCommand()+" for "+heroName);
                            button1.doClick();
                            //button1.setEnabled(false);
                            while(running){
                                
                            }
                        }
                        repaint();
                        revalidate();
    
                    //}
                    //catch(Exception e){
                    //    e.printStackTrace();
                    //}
                }
            
            
            // if(buttonName.equals("Hp Calcs")){
            //     hpButton.doClick();
            //     while(running){
            //     }
            // }
            // else if(buttonName.equals("Atk_Calcs")){
            //     setButton.doClick();
            //     while(running){
            //     }
            // }
            // else if(buttonName.equals("Export_XLSX")){
            //     xlsxButton.doClick();
            //     while(running){                    
            //     }
            }

        }
    }

    public void loadStuff() {

        // Path file = Paths.get("save1.txt");
        String file = Utils.loadFileAsString("save1.txt");
        String file1 = Utils.loadFileAsString("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\batchRun.txt");
        batchTxt.setText(file1);
        
        String[] token = file.split("\\s+");

        customAtkTxt.setText(token[0]);
        customHpTxt.setText(token[1]);
        customCritTxt.setText(token[2]);
        customSpeedTxt.setText(token[3]);
        customDefTxt.setText(token[4]);
        customEffTxt.setText(token[5]);
    }

    public void addTextStuff() {
        GridBagConstraints c = new GridBagConstraints();
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                "fdef", "fhp", "set", "pk");
       
        items = new HashMap<>();
        // int cnt1 =0;
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                items.put(rows.get(i) + columns.get(j), new JTextField());
                c.gridx = j + 1;
                c.gridy = i + 1;
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 0.5;
                items.get(rows.get(i) + columns.get(j)).addActionListener(this);
                add(items.get(rows.get(i) + columns.get(j)), c);
                // items.get(rows.get(i)+columns.get(j)).setText(Integer.toString(cnt1));
                // cnt1++;
            }
        }
    }

    public void sumCols() {
        GridBagConstraints c = new GridBagConstraints();
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                "fdef", "fhp", "set");

        double[] sum = new double[columns.size()];

        for (int j = 0; j < columns.size() - 1; j++) {
            sum[j] = 0;
            for (int i = 0; i < rows.size(); i++) {
                //System.out.println(rows.get(i) + columns.get(j));
                sum[j] += Double.parseDouble(items.get(rows.get(i) + columns.get(j)).getText());
            }
            // System.out.println(sum[j]);
            if (!sumItems.containsKey(columns.get(j))) {
                sumItems.put(columns.get(j), new JTextField());
                c.gridx = j + 1;
                c.gridy = rows.size() + 1;
                c.fill = GridBagConstraints.BOTH;
                add(sumItems.get(columns.get(j)), c);
            }
            history.put(columns.get(j) + cnt, sum[j]);
            // System.out.println(history.get(columns.get(j)+cnt));

            sumItems.get(columns.get(j)).setText(String.valueOf(sum[j]));

        }
        System.out.println("!");

        cnt++;
    }

    public void actionPerformed(ActionEvent evt) {
        running=true;
        String bName = evt.getActionCommand();
        if ("Atk Calcs".equals(evt.getActionCommand())) {
//thread            threadatk = new Thread() {
//thread                public void run() {
                    //Sets s = b.superCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB());
                    Sets s = b.superMaxAtkCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                                    Double.parseDouble(customAtkTxt.getText()), Double.parseDouble(customHpTxt.getText()),
                                    Double.parseDouble(customCritTxt.getText()), Double.parseDouble(customSpeedTxt.getText()),
                                    Double.parseDouble(customDefTxt.getText()), Double.parseDouble(customEffTxt.getText()),
                                    Double.parseDouble(customCdTxt.getText()),
                                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));

                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;

                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                                // System.out.println(e.pk+"!");
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }

                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                // values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                // values = Integer.toString(e.getPk());
                            } else if (j == 10) {
                                values = Integer.toString(e.getF_hp());
                            } else if (j == 11) {
                                values = e.getSet();
                            } else if (j == 12) {
                                values = Integer.toString(e.getPk());
                            }
                            // System.out.println(rows.get(i)+columns.get(j));
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    threadCnt++;
 //thread               }
//thread            };
 //thread           threadatk.start();
        } else if ("Run Speed Calcs".equals(evt.getActionCommand())) {
  //thread          threadspd = new Thread() {
  //thread              public void run() {
                    //Sets s = b.superSpeedCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB());
                    Sets s = b.superSpeedCalcsv1(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                    Double.parseDouble(customAtkTxt.getText()), Double.parseDouble(customHpTxt.getText()),
                    Double.parseDouble(customCritTxt.getText()), Double.parseDouble(customSpeedTxt.getText()),
                    Double.parseDouble(customDefTxt.getText()), Double.parseDouble(customEffTxt.getText()),
                    Double.parseDouble(customCdTxt.getText()),
                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));
                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;

                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }

                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                // values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                // values = Integer.toString(e.getPk());
                            } else if (j == 10) {
                                values = Integer.toString(e.getF_hp());
                            } else if (j == 11) {
                                values = e.getSet();
                            } else if (j == 12) {
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    threadCnt++;
    //thread            }
    //thread        };
    //thread        threadspd.start();
        } else if ("Wyvern 13".equals(evt.getActionCommand())) {
      //thread      threadwyvern = new Thread() {
       //thread         public void run() {
                    Sets s = b.superCalcsHunt(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB());
                    
                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;

                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }

                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                // values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                // values = Integer.toString(e.getPk());
                            } else if (j == 10) {
                                values = Integer.toString(e.getF_hp());
                            } else if (j == 11) {
                                values = e.getSet();
                            } else if (j == 12) {
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    threadCnt++;
  //thread              }
  //thread          };
   //thread         threadwyvern.start();
        } else if ("Custom Calcs".equals(evt.getActionCommand())) {
     //thread       threadcustom = new Thread() {
     //thread           public void run() {
                    Sets s = b.superCustomCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                            Double.parseDouble(customAtkTxt.getText()), Double.parseDouble(customHpTxt.getText()),
                            Double.parseDouble(customCritTxt.getText()), Double.parseDouble(customSpeedTxt.getText()),
                            Double.parseDouble(customDefTxt.getText()), Double.parseDouble(customEffTxt.getText()),
                            Double.parseDouble(customCdTxt.getText()),
                            b.getHeroBag().get(heroCb.getSelectedItem().toString()));

                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres",
                            "fatk", "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;

                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }

                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                // values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                // values = Integer.toString(e.getPk());
                            } else if (j == 10) {
                                values = Integer.toString(e.getF_hp());
                            } else if (j == 11) {
                                values = e.getSet();
                            } else if (j == 12) {
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    threadCnt++;
   //thread             }
  //thread         };
            
  //thread          threadcustom.start();
            

  
        } 
     else if ("Brusier Dmg".equals(evt.getActionCommand())) {
        //thread       threadcustom = new Thread() {
        //thread           public void run() {
                       Sets s = b.superBrusierCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                               Double.parseDouble(customAtkTxt.getText()), Double.parseDouble(customHpTxt.getText()),
                               Double.parseDouble(customCritTxt.getText()), Double.parseDouble(customSpeedTxt.getText()),
                               Double.parseDouble(customDefTxt.getText()), Double.parseDouble(customEffTxt.getText()),
                               Double.parseDouble(customCdTxt.getText()),
                               b.getHeroBag().get(heroCb.getSelectedItem().toString()));
   
                       List<String> rows = new ArrayList<String>();
                       Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                       List<String> columns = new ArrayList<String>();
                       Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres",
                               "fatk", "fdef", "fhp", "set", "pk");
                       Equipment e;
                       String values;
                       for (int i = 0; i < rows.size(); i++) {
                           for (int j = 0; j < columns.size(); j++) {
                               values = "";
                               e = null;
   
                               if (i == 0) {
                                   e = s.getWeapon();
                               } else if (i == 1) {
                                   e = s.getHead();
                               } else if (i == 2) {
                                   e = s.getChest();
                               } else if (i == 3) {
                                   e = s.getNeck();
                               } else if (i == 4) {
                                   e = s.getRing();
                               } else if (i == 5) {
                                   e = s.getBoot();
                               }
   
                               if (j == 0) {
                                   values = Integer.toString(e.getP_atk());
                               } else if (j == 1) {
                                   values = Integer.toString(e.getP_def());
                               } else if (j == 2) {
                                   values = Integer.toString(e.getP_hp());
                               } else if (j == 3) {
                                   values = Integer.toString(e.getC());
                               } else if (j == 4) {
                                   values = Integer.toString(e.getCd());
                               } else if (j == 5) {
                                   values = Integer.toString(e.getSpd());
                               } else if (j == 6) {
                                   values = Integer.toString(e.getEff());
                               } else if (j == 7) {
                                   values = Integer.toString(e.getEffres());
                               } else if (j == 8) {
                                   // values = e.getSet();
                                   values = Integer.toString(e.getF_atk());
                               } else if (j == 9) {
                                   values = Integer.toString(e.getF_def());
                                   // values = Integer.toString(e.getPk());
                               } else if (j == 10) {
                                   values = Integer.toString(e.getF_hp());
                               } else if (j == 11) {
                                   values = e.getSet();
                               } else if (j == 12) {
                                   values = Integer.toString(e.getPk());
                               }
                               items.get(rows.get(i) + columns.get(j)).setText(values);
                           }
                       }
                       sumCols();
                       revalidate();
                       calcHero(bName);
                       threadCnt++;
      //thread             }
     //thread         };
               
     //thread          threadcustom.start();
               
           } 
        
           else if ("Bella Dmg".equals(evt.getActionCommand())) {
            //thread       threadcustom = new Thread() {
            //thread           public void run() {
                           Sets s = b.superBellaCalcsv2(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));
       
                           List<String> rows = new ArrayList<String>();
                           Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                           List<String> columns = new ArrayList<String>();
                           Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres",
                                   "fatk", "fdef", "fhp", "set", "pk");
                           Equipment e;
                           String values;
                           for (int i = 0; i < rows.size(); i++) {
                               for (int j = 0; j < columns.size(); j++) {
                                   values = "";
                                   e = null;
       
                                   if (i == 0) {
                                       e = s.getWeapon();
                                   } else if (i == 1) {
                                       e = s.getHead();
                                   } else if (i == 2) {
                                       e = s.getChest();
                                   } else if (i == 3) {
                                       e = s.getNeck();
                                   } else if (i == 4) {
                                       e = s.getRing();
                                   } else if (i == 5) {
                                       e = s.getBoot();
                                   }
       
                                   if (j == 0) {
                                       values = Integer.toString(e.getP_atk());
                                   } else if (j == 1) {
                                       values = Integer.toString(e.getP_def());
                                   } else if (j == 2) {
                                       values = Integer.toString(e.getP_hp());
                                   } else if (j == 3) {
                                       values = Integer.toString(e.getC());
                                   } else if (j == 4) {
                                       values = Integer.toString(e.getCd());
                                   } else if (j == 5) {
                                       values = Integer.toString(e.getSpd());
                                   } else if (j == 6) {
                                       values = Integer.toString(e.getEff());
                                   } else if (j == 7) {
                                       values = Integer.toString(e.getEffres());
                                   } else if (j == 8) {
                                       // values = e.getSet();
                                       values = Integer.toString(e.getF_atk());
                                   } else if (j == 9) {
                                       values = Integer.toString(e.getF_def());
                                       // values = Integer.toString(e.getPk());
                                   } else if (j == 10) {
                                       values = Integer.toString(e.getF_hp());
                                   } else if (j == 11) {
                                       values = e.getSet();
                                   } else if (j == 12) {
                                       values = Integer.toString(e.getPk());
                                   }
                                   items.get(rows.get(i) + columns.get(j)).setText(values);
                               }
                           }
                           sumCols();
                           revalidate();
                           calcHero(bName);
                           threadCnt++;
          //thread             }
         //thread         };
                   
         //thread          threadcustom.start();
                   
               } 
               else if ("Lifesteal".equals(evt.getActionCommand())) {
                //thread       threadcustom = new Thread() {
                //thread           public void run() {
                               Sets s = b.superLifeBrusierCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                                        b.getHeroBag().get(heroCb.getSelectedItem().toString()));
           
                               List<String> rows = new ArrayList<String>();
                               Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                               List<String> columns = new ArrayList<String>();
                               Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres",
                                       "fatk", "fdef", "fhp", "set", "pk");
                               Equipment e;
                               String values;
                               for (int i = 0; i < rows.size(); i++) {
                                   for (int j = 0; j < columns.size(); j++) {
                                       values = "";
                                       e = null;
           
                                       if (i == 0) {
                                           e = s.getWeapon();
                                       } else if (i == 1) {
                                           e = s.getHead();
                                       } else if (i == 2) {
                                           e = s.getChest();
                                       } else if (i == 3) {
                                           e = s.getNeck();
                                       } else if (i == 4) {
                                           e = s.getRing();
                                       } else if (i == 5) {
                                           e = s.getBoot();
                                       }
           
                                       if (j == 0) {
                                           values = Integer.toString(e.getP_atk());
                                       } else if (j == 1) {
                                           values = Integer.toString(e.getP_def());
                                       } else if (j == 2) {
                                           values = Integer.toString(e.getP_hp());
                                       } else if (j == 3) {
                                           values = Integer.toString(e.getC());
                                       } else if (j == 4) {
                                           values = Integer.toString(e.getCd());
                                       } else if (j == 5) {
                                           values = Integer.toString(e.getSpd());
                                       } else if (j == 6) {
                                           values = Integer.toString(e.getEff());
                                       } else if (j == 7) {
                                           values = Integer.toString(e.getEffres());
                                       } else if (j == 8) {
                                           // values = e.getSet();
                                           values = Integer.toString(e.getF_atk());
                                       } else if (j == 9) {
                                           values = Integer.toString(e.getF_def());
                                           // values = Integer.toString(e.getPk());
                                       } else if (j == 10) {
                                           values = Integer.toString(e.getF_hp());
                                       } else if (j == 11) {
                                           values = e.getSet();
                                       } else if (j == 12) {
                                           values = Integer.toString(e.getPk());
                                       }
                                       items.get(rows.get(i) + columns.get(j)).setText(values);
                                   }
                               }
                               sumCols();
                               revalidate();
                               calcHero(bName);
                               threadCnt++;
              //thread             }
             //thread         };
                       
             //thread          threadcustom.start();
                       
                   } 
        else if ("Max Dmg".equals(evt.getActionCommand())) {
   //thread         threadbella = new Thread() {
   //thread     public void run() {
                    Sets s = b.superBellaCalcsv1(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));
                    //double cv =b.getHeroBag().get(heroCb.getSelectedItem().toString()).getCv();
                    
                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;
        
                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }
        
                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                //values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                //values = Integer.toString(e.getPk());
                            }
                            else if (j==10){
                                values = Integer.toString(e.getF_hp());
                            }
                            else if (j==11){
                                values = e.getSet();
                            }
                            else if (j==12){
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    //cvTxt.setText(String.valueOf(s.getCV()));
                    threadCnt++;
                }
    //thread        };
   //thread         threadbella.start();
   //thread     } 


   
         else if ("Max Dmg".equals(evt.getActionCommand())) {
   //thread         threadbella = new Thread() {
   //thread     public void run() {
                    Sets s = b.superBellaCalcsv1(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));
                    //double cv =b.getHeroBag().get(heroCb.getSelectedItem().toString()).getCv();
                    
                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;
        
                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }
        
                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                //values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                //values = Integer.toString(e.getPk());
                            }
                            else if (j==10){
                                values = Integer.toString(e.getF_hp());
                            }
                            else if (j==11){
                                values = e.getSet();
                            }
                            else if (j==12){
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    //cvTxt.setText(String.valueOf(s.getCV()));
                    threadCnt++;
                }
    //thread        };
   //thread         threadbella.start();
   //thread     } 
        else if ("Hp Calcs".equals(evt.getActionCommand())) {
 //thread           threadhp = new Thread() {
  //thread              public void run() {
                    //Sets s = b.superHpCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB());
                    Sets s = b.superHpCalcsv1(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                                    Double.parseDouble(customAtkTxt.getText()), Double.parseDouble(customHpTxt.getText()),
                                    Double.parseDouble(customCritTxt.getText()), Double.parseDouble(customSpeedTxt.getText()),
                                    Double.parseDouble(customDefTxt.getText()), Double.parseDouble(customEffTxt.getText()),
                                    Double.parseDouble(customCdTxt.getText()),
                                    b.getHeroBag().get(heroCb.getSelectedItem().toString()));
                    List<String> rows = new ArrayList<String>();
                    Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                    List<String> columns = new ArrayList<String>();
                    Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                            "fdef", "fhp", "set", "pk");
                    Equipment e;
                    String values;
                    for (int i = 0; i < rows.size(); i++) {
                        for (int j = 0; j < columns.size(); j++) {
                            values = "";
                            e = null;

                            if (i == 0) {
                                e = s.getWeapon();
                            } else if (i == 1) {
                                e = s.getHead();
                            } else if (i == 2) {
                                e = s.getChest();
                            } else if (i == 3) {
                                e = s.getNeck();
                            } else if (i == 4) {
                                e = s.getRing();
                            } else if (i == 5) {
                                e = s.getBoot();
                            }

                            if (j == 0) {
                                values = Integer.toString(e.getP_atk());
                            } else if (j == 1) {
                                values = Integer.toString(e.getP_def());
                            } else if (j == 2) {
                                values = Integer.toString(e.getP_hp());
                            } else if (j == 3) {
                                values = Integer.toString(e.getC());
                            } else if (j == 4) {
                                values = Integer.toString(e.getCd());
                            } else if (j == 5) {
                                values = Integer.toString(e.getSpd());
                            } else if (j == 6) {
                                values = Integer.toString(e.getEff());
                            } else if (j == 7) {
                                values = Integer.toString(e.getEffres());
                            } else if (j == 8) {
                                //values = e.getSet();
                                values = Integer.toString(e.getF_atk());
                            } else if (j == 9) {
                                values = Integer.toString(e.getF_def());
                                //values = Integer.toString(e.getPk());
                            }
                            else if (j==10){
                                values = Integer.toString(e.getF_hp());
                            }
                            else if (j==11){
                                values = e.getSet();
                            }
                            else if (j==12){
                                values = Integer.toString(e.getPk());
                            }
                            items.get(rows.get(i) + columns.get(j)).setText(values);
                        }
                    }
                    sumCols();
                    revalidate();
                    calcHero(bName);
                    threadCnt++;
    //thread            }
    //thread        };
    //thread        threadhp.start();
        } 
        else if ("Reset".equals(evt.getActionCommand())) {
            b = new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\bag.txt",
                    "C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\herobag.txt", getHandler());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                    "fdef", "fhp", "set", "pk");
            Equipment e;
            String values;
            for (int j = 0; j < columns.size(); j++) {
                for (int i = 0; i < rows.size(); i++) {
                    items.get(rows.get(i) + columns.get(j)).setText("");
                }
            }
            if(getCnt2()>0){
                for (int j = 0; j < columns.size() - 2; j++) {
                    sumItems.get(columns.get(j)).setText("");
                }
            }
            heroAtkTxt.setText("");
            heroDefTxt.setText(""); 
            heroHpTxt.setText(""); 
            heroSpdTxt.setText("");
            heroCritTxt.setText(""); 
            heroCdTxt.setText(""); 
            heroEffTxt.setText(""); 
            heroEffResTxt.setText("");
            //cvTxt.setText("");

            setCnt(0);
            setCnt2(0);
            history.clear();
            getFhero().clear();
        }

        else if ("Export XLSX".equals(evt.getActionCommand())) {
            xlsx = new ExportXLSX(getBag().getSets(), getHistory(),getFhero());
            xlsx.loadData();
        } else if ("Save".equals(evt.getActionCommand())) {
            saveStuff();
        } else if ("Load".equals(evt.getActionCommand())) {
            loadStuff();
        }
        else if ("Stop Execution".equals(evt.getActionCommand())) {
            try{
                System.out.println(threadCnt);
                if(getThread("Thread-"+threadCnt)!=null){
                    getThread("Thread-"+threadCnt++).stop();
                    //thread1.interrupt();
                    System.out.println("Thread Stopped"+threadCnt);
                }
                
                repaint();

                b = new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\bag.txt",
                    "C:\\Users\\jonmu\\Documents\\GitHub\\E7v3\\E7v2\\res\\herobag.txt", getHandler());
                List<String> rows = new ArrayList<String>();
                Collections.addAll(rows, "weapon", "head", "chest", "neck", "ring", "boot");
                List<String> columns = new ArrayList<String>();
                Collections.addAll(columns, "patk", "pdef", "php", "pcrit", "pcritdmg", "speed", "eff", "effres", "fatk",
                        "fdef", "fhp", "set", "pk");
                Equipment e;
                String values;
                for (int j = 0; j < columns.size(); j++) {
                    for (int i = 0; i < rows.size(); i++) {
                        items.get(rows.get(i) + columns.get(j)).setText("");
                    }
                }
                if(sumItems.get(columns.get(0))!=null){
                    for (int j = 0; j < columns.size() - 2; j++) {
                        sumItems.get(columns.get(j)).setText("");
                    }
                }
                
                heroAtkTxt.setText("");
                heroDefTxt.setText(""); 
                heroHpTxt.setText(""); 
                heroSpdTxt.setText("");
                heroCritTxt.setText(""); 
                heroCdTxt.setText(""); 
                heroEffTxt.setText(""); 
                heroEffResTxt.setText("");
                //cvTxt.setText("");

                setCnt(0);
                setCnt2(0);
                history.clear();
                getFhero().clear();
            }

            catch(Exception e){
                e.printStackTrace();
            }
        }   
        else if ("Dropdown".equals(evt.getActionCommand())) {
            if(getCnt2()>0){
                //getFhero().remove(getCnt2());
                //cnt2--;
                calcHeroUI();
            }

        }
        else if ("Batch Execution".equals(evt.getActionCommand())) {
            loadBatch();
        }
        
        running=false;
    }
    Thread getThread(String t) {
        ThreadGroup threadGroup = Thread.currentThread( ).getThreadGroup( );
        Thread[] threads = new Thread[ threadGroup.activeCount() ];
        threadGroup.enumerate(threads);
        for (int nIndex=0; nIndex<threads.length; nIndex++) {
           if(threads[nIndex] != null && threads[nIndex].getName().equals(t)) {
              return threads[nIndex];
           }
        }
        return null;
     }

    public void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("E7 Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add contents to the window.
        frame.add(this);

        // Display the window.
        frame.setSize(1200, 500);
        // frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void addButtons(){
        GridBagConstraints c = new GridBagConstraints();
        setButton = new JButton("Atk Calcs");
        setButton.setActionCommand("Atk Calcs");
        setButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6 + 2;
        add(setButton, c);
        spdButton = new JButton("Run Speed Calcs");
        spdButton.setActionCommand("Run Speed Calcs");
        spdButton.addActionListener(this);
        c.gridx = 1;
        c.gridy = 6 + 2;
        add(spdButton, c);

        wyvernButton = new JButton("Wyvern 13");
        wyvernButton.setActionCommand("Wyvern 13");
        wyvernButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 6 + 2;
        add(wyvernButton, c);

        critButton = new JButton("Custom Calcs");
        critButton.setActionCommand("Custom Calcs");
        critButton.addActionListener(this);
        c.gridx = 3;
        c.gridy = 6 + 2;
        add(critButton, c);

        customLabel = new JLabel("Custom Settings:");
        c.gridx = 5;
        c.gridy = 6 + 2;
        add(customLabel, c);

        bellaButton = new JButton("Max Dmg");
        bellaButton.setActionCommand("Max Dmg");
        bellaButton.addActionListener(this);
        c.gridx = 1;
        c.gridy = 6 + 3;
        add(bellaButton, c);

        brusierButton = new JButton("Brusier Dmg");
        brusierButton.setActionCommand("Brusier Dmg");
        brusierButton.addActionListener(this);
        c.gridx = 1;
        c.gridy = 6 + 4;
        add(brusierButton, c);

        bellav2Button = new JButton("Bella Dmg");
        bellav2Button.setActionCommand("Bella Dmg");
        bellav2Button.addActionListener(this);
        c.gridx = 1;
        c.gridy = 6 + 5;
        add(bellav2Button, c);

        lifeButton = new JButton("Lifesteal");
        lifeButton.setActionCommand("Lifesteal");
        lifeButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6 + 4;
        add(lifeButton, c);

        hpButton = new JButton("Hp Calcs");
        hpButton.setActionCommand("Hp Calcs");
        hpButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 6 + 3;
        add(hpButton, c);

        resetButton = new JButton("Reset");
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 6 + 3;
        add(resetButton, c);

        xlsxButton = new JButton("Export XLSX");
        xlsxButton.setActionCommand("Export XLSX");
        xlsxButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 6 + 4;
        add(xlsxButton, c);

        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        c.gridx = 3;
        c.gridy = 6 + 3;
        add(saveButton, c);

        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        c.gridx = 3;
        c.gridy = 6 + 4;
        add(loadButton, c);

        stopButton = new JButton("Stop Execution");
        stopButton.setActionCommand("Stop Execution");
        stopButton.addActionListener(this);
        c.gridx = 3;
        c.gridy = 6 + 5;
        add(stopButton, c);

        batchButton = new JButton("Batch Execution");
        batchButton.setActionCommand("Batch Execution");
        batchButton.addActionListener(this);
        c.gridx =2;
        c.gridy = 6 + 5;
        add(batchButton, c);
        
        // customAtkLabel, customHpLabel, customCritLabel,customSpeedLabel,
        // customDefLabel;
        customAtkLabel = new JLabel("atk:");
        c.gridx = 4;
        c.gridy = 6 + 3;
        add(customAtkLabel, c);
        customAtkTxt = new JTextField("0");
        customAtkTxt.setMinimumSize(new Dimension(80, 20 ));
        customAtkTxt.setPreferredSize(new Dimension(80, 20 ));
        c.gridx = 5;
        c.gridy = 6 + 3;
        add(customAtkTxt, c);

        customHpLabel = new JLabel("hp:");
        c.gridx = 4;
        c.gridy = 6 + 4;
        add(customHpLabel, c);
        customHpTxt = new JTextField("0");
        customHpTxt.setMinimumSize(new Dimension(80, 20 ));
        customHpTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 5;
        c.gridy = 6 + 4;
        add(customHpTxt, c);

        customCritLabel = new JLabel("crit chance:");
        c.gridx = 6;
        c.gridy = 6 + 3;
        add(customCritLabel, c);
        customCritTxt = new JTextField("0");
        customCritTxt.setMinimumSize(new Dimension(80, 20 ));
        customCritTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 7;
        c.gridy = 6 + 3;
        add(customCritTxt, c);

        customSpeedLabel = new JLabel("speed:");
        c.gridx = 6;
        c.gridy = 6 + 4;
        add(customSpeedLabel, c);
        customSpeedTxt = new JTextField("0");
        customSpeedTxt.setMinimumSize(new Dimension(80, 20 ));
        customSpeedTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 7;
        c.gridy = 6 + 4;
        add(customSpeedTxt, c);

        customDefLabel = new JLabel("def:");
        c.gridx = 8;
        c.gridy = 6 + 3;
        add(customDefLabel, c);
        customDefTxt = new JTextField("0");
        customDefTxt.setMinimumSize(new Dimension(80, 20 ));
        customDefTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 9;
        c.gridy = 6 + 3;
        add(customDefTxt, c);

        customEffLabel = new JLabel("eff:");
        c.gridx = 10;
        c.gridy = 6 + 3;
        add(customEffLabel, c);
        customEffTxt = new JTextField("0");
        customEffTxt.setMinimumSize(new Dimension(80, 20 ));
        customEffTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 11;
        c.gridy = 6 + 3;
        add(customEffTxt, c);

        customCdLabel = new JLabel("crit dmg:");
        c.gridx = 8;
        c.gridy = 6 + 4;
        add(customCdLabel, c);
        customCdTxt = new JTextField("0");
        customCdTxt.setMinimumSize(new Dimension(80, 20 ));
        customCdTxt.setPreferredSize(new Dimension(80, 20 ));

        c.gridx = 9;
        c.gridy = 6 + 4;
        add(customCdTxt, c);
    }
    public void addLabels() {
        // add components to panel
        GridBagConstraints c = new GridBagConstraints();

        patk = new JLabel();
        patk.setText("% atk");
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(patk, c);

        pdef = new JLabel();
        pdef.setText("% def");
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pdef, c);
        php = new JLabel();
        php.setText("% hp");
        c.gridx = 3;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(php, c);
        pcrit = new JLabel();
        pcrit.setText("% crit");
        c.gridx = 4;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pcrit, c);
        pcritdmg = new JLabel();
        pcritdmg.setText("% crit dmg");
        c.gridx = 5;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pcritdmg, c);
        speed = new JLabel();
        speed.setText("speed");
        c.gridx = 6;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(speed, c);
        eff = new JLabel();
        eff.setText("eff");
        c.gridx = 7;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(eff, c);
        effres = new JLabel();
        effres.setText("eff res");
        c.gridx = 8;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(effres, c);
        fatk = new JLabel();
        fatk.setText("flat atk");
        c.gridx = 9;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(fatk, c);

        fdef = new JLabel();
        fdef.setText("flat def");
        c.gridx = 10;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(fdef, c);
        fhp = new JLabel();
        fhp.setText("flat hp");
        c.gridx = 11;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(fhp, c);
        set = new JLabel();
        set.setText("set");
        c.gridx = 12;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(set, c);
        pk = new JLabel();
        pk.setText("pk");
        c.gridx = 13;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pk, c);

        lweapon = new JLabel();
        lweapon.setText("weapon:");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lweapon, c);

        lhead = new JLabel();
        lhead.setText("head:");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lhead, c);

        lchest = new JLabel();
        lchest.setText("chest:");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lchest, c);

        lneck = new JLabel();
        lneck.setText("neck:");
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lneck, c);

        lring = new JLabel();
        lring.setText("ring:");
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lring, c);
        lboot = new JLabel();
        lboot.setText("boot:");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lboot, c);
        total = new JLabel();
        total.setText("total:");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(total, c);

    }

    public void addHeros(){
        GridBagConstraints c = new GridBagConstraints();
        String[] heros = new String[b.getHeroBag().size()];
        Iterator hmIterator = b.getHeroBag().entrySet().iterator();
        int tmpcnt = 0;
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            Hero h = (Hero) mapElement.getValue();

            System.out.println(mapElement.getKey() + " : " + h.getName());
            heros[tmpcnt] = (String) mapElement.getKey();
            tmpcnt++;
        }

        heroCb = new JComboBox<String>(heros);
        heroCb.setMinimumSize(heroCb.getPreferredSize());
        c.gridx = 0;
        c.gridy = 6 + 5;
        add(heroCb, c);
        heroCb.setActionCommand("Dropdown");
        heroCb.addActionListener(this);
        

        // Hero Stats
        heroAtkLabel = new JLabel("atk:");
        c.gridx = 0;
        c.gridy = 6 + 6;
        add(heroAtkLabel, c);
        heroAtkTxt = new JTextField("0");
        heroAtkTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroAtkTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 1;
        c.gridy = 6 + 6;
        add(heroAtkTxt, c);
        heroDefLabel = new JLabel("def:");
        c.gridx = 0;
        c.gridy = 6 + 7;
        add(heroDefLabel, c);
        heroDefTxt = new JTextField("0");
        heroDefTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroDefTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 1;
        c.gridy = 6 + 7;
        add(heroDefTxt, c);
        heroHpLabel = new JLabel("hp:");
        c.gridx = 0;
        c.gridy = 6 + 8;
        add(heroHpLabel, c);
        heroHpTxt = new JTextField("0");
        heroHpTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroHpTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 1;
        c.gridy = 6 + 8;
        add(heroHpTxt, c);
        heroSpdLabel = new JLabel("speed:");
        c.gridx = 0;
        c.gridy = 6 + 9;
        add(heroSpdLabel, c);
        heroSpdTxt = new JTextField("0");
        heroSpdTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroSpdTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 1;
        c.gridy = 6 + 9;
        add(heroSpdTxt, c);

        heroCritLabel = new JLabel("crit:");
        c.gridx = 2;
        c.gridy = 6 + 6;
        add(heroCritLabel, c);
        heroCritTxt = new JTextField("0");
        heroCritTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroCritTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 3;
        c.gridy = 6 + 6;
        add(heroCritTxt, c);
        heroCdLabel = new JLabel("crit dmg:");
        c.gridx = 2;
        c.gridy = 6 + 7;
        add(heroCdLabel, c);
        heroCdTxt = new JTextField("0");
        heroCdTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroCdTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 3;
        c.gridy = 6 + 7;
        add(heroCdTxt, c);
        heroEffLabel = new JLabel("eff:");
        c.gridx = 2;
        c.gridy = 6 + 8;
        add(heroEffLabel, c);
        heroEffTxt = new JTextField("0");
        heroEffTxt.setMinimumSize(new Dimension(80, 20 ));
        heroEffTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 3;
        c.gridy = 6 + 8;
        add(heroEffTxt, c);
        heroEffResLabel = new JLabel("eff res:");
        c.gridx = 2;
        c.gridy = 6 + 9;
        add(heroEffResLabel, c);
        heroEffResTxt = new JTextField("0");
        heroEffResTxt.setMinimumSize(new Dimension( 80, 20 ));
        heroEffResTxt.setPreferredSize(new Dimension( 80, 20 ));

        c.gridx = 3;
        c.gridy = 6 + 9;
        add(heroEffResTxt, c);
        
        batchTxt = new JTextArea("",8,20);
        batchTxt.setLineWrap(true);
        //batchTxt.setMinimumSize(new Dimension( 200, 100 ));
        //batchTxt.setPreferredSize(new Dimension( 200, 100 ));

        JScrollPane scroll =new JScrollPane(batchTxt);
        scroll.setPreferredSize(new Dimension(200, 100));
        c.gridx = 5;
        c.gridy = 6 + 6;
        add(scroll, c);

        // JScrollPane sp = new JScrollPane(batchTxt); 
        // this.add(sp);

        // cvLabel = new JLabel("cv:");
        // c.gridx = 2;
        // c.gridy = 6 + 10;
        // add(cvLabel, c);
        // cvTxt = new JTextField("0");
        // cvTxt.setMinimumSize(new Dimension( 80, 20 ));
        // cvTxt.setPreferredSize(new Dimension( 80, 20 ));
        // c.gridx = 3;
        // c.gridy = 6 + 10;
        // add(cvTxt, c);
    }

    public synchronized Bag getBag() {
        return this.b;
    }

    public Map<String, Double> getHistory() {
        return this.history;
    }

    public void setCnt(int c) {
        this.cnt = c;
    }

    public Handler getHandler() {
        return this.h;
    }

    public Map<Integer, FinalHero> getFhero() {
        return this.fhero;
    }

    public int getCnt2(){
        return this.cnt2;
    }
    
    public void setCnt2(int cnt2){
        this.cnt2 = cnt2;
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        
        Thread thread = new Thread(new Runnable() {
            // Thread thread = new Thread(){
            
            public void run() {
                gui g = new gui();
                g.createAndShowGUI();
                System.out.println("Thread Running");
            }
        });
        thread.start();
    }

    // javax.swing.SwingUtilities.invokeLater(new Runnable() {
    // public void run() {

    // }
    // });
    // }

}
