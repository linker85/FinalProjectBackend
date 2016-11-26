package com.proximate.www.dashmate.interceptors;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.proximate.www.pushmate.jobs.EnvioNotificacionesJob;
import com.proximate.www.pushmate.jobs.GenCorreosPedidoJob;
import com.proximate.www.pushmate.jobs.GenNotificacionesPedidoJob;


public class LoginInterceptor implements Interceptor, ServletContextListener {


	private Map<String, Object> map;
	private ServletContext context = null;
		
	
	public void destroy() {


	}

	
	public void init() {


	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		return actionInvocation.invoke();
	}

	
	public void contextDestroyed(ServletContextEvent arg0) {
		if (scheduler3 != null) {
			try {
				scheduler3.shutdown();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		if (scheduler2 != null) {
			try {
				scheduler2.shutdown();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (scheduler1 != null) {
			try {
				scheduler1.shutdown();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet){
        	if (t.getName().equals("Envio notificaciones")) {
        		t.interrupt();
        	}
        	if (t.getName().equals("Gen notificacionesPedido")) {
        		t.interrupt();
        	}
        	if (t.getName().equals("Gen correosPedido")) {
        		t.interrupt();
        	}
        }
		System.out.println("Contexto terminado");
		this.destroy();
	}

	Scheduler scheduler3;
	Scheduler scheduler2;
	Scheduler scheduler1;
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("Contexto iniciado");
		this.context = event.getServletContext();

		
		JobDetail jobDetail1 = JobBuilder.newJob(GenNotificacionesPedidoJob.class).withIdentity("GenNotificacionesPedidoJob", "GenNotificacionesPedidoJob").build();
		Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("logEnvios1","GenNotificacionesPedidoJob").withSchedule(CronScheduleBuilder.cronSchedule("0/60 * * * * ?")).build();		
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
	    	scheduler1.start();
	    	scheduler1.scheduleJob(jobDetail1, trigger1);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		/*
		// Generar notificaciones-pedido
		JobDetail jobDetail1 = JobBuilder.newJob(GenNotificacionesPedidoJob.class).withIdentity("GenNotificacionesPedidoJob", "GenNotificacionesPedidoJob").build();
		Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("logEnvios1","GenNotificacionesPedidoJob").withSchedule(CronScheduleBuilder.cronSchedule("0 0 18 1/1 * ? *")).build();		
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
	    	scheduler1.start();
	    	scheduler1.scheduleJob(jobDetail1, trigger1);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}		
		
		// Generar correo-pedido
		JobDetail jobDetail2 = JobBuilder.newJob(GenCorreosPedidoJob.class).withIdentity("GenCorreosPedidoJob", "GenCorreosPedidoJob").build();
		Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("logEnvios2","GenCorreosPedidoJob").withSchedule(CronScheduleBuilder.cronSchedule("0 0 18 1/1 * ? *")).build();		
		try {
			scheduler2 = new StdSchedulerFactory().getScheduler();
	    	scheduler2.start();
	    	scheduler2.scheduleJob(jobDetail2, trigger2);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}		
		
		// Envio de notificaciones
		JobDetail jobDetail3 = JobBuilder.newJob(EnvioNotificacionesJob.class).withIdentity("sendPendientes", "envioNotificaciones").build();
		Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("logEnvios3","envioNotificaciones").withSchedule(CronScheduleBuilder.cronSchedule("0/60 * * * * ?")).build();
		
		try {
			scheduler3 = new StdSchedulerFactory().getScheduler();
	    	scheduler3.start();
	    	scheduler3.scheduleJob(jobDetail3, trigger3);

		} catch (SchedulerException e) {

			e.printStackTrace();
		}*/
	}
	
}