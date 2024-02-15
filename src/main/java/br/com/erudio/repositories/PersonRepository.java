package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import br.com.erudio.model.Person; // Importa a classe Person do pacote br.com.erudio.model

// Declaração da interface PersonRepository que estende JpaRepository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Não há métodos adicionais aqui, pois JpaRepository fornece métodos padrão para CRUD e consultas
}
