package EmTechLogin.login.service;
import EmTechLogin.login.model.LoginModel;
import EmTechLogin.login.repository.loginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class loginServiceImpl implements loginService {

    @Autowired
    private loginRepo loginRepo;

    @Override
    public LoginModel register(LoginModel user) {
        if (loginRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        return loginRepo.save(user);
    }

    @Override
    public Optional<LoginModel> findByUsername(String username) {
        return loginRepo.findByUsername(username);
    }
}
