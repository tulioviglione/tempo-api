package com.calculo.tempo.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.exceptions.TempoException;
import com.calculo.tempo.response.Response;
import com.calculo.tempo.services.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tempo")
@Api("Duração video")
public class VideoController {

	private static final Logger log = LoggerFactory.getLogger(VideoController.class);

	@Autowired
	private VideoService videoService;
	
	@PostMapping("/adicionaRegistro")
	@ApiOperation(value = "Adiciona novo registro de tempo", httpMethod = "POST")
	public ResponseEntity<Response<String>> adicionar(@Valid @RequestBody VideoDTO videoDto,
			BindingResult result) {

		log.debug("Cadastrando novo registro de tempo");

		Response<String> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Erro Validação Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			videoService.adicionaTempo(videoDto);
		} catch (TempoException e) {
			response.setData("Registro não inserido");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		
		response.setData("Registro cadastrado");
		return ResponseEntity.ok(response);
	}
}
