package com.jdbc.ws.repository;

import java.util.List;

import com.jdbc.ws.entity.ProdutoEntity;

public interface IProdutoRepository {

	ProdutoEntity salvar(ProdutoEntity produto);
	ProdutoEntity editar(ProdutoEntity produto);
	void excluir(Long id);
	ProdutoEntity recuperarPorId(Long id);
	List<ProdutoEntity> recuperarTodos();
}
