package com.shop.service.dataservice.security;


import com.shop.data.repository.UserRepository;
import com.shop.data.entity.TokenEntity;
import com.shop.data.entity.TokenType;
import com.shop.data.entity.UserEntity;
import com.shop.data.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by vazgen on 12/20/16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    RandomGenerator randomGenerator;


    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }


    @Override
    public boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(role));

    }

    @Override
    public boolean autoLogin(String username, String password) {
        boolean isAuthenticated = false;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.debug(String.format("Auto login %s successfully!", username));
                isAuthenticated = true;
            }
        } catch (UsernameNotFoundException ex) {
            logger.error(String.format("Auto login %s error!", ex.getMessage()));

        }
        return isAuthenticated;

    }

    @Override
    public long activateAccount(String token) throws InvalidTokenException {
        TokenEntity tokenEntity = tokenRepository.findByTokenTypeAndVal(TokenType.ACTIVATION, token);
        if (tokenEntity == null)
            throw new InvalidTokenException("Token not found");
        UserEntity userEntity = tokenEntity.getUser();
        userEntity.setActive(true);
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    private String generateToken(TokenType tokenType, Long userId) {

        String token = randomGenerator.generateString(64);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUser(userRepository.findOne(userId));
        tokenEntity.setTokenType(tokenType);
        tokenEntity.setVal(token);
        tokenRepository.save(tokenEntity);
        return token;
    }

    @Override
    public String generateActivationToken(long userId) {
        return generateToken(TokenType.ACTIVATION, userId);
    }
}