package com.platzimarket.persistence;

//Encargado de interactuar con la tabla de productos con la Base de Datos
import com.platzimarket.domain.Product;
import com.platzimarket.domain.repository.ProductRepository;
import com.platzimarket.persistence.crud.ProductoCrudRepository;
import com.platzimarket.persistence.entity.Producto;
import com.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* Le indicamos a Spring que esta clase esta interactuando con la BD , podemos usar component ,
donde le indicamos que es un componente de Spring (es una generalizacion) , pero queda mucho mejor con Repository ya que
le estamos indicando que tipo de componente de Spring es (Repositorio).
*/
@Repository

public class ProductoRepository implements ProductRepository {

    //cuando escribimos autowired le damos el control a spring para que cree las instancias de productoCrudRepository
    //mapper , etc
    //tenemos que estar 100% seguro de que el objeto o atributo que vamos a inyectar es un componente de Sptring
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    //al hacer el implements ProductRepository se heredan ciertos metodos
    //de la interface ,  para hacer la conversion de producto a products ,
    //tenemos que llamar el atributo de productMapper

    @Autowired
    private ProductMapper mapper;


    //metodo para obetener todos los productos del supermercado
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quatity) {
        Optional<List<Producto>> productos= productoCrudRepository.findBycantidadStockLessThanAndEstado(quatity , true);
        //el .map retorna un optional del arrow function
        //la arrow function recibe los productos que tiene adentro y los convierte en products y los retorna
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        //findBy ya devuelve un optional
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct( productoCrudRepository.save(producto));
    }


    //No necesitamos declarar el metodo en la interface porque nos lo proporciona CrudRepository


    //insertamos un producto en la BD

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

    //AHORA SI TODO ESTA ENFOCADO AL DOMINIO
}
