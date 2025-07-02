package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();

    public void adicionarLivro(String titulo) {
        if (!tituloExiste(titulo)) {
            livros.add(new Livro(titulo));
        }
    }

    private boolean tituloExiste(String titulo) {
        return livros.stream().anyMatch(l -> l.getTitulo().equalsIgnoreCase(titulo));
    }

    public void marcarComoLido(int id, int nota, String descricao) {
        Livro l = encontrarPorId(id);
        if (l != null && !l.isLido()) {
            l.marcarComoLido(nota, descricao);
        }
    }

    public Livro encontrarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    public void editarLivro(int id, String novoTitulo, int novaNota, String novaDescricao) {
        Livro l = encontrarPorId(id);
        if (l != null) {
            l.setTitulo(novoTitulo);
            if (l.isLido()) {
                l.setNota(novaNota);
                l.setDescricao(novaDescricao);
            }
        }
    }

    public void removerLivro(int id) {
        livros.removeIf(l -> l.getId() == id);
    }

    public List<Livro> getDesejados() {
        return livros.stream().filter(l -> !l.isLido()).toList();
    }

    public List<Livro> getLidos() {
        return livros.stream().filter(Livro::isLido).toList();
    }
}
