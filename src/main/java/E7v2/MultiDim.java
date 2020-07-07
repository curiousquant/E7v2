package E7v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

public class MultiDim extends SimpleApplication {
    
    gui g;
    private Picture cursor;

    public MultiDim(){
        g = new gui();
        g.createAndShowGUI();
    }

    public void simpleInitApp() {
        assetManager.registerLocator("assets", ClasspathLocator.class);
        viewPort.setBackgroundColor(ColorRGBA.Blue);
        Texture tex = assetManager.loadTexture("Cursor.png");
        cursor = new Picture("cursor");
        cursor.setTexture(assetManager, (Texture2D) tex, true);
        cursor.setWidth(8);
        cursor.setHeight(8);
        guiNode.attachChild(cursor);
        inputManager.addRawInputListener(inputListener);
        
        createAxis();
        addListener();
    }

    public synchronized void doStuff(){
        Map<Integer,Mesh> dotArray = new HashMap<>();
        Map<Integer,Geometry> geoArray = new HashMap<>();
        
        System.out.println(g.getBag().getR().size());
        
        ArrayList<Sets> s = g.getBag().getSets();
        System.out.println(s.size());
        
        double maxatk=0,maxdef=0,maxhp=0;

        for(int i=0; i<s.size();i++){
            Sets set = s.get(i);
            double atk = set.getWeapon().getP_atk()+set.getHead().getP_atk()+
                            set.getChest().getP_atk()+set.getNeck().getP_atk()+
                            set.getRing().getP_atk()+set.getBoot().getP_atk();
            ;

            double def = set.getWeapon().getP_def()+set.getHead().getP_def()+
            set.getChest().getP_def()+set.getNeck().getP_def()+
            set.getRing().getP_def()+set.getBoot().getP_def();
            ;

            double hp = set.getWeapon().getP_hp()+set.getHead().getP_hp()+
            set.getChest().getP_hp()+set.getNeck().getP_hp()+
            set.getRing().getP_hp()+set.getBoot().getP_hp();
            ;
            if(maxatk<atk){
                maxatk = atk;
            }
            if(maxdef<def){
                maxdef = def;
            }
            if(maxhp<hp){
                maxhp = hp;
            }
        }

        for(int i=0; i<s.size();i++){
            
            Sets set = s.get(i);
            double atk = (set.getWeapon().getP_atk()+set.getHead().getP_atk()+
                            set.getChest().getP_atk()+set.getNeck().getP_atk()+
                            set.getRing().getP_atk()+set.getBoot().getP_atk())/maxatk;
            ;

            double def = (set.getWeapon().getP_def()+set.getHead().getP_def()+
            set.getChest().getP_def()+set.getNeck().getP_def()+
            set.getRing().getP_def()+set.getBoot().getP_def())/maxdef
            ;

            double hp = (set.getWeapon().getP_hp()+set.getHead().getP_hp()+
            set.getChest().getP_hp()+set.getNeck().getP_hp()+
            set.getRing().getP_hp()+set.getBoot().getP_hp())/maxhp;
            ;


            dotArray.put(i,new Mesh());
            dotArray.get(i).setMode(Mesh.Mode.Points);
            dotArray.get(i).setBuffer(VertexBuffer.Type.Position, 3, 
                    new float[]{ 0, 0, 0, (float)atk, 
                        (float)def, (float)hp});

            geoArray.put(i,new Geometry("line",dotArray.get(i)));
            
            Material dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
            geoArray.get(i).setMaterial(dotMaterial);
            rootNode.attachChild(geoArray.get(i));
        }
    }
    private RawInputListener inputListener = new RawInputListener() {
        private float x = 0, y = 0;
        public void beginInput() {
        }
        public void endInput() {
        }
        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }
        public void onJoyButtonEvent(JoyButtonEvent evt) {
        }
        public void onMouseMotionEvent(MouseMotionEvent evt) {
            x += evt.getDX();
            y += evt.getDY();

            // Prevent mouse from leaving screen
            //AppSettings settings = TestSoftwareMouse.this.settings;
            x = FastMath.clamp(x, 0, settings.getWidth());
            y = FastMath.clamp(y, 0, settings.getHeight());

            // adjust for hotspot
            cursor.setPosition(x, y - 64);
        }
        public void onMouseButtonEvent(MouseButtonEvent evt) {
        }
        public void onKeyEvent(KeyInputEvent evt) {
        }
        public void onTouchEvent(TouchEvent evt) {
        }
    };
    private ActionListener actionListener = new ActionListener(){
        public void onAction(String name, boolean pressed, float tpf){
            System.out.println(name + " = " + pressed);
            doStuff();
        }
    };
    public AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            System.out.println(name + " = " + value);
            doStuff();
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
