package model;

public class Livro {
    private static int proximoId = 1;

    private final int id;
    private String titulo;
    private int nota;
    private String descricao;
    private boolean lido;

    public Livro(String titulo) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.nota = -1;
        this.descricao = "";
        this.lido = false;
    }

    public void marcarComoLido(int nota, String descricao) {
        this.lido = true;
        this.nota = nota;
        this.descricao = descricao;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getNota() {
        return nota;
    }
    public String getDescricao() {
        return descricao;
    }
    public boolean isLido() {
        return lido;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID " + id + ": " + titulo + (lido ? " (Nota: " + nota + "| Descrição: " + descricao + ")" : " [Desejo]");
    }
}
