package com.jdbc.ws.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jdbc.ws.entity.ProdutoEntity;
import com.jdbc.ws.service.IProdutoService;
import com.jdbc.ws.soap.wsdl.Produto;
import com.jdbc.ws.soap.wsdl.RecuperarProdutosRequest;
import com.jdbc.ws.soap.wsdl.RecuperarProdutosResponse;

@Endpoint
public class ProdutoEndpoint {
	private static final String NAMESPACE_URI = "http://wsdl.soap.ws.jdbc.com";

	@Autowired
	private IProdutoService produtoService;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "recuperarProdutosRequest")
	public RecuperarProdutosResponse getCountry(@RequestPayload RecuperarProdutosRequest request) {
		RecuperarProdutosResponse response = new RecuperarProdutosResponse();

		List<ProdutoEntity> produtos = produtoService.recuperarTodos();
		ArrayList<Produto> produtosResponse = new ArrayList<Produto>();
		int limite = request.getLimite();
		
		for (int itens = 0; itens< limite; itens++) {
			ProdutoEntity produto = produtos.get(itens);
			Produto produtoResponse = new Produto();
			produtoResponse.setId( produto.getId().intValue() );
			produtoResponse.setDescricao( produto.getDescricao() );
			produtoResponse.setPreco( produto.getPreco().doubleValue() );
			produtosResponse.add(produtoResponse);
		}
		
		response.produtos = produtosResponse ;
		return response;
	}
}
