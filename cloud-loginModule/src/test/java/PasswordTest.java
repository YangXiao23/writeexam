import com.example.LoginMain;
import com.example.mapper.UserMapper;
import com.example.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: yangxiao
 * @DATE 2023/10/24 14:16
 */
@SpringBootTest(classes = LoginMain.class)
public class PasswordTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;
    @Getter
    @Autowired
    private JWTUtil jwtUtil;

}
