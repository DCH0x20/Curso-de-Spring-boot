package com.platzimarket.persistence.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="productos")
public class Producto {
    //no utilizar int sino el valor empaquetado , es decir Integer
    @Id
    //se genera automaticamente - se autoincrementa , la estrategy permite que java lo autoincremente al igual que en la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;

    //como no vamos a cambiar de como esta en la clase a como esta en la tabla no le agregamos @Column
    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name =  "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //asi hacemos las relaciones entre tablas
    //no vamos a borrar ni insertar , ni actualizar una categoria , si queremos hacerlo , lo hacemos en el Entity categoria.
    //solo nos sirve para recuperar a que categoria nos sirve un producto.
    @ManyToOne
    @JoinColumn(name = "id_categoria" ,insertable = false , updatable = false)
    private Categoria categoria;


/*
    -- esta relacion no genera mucho valor
    -- por lo tanto no se agregara , debemos hacer las relaciones que creamos necesarias
    -- para poder mejorar el rendimiento de nuestra aplicacion.
    @OneToMany(mappedBy = "producto")
    private List<ComprasProducto> compras;
    */


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}
