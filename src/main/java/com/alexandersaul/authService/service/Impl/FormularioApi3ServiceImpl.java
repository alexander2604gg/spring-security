package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.model.FormularioApi2;
import com.alexandersaul.authService.model.FormularioApi3;
import com.alexandersaul.authService.model.UserSec;
import com.alexandersaul.authService.repository.FormularioApi3Repository;
import com.alexandersaul.authService.repository.IUserRepository;
import com.alexandersaul.authService.service.FormularioApi3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormularioApi3ServiceImpl implements FormularioApi3Service {

    @Autowired
    private FormularioApi3Repository formularioApi3Repository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public FormularioApi3 guardar(FormularioApi3 formulario) {
        UserSec userSec = userRepository.findById(formulario.getUser().getId())
                .orElseThrow(()-> new NotFoundException("User dont exist"));
        formulario.setUser(userSec);
        return formularioApi3Repository.save(formulario);
    }

    @Override
    public Optional<FormularioApi3> buscarPorId(Long id) {
        return formularioApi3Repository.findById(id);
    }

    @Override
    public List<FormularioApi3> listarTodos() {
        return formularioApi3Repository.findAll();
    }

}
