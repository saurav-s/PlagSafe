import objects.UserObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserObject, Integer> {
}
