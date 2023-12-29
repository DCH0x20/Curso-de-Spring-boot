package com.platzimarket.persistence.mapper;

import com.platzimarket.domain.Product;
import com.platzimarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//le decimos que cuanto convierta categoria en category , tiene que usar CategoryMapper.class
//aunque mapper es un componente de mapperStruct , le decimos que tambien es un componente de Spring para poder
//utilizar la inyeccion de dependencias.
@Mapper(componentModel = "spring" , uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto" , target = "productId"),
            @Mapping(source = "nombre" , target = "name"),
            @Mapping(source = "idCategoria" , target = "categoryId"),
            @Mapping(source = "precioVenta" , target = "price"),
            @Mapping(source = "cantidadStock" , target = "stock"),
            @Mapping(source = "estado" , target = "active"),
            @Mapping(source = "categoria" , target = "category")
    })
    Product toProduct(Producto producto);

    //no es necesario colocar mapping porque ya entiende que se debe comportar con los mappings anteriores porque es el mismo tipo de conversion.
    List<Product> toProducts(List<Producto> productos);

    //ignorar codigo de barras
    @InheritInverseConfiguration
    @Mapping( target = "codigoBarras" , ignore = true)
    Producto toProducto(Product product);
}
