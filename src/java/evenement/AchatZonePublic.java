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
import java.util.ArrayList;
import zone.ZonePublic;

/**
 *
 * @author ITU
 */
public class AchatZonePublic extends BddObject{
    String id;
    String id_evenement;
    String id_zone_public;
    int n_place;
    double prix;
    ZonePublic zone_public;

    public AchatZonePublic(){
        PrimaryKey pk = new PrimaryKey("A", 4, "achatzonepublic_seq");
        this.setPrimaryKey(pk);
    }
    
    public static AchatZonePublic[] getAchatEvenement(Connection connection , String id_achat)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
            
        String requete = "SELECT * FROM ACHATZONEPUBLIC WHERE ID_EVENEMENT='"+id_achat+"'";
        ArrayList<AchatZonePublic> listAchat = (new AchatZonePublic()).find(connection, "AchatZonePublic",requete );
        AchatZonePublic[] achat = new AchatZonePublic[listAchat.size()];
        for(int i=0 ; i<achat.length ;i++){
            achat[i]=listAchat.get(i);
        }
        
        if(newConnection) connection.close();
        return achat;
    }
    
    public static AchatZonePublic getAchatByID(Connection connection , String id)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }   
        
        String requete = "SELECT * FROM ACHATZONEPUBLIC WHERE ID='"+id+"'";
        ArrayList<AchatZonePublic> listAchat = (new AchatZonePublic()).find(connection, "ACHATZONEPUBLIC", requete);
        
        if(newConnection) connection.close();
        return listAchat.get(0);
    }
    public double calculPrix()throws Exception{
        double prix=this.getZone_public().getPrix()*this.getN_place();
        return prix;
    }
    
    public ZonePublic getZone_public()throws Exception {
        if(zone_public==null){
            ZonePublic z = ZonePublic.getZonePublicByID(null, this.getId_zone_public());
            this.setZone_public(z);
        }
        return zone_public;
    }

    public void setZone_public(ZonePublic zone_public) {
        this.zone_public = zone_public;
    }

    
    
    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getId_zone_public() throws Exception {
        return id_zone_public;
    }

    public void setId_zone_public(String id_zone_public) {
        this.id_zone_public = id_zone_public;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getN_place() {
        return n_place;
    }

    public void setN_place(int n_place) {
        this.n_place = n_place;
    }

    public double getPrix() throws Exception{
        if(prix==0) this.setPrix(this.calculPrix());
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
}
