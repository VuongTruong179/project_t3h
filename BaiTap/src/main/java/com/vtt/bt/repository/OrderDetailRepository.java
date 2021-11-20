package com.vtt.bt.repository;

import com.vtt.bt.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {


    //    Xóa order-detail
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from order_details\n" +
            "where order_details.id = :Id ")
    void deleteOrderById(@Param("Id") int Id);


    //update tổng tiền:
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update oder\n" +
            "set sum_all_total_oder = (SELECT sum(total_price)as total_price\n" +
            "from order_details\n" +
            "join menu on order_details.menu_id = menu.id\n" +
            "where order_details.id_table = :Id and (order_details.created_time between :TimeA and :TimeB ))\n" +
            "where oder.id_of_table = :Id ")
    void getSumOfBill(@Param("Id") int Id,
                      @Param("TimeA") String TimeA,
                      @Param("TimeB") String TimeB);


    //   thay đổi số lượng mua:
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update order_details\n" +
            "join menu on menu.id = order_details.menu_id\n" +
            "set quantity  = :quantity , total_price =  quantity * price \n" +
            "where order_details.id = :Id ")
    void changeOderDetail(@Param("quantity") int quantity,
                          @Param("Id") int Id);

    //  Hiển thị đơn hàng của bàn đang ăn, những đơn trùng nhau cùng 1 người đặt, tự cộng lại:
    @Query(nativeQuery = true, value = "select order_details.id, order_details.id_table, order_details.menu_id, sum(quantity) as quantity,sum(total_price) as total_price, order_details.created_time\n" +
            "from order_details\n" +
            "where ( order_details.id_table = :Id ) and (created_time between :TimeA and :TimeB )\n" +
            "group by order_details.id_table, order_details.menu_id")
    List<OrderDetail> takeOder(@Param("Id") int Id,
                               @Param("TimeA") String TimeA,
                               @Param("TimeB") String TimeB);

    //  Admin Thống kê sản phẩm đã bán:
    @Query(nativeQuery = true, value = "select menu.id, menu.foodname, menu.category, menu.price, menu.image,\n" +
            "order_details.id, order_details.id_table,  order_details.menu_id, sum(quantity) as quantity,  \n" +
            "order_details.created_time, sum(total_price) as total_price\n" +
            "from order_details\n" +
            "join menu on menu.id = order_details.menu_id\n" +
            "where  created_time between :TimeA and :TimeB \n" +
            "group by order_details.menu_id")
    List<OrderDetail> takeSell(@Param("TimeA") String A,
                               @Param("TimeB") String B);


    @Query(nativeQuery = true,value = "select menu.id, menu.foodname, menu.category, menu.price, menu.image,order_details.id, order_details.id_table, order_details.menu_id, sum(quantity) as quantity, order_details.created_time\n" +
            "from order_details\n" +
            "join menu on menu.id = order_details.menu_id\n" +
            "where ( order_details.id_table = :Id ) and (created_time between :TimeA and :TimeB )\n" +
            "group by order_details.id_table, order_details.menu_id; ")
    List<OrderDetail> Test(@Param("Id") int Id,
                           @Param("TimeA") String TimeA,
                           @Param("TimeB") String TimeB);


}