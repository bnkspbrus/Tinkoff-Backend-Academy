package ru.tinkoff.landscape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.landscape.dto.UserIdToSkills;
import ru.tinkoff.landscape.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Get all users which are not in any order ordered by length of skills using jpql query
     *
     * @return {@code List<User>}
     */
    @Query("select u from User u where u.orders is empty order by size(u.skills)")
    List<User> findAllByOrdersIsNullOrderBySkillSize();

    /**
     * Get user_id to skills mapping list using jpql query
     *
     * @return {@code List<UserIdToSkills>}
     */
    @Query("select new ru.tinkoff.landscape.dto.UserIdToSkills(u.id, u.skills) from User u")
    List<UserIdToSkills> findAllUserToSkills();
}
