package com.nt.service;

import java.sql.Date;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

@Component("pService")
public class PurchaseOrderServiceImpl implements IPurchaseOrderService{

	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	@Override
	public String purchase(String items[], double prices[], String toEmails[])throws Exception {
		double billAmt=0.0;
		for(double p:prices) {
			billAmt=billAmt+p;
		}
	String msg=Arrays.toString(items)+"are purchased having prices"+Arrays.toString(prices)+"having the bill Amount::"+billAmt;
	String statusMsg=sendMail(toEmails,msg);
	return msg+"----"+statusMsg;
	}

	private String sendMail(String toEmails[], String msg) throws Exception {
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    // Ensure `fromMail` is correctly assigned before using it
	    System.out.println("================================================");
	    System.out.println("from mail:: "+fromMail);
	    System.out.println("=================================================");
	    if (fromMail == null || fromMail.isEmpty()) {
	        throw new IllegalArgumentException("From email must not be null or empty");
	    }

	    helper.setFrom(fromMail);  // This should be set properly
	    helper.setTo(toEmails);
	    helper.setSubject("Open it to know it");
	    helper.setSentDate(new Date(0));
	    helper.setText(msg);
	    helper.addAttachment("mohan.jpg", new ClassPathResource("mohan.jpg"));

	    sender.send(message);
	    return "Mail sent with an attachment";
	}
	
}
