package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.FormularioApi2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioApi2Repository extends JpaRepository<FormularioApi2, Long> {
}
