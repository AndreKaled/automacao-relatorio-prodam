package process;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import model.Pessoa;

public class TratadorPDF {

    static ArrayList<String> linhasInteresse = new ArrayList<>();
    Principal principal = new Principal();
    static List<Pessoa> pessoas = new ArrayList<>();

    public void LerPDF(String caminhoArquivo) throws Exception{
        // Extrair texto do PDF usando PDFBox
            Path path = Paths.get(caminhoArquivo);
            File file = path.toFile();
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] linhas = text.split("\n");

            System.out.println("Caminho do arquivo PDF: " + caminhoArquivo);
            
            //começa a ler na linha 11
            System.out.println("---------\n");

            for(int i=11; i < linhas.length; i++){
                    if(linhas[i].length()>9){
                        if(linhas[i].startsWith("DETRAN-AM")||
                            linhas[i].startsWith("AV. MÁRIO")||
                            linhas[i].startsWith(" CLINICA")||
                            linhas[i].contains("Protocolo")||
                            linhas[i].startsWith(" ------")||
                            linhas[i].startsWith("Página")||
                            linhas[i].contains("P1")||
                            linhas[i].contains("PAG.")||linhas[i].contains("TOTAL")){
                            continue;
                        }
                    }
                    linhas[i] = linhas[i].trim();
                            
                    if(linhas[i].isBlank()){
                        continue;
                    }
                    linhasInteresse.add(linhas[i]);
            }
            System.out.println("PDF Lido");
        document.close();
        criarClasses();
    }

    public ArrayList<String> ExtrairDados(){
        return linhasInteresse;
    }

    public List<Pessoa> criarClasses(){
        String data="", serv="", cpf = "", val ="";

        for(int i= 0; i < linhasInteresse.size(); i++){
            String linha = linhasInteresse.get(i);

            //linhas pares da extração
            if(i % 2 == 0){
                data = linha.substring(0, 10);
                serv = linha.substring(16, 17); 
                cpf = linha.substring(25, 36);
                val = linha.substring(36+27);
                val = val.trim();
                serv = principal.investigarServico(serv, val);
            }else{
                //linhas impares, só possui os nomes
                String nome = linha.trim();
                Pessoa pessoa = new Pessoa(nome, cpf, 0, serv, val, data);
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }
    public void gerarExcel(){
        principal.main(null);
    }

    public void receberPresenca(String[] linhas){
        principal.realizarPresenca(linhas);
        principal.main(null);
    }
}
