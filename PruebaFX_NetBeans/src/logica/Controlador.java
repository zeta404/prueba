package logica;
import excepciones.DominioExcepcion;
import excepciones.DAOExcepcion;
import java.util.ArrayList;

import persistencia.DAL;


public class Controlador {
	private static Controlador controlador;
	private DAL dal;
	//**********************************************************************
	// Creaci�n del controlador
	// Un �nico controlador para todos los C.U.
	//**********************************************************************
	private Controlador() throws DominioExcepcion{
		try {
		// Objeto para comunicarse con la capa de acceso a datos
		this.dal = DAL.dameDAL();
		
		}catch (DAOExcepcion e){
			throw new DominioExcepcion(e.getMessage());
		}
	}
        
	public static Controlador dameControlador() throws DAOExcepcion, DominioExcepcion {
	if(controlador == null)
		controlador=new Controlador();
	return controlador;
	}
	
	public void insertarProducto(Producto producto) throws DAOExcepcion {
	dal.insertarProducto(producto);
}
public Producto getProducto(String _nombre) throws DAOExcepcion {
	return dal.getProducto(_nombre);
}
public ArrayList<Producto> getProductos() throws DAOExcepcion {
	return dal.getProductos();
}
public void eliminarProducto(Producto producto) throws DAOExcepcion {
	dal.eliminarProducto(producto);
}

    public ArrayList<Pigmento> getPigmentos() throws DAOExcepcion {
        return dal.getPigmentos();
    }

}
