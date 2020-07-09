package E7v2;

import java.util.concurrent.TimeUnit;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import com.jme3.util.BufferUtils;

public class MultiDim extends SimpleApplication {
    
    gui g;
    private Picture cursor;
    AllResults s1,s2;
    public MultiDim(){
       
        

    }

    public void simpleInitApp() {
    	 g = new gui();
         g.createAndShowGUI();
    	Bag b = g.getBag();
        s1 = g.getBag().superAllCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                                        b.getHeroBag().get(g.heroCb.getSelectedItem().toString()));
        
        s2 = g.getBag().superAllCalcs(b.getW(), b.getH(), b.getCh(), b.getN(), b.getR(), b.getB(),
                b.getHeroBag().get(g.heroCb.getSelectedItem().toString()));
        
        
        assetManager.registerLocator("assets", ClasspathLocator.class);
        viewPort.setBackgroundColor(ColorRGBA.Blue);
        // Texture tex = assetManager.loadTexture("Cursor.png");
        // cursor = new Picture("cursor");
        // cursor.setTexture(assetManager, (Texture2D) tex, true);
        // cursor.setWidth(8);
        // cursor.setHeight(8);
        // guiNode.attachChild(cursor);
        
        createAxis();
        addListener();
        
        //doStuff();
    }

    public synchronized void doStuff(){
        
        
        
        
        double maxatk=0,maxdef=0,maxhp=0;
        double maxatk2=0;
        double sumatk=0,meanatk = 0;
        double sumdef=0,meandef=0;
        double sumhp =0, meanhp = 0;
        int cnt = 0;
        for(int i=0;i<s1.getAllatk().length/2;i++){
            for(int j=0;j<s1.getAllatk()[i].length/2;j++){
                for(int k=0;k<s1.getAllatk()[i][j].length/2;k++){
                    for(int l=0;l<s1.allatk[i][j][k].length/2;l++){
                        for(int m=0;m<s1.allatk[i][j][k][l].length/2;m++){
                            for(int n=0;n<s1.allatk[i][j][k][l][m].length/2;n++){
                				System.out.println(s1.getAllatk()[i][j][k][l][m][n]);
                				sumatk+=s1.getAllatk()[i][j][k][l][m][n];
                				sumdef+= s1.getAlldef()[i][j][k][l][m][n];
                				sumhp+= s1.getAllhp()[i][j][k][l][m][n];
                                if(maxatk<s1.allatk[i][j][k][l][m][n]){
                                    maxatk = s1.getAllatk()[i][j][k][l][m][n];
                                    System.out.println("atk:"+maxatk);
                                }
                                sumdef = s1.getAlldef()[i][j][k][l][m][n];
                                if(maxdef<s1.getAlldef()[i][j][k][l][m][n]){
                                    maxdef = s1.getAlldef()[i][j][k][l][m][n];
                                    System.out.println("def:"+maxdef);
                                }
                                sumhp = s1.getAllhp()[i][j][k][l][m][n];
                                if(maxhp<s1.getAllhp()[i][j][k][l][m][n]){
                                    maxdef = s1.getAllhp()[i][j][k][l][m][n];
                                    System.out.println("def:"+maxdef);
                                }
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        meanatk = sumatk/cnt;
        meandef = sumdef/cnt;
        meanhp = sumhp/cnt;
        System.out.println(meanatk+"!"+meandef+"!"+meanhp);
        Vector3f[] lineVerticies=new Vector3f[cnt];
        Vector3f[] lineVerticies3=new Vector3f[6];
        
        cnt = 0;
        for(int i=0;i<s1.getAllatk().length/2;i++){
        	System.out.println(i);
            for(int j=0;j<s1.getAllatk()[i].length/2;j++){
                for(int k=0;k<s1.getAllatk()[i][j].length/2;k++){
                    for(int l=0;l<s1.allatk[i][j][k].length/2;l++){
                        for(int m=0;m<s1.allatk[i][j][k][l].length/2;m++){
                            for(int n=0;n<s1.allatk[i][j][k][l][m].length/2;n++){
                                //if(maxatk2<s.allatk[i][j][k][0][0][0]){
                                	lineVerticies[cnt]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                	
	                                
                                    if(maxatk==s1.getAllatk()[i][j][k][l][m][n]) {
                                		lineVerticies3[0]=new Vector3f(0,0,0);
                                		lineVerticies3[1]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	if(maxdef==s1.getAlldef()[i][j][k][l][m][n]) {
                                		lineVerticies3[2]=new Vector3f(0,0,0);
                                		lineVerticies3[3]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	if(maxhp==s1.getAllhp()[i][j][k][l][m][n]) {
                                		lineVerticies3[4]=new Vector3f(0,0,0);
                                		lineVerticies3[5]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                    
	                                cnt++;
                                //}
                            }
                        }
                    }
                }
            }
        }
        Material dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        Mesh mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Points);
        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies));
        mesh.updateBound();
        mesh.updateCounts();
        
        Geometry geo = new Geometry("line",mesh);
       
        geo.setMaterial(dotMaterial);
        
        rootNode.attachChild(geo);
        
        dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Lines);
        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies3));
        mesh.updateBound();
        mesh.updateCounts();
        
        geo = new Geometry("line",mesh);
       
        geo.setMaterial(dotMaterial);
        rootNode.attachChild(geo);
    
    }
public synchronized void doStuffv2(){
        
        double maxatk=0,maxdef=0,maxhp=0;
        double maxatk2=0;
        double sumatk=0,meanatk = 0;
        double sumdef=0,meandef=0;
        double sumhp =0, meanhp = 0;
        int cnt = 0;
        for(int i=s2.getAllatk().length/2;i<s2.getAllatk().length;i++){
            for(int j=s2.getAllatk()[i].length/2;j<s2.getAllatk()[i].length;j++){
                for(int k=s2.getAllatk()[i][j].length/2;k<s2.getAllatk()[i][j].length;k++){
                    for(int l=s2.allatk[i][j][k].length/2;l<s2.allatk[i][j][k].length;l++){
                        for(int m=s2.allatk[i][j][k][l].length/2;m<s2.allatk[i][j][k][l].length;m++){
                            for(int n=s2.allatk[i][j][k][l][m].length/2;n<s2.allatk[i][j][k][l][m].length;n++){
                            	sumatk+=s2.getAllatk()[i][j][k][l][m][n];
                				sumdef+= s2.getAlldef()[i][j][k][l][m][n];
                				sumhp+= s2.getAllhp()[i][j][k][l][m][n];
                                if(maxatk<s2.allatk[i][j][k][l][m][n]){
                                    maxatk = s2.getAllatk()[i][j][k][l][m][n];
                                    System.out.println("atk:"+maxatk);
                                }
                                if(maxdef<s2.getAlldef()[i][j][k][l][m][n]){
                                    maxdef = s2.getAlldef()[i][j][k][l][m][n];
                                    System.out.println("def:"+maxdef);
                                }
                                if(maxhp<s2.getAllhp()[i][j][k][l][m][n]){
                                    maxhp = s2.getAllhp()[i][j][k][l][m][n];
                                    System.out.println("hp:"+maxhp);
                                }
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        meanatk = sumatk/cnt;
        meandef = sumdef/cnt;
        meanhp = sumhp/cnt;
        System.out.println(meanatk+"!"+meandef+"!"+meanhp);
        Vector3f[] lineVerticies2=new Vector3f[cnt];
        Vector3f[] lineVerticies3=new Vector3f[6];
        
        
        cnt = 0;
        for(int i=s2.getAllatk().length/2;i<s2.getAllatk().length;i++){
        	System.out.println(i);
            for(int j=s2.getAllatk()[i].length/2;j<s2.getAllatk()[i].length;j++){
                for(int k=s2.getAllatk()[i][j].length/2;k<s2.getAllatk()[i][j].length;k++){
                    for(int l=s2.allatk[i][j][k].length/2;l<s2.allatk[i][j][k].length;l++){
                        for(int m=s2.allatk[i][j][k][l].length/2;m<s2.allatk[i][j][k][l].length;m++){
                            for(int n=s2.allatk[i][j][k][l][m].length/2;n<s2.allatk[i][j][k][l][m].length;n++){
                                //if(maxatk2<s.allatk[i][j][k][0][0][0]){
                                	lineVerticies2[cnt]=new Vector3f((float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                			(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                	
                                	if(maxatk==s2.getAllatk()[i][j][k][l][m][n]) {
                                		lineVerticies3[0]=new Vector3f(0,0,0);
                                		lineVerticies3[1]=new Vector3f((float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	if(maxdef==s2.getAlldef()[i][j][k][l][m][n]) {
                                		lineVerticies3[2]=new Vector3f(0,0,0);
                                		lineVerticies3[3]=new Vector3f((float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	if(maxhp==s2.getAllhp()[i][j][k][l][m][n]) {
                                		lineVerticies3[4]=new Vector3f(0,0,0);
                                		lineVerticies3[5]=new Vector3f((float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    			(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	
	                                cnt++;
                                //}
                            }
                        }
                    }
                }
            }
        }
        Material dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        Mesh mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Points);
        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies2));
        mesh.updateBound();
        mesh.updateCounts();
        
        Geometry geo = new Geometry("line",mesh);
       
        geo.setMaterial(dotMaterial);
        rootNode.attachChild(geo);
        
        
        dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Lines);
        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies3));
        mesh.updateBound();
        mesh.updateCounts();
        
        geo = new Geometry("line",mesh);
       
        geo.setMaterial(dotMaterial);
        rootNode.attachChild(geo);

    
    }
    private ActionListener actionListener = new ActionListener(){
        public void onAction(String name, boolean pressed, float tpf){
            System.out.println(name + " = " + pressed);
            doStuff();
            doStuffv2();
        }
    };
    public AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            System.out.println(name + " = " + value);
            doStuff();
            doStuffv2();
        }
    };

    public void addListener(){
        
        inputManager.addMapping("My Action",
                new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));

        // Test multiple listeners per mapping
        inputManager.addListener(actionListener, "My Action");
        inputManager.addListener(analogListener, "My Action");
    }
    public void createAxis(){
        
        Mesh posYaxis = new Mesh();
        posYaxis.setMode(Mesh.Mode.Lines);
        posYaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 0, 10, 0});
        Geometry lineGeometryY1 = new Geometry("line", posYaxis);
        Material lineMaterialY1 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryY1.setMaterial(lineMaterialY1);
        rootNode.attachChild(lineGeometryY1);

        Mesh negYaxis = new Mesh();
        negYaxis.setMode(Mesh.Mode.Lines);
        negYaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 0, -10, 0});
        Geometry lineGeometryY2 = new Geometry("line", negYaxis);
        Material lineMaterialY2 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryY2.setMaterial(lineMaterialY2);
        rootNode.attachChild(lineGeometryY2);

        Mesh posXaxis= new Mesh();
        posXaxis.setMode(Mesh.Mode.Lines);
        posXaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 10, 0, 0});
        Geometry lineGeometryX1 = new Geometry("line", posXaxis);
        Material lineMaterialX1 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryX1.setMaterial(lineMaterialX1);
        rootNode.attachChild(lineGeometryX1);

        Mesh negXaxis = new Mesh();
        negXaxis.setMode(Mesh.Mode.Lines);
        negXaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, -10, 0, 0});
        Geometry lineGeometryX2 = new Geometry("line", negXaxis);
        Material lineMaterialX2 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryX2.setMaterial(lineMaterialX2);
        rootNode.attachChild(lineGeometryX2);

        Mesh posZaxis= new Mesh();
        posZaxis.setMode(Mesh.Mode.Lines);
        posZaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 0, 0, 10});
        Geometry lineGeometryZ1 = new Geometry("line", posZaxis);
        Material lineMaterialZ1 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryZ1.setMaterial(lineMaterialZ1);
        rootNode.attachChild(lineGeometryZ1);

        Mesh negZaxis = new Mesh();
        negZaxis.setMode(Mesh.Mode.Lines);
        negZaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 0, 0, -10});
        Geometry lineGeometryZ2 = new Geometry("line", negZaxis);
        Material lineMaterialZ2 = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
        lineGeometryZ2.setMaterial(lineMaterialZ2);
        rootNode.attachChild(lineGeometryZ2);
    }


    public gui getG() {
        return this.g;
    }

    public void setG(gui g) {
        this.g = g;
    }


    public static void main(String[] args) {
        
        MultiDim app = new MultiDim();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        app.setSettings(settings);

        app.start();

    }
}
