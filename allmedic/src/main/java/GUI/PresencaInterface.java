package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import process.Principal;
import process.TratadorPDF;

public class PresencaInterface extends JPanel{
    private JTextArea textArea;
    private JButton confirmarButton;
    private JButton cancelarButton;

    PresencaInterface(){
        setLayout(new BorderLayout());

        // Cria a área de texto com scroll interno
        textArea = new JTextArea();
        // Envolve a textArea em um ScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Configurações da área de texto
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Cria os botões
        confirmarButton = new JButton("Confirmar Presença");
        cancelarButton = new JButton("Cancelar");

        // Adiciona os botões ao painel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmarButton);
        buttonPanel.add(cancelarButton);

        // Adiciona os componentes ao painel principal
        add(scrollPane, BorderLayout.CENTER); // Adiciona o scrollPane com a textArea
        add(buttonPanel, BorderLayout.SOUTH);
        // Ações dos botões
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para confirmar presença
                String[] linhas = textArea.getText().toString().split("\n");
                new TratadorPDF().receberPresenca(linhas);
                voltar();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }

    private void voltar(){
        MainInterface.card.show(MainInterface.mainPanel, "Principal");
        revalidate();
        repaint();
    }
}
