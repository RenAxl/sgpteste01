package com.redatha.sgp_authuser.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	 @GetMapping("/")
	    public String testServer() {
	        return "<html>" +
	                "<body style=\"text-align: center; font-family: Arial, sans-serif; line-height: 1.5;\">" +
	                "    <h1>Servidor Funcionando!</h1>" +
	                "    <pre style=\"font-size: 18px; font-weight: bold;\">*********** WELCOME TO THE SGP! Teste 16 ***********</pre>" +
	                "</body>" +
	                "</html>";
	    }
	}
