package icu.xhl.visibility.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import icu.xhl.visibility.Mapper.LoginMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {
    private static final long EXPIRE_DATE = 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "123456";

    @Autowired
    private LoginMapper loginMapper;

    private static TokenUtil tokenUtil;

    @PostConstruct
    public void init() {
        tokenUtil = this;
        tokenUtil.loginMapper = this.loginMapper;
    }

    public static String token(String username, String password) {

        String token;
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    public static boolean verify(String token) {
        /*
          @desc 验证token，通过返回true
         * @params [token]需要校验的串
         */
        if (token == null || token.isEmpty()) {
            return false;
        }
//        System.out.println(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            String realPassword = tokenUtil.loginMapper.selectByUsername(username);
            if (realPassword == null) {
//                System.out.println("realPassword is null");
                return false;
            }
            String password = jwt.getClaim("password").asString();
            if (!password.equals(realPassword)) {
//                System.out.println("password is not equal");
                return false;
            }
            return !jwt.getExpiresAt().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyManager(String token) {
        /*
          @desc 验证token是否属于Manager，通过返回true
         * @params [token]需要校验的串
         */
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            String realPassword = tokenUtil.loginMapper.selectByUsername(username);
            if (realPassword == null) {
                return false;
            }
            String password = jwt.getClaim("password").asString();
            if (!password.equals(realPassword)) {
                return false;
            }
            String role = tokenUtil.loginMapper.selectRoleByUsername(username);
            if (!role.equals("manager")) {
                return false;
            }
            return !jwt.getExpiresAt().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUsername(String token) {
        /*
          @desc 获取token中的username
         * @params [token]需要解析的串
         */
        if (token == null || token.equals("")) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
