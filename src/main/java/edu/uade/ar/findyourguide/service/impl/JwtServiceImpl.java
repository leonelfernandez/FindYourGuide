package edu.uade.ar.findyourguide.service.impl;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.service.IJwtService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements IJwtService {
    public JwtServiceImpl() {

    }

    private final byte[] SECRET_KEY_BYTES = Base64.getDecoder().decode("3yQ/F8rnTjSwAwCaEfgy2UOKMCfdN0n2QPE4Ftge9ggmQQnhZ8BC2lXKOyUcKJQ9U+34ezG08w8tDrqKkLFLYidt2zUctc+rJtiwIxinOwz6iY3cORDZNfMWroCU1vgW+XNLlZb1jXdus37u7ctMxLcm/Dnir4anvoKO9hUGbeGOiokJbhrufe2K2mjlPZCvWlrxYpUNKdlu9RQl7zbl59pcggB121WqUfXxWYbIFt5tkPLw/IBIb6mP0fHouvyQA4Zrqh57mpNs1RDSnprhFTDpFSK+GusFOsrWCX3YC+xHrzBx1L6+6YQzzdgXEug4sr00F/9SaYNI515PfFQZgyom9iZeZwE7/FBwvMcqVfA=");
    private final SecretKey SECRET_KEY = new SecretKeySpec(SECRET_KEY_BYTES, SignatureAlgorithm.HS256.getJcaName());

    @Override
    public String generateToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact(); // Remember to call the compact method to build the token
    }
}
