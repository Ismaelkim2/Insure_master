package EmTechLogin.login.repository;

import EmTechLogin.login.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface loginRepo extends JpaRepository<LoginModel,Long> {

    Optional<LoginModel> findByUsername(String username );
}
