package com.lucasloose.appfooturestars.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.dto.EmpresarioDTO;
import com.lucasloose.appfooturestars.dto.EmpresarioNewDTO;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresarioService {

	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	
	public Empresario find(Integer id) {
		Empresario empresario = empresarioRepository.findOne(id);
		if (empresario == null) {
			throw new ObjectNotFoundException("Empresário não encontrado! ID: " + id
					+ ", Tipo: " + Empresario.class.getName());
		}
		return empresario;
	}
	
	public Empresario fromDTO(EmpresarioDTO empresarioDTO) {
		//passei a data como null
		Usuario usuario = new Usuario(empresarioDTO.getIdUsuario());
		Empresario empresario = new Empresario(null, empresarioDTO.getNome(), empresarioDTO.getFoto(), empresarioDTO.getCpf(), null,
				empresarioDTO.getNacionalidade(), empresarioDTO.getEstado_nasc(), empresarioDTO.getMunicipio_nasc(), empresarioDTO.getSexo(),
				empresarioDTO.getPrefixo_fone(), empresarioDTO.getDdd_fone(), empresarioDTO.getFone(),
				empresarioDTO.getEmail(), empresarioDTO.getComplemento(), usuario);
		
		return empresario;
	}
	
	public Empresario fromDTO(EmpresarioNewDTO empresarioNewDTO) {
		//passei a data como null
		Usuario usuario = new Usuario(empresarioNewDTO.getIdUsuario());
		Empresario empresario = new Empresario(null, empresarioNewDTO.getNome(), empresarioNewDTO.getFoto(), empresarioNewDTO.getCpf(), null,
				empresarioNewDTO.getNacionalidade(), empresarioNewDTO.getEstado_nasc(), empresarioNewDTO.getMunicipio_nasc(), empresarioNewDTO.getSexo(),
				empresarioNewDTO.getPrefixo_fone(), empresarioNewDTO.getDdd_fone(), empresarioNewDTO.getFone(),
				empresarioNewDTO.getEmail(), empresarioNewDTO.getComplemento(), usuario);
		
		return empresario;
	}
	
	@Transactional
	public Empresario insert(Empresario empresario) {
		empresario.setId(null);
		return empresarioRepository.save(empresario);
	}
	
	public Empresario update(Empresario empresario) {
		Empresario newEmpresario = this.find(empresario.getId());
		this.updateData(newEmpresario, empresario);
		return empresarioRepository.save(newEmpresario);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			empresarioRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um EMPRESARIO pq há entidades relacionadas");
		}
	}
	
	private void updateData(Empresario newEmpresario, Empresario empresario) {
		newEmpresario.setNome(empresario.getNome());
		newEmpresario.setFoto(empresario.getFoto());
		newEmpresario.setCpf(empresario.getCpf() != null ? empresario.getCpf() : newEmpresario.getCpf());
		newEmpresario.setData_nasc(empresario.getData_nasc());
		newEmpresario.setNacionalidade(empresario.getNacionalidade());
		newEmpresario.setEstado_nasc(empresario.getEstado_nasc());
		newEmpresario.setMunicipio_nasc(empresario.getMunicipio_nasc());
		newEmpresario.setSexo(empresario.getSexo());
		newEmpresario.setPrefixo_fone(empresario.getPrefixo_fone());
		newEmpresario.setDdd_fone(empresario.getDdd_fone());
		newEmpresario.setFone(empresario.getFone());
		newEmpresario.setEmail(empresario.getEmail());
		newEmpresario.setComplemento(empresario.getComplemento());
		newEmpresario.setUsuario(empresario.getUsuario());
	}
	
}
