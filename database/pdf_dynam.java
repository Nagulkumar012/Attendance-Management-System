package database;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdf_dynam {
	public static void main(String args[]) throws SQLException
	{
		String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		char q='"';
		String file_name="E:\\pbl_pdf\\report_"+date+".pdf";
		Document doc=new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file_name));
			doc.open();
			
			connection conn=new connection();
			Connection con=conn.getConnection(); 
			
			char qu='"';
			String query="select *from attend where dt="+qu+date+qu;
			System.out.println(query);
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			
			/*String q="select *from students";
			PreparedStatement p=con.prepareStatement(query);
			ResultSet r=ps.executeQuery();*/
			
			
			//table
			PdfPTable table=new PdfPTable(2);
			PdfPCell c1=new PdfPCell(new Phrase("Reg_no"));
			table.addCell(c1);
			
			c1=new PdfPCell(new Phrase("status"));
			table.addCell(c1);
			
			while(rs.next()) {
				table.addCell(rs.getString("dt"));
				table.addCell(rs.getString("status"));
			}
			doc.add(table);
			doc.close();
			System.out.println("Complete");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
