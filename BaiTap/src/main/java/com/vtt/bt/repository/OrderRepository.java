package com.vtt.bt.repository;

import com.vtt.bt.model.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Oder, Integer> {


//    @Query(nativeQuery = true,value = "SELECT * FROM do_an.oder\n" +
//            "where oder.id_of_table = :Id " +
//            "and ( created_time between :TimeA and :TimeB )")
//    Oder Test(@Param("Id") int Id,
//                    @Param("TimeA") String TimeA,
//                    @Param("TimeB") String TimeB);

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.oder\n" +
            "where id_of_table = :Id and ( created_time between :TimeA and :TimeB ) ")
    Oder getSumAllOrder(@Param("Id")int Id,
                        @Param("TimeA") String TimeA,
                        @Param("TimeB") String TimeB);

    @Query(nativeQuery = true, value = "SELECT oder.id, oder.id_of_table, sum(sum_all_total_oder)as sum_all_total_oder, oder.created_time \n" +
            "FROM do_an.oder\n" +
            "where ( oder.created_time between '2021-10-31 17:24:29' and '2021-11-07 07:07:33' )")
    Oder orderStatistics(
                        @Param("TimeA") String TimeA,
                        @Param("TimeB") String TimeB);



}