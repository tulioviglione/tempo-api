package com.calculo.tempo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}