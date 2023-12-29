package com.platzimarket.domain.repository;

import com.platzimarket.domain.Product;

import java.util.List;
import java.util.Optional;

//creamos este repositorio para indicarle a todos los demas repositorios como debe
// comportarse en terminos de producto.
//indicamos el nombre de los metodos que queremos que cualquier repositorio
//que vaya a trabajar con productos tenga que implementar.
public interface ProductRepository {

    //Es similar a la clase ProductoRepository , pero esa clase va a implementar
    //esta clase que estamos contruyendo para que hable en terminos del dominio (Product)
    // y no de la tabla (Producto) que es la tabla a la que estamos haciendo referencia en la BD.
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quatity);
    Optional<Product> getProduct(int ProductId);
    Product save(Product product);
    void delete(int productId);

    //definimos las reglas de nuestro dominio cuando cualquier repositorio quiera utilizar o acceder a productos
    // de una BD , esto nos permite a poder ser mas flexibles a cambiar de BD , siempre estar hablando en terminos
    // del dominio (Product).
}
