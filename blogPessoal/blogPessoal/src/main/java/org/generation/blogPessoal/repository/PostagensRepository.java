package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostagensRepository extends JpaRepository<Postagens, Long> {
	public List<Postagens> findAllBytituloContainingIgnoreCase (String titulo);
	
}
