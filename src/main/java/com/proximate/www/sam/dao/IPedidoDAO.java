package com.proximate.www.sam.dao;

import java.util.List;

import com.proximate.www.dashmate.dao.IBaseDao;
import com.proximate.www.sam.dto.Pedido;
import com.proximate.www.sam.dto.PedidoDetalle;

/**
 * Esta interfaz contiene logica de negocio para comunicarse con la base de datos 
 * y realizar operaciones de lectura.
 *
 * @author Raul Garciaa
 * @version 1.0.9, 08/24/15
 * @since 1.7
 */
public interface IPedidoDAO extends IBaseDao<Pedido> {

	List<Pedido> obtenerPedidosADosDias();	
	List<PedidoDetalle> obtenerPedidoDetalle(Pedido pedido);
}