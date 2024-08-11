package cc.ziyi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.jupiter.api.Test;

public class JwtTest {

    @Test
    // usually companies or projects may choose to provide their own encapsulated utility classes or libraries
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();  // for store user-info
        claims.put("id", 1);
        claims.put("username", "Alice");
        // generate jwt-code
        String token = JWT.create()
                .withClaim("user", claims) // add load
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*12)) //add expiration time: 12h
                .sign(Algorithm.HMAC256("ziyiz")); //Specify algorithm, configure secret key(Prevent tampering)

        System.out.println(token);

    }

    @Test
    public void testParse() {
        // Define a string to simulate the token passed by the user
        // token below comes from the user-instance(1,"Alice") after running testGen()
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IkFsaWNlIn0sImV4cCI6MTcwODk5MjM3Nn0." +
                "2s86hTypOHdqDShP5lD1exJfkU-VU-VxI6jrwoMCXDU";

        // Generate validator: must use the same secret key "ziyiz"
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ziyiz")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token); // Verify token and generate a parsed JWT object
        Map<String, Claim> claims = decodedJWT.getClaims();  // getClaims() can get all payload
        System.out.println(claims.get("user"));  // get payload with "user" as key

        // after running testParse(), terminal shows:  "{"id":1,"username":"Alice"}"

        // if header and payload parts are tampered, the verification fails (system throws exception)
        // If the secret key is changed, verification fails (system throws exception)
        // If token expires, system also throws exception
    }

}
