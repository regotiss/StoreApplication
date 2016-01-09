package reports;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javacode.ConnectToDataBase;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;



public class ReportTemplate1 {

public static void main(String[] args) {
		
		ConnectToDataBase.main(null);
		Vector<String> v=new Vector<String>();
		v.add("GRN");
		v.add("pid");
		v.add("pname");
		v.add("quantity");
		
		String sql="select grnno||' '||sname||' '||bill as GRN,pid,pname,quantity from product1 natural join prod_purch natural join purchase1 natural join supplier order by grnno,pid";
		getReport(v,sql,"Title","SubTitle","GRN");
	}

	public static void getReport(Vector<String> v, String sql,String title,String subtitle,String gr) {
		try
		{
			DynamicReport dr = getDynamicReport(v,title,subtitle,gr);
		
			Statement s=ConnectToDataBase.getS();
			ResultSet rs=s.executeQuery(sql);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), rs);
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static DynamicReport getDynamicReport(Vector<String> v,String title, String subtitle, String gr) {
	
		Style headerStyle = createHeaderStyle();
	    Style detailTextStyle = createDetailTextStyle();        
	    //Style detailNumberStyle = createDetailNumberStyle();
	    
	    DynamicReportBuilder report=new DynamicReportBuilder();
	    
	    for(String attr:v){
	    	
	    	
	    	AbstractColumn column = createColumn(attr, String.class,attr, 30, headerStyle, detailTextStyle);
	    	
	    	//if(attr.equals("DOB"))
	    		//column = createColumn("Int([DOB])", java.sql.Date.class,"BirthDate", 30, headerStyle, detailTextStyle);
	    	report.addColumn(column);
	    	if(attr.equals(gr)){
	    		column.setStyle(ReportTemplate1.createDetailNumberStyle());
	    		 GroupBuilder gb  = new GroupBuilder();
	    		  DJGroup g = gb.setCriteriaColumn((PropertyColumn) column)
	    		    .setGroupLayout(GroupLayout.VALUE_IN_HEADER) 
	    		    .build();
	    		  g.setName(gr);
	    		 
	    		  report.addGroup(g);
	    	}
	    	
	    	
	    }
	   
	    
	    StyleBuilder titleStyle=new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
        
        StyleBuilder subTitleStyle=new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        
        report.setTitle(title);
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle(subtitle);
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true); 
       // report.setTemplateFile("D:\\study\\java\\Reports\\template1.jrxml");
        return report.build();

	}

	public static void getReport(Vector<String> v, ResultSet rs,String title,String subtitle) {
		try
		{
			DynamicReport dr = getDynamicReport(v,title,subtitle);
			
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), rs);
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void getReport(Vector<String> v, Vector<Vector<String>> data,String title,String subtitle) {
		try
		{
			DynamicReport dr = getDynamicReport(v,title,subtitle);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), 
					new JRBeanCollectionDataSource(data));
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static DynamicReport getDynamicReport(Vector<String> v,String title,String subtitle) {
		
		Style headerStyle = createHeaderStyle();
	    Style detailTextStyle = createDetailTextStyle();        
	    //Style detailNumberStyle = createDetailNumberStyle();
	    
	    DynamicReportBuilder report=new DynamicReportBuilder();
	    
	    for(String attr:v){
	    	
	    	
	    	AbstractColumn column = createColumn(attr, String.class,attr, 30, headerStyle, detailTextStyle);
	    
	    	//if(attr.equals("DOB"))
	    		//column = createColumn("Int([DOB])", java.sql.Date.class,"BirthDate", 30, headerStyle, detailTextStyle);
	    	report.addColumn(column);
	    }
	    
	    StyleBuilder titleStyle=new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
        
        StyleBuilder subTitleStyle=new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        
        report.setTitle(title);
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle(subtitle);
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true); 
       // report.setTemplateFile("D:\\study\\java\\Reports\\template1.jrxml");
        return report.build();
	}

	public static Style createDetailNumberStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM_BOLD);
        sb.setBorder(Border.DOTTED()); 
        sb.setBackgroundColor(Color.GRAY);
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingLeft(5);  
        sb.setPaddingTop(10);
        return sb.build();
	}
	 public static Style createDetailTextStyle(){
	        StyleBuilder sb=new StyleBuilder(true);
	        sb.setFont(Font.VERDANA_MEDIUM);
	        sb.setBorder(Border.PEN_1_POINT());        
	        sb.setBorderColor(Color.BLACK);        
	        sb.setTextColor(Color.BLACK);
	        sb.setHorizontalAlign(HorizontalAlign.LEFT);
	        sb.setVerticalAlign(VerticalAlign.MIDDLE);
	        sb.setPaddingLeft(5);        
	        return sb.build();
	 }
	 public static Style setOddRowStyle(){
		 Style oddRowStyle = new Style();
		 oddRowStyle.setBorder(Border.PEN_1_POINT());
		 oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		 oddRowStyle.setTransparency(Transparency.OPAQUE);
		 return oddRowStyle;
	 }
	public static AbstractColumn createColumn(String property, Class<String> type,
			 String title, int width, Style headerStyle, Style detailStyle)
	            throws ColumnBuilderException {
	        AbstractColumn columnState = ColumnBuilder.getNew()
	                .setColumnProperty(property, type.getName()).setTitle(
	                        title).setWidth(Integer.valueOf(width))
	                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();
	        return columnState;
	  }
	public static Style createHeaderStyle() {
		 
		 StyleBuilder sb=new StyleBuilder(false);
	     sb.setFont(Font.VERDANA_MEDIUM_BOLD);
	     sb.setBorder(Border.THIN());
	     sb.setBorderBottom(Border.PEN_2_POINT());
	     sb.setBorderColor(Color.RED);
	     sb.setBackgroundColor(new Color(238, 130, 238));
	     sb.setTextColor(Color.BLACK);
	     sb.setHorizontalAlign(HorizontalAlign.CENTER);
	     sb.setVerticalAlign(VerticalAlign.MIDDLE);
	     sb.setTransparency(Transparency.OPAQUE);        
	     return sb.build(); 
		
		 
	 }
	

}
