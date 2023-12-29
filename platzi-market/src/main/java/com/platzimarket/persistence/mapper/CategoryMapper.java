package com.platzimarket.persistence.mapper;


import com.platzimarket.domain.Category;
import com.platzimarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//le indicamos que es un mapeador

//componente de tipo Spring
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Nos permite hacer muchos mapeos
    @Mappings({
            //Hace el mapeo
            // nos permite llevar desde una fuente a un destino ,
            //desde una entidad (atributos) a un data mapper (atributos)
        @Mapping(source = "idCategoria" , target = "categoryId"),
        @Mapping(source = "descripcion" , target = "category"),
        @Mapping(source = "estado" , target = "active")
    })
    Category toCategory(Categoria categoria);

    //le dice a mapStruct que la conversion es invertida , es decir de data mapper a entidad
    @InheritInverseConfiguration
    //como en la entidad categoria no tenemos productos u otra variable , no lo podemos mapear (convertir)
    //es por eso que se coloca el ignore , para ignorarlo.
    @Mapping(target = "productos" , ignore = true)
    Categoria toCategoria(Category category);
}
