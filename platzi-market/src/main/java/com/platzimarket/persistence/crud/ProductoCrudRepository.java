package com.platzimarket.persistence.crud;

import com.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//el CrudRepository tiene dos parametros la tabla(Clase de java) y el tipo de dato de esa tabla(Clase).
public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {

    //@Query(value = "SELECT * FROM productos WHERE id_Categoria = ?" , nativeQuery = true)
    //si usamos @Query , ya no es necesario que sigamos la regla en el metodo de querymethods
    //si modifico algo aca , tambien lo hago en el Producto.Repository

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findBycantidadStockLessThanAndEstado(int cantidadStock , boolean estado);

}
