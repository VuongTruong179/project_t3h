package com.vtt.bt.repository;

import com.vtt.bt.model.entity.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessergerRepository extends JpaRepository<Messenger,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM do_an.messenger\n" +
            "where sender = :userId  or receiver = :userId " +
            "order by created_time desc")
    List<Messenger> showMessage(@Param("userId")int userId);

    @Query(nativeQuery = true,value = "select  messenger.id, messenger.sender, messenger.receiver, messenger.content, messenger.created_time\n" +
            "from user_profile\n" +
            "join \n" +
            "(select\n" +
            "\t(case\n" +
            "\t\twhen messenger.sender = :userId then messenger.receiver\n" +
            "        when messenger.receiver = :userId then messenger.sender \n" +
            "        end) as friend_id ,max(messenger.id) as max_id\n" +
            "        from messenger where messenger.sender = :userId or messenger.receiver = :userId\n" +
            "        group by\n" +
            "\t(case\n" +
            "        when messenger.sender = :userId then messenger.receiver\n" +
            "        when messenger.receiver = :userId then messenger.sender end)) as lastmessage\n" +
            "on user_profile.id = lastmessage.friend_id\n" +
            "join messenger on ( user_profile.id = messenger.sender or user_profile.id = messenger.receiver )\n" +
            "and ( lastmessage.max_id = messenger.id )\n" +
            "where (messenger.sender = :userId or messenger.receiver = :userId)\n" +
            "order by messenger.created_time DESC;")
    List<Messenger> showLastMessage(@Param("userId")int userId);

}
