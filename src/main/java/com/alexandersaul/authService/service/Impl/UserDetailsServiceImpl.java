package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.dto.AuthLoginRequestDTO;
import com.alexandersaul.authService.dto.AuthResponseDTO;
import com.alexandersaul.authService.model.UserSec;
import com.alexandersaul.authService.repository.IUserRepository;
import com.alexandersaul.authService.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserSec userSec = userRepository.findUserSecByUserName((userName)).orElseThrow(()->new UsernameNotFoundException(userName + " was not found"));

        Set<GrantedAuthority> authorityList = new HashSet<>();

        userSec.getRolesList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        userSec.getRolesList().stream()
                .flatMap(role-> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        System.out.println(userSec.getUserName() + userSec.getPassword() +  userSec.isEnabled() +  userSec.isAccountNotExpired()+
                userSec.isCredentialNotExpired() +  userSec.isAccountNotBlocked() +  authorityList);

        return new User(userSec.getUserName() , userSec.getPassword() , userSec.isEnabled() , userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired() , userSec.isAccountNotBlocked() , authorityList);
    }

    public AuthResponseDTO loginUser (AuthLoginRequestDTO authLoginRequestDTO) {
        String username = authLoginRequestDTO.userName();
        String password = authLoginRequestDTO.password();

        Authentication authentication = this.authenticate(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponseDTO(username,"Login successfull" , accessToken,true);
    }

    public Authentication  authenticate  (String username , String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("invalid username or password");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());

    }

}