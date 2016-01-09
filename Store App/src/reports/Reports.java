package reports;

import java.util.Vector;

public class Reports {
	public static void getAllCust(){
		String sql=" select ID,NAME,MOBNO,sum(balance) as CREDIT, sum(amount) as DEBIT from customr natural join sale1 natural join customerdebit where id <> 0 group by (id,name,mobno)  order by id";
		Vector<String> attr=new Vector<String>();
		attr.add("ID");
		attr.add("Name");
		attr.add("MOBNO");
		attr.add("CREDIT");
		attr.add("DEBIT");
		
		ReportTemplate.getReport(attr, sql, "Report Of All Customers","General Store");
	}
	public static void getSupp(String month,int stdt){
		String sql="select GRNNO,SNAME as SupplierName,BILL,purch_date as PurchaseDate from purchase1 natural join supplier where extract(day from purch_date) between "+stdt+" AND "+(stdt+6)+" and extract(month from purch_date)="+month;
		Vector<String> attr=new Vector<String>();
		attr.add("GRNNO");
		attr.add("SupplierName");
		attr.add("BILL");
		attr.add("PurchaseDate");
		//attr.add("DEBIT");
		System.out.println(sql);
		ReportTemplate.getReport(attr, sql, "Report Of Purchase","General Store");
	}
	public static void getSales(String month,int stdt){
		String sql="select BILLNO,NAME as CustomerName,BILL,sale_date as saledate from sale1 natural join customr where extract(day from sale_date) between "+stdt+" AND "+(stdt+6)+" and extract(month from sale_date)="+month;
		Vector<String> attr=new Vector<String>();
		attr.add("BILLNO");
		attr.add("CustomerName");
		attr.add("BILL");
		attr.add("saledate");
		//attr.add("DEBIT");
		System.out.println(sql);
		ReportTemplate.getReport(attr, sql, "Report Of Purchase","General Store");
	}
	
	public static void getSuppProd(String month,int stdt){
		String sql="select grnno||' '||sname||' '||bill||' '||' '||purch_date as GRN, pid,pname,quantity "+
				"from product1 natural join prod_purch natural join purchase1 natural join supplier where extract(day from purch_date) between "+stdt+" AND "+(stdt+6)+" and extract(month from purch_date)="+month+
				" order by grnno,pid";
		Vector<String> attr=new Vector<String>();
		attr.add("GRN");
		attr.add("pid");
		attr.add("pname");
		attr.add("quantity");
		//attr.add("DEBIT");
		System.out.println(sql);
		ReportTemplate1.getReport(attr, sql, "Report Of Purchase","General Store","GRN");
	}
}
