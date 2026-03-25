import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Constructor Injection
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Step 1: Extract the Authorization Header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Step 2: Check if the header is missing or doesn't start with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Pass it to the next filter (will likely be rejected)
            return;
        }

        // Step 3: Extract the actual token (skip the first 7 characters "Bearer ")
        jwt = authHeader.substring(7);

        // Step 4: Extract the email from the token using our JwtService
        userEmail = jwtService.extractUsername(jwt);

        // Step 5: If we have an email, AND the user is not already authenticated in this exact thread
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Get the user from the database (We will build this connection later!)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Step 6: If the token is valid (matches the user and isn't expired)
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Create the official Spring Security VIP Pass
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Add extra details like IP address to the token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Step 7: Put the VIP pass into the Security Context (The ThreadLocal Vault!)
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 8: Move on to the next filter or the controller
        filterChain.doFilter(request, response);
    }
}