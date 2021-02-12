package com.javatechie.solar.api.repository;

import com.javatechie.solar.api.model.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface BookRepository extends SolrCrudRepository<BookBean, String>{

	BookBean findByName(String name);

	@Query("id:*?0* OR name:*?0*")
	public Page<BookBean> findByCustomQuery(String searchTerm, Pageable pageable);

}
