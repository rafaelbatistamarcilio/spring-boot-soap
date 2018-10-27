package com.jdbc.ws.rest;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.ws.entity.ProdutoEntity;
import com.jdbc.ws.service.IProdutoService;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteProxy;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteServiceLocator;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;

@RestController
@RequestMapping("api/rest/produto")
public class ProdutoRestController {
	
	@Autowired
	private IProdutoService produtoService;

	@PostMapping
	public ProdutoEntity salvar(@RequestBody ProdutoEntity produto) {
		return produtoService.salvar(produto);
	}

	@GetMapping()
	public List<ProdutoEntity> recuperarTodos() {
		return produtoService.recuperarTodos();
	}	

	@GetMapping("{id}")
	public ProdutoEntity recuperarPorId(@PathVariable("id") Long id) {
		return produtoService.recuperarPorId(id);
	}	

	@PatchMapping("{id}")
	public ProdutoEntity editar(@PathVariable("id") Long id, @RequestBody ProdutoEntity produto) {
		return produtoService.editar(id, produto);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
		produtoService.excluir(id);
		return new ResponseEntity<String>("PRODUTO ID "+id+" EXCLUIDO", HttpStatus.OK);
	}	
}
