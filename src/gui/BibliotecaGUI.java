package gui;

import model.Biblioteca;
import model.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class BibliotecaGUI extends JFrame {
    private final Biblioteca biblioteca = new Biblioteca();
    private final JTextArea outputArea = new JTextArea(15, 40);

    private void atualizarListas() {
        StringBuilder sb = new StringBuilder();

        sb.append("📚 Lista de Desejos:\n");
        for (Livro l : biblioteca.getDesejados()) {
            sb.append(l.toString()).append("\n");
        }

        sb.append("\n✅ Livros Lidos:\n");
        for (Livro l : biblioteca.getLidos()) {
            sb.append(l.toString()).append("\n");
        }

        outputArea.setText(sb.toString());
    }


    public BibliotecaGUI() {
        super("Biblioteca de Leitura");
        setLayout(new BorderLayout());
        outputArea.setEditable(false);

        JPanel botoes = new JPanel(new GridLayout(0, 1, 5, 5));

        JButton addBtn = new JButton("Adicionar Livro");
        JButton lerBtn = new JButton("Marcar como Lido");
        JButton verDesejos = new JButton("Ver Desejados");
        JButton verLidos = new JButton("Ver Lidos");
        JButton editarBtn = new JButton("Editar Livro");
        JButton removerBtn = new JButton("Remover Livro");

        botoes.add(addBtn);
        botoes.add(lerBtn);
        botoes.add(verDesejos);
        botoes.add(verLidos);
        botoes.add(editarBtn);
        botoes.add(removerBtn);

        add(botoes, BorderLayout.WEST);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Listeners
        addBtn.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog("Título do livro:");
            if (titulo != null && !titulo.isBlank()) {
                biblioteca.adicionarLivro(titulo.trim());
                atualizarListas();
            }
        });

        lerBtn.addActionListener(e -> {
            try {
                int nota;
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID do livro a marcar como lido:"));
                if(biblioteca.encontrarPorId(id)==null || biblioteca.encontrarPorId(id).isLido()){
                    throw null;
                }
                do {
                     nota = Integer.parseInt(JOptionPane.showInputDialog("Nota (0-5):"));
                } while (nota<0 || nota>5);
                String desc = JOptionPane.showInputDialog("Descrição (opcional):");
                biblioteca.marcarComoLido(id, nota, desc != null ? desc : "");
                atualizarListas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao marcar um livro como lido");
            }
        });

        verDesejos.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("📚 Lista de Desejos:\n");
            for (Livro l : biblioteca.getDesejados()) sb.append(l).append("\n");
            outputArea.setText(sb.toString());
        });

        verLidos.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("✅ Livros Lidos:\n");
            for (Livro l : biblioteca.getLidos()) sb.append(l).append("\n");
            outputArea.setText(sb.toString());
        });

        editarBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID do livro a editar:"));
                Livro l = biblioteca.encontrarPorId(id);
                if (l == null) {
                    throw null;
                }
                String novoTitulo = JOptionPane.showInputDialog("Novo título:", l.getTitulo());
                if(l.isLido()){
                    int novaNota;
                    do{
                        novaNota = l.isLido() ? Integer.parseInt(JOptionPane.showInputDialog("Nova nota:", l.getNota())) : -1;
                    } while (novaNota<0 || novaNota>5);

                    String novaDesc = l.isLido() ? JOptionPane.showInputDialog("Nova descrição:", l.getDescricao()) : "";
                    biblioteca.editarLivro(id, novoTitulo, novaNota, novaDesc);
                } else {
                    biblioteca.editarLivro(id, novoTitulo, -1, "");
                }
                atualizarListas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar livro.");
            }
        });

        removerBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID do livro a remover:"));
                if(biblioteca.encontrarPorId(id)==null){
                    throw null;
                }
                biblioteca.removerLivro(id);
                atualizarListas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover livro.");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
