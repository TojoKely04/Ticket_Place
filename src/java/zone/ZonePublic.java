/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone;

import bdd.BddObject;
import bdd.Connect;
import bdd.PrimaryKey;
import evenement.AchatZonePublic;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author ITU
 */
public class ZonePublic extends BddObject{
    String id_zone_public;
    String id_evenement;
    int n_place_total;
    int n_place_libre;
    double prix;

    
    public ZonePublic(){
        PrimaryKey pk = new PrimaryKey("ZP", 4, "ZonePublic_seq");
        this.setPrimaryKey(pk);
    }

    public int calculPlaceRestante(Connection connection){
        int place = this.getN_place_total();
        AchatZonePublic[] listAchat = null;
        try{
            listAchat = AchatZonePublic.getAchatEvenement(connection, this.getId_evenement());
            for(int i=0 ; i<listAchat.length ; i++){
                place-=listAchat[i].getN_place();
            }
        }catch(Exception ex){ }
        
        return place;
    }
    public ZonePublic(String id_evenement, int n_place_total, int n_place_libre, double prix) {
        PrimaryKey pk = new PrimaryKey("ZP", 4, "ZonePublic_seq");
        this.setPrimaryKey(pk);       
        
        this.setId_evenement(id_evenement);
        this.setN_place_total(n_place_total);
        this.setN_place_libre(n_place_libre);
        this.setPrix(prix);
    }

    
    public static ZonePublic getZonePublicEvenement(Connection connection , String id_evenement)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }

        String requete = "SELECT * FROM ZONEPUBLIC WHERE ID_EVENEMENT='"+id_evenement+"'";
        ArrayList<ZonePublic> listZone = (new ZonePublic()).find(connection, "ZONEPUBLIC", requete);

        if(newConnection) connection.close();
        return listZone.get(0);
    }
    public static ZonePublic getZonePublicByID(Connection connection , String id) throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM ZONEPUBLIC WHERE ID_ZONE_PUBLIC='"+id+"'";
        ArrayList<ZonePublic> list = (new ZonePublic()).find(connection, "ZonePublic", requete);
        ZonePublic zone = list.get(0);
                
        if(newConnection) connection.close();
        return zone;
    }
    
    public String getId_zone_public() {
        return id_zone_public;
    }

    public void setId_zone_public(String id_zone_public) {
        this.id_zone_public = id_zone_public;
    }
    
    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getN_place_total() {
        return n_place_total;
    }

    public void setN_place_total(int n_place_total) {
        this.n_place_total = n_place_total;
    }

    public int getN_place_libre() {
        if(n_place_libre==0) this.setN_place_libre(this.calculPlaceRestante(null));
        return n_place_libre;
    }

    public void setN_place_libre(int n_place_libre) {
        this.n_place_libre = n_place_libre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
}
