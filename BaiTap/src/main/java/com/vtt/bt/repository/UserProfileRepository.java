package com.vtt.bt.repository;


import com.vtt.bt.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.user_profile")
    List<UserProfile> getAllAccout();

    //  Kiểm tra tk tổn tại để Lập tài khoản
    @Query(nativeQuery = true, value = "select id, username, password, address, email,fullname, created_time, number_phone " +
            " from user_profile where email = :email or username = :user_name ")
    List<UserProfile> addUser(
            @Param("email") String email,
            @Param("user_name") String username
    );
    @Query(nativeQuery = true,value = "select id, username, password, address, email,fullname, created_time, number_phone \n" +
            "from user_profile where user_profile.id = :Id")
    UserProfile getAccout(@Param("Id")int Id);

    //  Xóa tk dành cho admin:
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user_profile\n" +
            "where id = :Id")
    void deleteAccout(@Param("Id") int Id);


    //  login
    @Query(nativeQuery = true, value = "SELECT * FROM user_profile where username = :username")
    List<UserProfile> login(
            @Param("username") String username
    );

    //  thay đổi mật khẩu
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update do_an.user_profile\n" +
            "set password  = :password\n" +
            "where  user_profile.id = :userId  ")
    void changePassword(String password,
                        int userId
    );

    //  Admin Tìm tài khoản theo email
    @Query(nativeQuery = true, value = "SELECT * FROM user_profile where email = :content ")
    UserProfile getUserByEmail(
            @Param("content") String content
    );

    //    Kiểm Tra:
    @Query(nativeQuery = true, value = "SELECT * FROM do_an.user_profile\n" +
            "where user_profile.id = :userId")
    List<UserProfile> get(int userId);


    //  Lập tk đồng thời thêm vai trò
    @Query(nativeQuery = true, value = "SELECT * FROM do_an.user_profile\n" +
            "where username = :username ")
    List<UserProfile> getUserByName(
            @Param("username") String username);

    //    Lấy Id của admin để thêm vào chức năng ib admin
    @Query(nativeQuery = true, value = "SELECT user_profile.id FROM do_an.user_profile\n" +
            "where email = 'vuongtattruong1999@gmail.com'")
    int getId();

}