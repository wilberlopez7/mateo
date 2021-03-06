package inventario
import general.*

class Producto {//implements java.io.Serializable {
    String codigo 
    //String sku //it already has codigo, also nombre should be diferent in each
    //Stock-keeping unit, used to identify each unique product or item for sale in a store or other business.
    String nombre
    String descripcion
    String marca
    String modelo
    /*BigDecimal precioUnitario = new BigDecimal("0.00")
    BigDecimal ultimoPrecio = new BigDecimal("0.00")
    BigDecimal existencia = new BigDecimal("0.00")
    BigDecimal puntoReorden = new BigDecimal("0.00")*/ //cuestiones de contabilidad
    //Integer tiempoEntrega = 3 //?
    String unidadMedida = "Unidades"
    Boolean fraccion = false //supongo que significa si se puede fraccionar el producto o no
    BigDecimal iva = new BigDecimal("0.16")
    //String ubicacion //ubicación del producto??
    Almacen almacen //en qué almacen hay o está
    TipoProducto tipoProducto
    Date dateCreated 
    Date lastUpdated
    Long entradaId
    Long salidaId
    Set lotesEntrada //cuantas veces ha entrado 
    Set lotesSalida //cuantas veces ha salido
    Set imagenes //imagen del producto

    //static transients = ["entradaId","salidaId"]

    static belongsTo = [TipoProducto, Almacen]

    static hasMany = [lotesSalida: LoteSalida, lotesEntrada: LoteEntrada, imagenes: Imagen]

    static constraints = {
        codigo(maxSize:6,unique:'almacen',blank:false)
        //sku(maxSize:64,unique:'almacen',blank:false)
        nombre(maxSize:128,unique:'almacen',blank:false)
        descripcion(nullable:true,maxSize:254,blank:false)
        marca(nullable:true,maxSize:32)
        modelo(nullable:true,maxSize:32)
        //precioUnitario(scale:2,precision:8,min:new BigDecimal("0"))
        //existencia(scale:3,precision:8,min:new BigDecimal("0"))
        //puntoReorden(scale:0,precision:0,min:new BigDecimal("0"))
        unidadMedida(maxSize:16,nullable:true)
        iva(scale:2,precision:8,size:new BigDecimal("0")..new BigDecimal("1"))
        //ubicacion(nullable:true,maxSize:64)
    }

    static mapping = {
        table 'productos'
        imagenes cascade:'all-delete-orphan'
        codigo index:'producto_codigo_idx'
        sku index:'producto_sku_idx'
        nombre index:'producto_nombre_idx'
        descripcion index:'producto_descripcion_idx'
    }

    static namedQueries = {
        buscaPorFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike 'codigo', filtro
                ilike 'sku', filtro
                ilike 'nombre', filtro
                ilike 'descripcion', filtro
                ilike 'marca', filtro
                ilike 'modelo', filtro
                ilike 'ubicacion', filtro
                tipoProducto {
                    ilike 'nombre', filtro
                }
            }
        }

        buscaPorAlmacen { almacenId ->
            almacen {
                idEq(almacenId)
            }
        }
        
        buscaPorCodigo { filtro ->
            filtro = "%$filtro%"
            ilike 'codigo', filtro
        }

        buscaPorSKU { filtro ->
            filtro = "%$filtro%"
            ilike 'sku', filtro
        }

        buscaPorNombre { filtro ->
            filtro = "%$filtro%"
            ilike 'nombre', filtro
        }

        buscaPorDescripcion { filtro ->
            filtro = "%$filtro%"
            ilike 'descripcion', filtro
        }

    }

    int hashCode() {
        int hash = 7;
        hash+= 7 * id;
        hash+= 7 * version;
        hash+= 7 * codigo.hashCode();
        hash+= 7 * nombre.hashCode();
        return hash
    }

    boolean equals(Object o) {
        def resultado = false
        if (o != null && o instanceof Producto) {
            if (o.id == this.id) {
                resultado = true
            }
        }

        return resultado
    }

    String toString() {
        "${sku}|${nombre}"
    }

}
