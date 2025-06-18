package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.model.FormularioApi2;
import com.alexandersaul.authService.model.UserSec;
import com.alexandersaul.authService.repository.FormularioApi1Repository;
import com.alexandersaul.authService.repository.FormularioApi2Repository;
import com.alexandersaul.authService.repository.IUserRepository;
import com.alexandersaul.authService.service.FormularioApi2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormularioApi2ServiceImpl implements FormularioApi2Service {

    @Autowired
    private FormularioApi2Repository formularioApi2Repository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public FormularioApi2 guardar(FormularioApi2 formulario) {
        UserSec userSec = userRepository.findById(formulario.getUser().getId())
                .orElseThrow(()-> new NotFoundException("User dont exist"));
        formulario.setUser(userSec);
        return formularioApi2Repository.save(formulario);
    }

    @Override
    public Optional<FormularioApi2> buscarPorId(Long id) {
        return formularioApi2Repository.findById(id);
    }

    @Override
    public List<FormularioApi2> listarTodos() {
        return formularioApi2Repository.findAll();
    }
}
