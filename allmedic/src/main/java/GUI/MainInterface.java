package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import process.TratadorPDF;

public class MainInterface extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea textArea;
    private JButton abrirButton, confirmarButton;
    private PresencaInterface presencaInterface = new PresencaInterface();
    private RenomearArquivoInterface renomearArquivoInterface = new RenomearArquivoInterface();
    static CardLayout card = new CardLayout();
    static JPanel mainPanel = new JPanel(card);

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
                    confirmarButton.setEnabled(true);
                }
            }
        });

        // Cria o botão de confirmar
        confirmarButton = new JButton("Processar PDF");
        confirmarButton.setEnabled(false);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caminhoArquivo = textArea.getText();
                if (!caminhoArquivo.isEmpty()) {
                    try {
                        TratadorPDF t = new TratadorPDF();
                        t.LerPDF(caminhoArquivo);
                        t.gerarExcel();
                        confirmarButton.setEnabled(false);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }else{
                    System.out.println("Nenhum pdf encontrado.");
                }
            }
        });

        Button presencaButton = new Button("Presença");
        presencaButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                card.show(mainPanel, "Presenca");
                revalidate();
                repaint();
            }
            
        });

        Button renomearButton = new Button("Renomear");
        renomearButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                card.show(mainPanel, "Renomear");
                revalidate();
                repaint();
            }
            
        });

        JPanel initPanel = new JPanel(new BorderLayout());
        // Adiciona os componentes ao painel principal
        initPanel.add(abrirButton, BorderLayout.NORTH);
        initPanel.add(scrollPane, BorderLayout.CENTER);
        initPanel.add(confirmarButton, BorderLayout.SOUTH);
        initPanel.add(presencaButton, BorderLayout.EAST);
        initPanel.add(renomearButton,BorderLayout.WEST);

        //gerenciando cardLayout
        add(mainPanel);
        mainPanel.add(initPanel, "Principal");
        mainPanel.add(presencaInterface, "Presenca");
        mainPanel.add(renomearArquivoInterface, "Renomear");


        // Configura o frame
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
