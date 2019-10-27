package com.calculo.tempo.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/tempo")
@Api("Duração video")
public class VideoController {

	private static final Logger log = LoggerFactory.getLogger(VideoController.class);

	@Autowired
	private VideoService videoService;
	
	@PostMapping("/adicionaRegistro")
	@ApiOperation(value = "Adiciona novo registro de tempo", httpMethod = "POST")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Registro cadastrado"),
		    @ApiResponse(code = 204, message = "Data do registro menor que o esperado"),
		    @ApiResponse(code = 400, message = "Erro validação registro")
		      })
	public ResponseEntity<Response<String>> adicionaRegistro(@Valid @RequestBody VideoDTO videoDto,
			BindingResult result) {

		log.debug("Cadastrando novo registro de tempo");

		Response<String> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Erro Validação registro: {}", result.getAllErrors());
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
	
	@PostMapping("/realizaCarga")
	@ApiOperation(value = "Adiciona novo registro de tempo", httpMethod = "POST")
	@ApiResponses(value = { 
			@ApiResponse(code=200, message = "realizado carga de dados")
	})
	public ResponseEntity<Response<String>> populaBanco() {

		log.debug("Cadastrandos novo registro no banco para teste");

		Response<String> response = new Response<>();

		try {
			videoService.populaBanco();
			response.setData("realizado carga de dados");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setData("Erro inesperado");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@DeleteMapping(value = "/")
	@ApiOperation(value = "Apaga todos os registros de tempo no banco", httpMethod = "DELETE")
	@ApiResponses(value = { 
			@ApiResponse(code=204, message = "Registros apagados")
	})
	@ApiResponse(code=204, message = "Registros apagados")
	public ResponseEntity<Response<String>> limpaRegistros() {
		log.debug("Apagando registros");
		
		Response<String> response = new Response<>();
		
		try {
			videoService.apagaTodosRegistros();
			response.setData("Registros apagados");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		} catch (Exception e) {
			response.setData("Erro ao limpar banco");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
