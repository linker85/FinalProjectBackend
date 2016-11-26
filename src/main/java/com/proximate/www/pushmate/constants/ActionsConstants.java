package com.proximate.www.pushmate.constants;

public interface ActionsConstants {
	//Commons
	public String TYPE_TILES             = "tiles";
	public String TYPE_ACTION            = ".action";
	public String TYPE_REDIRECT_ACTION   = "redirectAction";
	public String TYPE_STREAM            = "stream";
	public String ACTION_NAME            = "baseAction";
	public String ERROR                  = "ERROR";
	public String INICIAR                = "INICIAR";
	public String CERRAR               	 = "CERRAR";
	public String GUARDAR                = "GUARDAR";
	public String MODIFICAR              = "MODIFICAR";
	public String CAPTURA                = "CAPTURA";
	public String DETALLE                = "DETALLE";
	public String SELF                   = "SELF";
	public String EXCEL                  = "EXCEL";
	public String BLANK                  = "/blank.tiles";
	
	// URLs
	public String URL_INICIAR            = "/welcome.tiles";
	public String URL_GUARDAR            = "INICIAR";
	public String URL_MODIFICAR          = "INICIAR";
	public String URL_ERROR              = "/error.tiles";
	
	public String URL_WELCOME           = "/welcome.tiles";
	public String URL_NOTIFICACION_ACCION     = "/notificacion.tiles";
	public String URL_NOTIFICACION_ADD_ACCION     = "/notificacionAdd.tiles";
	public String URL_NOTIFICACION_CANCEL_ACCION     = "/notificacionCancel.tiles";
	public String URL_NOTIFICACION_EDIT_ACCION     = "/notificacionEdit.tiles";
	public String URL_LOG_ACCION     = "/logs.tiles";
	public String URL_MIS_DATOS_ACCION     = "/misDatos.tiles";
	
	public String URL_CONFIGURACION_ACCION     = "/resultadosConfiguracion.tiles";
	public String URL_ADMINISTRACION_ACCION     = "/resultadosAdministracion.tiles";
	
	public String URL_NOTIFICACION_NEGOCIO     = "/notificacionNegocio.tiles";
	
	//Actions
	public String ACTION_REPORTE_FALLAS_CONSULTA = "reporteFallasAction";
	public String ACTION_PERFILES_CONSULTA       = "perfilesAction";
	public String ACTION_MANTENIMIENTOS_CONSULTA = "mantenimientosAction";
	public String ACTION_AUDITORIA_CONSULTA      = "auditoriaAction";
	public String ACTION_PUNTOS_PAGO             = "puntosPagoAction";
	public String ACTION_PUNTOS_SERVICIO         = "puntosServicioAction";
    public String COMA                           = ",";
    
    
    public String FORMATO_ANALYTICS             = "yyyy-MM-dd";
}
