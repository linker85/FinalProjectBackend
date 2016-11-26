package com.proximate.www.pushmate.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.proximate.www.pushmate.service.ProgramacionBackground;

public class EnvioNotificacionesJob implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Thread t = new Thread(new ProgramacionBackground());
		t.setName("Envio notificaciones");
		t.start();
	}

}
