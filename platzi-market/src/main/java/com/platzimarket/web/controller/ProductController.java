package com.platzimarket.web.controller;

import com.platzimarket.domain.Product;
import com.platzimarket.domain.repository.ProductRepository;
import com.platzimarket.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//LE INDICA QUE ESTA CLASE VA A HACER UN CONTROLADOR DE UNA API REST
@RestController

//recibe como parametro la peticion -- https://.../localhost/products
@RequestMapping("/products")
public class ProductController {

    //Podemos usarlo porque produtService tiene una anotacion de Spring.
    @Autowired
    private ProductService productService;

    //getMapping porque estamos obteniendo informacion de productos
    @GetMapping("/all")
    //CAMBIO CON EL NUEVO SWAGGER OPEN..nose que cosa
    @Operation(summary = "Get all supermarket products")
    @ApiResponse(responseCode = "200" , description = "OK")
    //le indicamos que no retorne esta lista de productos , sino que que responda a un responsiveEntity
    public ResponseEntity<List<Product>>  getAll(){
        //EL PRIMER PARAMETRO ES EL CUERPO DE LA RESPUESTA
        //EL SEGUNDO PARAMETRO ES EL HTTPSTATUS , DONDE LE INDICAMOS QUE LA PETICION RESPONDIO DE MANERA ADECUADA CUANDO FUE LLAMADA
        return new ResponseEntity<>(productService.getAll() , HttpStatus.OK);
    }



    //utilizamos las llaves porque al parametro  productId le añadimos una anotacion.
    @GetMapping("/{id}")
    @Operation(summary = "Search a product with an ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200" , description = "OK"),
            @ApiResponse(responseCode = "404" , description = "Product not found"),

    })
    public ResponseEntity<Product> getProduct(@Parameter(description = "The id of the product" , required = true , example = "7")@PathVariable("id") int productId){
        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product , HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products , HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //Este se va a guardar por eso usamos post
    @PostMapping("/save")
    //Como el producto no va a viajar dentro del path sino que va a hacer parte del cuerpo de la peticion
    //le añadimos @RequestBody
    public ResponseEntity<Product> save(@RequestBody  Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }


    //Este se va a borrar por eso usamos @DeleteMapping
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
       if(productService.delete(productId)){
           return new ResponseEntity(HttpStatus.OK);
       }else {
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
    }
}
