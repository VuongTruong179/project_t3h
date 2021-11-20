package com.vtt.bt.repository;


import com.vtt.bt.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

//    Dùng để Kiểm tra tổn tại:
    @Query(nativeQuery = true,value = "SELECT * FROM do_an.menu\n" +
            "where id = :Id")
    List<Menu> menu(@Param("Id")int Id);


//  xóa menu theo id của admin
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from menu \n" +
            "where menu.id = :Id ")
    void deleteById(int Id);

//  thay đổi tên, giá menu của admin
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update menu\n" +
            "set foodname = :foodname , price = :price\n" +
            "where menu.id = :id")
    void changeMenu(int id, String foodname, long price);

//  Admin + Member tìm món trong menu theo name
    @Query(nativeQuery = true,value = "SELECT * FROM menu where foodname like :content")
    List<Menu> getFoodByName(
            @Param("content")String content);

//  Lấy hết menu:
    @Query(nativeQuery = true, value = "SELECT * FROM do_an.menu\n" +
            "order by id desc ")
    List<Menu> getAllMenu();


//  Tìm theo id menu để tính giá cho member
    @Query(nativeQuery = true, value = "SELECT * FROM do_an.menu\n" +
            "where id = :Id")
    List<Menu> get(@Param("Id")int Id);

//   Hiển thị food theo category
    @Query(nativeQuery = true,value = "SELECT menu.id, menu.category, foodname, price, image FROM do_an.menu\n" +
            "join category on menu.category = category.id\n" +
            "where category.id = :Id")
    List<Menu> getFoodByCategory(@Param("Id")int Id);


    @Query(nativeQuery = true,value = "SELECT * FROM do_an.menu\n" +
            "where menu.id = :Id ")
    Menu takeName(@Param("Id")int Id);

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.menu\n" +
            "where menu.id = :Id")
    List<Menu> Test(@Param("Id")int Id);

}
