package com.proximate.www.pushmate.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.proximate.www.pushmate.service.GenNotifPedidosBackground;

public class GenNotificacionesPedidoPercJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Thread t = new Thread(new GenNotifPedidosBackground());
		t.setName("Gen notificacionesPedido");
		t.start();
	}

}
