package com.virtual.powerplant.repository;

import com.virtual.powerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BatteryRepository extends JpaRepository<Battery, Long> {
    //

    @Query(nativeQuery = true, value = """
            SELECT * FROM battery b WHERE b.postcode BETWEEN :startPostcode AND :endPostcode ORDER BY b.name ASC
            """)
    List<Battery> findBatteriesByPostcodeRange(@Param("startPostcode") String startPostcode, @Param("endPostcode") String endPostcode);
}
