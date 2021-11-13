package ccpa.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xlreader {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Workbook wb;
	public static Sheet ws;
	public static Row row;
	public static Row row1;
	public static Cell cell;
	public static CellStyle style;
	
	public Xlreader(String path)
	{
		try {
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static int getRowCount(String xlsheet) throws IOException
	{
		
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlsheet,int rownum) throws IOException
	{
//		fi=new FileInputStream(xlfile);
//		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public static String getCellData(String xlsheet,int rownum,int colnum) throws IOException
	{
//		fi=new FileInputStream(xlfile);
//		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			data=cell.getStringCellValue();
		} catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
//	Get getdata by column name
	public static String getCellData(String xlsheet,String colname, int rownum) throws IOException
	{
		int index = wb.getSheetIndex(xlsheet);
		int col_Num=-1;
		ws=wb.getSheetAt(index);
		row=ws.getRow(0);
		exitloop:
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().equals(colname))
			{
				col_Num=i;
				break exitloop;
			}
		}
		row=ws.getRow(rownum);
		cell=row.getCell(col_Num);
		String data;
		try 
		{
			if(cell.getCellType()==CellType.STRING) {
				 return cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
				 
				 String cellText = String.valueOf(cell.getNumericCellValue());
				  if (HSSFDateUtil.isCellDateFormatted(cell)) {
			 
					  double d = cell.getNumericCellValue();

					  Calendar cal =Calendar.getInstance();
					  cal.setTime(HSSFDateUtil.getJavaDate(d));
					  	cellText =
					  		(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					  	cellText=cal.get(Calendar.DAY_OF_MONTH)+"/"+
					  			cal.get(Calendar.MONTH)+1 + "/" + 
					  			cellText;
			 
			

				  }

				 
				 
				  return cellText;
			  }else if(cell.getCellType()==CellType.BLANK)
				  return ""; 
			  else 
				  return String.valueOf(cell.getBooleanCellValue());
			
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rownum+" or column "+colname +" does not exist in xls";
			}
//		wb.close();
//		fi.close();
//		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
//		fi=new FileInputStream(xlfile);
//		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();	
	}
	
	public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
//		fi=new FileInputStream(xlfile);
//		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();	
	}

}
