/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone;

import bdd.BddObject;
import bdd.Connect;
import bdd.PrimaryKey;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author ITU
 */
public class ZoneReservee extends BddObject{
    String id_zone_reservee;
    String id_evenement;
    String nom;
    int n_place;
    int num_debut;
    int num_fin;
    double prix;
    double delai;
    
    public ZoneReservee(){
        PrimaryKey pk = new PrimaryKey("ZR", 4, "ZoneReservee_seq");
        this.setPrimaryKey(pk);
    }

    public ZoneReservee(String id_Evmt , String nom , int nPlace , int numDebut ,int numFin , double prix , double delai){
        PrimaryKey pk = new PrimaryKey("ZR", 4, "ZoneReservee_seq");
        this.setPrimaryKey(pk);
        
        this.setId_evenement(id_Evmt);
        this.setNom(nom);
        this.setN_place(nPlace);
        this.setNum_debut(numDebut);
        this.setNum_fin(numFin);
        this.setPrix(prix);
        this.setDelai(delai);
    }
    
    public static ZoneReservee[] getZoneReserveeEvenement(Connection connection , String id_evenement)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM ZONERESERVEE WHERE ID_EVENEMENT='"+id_evenement+"'";
        ArrayList<ZoneReservee> listZone = (new ZoneReservee()).find(connection, "ZONERESERVEE", requete);
        ZoneReservee[] zones = new ZoneReservee[listZone.size()];
        for(int i=0 ; i < zones.length ; i++){
            zones[i]=listZone.get(i);
        }
        
        if(newConnection) connection.close();
        return zones;
    }
    
    public static ZoneReservee getZoneReserveeById(Connection connection , String id_zone_reservee)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        String requete = "SELECT * FROM ZONERESERVEE WHERE ID_ZONE_RESERVEE='"+id_zone_reservee+"'";
        ArrayList<ZoneReservee> listZone = (new ZoneReservee()).find(connection, "ZONERESERVEE", requete);
        if(newConnection) connection.close();
        
        return listZone.get(0);
    }
    
    public String getId_zone_reservee() {
        return id_zone_reservee;
    }

    public void setId_zone_reservee(String id_zone_reservee) {
        this.id_zone_reservee = id_zone_reservee;
    }

    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getN_place() {
        return n_place;
    }

    public void setN_place(int n_place) {
        this.n_place = n_place;
    }

    public int getNum_debut() {
        return num_debut;
    }

    public void setNum_debut(int num_debut) {
        this.num_debut = num_debut;
    }

    public int getNum_fin() {
        return num_fin;
    }

    public void setNum_fin(int num_fin) {
        this.num_fin = num_fin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getDelai() {
        return delai;
    }

    public void setDelai(double delai) {
        this.delai = delai;
    }
    
    
}
