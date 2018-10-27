package com.jdbc.ws.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteServiceLocator;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;

@RestController
@RequestMapping("api/rest/consulta")
public class ConsultaCepRestController {

	@GetMapping("cep/{cep}")
	public EnderecoERP recuperarEndereco(@PathVariable("cep") String cep) throws Exception {
		AtendeClienteServiceLocator atendente = new AtendeClienteServiceLocator();
		EnderecoERP consultaCEP = atendente.getAtendeClientePort().consultaCEP(cep);
		return consultaCEP;
	}
}
