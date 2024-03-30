package process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Pessoa;

public class Principal {

	static List<String> presentes = new ArrayList<>();
	public static String nomeArquivo = "excel";
	public static String nomeTabela = "Protocolos";

	public static void main(String[] args) {
		gerarExcel(TratadorPDF.pessoas);
	}

	static void realizarPresenca(String[] linhas){
		int i = 0; 

		while(i < linhas.length){
			for(Pessoa p : TratadorPDF.pessoas){
				if(p.getCpf().equals(linhas[i])){
					p.setPresente(1);
				}
			}
			i++;
		}
	}

	static String investigarServico(String servico, String val) {
		// TODO Auto-generated method stub
		if(servico.equalsIgnoreCase("R") && val.equalsIgnoreCase("333,58")) {
			servico = "Renovação Remunerada.";
		}else if(servico.equalsIgnoreCase("R") && val.equalsIgnoreCase("184,03")){
			servico = "Renovação";
		}else if(servico.equalsIgnoreCase("1")) {
			servico = "1° Habilitação";
		}else if(servico.equalsIgnoreCase("T") && val.equalsIgnoreCase("333,58")) {
			servico = "Troca de cat. remunerada";
		}else if(servico.equalsIgnoreCase("T") && val.equalsIgnoreCase("184,03")) {
			servico = "Troca de Cat.";
		}else if(servico.equalsIgnoreCase("2")) {
			servico = "2° Via";
		}
		
		return servico;
	}

	static void gerarExcel(List<Pessoa> pessoas) {
		String[] colunas = {"Presente","Nome", "Serviço", "Valor", "Data"};
		Workbook work = new XSSFWorkbook();
		CreationHelper create = work.getCreationHelper();
		Sheet sheet = work.createSheet(nomeTabela);
		
		Font headerFont = work.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.GREEN.getIndex());
		
		// Create a CellStyle with the font
        CellStyle headerCellStyle = work.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
            cell.setCellStyle(headerCellStyle);
        }

        
     // Create Cell Style for formatting Date
        CellStyle dateCellStyle = work.createCellStyle();
        dateCellStyle.setDataFormat(create.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Pessoa p : pessoas) {
            Row row = sheet.createRow(rowNum++);
    		row.createCell(0).setCellValue(p.getPresente());
		
            row.createCell(1)
                    .setCellValue(p.getNome());

            row.createCell(2)
                    .setCellValue(p.getServico());

            row.createCell(3).setCellValue(p.getValor());

            row.createCell(4)
                    .setCellValue(p.getData());         
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(nomeArquivo  +".xlsx");
			work.write(fileOut);
	        fileOut.close();
	     // Closing the workbook
	        work.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
