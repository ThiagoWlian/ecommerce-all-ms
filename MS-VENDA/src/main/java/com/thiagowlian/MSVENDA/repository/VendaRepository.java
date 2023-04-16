package com.thiagowlian.MSVENDA.repository;

import com.thiagowlian.MSVENDA.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Long> {
}
