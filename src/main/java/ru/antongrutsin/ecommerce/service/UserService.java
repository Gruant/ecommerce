package ru.antongrutsin.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antongrutsin.ecommerce.domain.SystemUser;
import ru.antongrutsin.ecommerce.domain.User;
import ru.antongrutsin.ecommerce.repository.AuthorityRepository;
import ru.antongrutsin.ecommerce.repository.UserRepository;
import ru.antongrutsin.ecommerce.security.AuthoritiesConstants;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByLogin(username);
    }

    @Transactional
    public void create(SystemUser systemUser){
        User user = new User();
        user.setLogin(systemUser.getLogin());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setEmail(systemUser.getEmail());
        user.setAuthorities(Collections.singletonList(authorityRepository.getById(AuthoritiesConstants.USER)));
        userRepository.save(user);
    }
}
