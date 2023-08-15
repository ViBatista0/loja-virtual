package com.lojadediscos.model;

public class Produto {

    private Integer id;
    private String nome;
    private String marca;

    public Produto(Integer id, String nome, String marca) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
    }

    public Produto(String nome, String marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return String.format("O produto é: %d, %s, %s", this.id, this.nome, this.marca);
    }
}
