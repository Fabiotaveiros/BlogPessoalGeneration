package com.generation.lojagames.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	//acesso ao banco de dados, injeção de dependência
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		//if para verificar se o usuário existe ou não no nosso banco de dados
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe", null);
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
			
			
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//para criptografar a senha 
		String SenhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(SenhaEncoder); //para atualizar a criptografia para a senha atual
		
		return Optional.of(usuarioRepository.save(usuario)); //retorno precisa ser um optional, e o OF é o metodo pra ele voltar
		
	}
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		//if para verificar se o usuário existe ou não no nosso banco de dados
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String SenhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(SenhaEncoder); //para atualizar a criptografia para a senha atual
		
		return Optional.of(usuarioRepository.save(usuario));
	}else{
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
		}	
	}
	
	public Optional <UsuarioLogin> loginUsuario(Optional <UsuarioLogin> UsuarioLogin){
		
		Optional <Usuario> usuario = usuarioRepository.findByUsuario(UsuarioLogin.get().getUsuario());
		
		if (usuario.isPresent()) {
			
			if(encoder.matches(UsuarioLogin.get().getSenha(), usuario.get().getSenha())) {	
			
				String auth = UsuarioLogin.get().getUsuario() + ":" + UsuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				UsuarioLogin.get().setId(usuario.get().getId());
				UsuarioLogin.get().setNome(usuario.get().getNome());
				UsuarioLogin.get().setSenha(usuario.get().getSenha());
				UsuarioLogin.get().setToken(authHeader);
				
				return UsuarioLogin;
			}
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos", null);
	}
}
