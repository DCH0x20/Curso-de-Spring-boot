package com.platzimarket.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


//cuando tenemos una llave compuesta hacemos otra clase y lo contenemos en otra clase que hace el mapeo
@Embeddable
public class ComprasProductoPK implements Serializable {
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }


}
