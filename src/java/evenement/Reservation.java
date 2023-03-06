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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import zone.ZoneReservee;

/**
 *
 * @author ITU
 */
public class Reservation extends BddObject{
    String id_reservation;
    String id_evenement;
    String id_zone_reservee;
    String place; // avy anaty base 1,2,3,5,6...
    int[] liste_place; // String place => int[]
    int confirmation;// 0:non confirmé 1:confirmé
    int annule;
    double prix;
    String date_time_reservation;
    ZoneReservee zone;

    public Reservation(){
        PrimaryKey pk = new PrimaryKey("R", 4, "Reservation_seq");
        this.setPrimaryKey(pk);
        this.setConfirmation(0);
        this.setAnnule(0);
    }
    
    public Reservation(String id_evenement, String id_zone, String place, int[] liste_place, int confirmation,int annule) {
        PrimaryKey pk = new PrimaryKey("R", 4, "Reservation_seq");
        this.setPrimaryKey(pk);
        this.id_evenement = id_evenement;
        this.id_zone_reservee = id_zone;
        this.place = place;
        this.liste_place = liste_place;
        this.confirmation = confirmation;
        this.annule=annule;
    }
    
    public void estDansLaZone()throws Exception
    {
        boolean valide = true;
        String numeroInvalide = new String();
        for(int i=0 ;i<this.getListe_place().length ; i++){
            if(this.getListe_place()[i]<this.getZone().getNum_debut() || this.getListe_place()[i]>this.getZone().getNum_fin()){
                valide=false;
                numeroInvalide+=String.valueOf(this.getListe_place()[i])+" ";
            }
        }
        if(!valide) throw new Exception("Les numeros : "+numeroInvalide+" ne sont pas dans la "+this.getZone().getNom());
    }
    
    public boolean estReservee(int place , int[] listePlace , int annule , int confirmation){
        boolean estReservee = false ; 
        for(int i=0 ;i<listePlace.length ; i++){
            if(listePlace[i]==place && (annule==0 || confirmation==1)) estReservee=true;
        }
        return estReservee;
    }
    
    public void annuler(Connection connection)throws Exception{
        this.setAnnule(1);
        this.update(connection, "Reservation");
    }
    
    public void setPrix(Connection connection , Date date , double prix_initial)throws Exception{

        if(date==null) date = Date.valueOf(LocalDate.now());
        double prix = prix_initial;
        Promotion[] promotion = Promotion.getPromotionZone(connection, this.getId_evenement(), this.getId_zone_reservee());
        for(int i=0 ; i<promotion.length ; i++){
            if(date.compareTo(promotion[i].getDebut())>=0 && date.compareTo(promotion[i].getFin())<=0){
                prix = prix-(prix*promotion[i].getReduction()/100);
                this.setPrix(prix);
            }
        }
        prix=prix*this.getListe_place().length;
        this.setPrix(prix);
    }
    
    public static void verificationDelai(Connection connection , Reservation reservation)throws Exception{
        ZoneReservee zoneReservee = ZoneReservee.getZoneReserveeById(connection, reservation.getId_zone_reservee());
        if(zoneReservee.getDelai()!=-1){
            String DTreservation = reservation.getDate_time_reservation();
            double delai = zoneReservee.getDelai();
            LocalDateTime date_time_now = LocalDateTime.now();
            LocalDateTime date_time_reservation = LocalDateTime.parse(DTreservation);
            LocalDateTime reservationDelai = date_time_reservation.plusMinutes((long) delai);
            if(reservationDelai.compareTo(date_time_now)<0 && reservation.getConfirmation()==0) reservation.annuler(connection);
        }
    }
    
    public void verificationPlace(Connection connection)throws Exception{
        this.estDansLaZone();
        boolean reserver=false;
        String placeReservee=new String();
        Reservation[] listReservation = Reservation.getReservationEvenement(connection,this.getId_evenement(),this.getId_zone_reservee());
        
        for(int i=0 ; i<this.getListe_place().length ; i++){
            for(int j=0 ; j<listReservation.length ; j++){
                Reservation.verificationDelai(connection, listReservation[j]);
                if(estReservee(this.getListe_place()[i], listReservation[j].getListe_place(),listReservation[j].getAnnule(),listReservation[j].getConfirmation())){
                    placeReservee+=this.getListe_place()[i]+" ";
                    reserver=true;
                }
            }
        }
        
        if(reserver==true) throw new Exception("Les places "+placeReservee+" sont deja reservee");
    }
        
    public static Reservation getReservationByID(Connection connection , String id_reservation)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM RESERVATION WHERE ID_RESERVATION='"+id_reservation+"'";
        ArrayList<Reservation> listReservation = (new Reservation()).find(connection, "RESERVATION", requete);
        
        if(newConnection) connection.close();
        return listReservation.get(0);
    }
    
    public static Reservation[] getReservationEvenement(Connection connection , String id_evenement , String id_zone_reservee)throws Exception{
        boolean newConnection = false;
        if(connection==null){
            connection = Connect.getConnection();
            newConnection=true;
        }
        
        String requete = "SELECT * FROM RESERVATION WHERE ID_EVENEMENT='"+id_evenement+"' and id_zone_reservee='"+id_zone_reservee+"'";
        ArrayList<Reservation> listReservation = (new Reservation()).find(connection, "RESERVATION", requete);
        Reservation[] reservations = new Reservation[listReservation.size()];
        for(int i=0 ; i<reservations.length ; i++){
            reservations[i]=listReservation.get(i);
        }
        
        if(newConnection) connection.close();
        return reservations;
    }
    
    
    public String changePlace(){
        boolean change = false;
        String result="";
        String place = this.getPlace();
        String[] listePlace = place.split(",");
        for(int i=0 ; i < listePlace.length ; i++){
            if(listePlace[i].equals("...")){
                String newS ="";
                int fin=Integer.parseInt(listePlace[i+1]);
                int debut=Integer.parseInt(listePlace[i-1]);
                for(int j=debut+1 ; j<fin ; j++ ){
                    newS+=j;
                    if(j!=fin-1) newS+=",";
                }
                listePlace[i]=newS;
            }
        }
        for(int i=0 ; i < listePlace.length ; i++){
            result+=listePlace[i];
            if(i!=listePlace.length-1) result+=",";
        }
        if(result=="") return place;
        return result;
    }
    public ZoneReservee getZone()throws Exception {
        if(zone==null){
            this.setZone(ZoneReservee.getZoneReserveeById(null, this.getId_zone_reservee()));
        }
        return zone;
    }

    public void setZone(ZoneReservee zone) {
        this.zone = zone;
    }
    
    public String getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(String id_evenement) {
        this.id_evenement = id_evenement;
    }
    public String getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(String id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String getId_zone_reservee() {
        return id_zone_reservee;
    }

    public void setId_zone_reservee(String id_zone_reservee) {
        this.id_zone_reservee = id_zone_reservee;
    }

    public String getDate_time_reservation() {
        return date_time_reservation;
    }

    public void setDate_time_reservation(String date_time_reservation) {
        this.date_time_reservation = date_time_reservation;
    }


    public String getPlace() {
        
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int[] getListe_place() {
        if(this.liste_place==null){
            int[] liste = new int[this.getPlace().split(",").length];
            for(int i=0 ; i<liste.length ; i++){
                liste[i]=Integer.parseInt(this.getPlace().split(",")[i].trim());
            }
            this.setListe_place(liste);
        }
        return liste_place;
    }

    public void setListe_place(int[] liste_place) {
        this.liste_place = liste_place;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }


    public int getAnnule() {
        return annule;
    }

    public void setAnnule(int annule) {
        this.annule = annule;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
}
