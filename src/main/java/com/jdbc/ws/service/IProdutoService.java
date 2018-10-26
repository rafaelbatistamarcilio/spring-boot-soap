package com.jdbc.ws.service;

import java.util.List;

import com.jdbc.ws.entity.ProdutoEntity;

public interface IProdutoService {

	ProdutoEntity salvar(ProdutoEntity produto);
	ProdutoEntity editar(Long id, ProdutoEntity produto);
	void excluir(Long id);
	ProdutoEntity recuperarPorId(Long id);
	List<ProdutoEntity> recuperarTodos();
}
