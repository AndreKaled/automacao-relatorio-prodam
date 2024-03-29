package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class MainInterface extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea textArea;
    private JButton abrirButton, confirmarButton;

    public MainInterface() {
        super("Selecionar e Processar PDF");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria a área de texto
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Cria o botão para abrir o seletor de arquivos
        abrirButton = new JButton("Selecionar PDF");
        abrirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(MainInterface.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textArea.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Cria o botão de confirmar
        confirmarButton = new JButton("Processar PDF");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caminhoArquivo = textArea.getText();
                if (!caminhoArquivo.isEmpty()) {
                    try {
                        // Extrair texto do PDF usando PDFBox
                        Path path = Paths.get(caminhoArquivo);
                        File file = path.toFile();
                        PDDocument document = PDDocument.load(file);
                        PDFTextStripper stripper = new PDFTextStripper();
                        String text = stripper.getText(document);
                        System.out.println("Caminho do arquivo PDF: " + caminhoArquivo);
                        System.out.println("---------\n" + text);
                        document.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.out.println("Erro ao processar o PDF.");
                    }
                } else {
                    System.out.println("Nenhum arquivo PDF selecionado.");
                }
            }
        });

        // Adiciona os componentes ao painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(abrirButton, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(confirmarButton, BorderLayout.SOUTH);

        // Configura o frame
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 300));
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }
}
