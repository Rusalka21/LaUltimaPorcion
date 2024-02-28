package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}
