package com.platzimarket.persistence.mapper;

import com.platzimarket.domain.Purchase;
import com.platzimarket.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {PuchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "idCompra" , target = "purchaseId"),
            @Mapping(source = "idCliente" , target = "clienteId"),
            @Mapping(source = "fecha" , target = "date"),
            @Mapping(source = "medioPago" , target = "paymentMethod"),
            @Mapping(source = "comentario" , target = "comment"),
            @Mapping(source = "estado" , target = "state"),
            //Este mapping lo utiliza purchaseItemMapper
            @Mapping(source = "productos" , target = "items")
    })
    Purchase toPurchase(Compra compra);

    //Esta Lista tambien adquieren los mappers anteriores.
    List<Purchase> toPurchases(List<Compra> compras);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target =  "cliente" , ignore = true)
    })

    Compra toCompra(Purchase purchase);
}
