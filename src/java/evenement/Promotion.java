/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;

import bdd.BddObject;
import bdd.Connect;
import bdd.PrimaryKey;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import zone.ZonePublic;
import zone.ZoneReservee;

/**
 *
 * @author ITU
 */
public class Promotion extends BddObject{
    
    String id_promotion;
    String id_evenement;
    String id_zone_reservee;
    double reduction;
    Date debut;
    Date fin;
    ZoneReservee zoneReservee;

    public Promotion(){
        PrimaryKey pk = new PrimaryKey("P", 4, "Promotion_seq");
        this.setPrimaryKey(pk);
    }

    public Promotion(String id_evenement, String id_zone, double reduction, Date debut, Date fin, ZoneReservee zoneReservee, ZonePublic zonePublic) {
        PrimaryKey pk = new PrimaryKey("P", 4, "Promotion_seq");
        this.setPrimaryKey(pk);
        
        this.id_evenement = id_evenement;
        this.id_zone_reservee = id_zone;
        this.reduction = reduction;
        this.debut = debut;
        this.fin = fin;
        this.zoneReservee = zoneReservee;
    }
    
    public static Promotion getPromotionByID(Connection connection , String id_promotion)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM PROMOTION WHERE ID_PROMOTION='"+id_promotion+"'";
        ArrayList<Promotion> listPromotion = (new Promotion()).find(connection, "PROMOTION", requete);
        
        if(newConnection) connection.close();
        return listPromotion.get(0);
    }
    
    public static Promotion[] getPromotionZone(Connection connection , String id_evenement , String id_zone_reservee)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM PROMOTION WHERE ID_Evenement='"+id_evenement+"' and id_zone_reservee='"+id_zone_reservee+"'";
        ArrayList<Promotion> listPromotion = (new Promotion()).find(connection, "PROMOTION", requete);
        Promotion[] promotions = new Promotion[listPromotion.size()];
        for(int i=0 ; i<promotions.length ; i++){
            promotions[i]=listPromotion.get(i);
        }
        
        if(newConnection) connection.close();
        return promotions;
    }
    public static Promotion[] getPromotionEvenement(Connection connection , String id_evenement)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }

        String requete = "SELECT * FROM PROMOTION WHERE ID_EVENEMENT='"+id_evenement+"'";
        ArrayList<Promotion> listPromotion = (new Promotion()).find(connection, "PROMOTION", requete);
        Promotion[] promotions = new Promotion[listPromotion.size()];
        for(int i=0 ; i<promotions.length ; i++){
            promotions[i]=listPromotion.get(i);
        }
        
        if(newConnection) connection.close();
        return promotions;
    }
    
    public String getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(String id_promotion) {
        this.id_promotion = id_promotion;
    }

    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getId_zone_reservee() {
        return id_zone_reservee;
    }

    public void setId_zone_reservee(String id_zone_reservee) {
        this.id_zone_reservee = id_zone_reservee;
    }



    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }
    
    public void setDebut(String debut) {
        this.debut = Date.valueOf(debut);
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setFin(String fin) {
        this.fin = Date.valueOf(fin);
    }
    public ZoneReservee getZoneReservee() throws Exception{
        if(zoneReservee==null) this.setZoneReservee(ZoneReservee.getZoneReserveeById(null, this.getId_zone_reservee()));
        return zoneReservee;
    }

    public void setZoneReservee(ZoneReservee zoneReservee) {
        this.zoneReservee = zoneReservee;
    }

}
