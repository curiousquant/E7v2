package E7v2;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import com.jme3.util.BufferUtils;



public class MultiDim extends SimpleApplication implements Runnable{
    
    gui g;
    private Picture cursor;
    AllResults s1,s2;
    Node dots;
    Geometry mark;
    BitmapText helloText;
    Map<Integer,double[]> id2Stats; 
    Map<Integer,Geometry> id2Geo;
    Thread t;
    
    public MultiDim(){
    }

    public void simpleInitApp() {
    	id2Stats = new HashMap<Integer,double[]>();
    	id2Geo = new HashMap<Integer,Geometry>();
    	
    	helloText = new BitmapText(guiFont, false);
    	//flyCam.setDragToRotate(true);
    	//initMark();
    	initCrossHairs();
    	//getInputManager().setCursorVisible(true);
    	//flyCam.setDragToRotate(true);
    	
    	//flyCam.setEnabled(true);
    	
    	dots = new Node("Shootables");
    	rootNode.attachChild(dots);
    	try {
			getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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
        enqueue((new Callable() {
    		public Object call() throws Exception {
    			doStuff();
    			return null;
    		}
    	}));
    }

    public void simpleUpdate(float tpf){
    	
    	try {
    		getThread().join();
    		
	        Vector3f origin    = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);
	        Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
	        direction.subtractLocal(origin).normalizeLocal();
	
	        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
	        CollisionResults results = new CollisionResults();
	        dots.collideWith(ray, results);
	        Hero hero =  g.getBag().getHeroBag().get(g.heroCb.getSelectedItem().toString());
	        int heroatk = hero.getAtk();
	        int herodef = hero.getDef();
	        int herohp = hero.getHp();
	        
	        
	        if (results.size() > 0) {
	        	//System.out.println(id2Stats.size());
	        	double[] stats = id2Stats.get(Integer.parseInt(results.getClosestCollision().getGeometry().getName()));
	        	//System.out.println(results.getClosestCollision().getGeometry().getName());
	        	//System.out.println("atk:"+Math.ceil(stats[0])+" def:"+Math.ceil(stats[1])+" hp:"+Math.ceil(stats[2]));
	        	
	            CollisionResult closest = results.getClosestCollision();
	            //mark.setLocalTranslation(closest.getContactPoint());
	
	            Quaternion q = new Quaternion();
	            q.lookAt(closest.getContactNormal(), Vector3f.UNIT_Y);
	            //mark.setLocalRotation(q);
	
	            //rootNode.attachChild(mark);
	            
	            if(guiNode.hasChild(helloText)) {
	            	guiNode.getChild(4).removeFromParent();
	            	
	            	//guiNode.getChild("Hello World").removeFromParent();
	            }
	            else {
	            	//System.out.println("atk:"+(Math.ceil(stats[0]))+" def:"+(Math.ceil(stats[1]))+" hp:"+(Math.ceil(stats[2]))+"weapon:"+stats[3]+" helmet:"+stats[4]+" chest:"+stats[5]+" necklace:"+stats[6]+" ring:"+stats[7]+" boot:"+stats[8]);
	            	/** Display a line of text (default font from test-data) */
	                setDisplayStatView(false);
	                guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
	                
	                helloText.setSize(guiFont.getCharSet().getRenderedSize());
	                helloText.setText("atk:"+(Math.ceil(stats[0]))+" def:"+(Math.ceil(stats[1]))+" hp:"+(Math.ceil(stats[2])));
	                helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
	                guiNode.attachChild(helloText);
	                
	                
	                Equipment weapon = g.getBag().getW().get((int)stats[3]);
		            Equipment helmet = g.getBag().getH().get((int)stats[4]);
		            Equipment chest = g.getBag().getCh().get((int)stats[5]);
		            Equipment necklace = g.getBag().getN().get((int)stats[6]);
		            Equipment ring = g.getBag().getR().get((int)stats[7]);
		            Equipment boot = g.getBag().getB().get((int)stats[8]);
		            
		            Sets s = new Sets(weapon, helmet, chest, necklace, ring, boot);
		            //g.getBag().getSets().add(s);
		            
		            
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
	                        g.items.get(rows.get(i) + columns.get(j)).setText(values);
	                    }
	                }
	                g.sumCols();
	                g.revalidate();
	                g.calcHerov2("From 3D");
	                
	               
	            }
	            
	        } else {
	            //rootNode.detachChild(mark);
	        }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }


    
    protected Geometry makeCube(String name, float x, float y, float z) {
        Box box = new Box((float).03, (float).03, (float).03);
        Geometry cube = new Geometry(name, box);
        cube.setLocalTranslation(x, y, z);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.randomColor());
        cube.setMaterial(mat1);
        return cube;
    }
    
    protected void initMark() {
        Sphere sphere = new Sphere(30, 30, 0.1f);
        mark = new Geometry("BOOM!", sphere);
        Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mark_mat.setColor("Color", ColorRGBA.Red);
        mark.setMaterial(mark_mat);
      }

    
    
    
    public synchronized void doStuff(){
    	dots.detachAllChildren();
    	s1 = g.getBag().superAllCalcs(g.getBag().getW(), g.getBag().getH(), g.getBag().getCh(), g.getBag().getN(), g.getBag().getR(), g.getBag().getB(),
    			g.getBag().getHeroBag().get(g.heroCb.getSelectedItem().toString()));

    	try {
			getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        double maxatk=0,maxdef=0,maxhp=0;
        double minatk=100000,mindef=100000,minhp=100000;
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
                				//System.out.println(s1.getAllatk()[i][j][k][l][m][n]);
                				sumatk+=s1.getAllatk()[i][j][k][l][m][n];
                				sumdef+= s1.getAlldef()[i][j][k][l][m][n];
                				sumhp+= s1.getAllhp()[i][j][k][l][m][n];
                                if(maxatk<s1.allatk[i][j][k][l][m][n]){
                                    maxatk = s1.getAllatk()[i][j][k][l][m][n];
                                   
                                }
                                
                                if(maxdef<s1.getAlldef()[i][j][k][l][m][n]){
                                    maxdef = s1.getAlldef()[i][j][k][l][m][n];
                                    
                                }
                                
                                if(maxhp<s1.getAllhp()[i][j][k][l][m][n]){
                                    maxhp = s1.getAllhp()[i][j][k][l][m][n];
                                    
                                }
                                
                                
                                if(minatk>s1.allatk[i][j][k][l][m][n]){
                                	minatk = s1.getAllatk()[i][j][k][l][m][n];
                                	
                                }
                                
                                if(mindef>s1.getAlldef()[i][j][k][l][m][n]){
                                	mindef = s1.getAlldef()[i][j][k][l][m][n];
                                	
                                }
                                
                                if(minhp>s1.getAllhp()[i][j][k][l][m][n]){
                                	minhp = s1.getAllhp()[i][j][k][l][m][n];
                                	
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
        System.out.println("max atk:"+maxatk);
        System.out.println("max def:"+maxdef);
        System.out.println("max hp:"+maxhp);
        System.out.println("min atk:"+minatk);
        System.out.println("min def:"+mindef);
        System.out.println("min def:"+minhp);
        System.out.println(meanatk+"!"+meandef+"!"+meanhp);
        Vector3f[] lineVerticies=new Vector3f[cnt];
        Vector3f[] lineVerticies3=new Vector3f[2];
       
        cnt = 0;
        for(int i=0;i<s1.getAllatk().length/2;i++){
        	System.out.println(i);
            for(int j=0;j<s1.getAllatk()[i].length/2;j++){
                for(int k=0;k<s1.getAllatk()[i][j].length/2;k++){
                    for(int l=0;l<s1.getAllatk()[i][j][k].length/2;l++){
                        for(int m=0;m<s1.getAllatk()[i][j][k][l].length/2;m++){
                            for(int n=0;n<s1.getAllatk()[i][j][k][l][m].length/2;n++){
                                //if(maxatk2<s.allatk[i][j][k][l][m][n]){
                                	//lineVerticies[cnt]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                	//		(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                            		//dots.attachChild(makeCube("a Dragon", -2f, 0f, 1f));
                	 				double[] tmp = new double[9];
                					tmp[0]=s1.getAllatk()[i][j][k][l][m][n];
                					tmp[1]=s1.getAlldef()[i][j][k][l][m][n];
                					tmp[2]=s1.getAllhp()[i][j][k][l][m][n];
                					tmp[3]=i;
                					tmp[4]=j;
                					tmp[5]=k;//
                					tmp[6]=0;//l
                					tmp[7]=0;//m
                					tmp[8]=0;//n
                					id2Stats.put(cnt,tmp);
                					//System.out.println(cnt+":"+tmp[0]+":"+tmp[1]+":"+tmp[2]);
                					
                					if(s1.getAllatk()[i][j][k][l][m][n]>(maxatk+minatk)/2 
                							|| s1.getAlldef()[i][j][k][l][m][n]>(maxdef+mindef)/2 
                								|| s1.getAllhp()[i][j][k][l][m][n]>(maxhp+minhp)/2 ) {
//                						System.out.println(i+"!"+j+"!"+k+"!"+l+"!"+m+"!"+n+"!");
//                						System.out.println(s1.getAllatk()[i][j][k][l][m][n]);
//                						System.out.println(s1.getAlldef()[i][j][k][l][m][n]);
//                						System.out.println(s1.getAllhp()[i][j][k][l][m][n]);
                						//System.out.println(cnt);
                						dots.attachChild(makeCube(String.valueOf(cnt), (float)(3*s1.getAllatk()[i][j][k][l][m][n]/maxatk-3*meanatk/maxatk),
                																			(float)(3*s1.getAlldef()[i][j][k][l][m][n]/maxdef-3*meandef/maxdef),
                																				(float)(3*s1.getAllhp()[i][j][k][l][m][n]/maxhp-3*meanhp/maxhp)));
                					}
                					
                						
	                                
                                	if(maxatk==s1.getAllatk()[i][j][k][l][m][n]) {
                                		lineVerticies3[0]=new Vector3f(0,0,0);
                                    	lineVerticies3[1]=new Vector3f((float)(3*s1.getAllatk()[i][j][k][l][m][n]/maxatk-3*meanatk/maxatk),(float)(3*s1.getAlldef()[i][j][k][l][m][n]/maxdef-3*meandef/maxdef),
                                        			(float)(3*s1.getAllhp()[i][j][k][l][m][n]/maxhp-3*meanhp/maxhp));
                                		
                                	}
                                	if(maxdef==s1.getAlldef()[i][j][k][l][m][n]) {
                                	//	lineVerticies3[2]=new Vector3f(0,0,0);
                                	//	lineVerticies3[3]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                    //			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                	if(maxhp==s1.getAllhp()[i][j][k][l][m][n]) {
                                	//	lineVerticies3[4]=new Vector3f(0,0,0);
                                	//	lineVerticies3[5]=new Vector3f((float)(1*s1.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s1.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                   // 			(float)(1*s1.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                                		
                                	}
                                    
	                                cnt++;
                                //}
                            }
                        }
                    }
                }
            }
        }

//        mesh.setMode(Mesh.Mode.Points);
//        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies));
//        mesh.updateBound();
//        mesh.updateCounts();
//        
//      Geometry geo = new Geometry("line",mesh);
//       
//        geo.setMaterial(dotMaterial);
//        
//        rootNode.attachChild(geo);
        
        Material dotMaterial = getAssetManager().loadMaterial("Common/Materials/RedColor.j3m");
        Mesh mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Lines);
        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies3));
        mesh.updateBound();
        mesh.updateCounts();
        
        Geometry geo = new Geometry("line",mesh);
       
        geo.setMaterial(dotMaterial);
        dots.attachChild(geo);
    
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
                                //if(maxatk2<s.allatk[i][j][k][l][m][n]){
                                	//lineVerticies2[cnt]=new Vector3f((float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                                	//		(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0));
                            		dots.attachChild(makeCube("a Dragon", (float)(1*s2.getAllatk()[i][j][k][l][m][n]/maxatk-0),(float)(1*s2.getAlldef()[i][j][k][l][m][n]/maxdef-0),
                            			(float)(1*s2.getAllhp()[i][j][k][l][m][n]/maxhp-0)));
                            		
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
//        Material dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
//        Mesh mesh = new Mesh();
//        mesh.setMode(Mesh.Mode.Points);
//        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies2));
//        mesh.updateBound();
//        mesh.updateCounts();
//        
//        Geometry geo = new Geometry("line",mesh);
//
//        geo.setMaterial(dotMaterial);
//
//        
//        dots.attachChild(geo);
        
        
//        dotMaterial = getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m");
//        mesh = new Mesh();
//        mesh.setMode(Mesh.Mode.Lines);
//        mesh.setBuffer(VertexBuffer.Type.Position, 3,BufferUtils.createFloatBuffer(lineVerticies3));
//        mesh.updateBound();
//        mesh.updateCounts();
//        
//        geo = new Geometry("line",mesh);
//       
//        geo.setMaterial(dotMaterial);
//        rootNode.attachChild(geo);

    
    }
    
    public void actionPerformed(ActionEvent evt) {
    	g.actionPerformed(evt);
	    if ("Dropdown".equals(evt.getActionCommand())) {
	        if(g.getCnt2()>0){
	            //getFhero().remove(getCnt2());
	            //cnt2--;
	            g.calcHeroUI();
                enqueue((new Callable() {
	        		public Object call() throws Exception {
	        			doStuff();
	        			return null;
	        		}
	        	}));
	        }
		}
    }
    private ActionListener actionListener = new ActionListener() {
	    public void onAction(String name, boolean keyPressed, float tpf) {
	    	
	    	
	        if (name.equals("export")){
	    		g.xlsxButton.doClick();
	    	}
	        if (name.equals("click") && !keyPressed) {
	        	
	          // 1. Reset results list.
	          
	          Ray ray = new Ray(cam.getLocation(), cam.getDirection());
	          //System.out.println(cam.getLocation()+"-"+ cam.getDirection());
	          CollisionResults results = new CollisionResults();
	          // 2. Aim the ray from cam loc to cam direction.
	          
	          // 3. Collect intersections between Ray and Shootables in results list.
	          // DO NOT check collision with the root node, or else ALL collisions will hit the
	          // skybox! Always make a separate node for objects you want to collide with.
	         
	          dots.collideWith(ray, results);
	          // 4. Print the results
	          //System.out.println("----- Collisions? " + results.size() + "-----");
	          for (int i = 0; i < results.size(); i++) {
	            // For each hit, we know distance, impact point, name of geometry.
	            float dist = results.getCollision(i).getDistance();
	            Vector3f pt = results.getCollision(i).getContactPoint();
	            String hit = results.getCollision(i).getGeometry().getName();
	            //System.out.println("* Collision #" + i);
	           // System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
	          }
	          // 5. Use the results (we mark the hit object)
	          if (results.size() > 0) {
	            // The closest collision point is what was truly hit:
	            CollisionResult closest = results.getClosestCollision();
	            // Let's interact - we mark the hit with a red dot.
	            //mark.setLocalTranslation(closest.getContactPoint());
	            //rootNode.attachChild(mark);
	            
	            double[] stats = id2Stats.get(Integer.parseInt(results.getClosestCollision().getGeometry().getName()));
	            if(stats!=null) {

		            Equipment weapon = g.getBag().getW().get((int)stats[3]);
		            Equipment helmet = g.getBag().getH().get((int)stats[4]);
		            Equipment chest = g.getBag().getCh().get((int)stats[5]);
		            Equipment necklace = g.getBag().getN().get((int)stats[6]);
		            Equipment ring = g.getBag().getR().get((int)stats[7]);
		            Equipment boot = g.getBag().getB().get((int)stats[8]);
		            
		            Sets s = new Sets(weapon, helmet, chest, necklace, ring, boot);
		            g.getBag().getSets().add(s);
		            
		            g.getBag().setWcnt(g.getBag().getWcnt()-1);
		            g.getBag().setBcnt(g.getBag().getBcnt()-1);
		            g.getBag().setCnnt(g.getBag().getCnnt()-1);
		            g.getBag().setHcnt(g.getBag().getHcnt()-1);
		            g.getBag().setNcnt(g.getBag().getNcnt()-1);
		            g.getBag().setRcnt(g.getBag().getRcnt()-1);
		            
		            g.getBag().getW().remove(weapon);
		            g.getBag().getH().remove(helmet);
		            g.getBag().getCh().remove(chest);
		            g.getBag().getN().remove(necklace);
		            g.getBag().getR().remove(ring);
		            g.getBag().getB().remove(boot);
		            
		            
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
	                        g.items.get(rows.get(i) + columns.get(j)).setText(values);
	                    }
	                }
	                g.sumCols();
	                g.revalidate();
	                g.calcHero("From 3D");
	                //remove from 3D
//	                dots.getChild(Integer.parseInt(results.getClosestCollision().getGeometry().getName())).removeFromParent();
	                //dots.detachChild(results.getClosestCollision().getGeometry());
	                id2Stats.remove(Integer.parseInt(results.getClosestCollision().getGeometry().getName()));
	               
	                enqueue((new Callable() {
		        		public Object call() throws Exception {
		        			doStuff();
		        			return null;
		        		}
		        	}));
		        	
	                
	                
//	                if(guiNode.hasChild(helloText)) {
//		            	
//		            	System.out.println(guiNode.getChild(4).removeFromParent());
//		            	
//		            	//guiNode.getChild("Hello World").removeFromParent();
//		            }
//		            else {
//		            	guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
//		                
//		                helloText.setSize(guiFont.getCharSet().getRenderedSize());
//		                helloText.setText("atk:"+Math.ceil(stats[0])+" def:"+Math.ceil(stats[1])+" hp:"+Math.ceil(stats[2]));
//		                helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
//		                guiNode.attachChild(helloText);
//		            }
	                
		          } else {
		            // No hits? Then remove the red mark.
		            rootNode.detachChild(mark);
		          }
	            }
	            
	            
	        }
	      }
    	};

    	  /** A centred plus sign to help the player aim. */
    protected void initCrossHairs() {
        	setDisplayStatView(false);
    	guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    	BitmapText ch = new BitmapText(guiFont, false);
    	ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    	ch.setText("+"); // crosshairs
    	ch.setLocalTranslation( // center
      settings.getWidth() / 2 - ch.getLineWidth()/2, settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
    	guiNode.attachChild(ch);
    }
    
    
    public void addListener(){
        
        inputManager.addMapping("click",
                new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("export",
                new KeyTrigger(KeyInput.KEY_0));
        // Test multiple listeners per mapping
        inputManager.addListener(actionListener, "click");
        inputManager.addListener(actionListener, "export");
    }
    public void createAxis(){
        
        Mesh posYaxis = new Mesh();
        posYaxis.setMode(Mesh.Mode.Lines);
        posYaxis.setBuffer(VertexBuffer.Type.Position, 3, new float[]{ 0, 0, 0, 0, 10, 0});
        Geometry lineGeometryY1 = new Geometry("line", posYaxis);
        Material lineMaterialY1 = getAssetManager().loadMaterial("Common/Materials/RedColor.j3m");
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
        Material lineMaterialX1 = getAssetManager().loadMaterial("Common/Materials/RedColor.j3m");
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
        Material lineMaterialZ1 = getAssetManager().loadMaterial("Common/Materials/RedColor.j3m");
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


    public void setThread(Thread t) {
    	this.t = t;
    }
    
    public Thread getThread() {
    	return this.t;
    }
    
    public static void main(String[] args) {
    	
    	Thread th1 = new Thread(new MultiDim(), "th1");
    	th1.start();
    	
    	
        //MultiDim app = new MultiDim(th1);
        
        //app.setSettings(settings);


    }

	@Override
	public void run() {
		AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        setSettings(settings);
        
		Thread t = Thread.currentThread();
        System.out.println("Thread started: "+t.getName());
 
    	g = new gui(this);
        g.createAndShowGUI();
        
        this.start();
        this.setThread(t);
	}
}
