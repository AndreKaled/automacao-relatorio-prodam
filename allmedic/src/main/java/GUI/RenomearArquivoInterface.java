package GUI;

import javax.swing.*;

import process.Principal;

import java.awt.*;

public class RenomearArquivoInterface extends JPanel {

    private JTextField nomeArquivoTextField;
    private JTextField nomeTabelaTextField;
    private JButton confirmarButton;
    private JButton voltarButton;

    public RenomearArquivoInterface() {
        // Configurações do painel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criação dos rótulos
        JLabel nomeArquivoLabel = new JLabel("Nome do Arquivo:");
        JLabel nomeTabelaLabel = new JLabel("Nome da Tabela:");

        // Criação dos campos de texto
        nomeArquivoTextField = new JTextField(20);
        nomeArquivoTextField.setText(new Principal().nomeArquivo);
    
        nomeTabelaTextField = new JTextField(20);
        nomeTabelaTextField.setText(new Principal().nomeTabela);

        // Criação dos botões
        confirmarButton = new JButton("Confirmar");
        voltarButton = new JButton("Voltar");

        // Criação do painel central
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());

        // Adição dos componentes ao painel central
        GridBagConstraints gbc = new GridBagConstraints();

        // Linha 1: Rótulo e campo de texto do nome do arquivo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        centralPanel.add(nomeArquivoLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centralPanel.add(nomeArquivoTextField, gbc);

        // Linha 2: Rótulo e campo de texto do nome da tabela
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        centralPanel.add(nomeTabelaLabel, gbc);

        gbc.gridx = 1;
        centralPanel.add(nomeTabelaTextField, gbc);

        // Adição dos painéis ao painel principal
        add(centralPanel, BorderLayout.CENTER);

        // Criação do painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmarButton);
        buttonPanel.add(voltarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Ações dos botões
        confirmarButton.addActionListener(e -> {
            // Código para renomear o arquivo e a tabela
            new Principal().nomeArquivo = nomeArquivoTextField.getText().toString();
            new Principal().nomeTabela = nomeTabelaTextField.getText().toString();
            System.out.println(new Principal().nomeArquivo +"|" +new Principal().nomeTabela);
            voltar();
        });

        voltarButton.addActionListener(e -> {
            // Código para voltar para a tela anterior
            voltar();
        });

        // Redimensionamento automático dos campos de texto
        nomeArquivoTextField.setMinimumSize(nomeArquivoTextField.getPreferredSize());
        nomeTabelaTextField.setMinimumSize(nomeTabelaTextField.getPreferredSize());
    }

    private void voltar(){
        MainInterface.card.show(MainInterface.mainPanel, "Principal");
        revalidate();
        repaint();
    }

    // Métodos para obter os valores dos campos de texto
    public String getNomeArquivo() {
        return nomeArquivoTextField.getText();
    }

    public String getNomeTabela() {
        return nomeTabelaTextField.getText();
    }
}
