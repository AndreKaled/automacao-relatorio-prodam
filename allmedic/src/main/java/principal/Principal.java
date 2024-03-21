package principal;

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

	static List<Pessoa> pessoas = new ArrayList<>();
	private static String nomeArquivo = "excel";
	private static String nomeTabela = "Protocolos";
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		while(true){
			System.out.println("1 --- Receber dados da lista");
			System.out.println("2 --- Receber presença");
			System.out.println("3 --- Renomear arquivo");
			System.out.println("4 --- Gerar planilha e finalizar");
			
			System.out.print(">");
			String op = s.next();
			switch(op.charAt(0)) {
			case '1':
				System.out.print(">");
				escreverLista();
				break;
			case '2':
				escreverPresenca();
				break;
			case '3':
				renomearArquivo();
			case '4':
				gerarExcel(pessoas);
				fechar();
				break;
			default:
				System.out.println("Comando inválido");
				break;
			}
		}
	}

	private static void fechar() {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

	private static void renomearArquivo() {
		
		
		// TODO Auto-generated method stub
		while(true){
			System.out.println("Nome do arquivo atual:" +nomeArquivo);
			System.out.println("Nome da tabela atual: " +nomeTabela );
			System.out.println("--------------------------------------");
	
			System.out.println(" 1 ------- Renomear arquivo");
			System.out.println(" 2 ------- Renomear Tabela");
			System.out.println(" 3 ------- Voltar ao menu");
	
			System.out.print(">");
			String op = s.nextLine();
			if(op.charAt(0)=='1') {
				System.out.println("Insira o novo nome do arquivo.");
				System.out.println(">");
				nomeArquivo = s.nextLine();
			}else if(op.charAt(0)=='2') {
				System.out.println("Insira o novo nome da tabela.");
				System.out.println(">");
				nomeTabela = s.nextLine();
			}else if(op.charAt(0)=='3') {
				break;
			}
		}
		
	}

	private static void escreverPresenca() {
		// TODO Auto-generated method stub
		System.out.println("Escreva todos os nomes dos presentes (nomes incompletos ou com caracteres diferentes não serão considerados).");
		System.out.println("Digite ? para finalizar.");;
		System.out.print(">");
		List<String> nomes = new ArrayList<>();
		s.nextLine();
		while(true) {
			String nome = s.nextLine();
			if(nome.charAt(0) != '?') {
				nome = tirarAcentos(nome);
				nomes.add(nome);
				continue;
			}else {
				break;
			}
		}
		System.out.println("Comparando nomes...");
		for(Pessoa p: pessoas) {
			for(String nome: nomes) {
				if(p.getNome().equalsIgnoreCase(nome)) {
					p.setPresente(true);
				}
			}
		}
		
		System.out.println("Finalizado");
	}

	private static String tirarAcentos(String nome) {
		nome = nome.toLowerCase();
		// TODO Auto-generated method stub
		nome = nome.replace("à", "a");
		nome = nome.replace("á", "a");
		nome = nome.replace("â", "a");
		nome = nome.replace("ã", "a");
		nome = nome.replace("ä", "a");
		
		nome = nome.replace("é", "e");
		nome = nome.replace("è", "e");
		nome = nome.replace("ê", "e");
		nome = nome.replace("ë", "e");

		nome = nome.replace("í", "i");
		nome = nome.replace("ì", "i");
		nome = nome.replace("î", "i");
		nome = nome.replace("ï", "i");
		
		nome = nome.replace("ô", "o");
		nome = nome.replace("õ", "o");
		nome = nome.replace("ó", "o");
		nome = nome.replace("ò", "o");
		nome = nome.replace("ö", "o");
		
		nome = nome.replace("û", "u");
		nome = nome.replace("ú", "u");
		nome = nome.replace("ù", "u");
		nome = nome.replace("ü", "u");
		
		nome = nome.replace("ç", "c");
		
		
		return nome;
	}

	private static void escreverLista() {
		String data,servico,bla,cpf,pag,val,nome,proxDado = null;
		
		while(true) {
			if(proxDado != null) {
				data = proxDado;
				proxDado = null;
			}else {
				data = s.next();
			}
			if(data.charAt(0)=='?'){
				break;
			}
			servico = s.next();
			cpf = s.next();
			pag = s.next();
			bla = s.next();
			val = s.next();
			nome = s.next();
			if(!Character.isUpperCase(nome.charAt(0))) {
				val = nome;
				nome = s.next();
			}
			while (true) {
			    proxDado = s.next();
			    if (Character.isUpperCase(proxDado.charAt(0))) {
			        StringBuilder sb = new StringBuilder();
			        sb.append(nome).append(" ").append(proxDado);
			        nome = sb.toString();
			    } else if (proxDado.charAt(0) == ' ' || !Character.isLetter(proxDado.charAt(0))) {
			        break;
			    }else {
			    	break;
			    }
			}
			
			nome.trim();
			servico.trim();
			data.trim();
			cpf.trim();
			
			servico = investigarServico(servico, val);
			data = formataData(data);
			
			Pessoa p = new Pessoa();
			p.setNome(nome);
			p.setPresente(false);
			p.setServico(servico);
			p.setValor(val);
			p.setData(data);
			pag = null;
			
			pessoas.add(p);
					
		}
		
		
		System.out.println("FIM");
	}
	
	private static String formataData(String data) {
		// TODO Auto-generated method stub
		data = data.substring(0, 10);
		return data;
	}

	private static String investigarServico(String servico, String val) {
		// TODO Auto-generated method stub
		if(servico.equalsIgnoreCase("Renovaca") && val.equalsIgnoreCase("333,58")) {
			servico = "Renovação Remunerada.";
		}else if(servico.equalsIgnoreCase("Renovaca") && val.equalsIgnoreCase("184,03")){
			servico = "Renovação";
		}else if(servico.equalsIgnoreCase("1a.")) {
			servico = "1° Habilitação";
		}else if(servico.equalsIgnoreCase("Troca") && val.equalsIgnoreCase("333,58")) {
			servico = "Troca de cat. remunerada";
		}else if(servico.equalsIgnoreCase("Troca") && val.equalsIgnoreCase("184,03")) {
			servico = "Troca de Cat.";
		}else if(servico.equalsIgnoreCase("2a.via")) {
			servico = "2° Via";
		}
		
		return servico;
	}

	private static void gerarExcel(List<Pessoa> pessoas) {
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

		if(p.isPresente()){
            		row.createCell(0).setCellValue(true);
		}else{
			row.createCell(0).setCellValue(false);
		}
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
