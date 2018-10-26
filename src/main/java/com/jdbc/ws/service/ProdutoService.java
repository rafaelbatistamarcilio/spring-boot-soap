package com.jdbc.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.ws.entity.ProdutoEntity;
import com.jdbc.ws.repository.IProdutoRepository;

@Service
public class ProdutoService implements IProdutoService {
	
	@Autowired
	private IProdutoRepository produtoRepository;

	@Override
	public ProdutoEntity salvar(ProdutoEntity produto) {
		return produtoRepository.salvar(produto);
	}

	@Override
	public ProdutoEntity recuperarPorId(Long id) {
		return produtoRepository.recuperarPorId(id);
	}

	@Override
	public List<ProdutoEntity> recuperarTodos() {
		return produtoRepository.recuperarTodos();
	}

	@Override
	public ProdutoEntity editar(Long id, ProdutoEntity produto) {
		try {
			verificarProdutoExistente(id);			
			return produtoRepository.editar(produto);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao editar produto id: "+id+". "+e.getMessage());
		}
	}

	@Override
	public void excluir(Long id) {
		try {
			verificarProdutoExistente(id);			
			this.produtoRepository.excluir(id);	
		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir produto id: "+id+". "+e.getMessage());
		}	
	}	
	
	private void verificarProdutoExistente(Long id) {

		ProdutoEntity produtoAntigo = recuperarPorId(id);
		
		if(produtoAntigo == null) {
			throw new RuntimeException("O produto não foi encontrado!");
		}
	}
}
