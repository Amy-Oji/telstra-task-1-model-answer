package au.com.telstra.simcardactivator.repositories;

import au.com.telstra.simcardactivator.foundation.SimCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCardEntity, Long> {
    Optional<SimCardEntity> findById(long id);
}
