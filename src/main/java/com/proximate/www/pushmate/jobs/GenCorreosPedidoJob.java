package com.proximate.www.pushmate.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.proximate.www.pushmate.service.GenCorreoPedidosBackground;

public class GenCorreosPedidoJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Thread t = new Thread(new GenCorreoPedidosBackground());
		t.setName("Gen correosPedido");
		t.start();
	}

}
