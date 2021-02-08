package io.github.murilonerdx.agendaapi.model.repository;

import io.github.murilonerdx.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
