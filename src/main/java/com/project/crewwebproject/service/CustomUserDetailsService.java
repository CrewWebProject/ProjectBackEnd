package com.project.crewwebproject.service;


import com.project.crewwebproject.domain.User;
import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.StatusCode;
import com.project.crewwebproject.jwt.UserDetailsImpl;
import com.project.crewwebproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

   public CustomUserDetailsService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String id) {
       User user = userRepository.findById(id)
              .orElseThrow(() -> new PrivateException(StatusCode.INTERNAL_SERVER_ERROR_PLZ_CHECK));

      return new UserDetailsImpl(user);
   }
}
