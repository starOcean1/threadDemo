package com.example.demo.controller.PDF;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class PDFTableTwoReport {


    public static void main(String[] args) throws Exception, DocumentException {

        List<String> ponum=new ArrayList<String>();
        add(ponum, 26);
        List<String> line=new ArrayList<String>();
        add(line, 26);
        List<String> part=new ArrayList<String>();
        add(part, 26);
        List<String> description=new ArrayList<String>();
        add(description, 26);
        List<String> origin=new ArrayList<String>();
        add(origin, 26);

        //Create Document Instance
        Document document=new Document();

        //add Chinese font
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //Font headfont=new Font(bfChinese,10,Font.BOLD);
        Font keyfont=new Font(bfChinese,8,Font.BOLD);
        Font textfont=new Font(bfChinese,8,Font.NORMAL);

        //Create Writer associated with document
        PdfWriter.getInstance(document, new FileOutputStream(new File("F:\\POReceiveReport.pdf")));

        document.open();

        //Seperate Page controller
        int recordPerPage=10;
        int fullPageRequired=ponum.size()/recordPerPage;
        int remainPage=ponum.size()%recordPerPage>1?1:0;
        int totalPage=fullPageRequired+remainPage;

        for(int j=0;j<totalPage;j++){
            document.newPage();

            //create page number
            String pageNo=leftPad("页码: "+(j+1)+" / "+totalPage,615);
            Paragraph pageNumber=new Paragraph(pageNo, keyfont) ;
            document.add(pageNumber);

            //create title image
            Image jpeg=Image.getInstance("C:\\Users\\ASUS\\Pictures\\Camera Roll\\dog.jpg");
            jpeg.setAlignment(Image.ALIGN_CENTER);
            jpeg.scaleAbsolute(100, 37);
            jpeg.setAbsolutePosition(20,100);
            document.add(jpeg);

            //header information
            PdfPTable tHeader=new PdfPTable(2);
            float[] widthsHeader={2.1f,2.9f};
            tHeader.setWidths(widthsHeader);
            tHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


            String compAdd="河源市高新技术开发区兴业大道中66号";
            String company="丰达音响（河源）有限公司";
            String vendor="V006";
            String vendorName="中山市卢氏五金有限公司";
            String ccn="FHH";
            String mas_loc="FHH";
            String delivery_note="20130718001";
            String receive_date="20130718";
            String dept="H11";
            String asn="0123456789";


            PdfPCell c1Header=new PdfPCell(new Paragraph("地址："+compAdd,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new PdfPCell(new Paragraph("供应商："+vendor,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new PdfPCell(new Paragraph("公司："+company,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new PdfPCell(new Paragraph("供应商工厂："+vendorName,keyfont));
            tHeader.addCell(c1Header);
            c1Header = new PdfPCell(new Paragraph("CCN:   "+ccn+"    Master Loc:   "+mas_loc,keyfont));
            tHeader.addCell(c1Header);
            c1Header = new PdfPCell(new Paragraph("送货编号: "+delivery_note+"                             送货日期: "+receive_date,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new PdfPCell(new Paragraph("Dept："+dept,keyfont));
            tHeader.addCell(c1Header);
            c1Header=new PdfPCell(new Paragraph("ASN#："+asn,keyfont));
            tHeader.addCell(c1Header);
            document.add(tHeader);

            //record header field
            PdfPTable t=new PdfPTable(5);
            float[] widths={1.5f,1f,1f,1.5f,1f};
            t.setWidths(widths);
            t.getDefaultCell().setBorder(1);
            PdfPCell c1 = new PdfPCell(new Paragraph("PO#",keyfont));
            t.addCell(c1);
            c1 = new PdfPCell(new Paragraph("Line",keyfont));
            t.addCell(c1);
            c1 = new PdfPCell(new Paragraph("Part#",keyfont));
            t.addCell(c1);
            c1 = new PdfPCell(new Paragraph("Description",keyfont));
            t.addCell(c1);
            c1 = new PdfPCell(new Paragraph("Origin",keyfont));
            t.addCell(c1);

            //calculate the real records within a page ,to calculate the last record number of every page
            int maxRecordInPage= j+1 ==totalPage ? (remainPage==0?recordPerPage:(ponum.size()%recordPerPage)):recordPerPage;

            for(int i=j*recordPerPage;i<((j*recordPerPage)+maxRecordInPage);i++){
                PdfPCell c2=new PdfPCell(new Paragraph(ponum.get(i), textfont));
                t.addCell(c2);
                c2=new PdfPCell(new Paragraph(line.get(i), textfont));
                t.addCell(c2);
                c2=new PdfPCell(new Paragraph(part.get(i), textfont));
                t.addCell(c2);
                c2=new PdfPCell(new Paragraph(description.get(i), textfont));
                t.addCell(c2);
                c2=new PdfPCell(new Paragraph(origin.get(i), textfont));
                t.addCell(c2);
            }
            document.add(t);

            if(j+1==totalPage){

                Paragraph foot11 = new Paragraph("文件只作  Foster 收貨用"+printBlank(150)+"__________________________",keyfont);
                document.add(foot11);
                Paragraph foot12 = new Paragraph("Printed from Foster supplier portal"+printBlank(134)+company+printBlank(40)+"版本: 1.0",keyfont);
                document.add(foot12);

            }
        }
        document.close();
    }

    public static String leftPad(String str, int i) {
        int addSpaceNo = i-str.length();
        String space = "";
        for (int k=0; k<addSpaceNo; k++){
            space= " "+space;
        };
        String result =space + str ;
        return result;
    }

    public static void add(List<String> list,int num){
        for(int i=0;i<num;i++){
            list.add("test"+i);
        }
    }

    public static String printBlank(int tmp){
        String space="";
        for(int m=0;m<tmp;m++){
            space=space+" ";
        }
        return space;
    }

}
