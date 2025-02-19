package print.Lora.Auth.Rpository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Auth.Model.AppUser;
import print.Lora.TimeTable.Entity.ClassEntity;

import java.util.Optional;

@Repository

public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = ?2 WHERE a.email = ?1")
    int enableAppUser(String email,boolean isEnable);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.password =?2  WHERE a.email = ?1")
    int forgetAppUser(String email,String password);

    @Transactional
    @Modifying
    @Query("DELETE FROM AppUser a WHERE a.email = ?1")
    int deleteByEmail(String email);


    @Query("SELECT a.classEntity FROM AppUser a WHERE a.email=?1 ")
    Optional<ClassEntity> findClassByUsername(String userName);
}

