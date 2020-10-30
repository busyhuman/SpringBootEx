package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    UserRepository userRepostory;

    PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepostory, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepostory = userRepostory;
    }



    public User registerUser(String email, String name, String password) {
        Optional<User> existed = userRepostory.findByEmail(email);

        if(existed.isPresent()) {
            throw new EmailExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepostory.save(user);
    }

    public User authenticate(String email, String password) {
        // 람다를 써야 아무때나 만들지 않게 해줄 수 있다.
        User user = userRepostory.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if( !passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }
}
