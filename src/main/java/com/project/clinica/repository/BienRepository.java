package com.project.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clinica.entity.Bien;

public interface BienRepository extends JpaRepository<Bien, Integer>{
	
	//select *from tb_bien where cod_tipo_bien=1
	//consulta hql o jpql
	@Query("select b from Bien b where b.tipo.codigo=?1")
	public List<Bien> listarPorTipoBien(int codTipoBien);
}
