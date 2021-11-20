package com.vtt.bt.repository;

import com.vtt.bt.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {

//    kiểm tra quyền
    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM do_an.rol \n" +
            "join user_rol on rol.id = user_rol.rol_id\n" +
            "join user_profile on user_profile.id = user_rol.user_of_id\n" +
            "where user_profile.id = :userId")
    List<Rol> getNameRol(int userId);
}