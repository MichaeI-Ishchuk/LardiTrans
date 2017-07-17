package com.lardi_trans.repository;

import com.lardi_trans.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created by nata on 19.03.2017.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT us FROM com.lardi_trans.domain.User us " +
            "WHERE us.login= :login")
    User getUserByLogin(@Param("login") String login);


//    @Query("UPDATE ua.ishchuk.domain.Student stud SET stud.mark= :mark " +
//            "WHERE stud.login= :login")
//    void setMark(@Param("login") String login, @Param("mark") int mark);

}
