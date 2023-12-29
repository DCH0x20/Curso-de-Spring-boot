package com.platzimarket.domain.service;


import com.platzimarket.domain.Product;
import com.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//cuando creamos un servicio le agregamos siempre la notacion Service
//podemos usar @component pero por semantica es mejor usar Service
//En este servicio va a inyectar el ProductRepository
@Service
public class ProductService {

    //Es la interface

    //para que sepa que debe hacer , le agregamos autowired
    // inicializa un nuevo ProductoRepository que es la clase que en realidad esta implementada
    // colocamos autowired porque la clase ProductoRepository es la que se va a utilizar y es
    //un componente de Spring .
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);

        /*Otra forma de Hacerlo
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        }else {
            return false;
        }
        */

    }
}
