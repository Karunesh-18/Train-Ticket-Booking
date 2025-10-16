package com.example.trainticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.trainticketbooking.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query("SELECT t FROM Train t WHERE " +
           "(:source IS NULL OR LOWER(t.source) LIKE LOWER(CONCAT('%', :source, '%'))) AND " +
           "(:destination IS NULL OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :destination, '%')))")
    List<Train> findBySourceAndDestination(@Param("source") String source,
                                          @Param("destination") String destination);
}