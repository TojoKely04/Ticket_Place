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
public class Evenement extends BddObject{
    String id_evenement;
    String nom;
    int n_zone; // Nombre zone reservee
    Date date_evenement;
    ZoneReservee[] zone_reservees;
    ZonePublic zone_public;
    String[] initial = {"A","B","C","D","E","F","G","H","I","J"};

    public Evenement(){
        PrimaryKey pk = new PrimaryKey("E", 4, "Evenement_seq");
        this.setPrimaryKey(pk);
    }

    public Evenement(String nom , int n_zone, ZoneReservee[] zone_reservees, ZonePublic zone_public , Date date) {
        PrimaryKey pk = new PrimaryKey("E", 4, "Evenement_seq");
        this.setPrimaryKey(pk);
        this.setNom(nom);
        this.n_zone = n_zone;
        this.zone_reservees = zone_reservees;
        this.zone_public = zone_public;
        this.date_evenement = date;
    }
    
    public static Evenement getEvenementById(Connection connection , String id_evenement) throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM EVENEMENT WHERE ID_EVENEMENT='"+id_evenement+"'";
        ArrayList<Evenement> listEvenement = (new Evenement()).find(connection, "EVENEMENT", requete);
        
        if(newConnection) connection.close();
        
        return listEvenement.get(0);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }
    public void setDate_evenement(String date_evenement) {
        
        this.date_evenement = Date.valueOf(date_evenement);
    }

    
    public int getN_zone() {
        return n_zone;
    }

    public void setN_zone(int n_zone) {
        this.n_zone = n_zone;
    }

    public ZoneReservee[] getZone_reservees()throws Exception {
        if(zone_reservees==null){
            ZoneReservee[] z = ZoneReservee.getZoneReserveeEvenement(null, this.getId_evenement());
            this.setZone_reservees(z);
        }
        return zone_reservees;
    }

    public void setZone_reservees(ZoneReservee[] zone_reservees) {
        this.zone_reservees = zone_reservees;
    }

    public ZonePublic getZone_public()throws Exception{
        if(zone_public==null){
            ZonePublic z = ZonePublic.getZonePublicEvenement(null, this.getId_evenement());
            this.setZone_public(z);
        }
        return zone_public;
    }

    public void setZone_public(ZonePublic zone_public) {
        this.zone_public = zone_public;
    }

    public String[] getInitial() {
        return initial;
    }

    public void setInitial(String[] initial) {
        this.initial = initial;
    }
    
    
}
