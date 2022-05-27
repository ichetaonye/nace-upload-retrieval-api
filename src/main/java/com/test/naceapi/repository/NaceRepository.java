package com.test.naceapi.repository;

import com.test.naceapi.domain.transaction.NaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaceRepository extends JpaRepository<NaceEntity, Long> {
        NaceEntity findByNaceOrder(String naceOrder);

}