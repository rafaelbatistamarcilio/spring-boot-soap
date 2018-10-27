package com.jdbc.ws.entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "produto", propOrder = {
	    "id",
	    "descricao",
	    "preco"
	})
@XmlRootElement(name = "produto")
public class ProdutoEntity {
	
	@XmlElement(name = "id")
	public Long id;
	@XmlElement(name = "descricao")
	public String descricao;
	@XmlElement(name = "preco")
	public BigDecimal preco;
	
	
	
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
