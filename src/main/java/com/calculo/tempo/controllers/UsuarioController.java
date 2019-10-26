package com.calculo.tempo.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculo.tempo.dtos.UsuarioDTO;
import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.response.Response;
import com.calculo.tempo.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/usuarios")
@Api("Cadastro de usuário")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@ApiOperation(value = "Adiciona novo usuario no sistema", response = UsuarioDTO.class, httpMethod = "POST")
	public ResponseEntity<Response<UsuarioDTO>> adicionar(@Valid @RequestBody UsuarioDTO usuarioDto,
			BindingResult result) {

		log.debug("Cadastrando novo usuário na API");

		Response<UsuarioDTO> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Erro Validação Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(new UsuarioDTO(this.usuarioService.cadastraNovoUsuario(new Usuario(usuarioDto))));
		return ResponseEntity.ok(response);

	}
	
}
