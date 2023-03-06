
import bdd.Connect;
import evenement.AchatZonePublic;
import evenement.Reservation;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import zone.ZonePublic;
import zone.ZoneReservee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ITU
 */
public class Main {
    public static void main(String[] args)throws Exception{
//        Connection c = Connect.getConnection();
        Reservation r = new Reservation();
        r.setPlace("1,2,10,23");
        String s = r.changePlace();
        System.out.println(s.split(",").length);
//        c.close();
    }
}
