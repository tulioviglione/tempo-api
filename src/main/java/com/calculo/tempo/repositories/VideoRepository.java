package com.calculo.tempo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculo.tempo.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}