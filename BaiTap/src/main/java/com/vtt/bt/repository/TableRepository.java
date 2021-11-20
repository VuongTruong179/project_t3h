package com.vtt.bt.repository;


import com.vtt.bt.model.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.table_food")
    List<Table> getAllTable();

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.table_food " +
            "where table_food.id = :Id")
    List<Table> getTableById(@Param("Id")int Id);

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.table\n" +
            "where status = 'Trống'")
    List<Table> getTableEmpty();

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update do_an.table_food\n" +
            "set number_table = :nameFood \n" +
            "where table_food.id = :Id ")
    void changeTable(@Param("nameFood")String nameFood,
                      @Param("Id")int Id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from table_food\n" +
            "where id = :Id ")
    void deleteTableById(@Param("Id")int Id);


    @Query(nativeQuery = true,value = "SELECT * FROM do_an.table_food\n" +
            "where id = :Id and status = 'Đang có người'")
    Table TableEmpty(@Param("Id")int Id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update  table_food\n" +
            "set table_food.status = 'Trống'\n" +
            "where id = :Id ")
    void changeTableEmpty(@Param("Id")int Id);


    @Query(nativeQuery = true,value = "SELECT * FROM do_an.table_food\n" +
            "where id = :Id and status = 'Trống'")
    Table TableActive(@Param("Id")int Id);


    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update  table_food\n" +
            "set table_food.status = 'Đang có người'\n" +
            "where id = :Id ")
    void changeTableActive(@Param("Id")int Id);
}
