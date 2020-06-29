package E7v2;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class gui extends JPanel implements ActionListener {
    protected JTextField f,weapon,head,chest,neck,ring,boot;
    protected JTextArea a;
    JButton button;
    private JLabel lweapon,lhead,lchest,lneck,lring,lboot,total;
    private JLabel patk,pdef,php,pcrit,pcritdmg,speed,eff,effres,set,pk;
    Bag b;
    Map<String,JTextField> items,sumItems;
    final JButton setButton,spdButton,resetButton,wyvernButton,bellaButton,hpButton,critButton,xlsxButton;
    protected ExportXLSX xlsx;
    
    public gui(){
        super(new GridBagLayout());
        b = new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v2\\EpicSeven\\res\\bag.txt");
        addLabels();
        addTextStuff();
        sumItems = new HashMap<>();
        setButton = new JButton("Run Calcs");
        setButton.setActionCommand("RunCalcs");
        setButton.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
            c.gridx=0;
            c.gridy=6+2;
        add(setButton,c);
        spdButton = new JButton("Run Speed Calcs");
        spdButton.setActionCommand("RunSpdCalcs");
        spdButton.addActionListener(this);
            c.gridx=1;
            c.gridy=6+2;
        add(spdButton,c);

        wyvernButton = new JButton("Wyvern 13");
        wyvernButton.setActionCommand("Wyvern");
        wyvernButton.addActionListener(this);
            c.gridx=2;
            c.gridy=6+2;
        add(wyvernButton,c);

        critButton = new JButton("90+ Crit");
        critButton.setActionCommand("Crit");
        critButton.addActionListener(this);
            c.gridx=3;
            c.gridy=6+2;
        add(critButton,c);

        bellaButton = new JButton("Belladona");
        bellaButton.setActionCommand("Belladona");
        bellaButton.addActionListener(this);
            c.gridx=0;
            c.gridy=6+3;
        add(bellaButton,c);

        hpButton = new JButton("Hp Calcs");
        hpButton.setActionCommand("Hp");
        hpButton.addActionListener(this);
            c.gridx=1;
            c.gridy=6+3;
        add(hpButton,c);

        resetButton = new JButton("Reset");
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(this);
            c.gridx=2;
            c.gridy=6+3;
        add(resetButton,c);

        xlsxButton = new JButton("Export XLSX");
        xlsxButton.setActionCommand("XLSX");
        xlsxButton.addActionListener(this);
            c.gridx=3;
            c.gridy=6+3;
        add(xlsxButton,c);
    }

    public void addTextStuff(){
        GridBagConstraints c = new GridBagConstraints();
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
        
        items = new HashMap<>();
        //int cnt1 =0;
        for(int i=0;i<rows.size();i++){
            for(int j=0;j<columns.size();j++){
                items.put(rows.get(i)+columns.get(j),new JTextField());
                c.gridx=j+1;
                c.gridy=i+1;
                c.fill=GridBagConstraints.BOTH;
                c.weightx = 0.5;
                items.get(rows.get(i)+columns.get(j)).addActionListener(this);
                add(items.get(rows.get(i)+columns.get(j)),c);
                //items.get(rows.get(i)+columns.get(j)).setText(Integer.toString(cnt1));
                //cnt1++;
            }
        }
    }
    public void sumCols(){
        GridBagConstraints c = new GridBagConstraints();
        List<String> rows = new ArrayList<String>();
        Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
        List<String> columns = new ArrayList<String>();
        Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set");

        double[] sum = new double[columns.size()];
        
        for(int j=0;j<columns.size()-1;j++){
            sum[j]=0;
            for(int i=0;i<rows.size();i++){
                sum[j]+=Double.parseDouble(items.get(rows.get(i)+columns.get(j)).getText());
            }
            //System.out.println(sum[j]);
            if(!sumItems.containsKey(columns.get(j))){
                sumItems.put(columns.get(j),new JTextField());
                c.gridx=j+1;
                c.gridy=rows.size()+1;
                c.fill=GridBagConstraints.BOTH;
                add(sumItems.get(columns.get(j)),c);
            }
            
            sumItems.get(columns.get(j)).setText(String.valueOf(sum[j]));
            
        }
    }

    public void actionPerformed(ActionEvent evt) {

        
        if ("RunCalcs".equals(evt.getActionCommand())){        
            Sets s = b.superCalcs(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                        //System.out.println(e.pk+"!");
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    //System.out.println(rows.get(i)+columns.get(j));
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();
        }
        else if ("RunSpdCalcs".equals(evt.getActionCommand())){
            Sets s = b.superSpeedCalcs(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();
        }
        else if ("Wyvern".equals(evt.getActionCommand())){
            Sets s = b.superCalcsHunt(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();
        }
        else if ("Crit".equals(evt.getActionCommand())){
            Sets s = b.superCritCalcs(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();
        }
        else if ("Belladona".equals(evt.getActionCommand())){
            Sets s = b.superBellaCalcs(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();
        }
        else if ("Hp".equals(evt.getActionCommand())){
            Sets s = b.superHpCalcs(b.getW(),b.getH(),b.getCh(),b.getN(),b.getR(),b.getB());
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int i=0;i<rows.size();i++){
                for(int j=0;j<columns.size();j++){
                    values="";
                    e=null;
                    
                    if (i==0){
                        e = s.getWeapon();
                    }
                    else if(i==1){
                        e = s.getHead();
                    }
                    else if(i==2){
                        e = s.getChest();
                    }
                    else if(i==3){
                        e = s.getNeck();
                    }
                    else if(i==4){
                        e = s.getRing();
                    }
                    else if(i==5){
                        e = s.getBoot();
                    }

                    if(j==0){
                        values = Integer.toString(e.getP_atk());
                    }
                    else if(j==1){
                        values = Integer.toString(e.getP_def());
                    }
                    else if(j==2){
                        values = Integer.toString(e.getP_hp());
                    }
                    else if(j==3){
                        values = Integer.toString(e.getC());
                    }
                    else if(j==4){
                        values = Integer.toString(e.getCd());
                    }
                    else if(j==5){
                        values = Integer.toString(e.getSpd());
                    }
                    else if(j==6){
                        values = Integer.toString(e.getEff());
                    }
                    else if(j==7){
                        values = Integer.toString(e.getEffres());
                    }
                    else if(j==8){
                        values = e.getSet();
                    }
                    else if(j==9){
                        values = Integer.toString(e.getPk());
                    }
                    items.get(rows.get(i)+columns.get(j)).setText(values);
                }
            }
            sumCols();
            revalidate();

        }
        else if ("Reset".equals(evt.getActionCommand())){
            b = new Bag("C:\\Users\\jonmu\\Documents\\GitHub\\E7v2\\EpicSeven\\res\\bag.txt");
            List<String> rows = new ArrayList<String>();
            Collections.addAll(rows,"weapon","head","chest","neck","ring","boot");
            List<String> columns = new ArrayList<String>();
            Collections.addAll(columns,"patk","pdef","php","pcrit","pcritdmg","speed","eff","effres","set","pk");
            Equipment e;
            String values;
            for(int j=0;j<columns.size();j++){
                for(int i=0;i<rows.size();i++){            
                    items.get(rows.get(i)+columns.get(j)).setText(" ");
                }
            }
            for(int j=0;j<columns.size()-2;j++){
                sumItems.get(columns.get(j)).setText(" ");
            }
        }

        else if("XLSX".equals(evt.getActionCommand())){ 
            xlsx = new ExportXLSX(getBag().getSets());
            xlsx.loadData();
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new gui());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void addLabels(){
        //add components to panel
        GridBagConstraints c = new GridBagConstraints();

        patk = new JLabel();
        patk.setText("% atk");
            c.gridx=1;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(patk,c);
                
        pdef= new JLabel();
        pdef.setText("% def");
            c.gridx=2;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(pdef,c);
        php = new JLabel();
        php.setText("% hp");
            c.gridx=3;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(php,c);
        pcrit = new JLabel();
        pcrit.setText("% crit");
            c.gridx=4;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(pcrit,c);
        pcritdmg = new JLabel();
        pcritdmg.setText("% crit dmg");
            c.gridx=5;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(pcritdmg,c);
        speed = new JLabel();
        speed.setText("speed");
            c.gridx=6;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(speed,c);
        eff = new JLabel();
        eff.setText("eff");
            c.gridx=7;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(eff,c);
        effres = new JLabel();
        effres.setText("eff res");
            c.gridx=8;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(effres,c);
        set = new JLabel();
        set.setText("set");
            c.gridx=9;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(set,c);
        pk = new JLabel();
        pk.setText("pk");
            c.gridx=10;
            c.gridy=0;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(pk,c);
        
        lweapon = new JLabel();
        lweapon.setText("weapon:");
            c.gridx=0;
            c.gridy=1;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lweapon,c);

        lhead = new JLabel();
        lhead.setText("head:");
            c.gridx=0;
            c.gridy=2;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lhead,c);

        lchest = new JLabel();
        lchest.setText("chest:");
            c.gridx=0;
            c.gridy=3;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lchest,c);

        lneck = new JLabel();
        lneck.setText("neck:");
            c.gridx=0;
            c.gridy=4;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lneck,c);

        lring = new JLabel();
        lring.setText("ring:");
            c.gridx=0;
            c.gridy=5;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lring,c);
        lboot = new JLabel();
        lboot.setText("boot:");
            c.gridx=0;
            c.gridy=6;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(lboot,c);
        total = new JLabel();
        total.setText("total:");
            c.gridx=0;
            c.gridy=7;
            c.fill = GridBagConstraints.HORIZONTAL;
                add(total,c);
    }

    public Bag getBag(){
        return this.b;
    }
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}

