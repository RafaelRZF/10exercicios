package org.example.models;

public class PostCategoria {
    Posts postID;
    Categorias categoriaID;

    public PostCategoria() {
        this.postID = new Posts();
        this.categoriaID = new Categorias();
    }
}
