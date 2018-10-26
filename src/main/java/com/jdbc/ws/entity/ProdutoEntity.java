package com.jdbc.ws.entity;

import java.math.BigDecimal;

public class ProdutoEntity {

	private Long id;
	private String descricao;
	private BigDecimal preco;
	
	
	
	public ProdutoEntity() {
		super();
	}
	
	public ProdutoEntity(Long id, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "ProdutoEntity [id=" + id + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
}
