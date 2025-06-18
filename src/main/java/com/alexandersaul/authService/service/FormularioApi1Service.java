package com.alexandersaul.authService.service;

import com.alexandersaul.authService.dto.Api1ResponseDTO;
import com.alexandersaul.authService.model.FormularioApi1;

import java.util.List;
import java.util.Optional;

public interface FormularioApi1Service {

    public Api1ResponseDTO guardar(FormularioApi1 formulario);
    public Optional<FormularioApi1> buscarPorId(Long id);
    public List<FormularioApi1> listarTodos();

}
