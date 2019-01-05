package moriakoff.curse.repository;

import moriakoff.curse.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends JpaRepository <SellerEntity, String> {

    SellerEntity findBySellerName(String sellerName);

    @Query("SELECT s from seller s where s.sellerName=:sellerName ")
    SellerEntity getSellerByName(@Param("sellerName") String sellerName);


}