package com.example.virtualBookStore.filter;

import com.example.virtualBookStore.exceptions.InvalidAccessTokenException;
import com.example.virtualBookStore.service.Jwt.JwtService;
import com.example.virtualBookStore.service.UserService;
import com.example.virtualBookStore.validation.JwtValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // фильтр вызывается один раз за запрос

    public static final String AUTHORIZATION_HEADER = "Authorization";// стандартное имя заголовка
    public static final String BEARER_PREFIX = "Bearer ";// префикс токена

    private final JwtService jwtService;
    private final UserService userService;
    private final JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal( // метод фильтрации запроса
                                     // вызывается SSecurity для обработки каждого запроса
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response); // если токен отсутсвует идем дальше
                                                    // есть ли токен в заголовке и начинается ли с Bearer
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length()); // извлекаем чситый токен
        try {
            String email = jwtService.extractUserName(token);// извлекаем имя из jwt
            jwtValidator.accessTokenValid(email, token);// проверка валидности токкена

            UserDetails userDetails = userService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities() // создаем объект аунтификации
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);// кладем объект в SecurityContext
                                                                                // чтобы пользователь считался вошедшим

            filterChain.doFilter(request, response);// пропускаем дальше

        } catch (InvalidAccessTokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = String.format("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"%s\"}", e.getMessage());
            response.getWriter().write(json);
        }
    }
}
