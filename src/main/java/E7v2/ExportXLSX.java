package E7v2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportXLSX {
    ArrayList<Sets> s;
    XSSFSheet sheet;
    XSSFWorkbook workbook;
    public ExportXLSX(ArrayList<Sets> s){
        this.s = s;
        workbook = new XSSFWorkbook(); 
        sheet = workbook.createSheet("Sheet1"); 
    }
    

    public void loadData(){
        int rownum=0;
        int cntr=0;
        Equipment equip;
        //add headers
        Cell first_cell;
        Row first_row = sheet.createRow(rownum++);
        first_cell = first_row.createCell(0); 
        first_cell.setCellValue("p atk");
        first_cell = first_row.createCell(1); 
        first_cell.setCellValue("p def");
        first_cell = first_row.createCell(2); 
        first_cell.setCellValue("p hp");
        first_cell = first_row.createCell(3); 
        first_cell.setCellValue("crit %");
        first_cell = first_row.createCell(4); 
        first_cell.setCellValue("crit dmg");
        first_cell = first_row.createCell(5); 
        first_cell.setCellValue("speed");
        first_cell = first_row.createCell(6); 
        first_cell.setCellValue("eff");
        first_cell = first_row.createCell(7); 
        first_cell.setCellValue("eff res");
        first_cell = first_row.createCell(8); 
        first_cell.setCellValue("set");
        first_cell = first_row.createCell(9); 
        first_cell.setCellValue("pk");

        for (int i=0;i<getS().size();i++){
            //Row row = sheet.createRow(rownum++);
            Sets s = getS().get(i);
            
            for (int j=0;j<6;j++){
                //System.out.println(e.getWeapon().id);
                Row row = sheet.createRow(rownum++);
                equip=null;
                if (j==0){
                    equip = s.getWeapon();
                }
                else if(j==1){
                    equip = s.getHead();
                }
                else if(j==2){
                    equip = s.getChest();
                }
                else if(j==3){
                    equip = s.getNeck();
                }
                else if(j==4){
                    equip = s.getRing();
                }
                else if(j==5){
                    equip = s.getBoot();
                }
                int cellnum=0;
                for (int k=0;k<13;k++){
                    Cell cell = row.createCell(cellnum++); 
                    
                    if (k==0){
                        cell.setCellValue(equip.getP_atk());
                        
                    }
                    else if (k==1){
                        cell.setCellValue(equip.getP_def());
                        //System.out.println(e.getP_def());
                    }
                    else if (k==2){
                        cell.setCellValue(equip.getP_hp());
                        //System.out.println(e.getP_hp());
                    }
                    else if (k==3){
                        cell.setCellValue(equip.getC());
                        //System.out.println(e.getC());
                    }
                    else if (k==4){
                        cell.setCellValue(equip.getCd());
                        //System.out.println(e.getCd());
                    }
                    else if (k==5){
                        cell.setCellValue(equip.getSpd());
                        //System.out.println(e.getSpd());
                    }
                    else if (k==6){
                        cell.setCellValue(equip.getEff());
                        //System.out.println(e.getEff());
                    }
                    else if (k==7){
                        cell.setCellValue(equip.getEffres());
                        //System.out.println(e.getEffres());
                    }
                    else if (k==8){
                        cell.setCellValue(equip.getSet());
                        //System.out.println(e.getSet());
                    }
                    else if (k==9){
                        cell.setCellValue(equip.getPk());
                        //System.out.println(e.getPk());
                    }
                }            
            }
            
            Row r1 = sheet.createRow(rownum++);
            first_cell = r1.createCell(0); 
            first_cell.setCellValue("-------------------------------------------------------------------------------------------------------------------------------");

        }
        
        try { 
            // this Writes the workbook gfgcontribute 
            FileOutputStream out = new FileOutputStream(new File("gearbag_output.xlsx")); 
            workbook.write(out); 
            out.close(); 
            System.out.println("gearbag_output.xlsx written successfully on disk."); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }


    public ArrayList<Sets> getS() {
        return this.s;
    }

    public void setS(ArrayList<Sets> s) {
        this.s = s;
    }

}