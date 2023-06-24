package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Select users whose orders are empty
     *
     * @return List of users
     */
    List<User> findByOrdersIsEmpty();

    /**
     *  Select users whose orders are empty ordered by distance between given lat, lon and user's lat, lon
     *
     *  @param lat Latitude
     *  @param lon Longitude
     *  @return List of users
     */
    @Query(value = "select u from User u where u.orders is empty order by ST_Distance(ST_Point(:lat, :lon), ST_Point(u.latitude, u.longitude))")
    List<User> findByOrdersIsEmptyOrderByDistance(@Param("lat") Double lat, @Param("lon") Double lon);
}
