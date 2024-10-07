package EmTechLogin.login.service;


import EmTechLogin.login.model.LoginModel;
import org.apache.catalina.User;

import java.util.Optional;

public interface loginService {

  LoginModel register(LoginModel user);

  Optional<LoginModel> findByUsername(String Username);
}
