package com.proximate.www.pushmate.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.proximate.www.dashmate.utils.EnviaMail;
import com.proximate.www.pushmate.dao.IConfiguracionDAO2;
import com.proximate.www.pushmate.dao.ILogDAO;
import com.proximate.www.pushmate.utils.DateUtils;
import com.proximate.www.sam.dao.IPedidoDAO;
import com.proximate.www.sam.dto.Pedido;
import com.proximate.www.sam.dto.PedidoDetalle;

@Component
public class GenCorreoPedidosBackground implements ServletContextListener, Runnable {

	private static ILogDAO logDAO;

	private static IConfiguracionDAO2 configuracionDAO;
	
	private static IPedidoDAO pedidoDAO;
	 
	public synchronized void run() {
		try {
			System.out.println("Gen notificacionesCorreo");
			List<Pedido> pedido = pedidoDAO.obtenerPedidosADosDias();
			
			// Si tengo pedidos por caducar
			if (pedido != null && !pedido.isEmpty()) {
				// Recorro lista
				for (Pedido p : pedido) {
					// Obtener tokens
					if (p.getNumero_fullerette() != 0) {						
						// Enviar correo con detalle de pedido
						if (p.getEmail() != null && !p.getEmail().equals("")) {
							// Obtengo detalle
							List<PedidoDetalle> detalle = pedidoDAO.obtenerPedidoDetalle(p);
							if (detalle != null && !detalle.isEmpty()) {
								// Envio correo
								enviarCorreo(p, detalle);
							}
						}						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Hubo un error, no pasa nada continuamos con los demas
			logDAO.guardar("error", "Error general enviando correo", DateUtils.getDate());			
		}
	}

	private boolean enviarCorreo(Pedido pedido, List<PedidoDetalle> detalle) {

		try {			
			StringBuffer BODY = new StringBuffer(configuracionDAO.obtenerTemplatCorreo(1));
			
			if (pedido.getNombre() == null) {
				pedido.setNombre("");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String strDate = sdf.format(now);

			BODY = new StringBuffer(
					BODY.toString().replaceAll("__LOGO_BOTTOM__", configuracionDAO.getLogoCorreoBottom()));
			BODY = new StringBuffer(
					BODY.toString().replaceAll("__LOGO_HEAD__", configuracionDAO.getLogoCorreoHead()));
			BODY = new StringBuffer(
					BODY.toString().replaceAll("__CLIENTENOMBRE__", pedido.getNombre()));
			BODY = new StringBuffer(BODY.toString().replaceAll("__FECHA__",
					strDate));
			BODY = new StringBuffer(BODY.toString().replaceAll("__FECHAPEDIDO__",
					pedido.getFecha_pedido()));
			BODY = new StringBuffer(BODY.toString().replaceAll("__FECHACADUCA__",
					pedido.getFecha_termina_camp()));
			BODY = new StringBuffer(BODY.toString().replaceAll("__CAMP__",
					"" + pedido.getCampana()));
			BODY = new StringBuffer(BODY.toString().replaceAll("__ANIO__", "" + pedido.getAno_fiscal()));
			BODY = new StringBuffer(BODY.toString().replaceAll("__NO_FULL__", "" + pedido.getNumero_fullerette()));
			
			StringBuffer inicioTabla = null;
			
			if (detalle != null && !detalle.isEmpty()) {
				inicioTabla = new StringBuffer("<table cellpadding='0' cellspacing='1' border='0' bordercolor='#000000' bgcolor='#000000'><tr><td bgcolor='#FFFFFF' width='100px' align='center'><strong>Clave art&iacute;culo<strong></td><td bgcolor='#FFFFFF' width='280px' align='center'><strong>Cantidad<strong></td><td bgcolor='#FFFFFF' width='100px' align='center'><strong>Descripci&oacute;n<strong></td><td bgcolor='#FFFFFF' width='100px' align='center'><strong>Precio unitario<strong></td><td bgcolor='#FFFFFF' width='100px' align='center'><strong>Importe<strong></td></tr>");
				float total = 0;
				for (PedidoDetalle d : detalle) {
					inicioTabla.append("<tr>");
					inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>" + d.getClave_articulo() + "</td>");
					inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>" + d.getCantidad() + "</td>");
					inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>" + d.getDescripcion() + "</td>");
					inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>$" + d.getPrecio() + "</td>");
					inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>$" + d.getTotal() + "</td>");
					inicioTabla.append("</tr>");
					total += d.getTotal();
				}
				inicioTabla.append("<tr>");
				inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'></td>");
				inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'></td>");
				inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'></td>");
				inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'><strong>Total<strong></td>");
				inicioTabla.append("<td bgcolor='#FFFFFF' width='100px' align='center'>$" + total + "</td>");
				inicioTabla.append("</tr>");				
				inicioTabla.append("</table>");
			}
			
			if (inicioTabla != null) {
				BODY = new StringBuffer(
						BODY.toString().replace("__TABLA_CARGOS__", inicioTabla.toString()));
			} else {
				BODY = new StringBuffer(
						BODY.toString().replace("__TABLA_CARGOS__", ""));				
			}

			String mensaje      = BODY.toString();
			EnviaMail enviaMail = new EnviaMail();
			
			String logoBottom = configuracionDAO.getLogoCorreoBottom();
			String logoHead   = configuracionDAO.getLogoCorreoHead();
			
			enviaMail.sendMail(pedido.getEmail(), "Tu pedido está por de caducar", mensaje, logoHead, logoBottom);
			//enviaMail.enviar(pedido.getEmail(), "Detalle de tu pedido a caducar", mensaje);			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	public void contextDestroyed(ServletContextEvent arg0) {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet){
        	if (t.getName().equals("Gen correosPedido")) {
        		t.interrupt();
        	}
        }
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(arg0.getServletContext());
			logDAO = (ILogDAO) applicationContext.getBean("logDAO");
			configuracionDAO = (IConfiguracionDAO2) applicationContext.getBean("configuracionDAO2");
			pedidoDAO = (IPedidoDAO) applicationContext.getBean("pedidoDAO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}