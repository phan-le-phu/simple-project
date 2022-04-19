package com.example.simpleproject.security;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOGGER = LogManager.getLogger(CustomLogoutSuccessHandler.class.getName());

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        super.handle(request, response, authentication);
        super.clearAuthenticationAttributes(request);

        LOGGER.log(Level.INFO, "User " + authentication.getName() + " has logged in.");
    }
}
