package com.nt.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.IPurchaseOrderService;

@Component
public class MailOperationsTestRunner implements CommandLineRunner {

	@Autowired
	private IPurchaseOrderService service;
	@Override
	public void run(String... args) throws Exception {
		String resultMsg=service.purchase(new String[] {"shirt", "trouser","hat"}, new double[] {2000.0,4000.0,1000.0},
				new String[]{"tophanpradhan580@gmail.com"});

	}

}
