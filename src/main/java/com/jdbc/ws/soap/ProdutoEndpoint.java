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
import com.jdbc.ws.soap.wsdl.AddProdutoRequest;
import com.jdbc.ws.soap.wsdl.AddProdutoResponse;
import com.jdbc.ws.soap.wsdl.DeleteProdutoRequest;
import com.jdbc.ws.soap.wsdl.DeleteProdutoResponse;
import com.jdbc.ws.soap.wsdl.GetProdutoRequest;
import com.jdbc.ws.soap.wsdl.GetProdutoResponse;
import com.jdbc.ws.soap.wsdl.GetProdutosRequest;
import com.jdbc.ws.soap.wsdl.GetProdutosResponse;
import com.jdbc.ws.soap.wsdl.Produto;
import com.jdbc.ws.soap.wsdl.UpdateProdutoRequest;

@Endpoint
public class ProdutoEndpoint {
	private static final String NAMESPACE_URI = "http://wsdl.soap.ws.jdbc.com";

	@Autowired
	private IProdutoService produtoService;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProdutoRequest")
	public DeleteProdutoResponse excluir(@RequestPayload DeleteProdutoRequest request) {
		
		produtoService.excluir( request.getId() );		
		DeleteProdutoResponse response = new DeleteProdutoResponse();
		response.setResposta("Produto id: " + request.getId() + " excluirdo com sucesso");
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProdutoRequest")
	public UpdateProdutoRequest atualizar(@RequestPayload UpdateProdutoRequest request) {
		ProdutoEntity novoProduto = new ProdutoEntity();
		novoProduto.setId(request.getProduto().getId());
		novoProduto.setDescricao(request.getProduto().getDescricao());
		novoProduto.setPreco(request.getProduto().getPreco());
		
		ProdutoEntity produtoCadastrado = produtoService.editar( novoProduto.getId(),novoProduto );
		Produto produto = new Produto();
		produto.setId(produtoCadastrado.getId());
		produto.setDescricao(produtoCadastrado.getDescricao());
		produto.setPreco(produtoCadastrado.getPreco());
		
		UpdateProdutoRequest response = new UpdateProdutoRequest();
		response.setProduto(produto);
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProdutoRequest")
	public AddProdutoResponse adicionar(@RequestPayload AddProdutoRequest request) {
		ProdutoEntity novoProduto = new ProdutoEntity();
		novoProduto.setDescricao(request.getProduto().getDescricao());
		novoProduto.setPreco(request.getProduto().getPreco());
		
		ProdutoEntity produtoCadastrado = produtoService.salvar( novoProduto );
		Produto produto = new Produto();
		produto.setId(produtoCadastrado.getId());
		produto.setDescricao(produtoCadastrado.getDescricao());
		produto.setPreco(produtoCadastrado.getPreco());
		
		AddProdutoResponse response = new AddProdutoResponse();
		response.setProduto(produto);
		return response;
		
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProdutosRequest")
	public GetProdutosResponse recuperarTodos(@RequestPayload GetProdutosRequest request) {
		List<ProdutoEntity> produtos = produtoService.recuperarTodos();
		List<Produto> produtosResponse = new ArrayList<>();
		
		for (ProdutoEntity produtoEntity : produtos) {
			Produto produto = new Produto();
			produto.setId(produtoEntity.getId());
			produto.setDescricao(produtoEntity.getDescricao());
			produto.setPreco(produtoEntity.getPreco());
			produtosResponse.add(produto);
		}
		GetProdutosResponse produtosXml = new GetProdutosResponse();
		produtosXml.getProdutos().addAll(produtosResponse);
		return produtosXml;
		
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProdutoRequest")
	public GetProdutoResponse recuperarPorId(@RequestPayload GetProdutoRequest request) {
		ProdutoEntity produto = produtoService.recuperarPorId(request.getId());
		
		Produto produtoResponse = new Produto();
		produtoResponse.setId( produto.getId() );
		produtoResponse.setDescricao(produto.getDescricao());
		produtoResponse.setPreco( produto.getPreco());
		
		GetProdutoResponse response = new GetProdutoResponse();
		response.setProduto(produtoResponse);
		return response;
	}
}
