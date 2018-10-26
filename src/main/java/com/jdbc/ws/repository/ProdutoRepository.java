package com.jdbc.ws.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbc.ws.entity.ProdutoEntity;

@Repository
public class ProdutoRepository implements IProdutoRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public ProdutoEntity salvar(ProdutoEntity produto) {

		try {
			Long proximoId = jdbcTemplate.queryForObject("SELECT NEXTVAL('ESTOQUE_PRODUTO_SEQ')", Long.class);
			
			StringBuilder insert = new StringBuilder();
			insert.append("INSERT INTO \"ESTOQUE\".\"PRODUTO\" ");
			insert.append("( \"ID_PRODUTO\" , \"DESCRICAO\" , \"PRECO\" ) ");
			insert.append("VALUES ( :id , :descricao , :preco ) ");
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("id", proximoId);
			parameters.put("descricao", produto.getDescricao());
			parameters.put("preco", produto.getPreco() );
			
			namedParameterJdbcTemplate.update(insert.toString(), parameters);

			return recuperarPorId(proximoId);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar produto: " + e.getMessage());
		}

	}

	@Override
	public ProdutoEntity recuperarPorId(Long id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT \"ID_PRODUTO\" id , \"DESCRICAO\" descricao, \"PRECO\" preco ");
			sql.append("FROM \"ESTOQUE\".\"PRODUTO\" \"PRODUTO\" ");
			sql.append("WHERE \"PRODUTO\".\"ID_PRODUTO\" = :id ");
			
			Map<String,Long> parameters = new HashMap<>();
			parameters.put("id", id);
			
			ProdutoEntity produto = namedParameterJdbcTemplate.queryForObject( sql.toString(), parameters, new ProdutoMapper());
			return produto;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar produto: " + e.getMessage());
		}
	}

	@Override
	public List<ProdutoEntity> recuperarTodos() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT \"ID_PRODUTO\" id , \"DESCRICAO\" descricao, \"PRECO\" preco ");
			sql.append("FROM \"ESTOQUE\".\"PRODUTO\" \"PRODUTO\" ");
			
			List<ProdutoEntity> produto = jdbcTemplate.query( sql.toString(), new ProdutoMapper());
			return produto;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar produto: " + e.getMessage());
		}
	}

	@Override
	public ProdutoEntity editar(ProdutoEntity produto) {

		try {
			
			StringBuilder insert = new StringBuilder();
			insert.append("UPDATE \"ESTOQUE\".\"PRODUTO\" ");
			insert.append(" SET \"DESCRICAO\" = :descricao , \"PRECO\" = :preco ");
			insert.append("WHERE \"ID_PRODUTO\" = :id");
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("id", produto.getId());
			parameters.put("descricao", produto.getDescricao());
			parameters.put("preco", produto.getPreco() );
			
			namedParameterJdbcTemplate.update(insert.toString(), parameters);

			return recuperarPorId(produto.getId());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar produto: " + e.getMessage());
		}
	}

	@Override
	public void excluir(Long id) {

		try {
			
			StringBuilder insert = new StringBuilder();
			insert.append("DELETE FROM \"ESTOQUE\".\"PRODUTO\" ");
			insert.append("WHERE \"ID_PRODUTO\" = :id");
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("id", id);
			
			namedParameterJdbcTemplate.update(insert.toString(), parameters);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar produto: " + e.getMessage());
		}
	}
	
	class ProdutoMapper implements RowMapper<ProdutoEntity> {
		@Override
		public ProdutoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ProdutoEntity( Long.parseLong(rs.getString("id")), rs.getString("descricao") , new BigDecimal(rs.getString("preco")));
		}
	}
}
