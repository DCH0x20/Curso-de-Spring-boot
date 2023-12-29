package com.platzimarket.persistence.mapper;

import com.platzimarket.domain.PurchaseItem;
import com.platzimarket.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" , uses = {ProductMapper.class})
public interface PuchaseItemMapper {
    @Mappings({
            //es una llave compuesta dentro de ComprasProdutoPK es por ello que lo colocamos asi
            @Mapping(source = "id.idProducto" , target = "productId"),
            @Mapping(source = "cantidad" , target = "quantity"),
            // como los atributos se llaman igual no es necesario colocar : @Mapping(source = "total" , target = "total"),
            @Mapping(source = "estado" , target = "active")

    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra" , ignore = true),
            @Mapping(target = "producto" , ignore = true),
            @Mapping(target = "id.idCompra" , ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
