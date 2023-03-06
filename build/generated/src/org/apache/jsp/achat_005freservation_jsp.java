package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import evenement.Evenement;
import evenement.Promotion;
import zone.ZonePublic;
import zone.ZoneReservee;

public final class achat_005freservation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    Promotion[] promotion = new Promotion[3];
    ZoneReservee[] zoneReservee = new ZoneReservee[3];
    for (int i=0;i<3 ;i++ ) {
        zoneReservee[i]=new ZoneReservee();
        zoneReservee[i].setNom("ZONE");
        zoneReservee[i].setN_place(10);
        zoneReservee[i].setNum_debut(1);
        zoneReservee[i].setNum_fin(10);
        zoneReservee[i].setPrix(1000);

        promotion[i]=new Promotion();
        promotion[i].setReduction(20);
        promotion[i].setDebut("2022-12-12");
        promotion[i].setFin("2022-12-12");
        promotion[i].setZoneReservee(zoneReservee[i]);
    }

    ZonePublic zonePublic = new ZonePublic();
    zonePublic.setN_place_libre(10);
    zonePublic.setPrix(2000);



      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Achat ou Reservation</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <form action=\"\" method=\"get\">\n");
      out.write("            <h2>ZONE PUBLIC</h2>\n");
      out.write("            <p>\n");
      out.write("                Place Libre : ");
      out.print( zonePublic.getN_place_libre() );
      out.write("  |   Prix : ");
      out.print( zonePublic.getPrix() );
      out.write("        \n");
      out.write("            </p>\n");
      out.write("            <input type=\"number\" name=\"place_public\" placeholder=\"nombre de place\">     \n");
      out.write("            <input type=\"submit\" value=\"Acheter\">\n");
      out.write("        </form>    \n");
      out.write("\n");
      out.write("        <form action=\"\" method=\"get\">\n");
      out.write("            <h2>ZONE RESERVEE</h2>\n");
      out.write("            <table border=\"1\" width=\"100%\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>Nom</th>\n");
      out.write("                    <th>Nombre De Place</th>\n");
      out.write("                    <th>Numero Debut</th>\n");
      out.write("                    <th>Numero Fin</th>\n");
      out.write("                    <th>Prix</th>  \n");
      out.write("                </tr>\n");
      out.write("                  \n");
      out.write("                ");
 for (int i=0 ; i<zoneReservee.length ;i++ ) { 
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( zoneReservee[i].getNom() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( zoneReservee[i].getN_place() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( zoneReservee[i].getNum_debut() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( zoneReservee[i].getNum_fin() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( zoneReservee[i].getPrix() );
      out.write("</td>\n");
      out.write("                        <td><input type=\"text\" name=\"place_reservee\"></td>\n");
      out.write("                        <td><input type=\"submit\" value=\"Reserver\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        ");
 if(promotion!=null) { 
      out.write("\n");
      out.write("            <h2>LISTE PROMOTION</h2>\n");
      out.write("            ");
 for (int i=0 ; i<promotion.length ;i++ ) { 
      out.write("\n");
      out.write("                <p>\n");
      out.write("                    La ");
      out.print( promotion[i].getZoneReservee().getNom() );
      out.write(" reduit de \n");
      out.write("                    ");
      out.print( promotion[i].getReduction() );
      out.write("% du \n");
      out.write("                    ");
      out.print( promotion[i].getDebut() );
      out.write(" jusqu'au \n");
      out.write("                    ");
      out.print( promotion[i].getFin() );
      out.write("\n");
      out.write("                </p>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("        <form action=\"\" method=\"get\">\n");
      out.write("            <h2>CONFIRMER UNE RESERVATION</h2>\n");
      out.write("            <p>\n");
      out.write("                ID De Reservation : <input type=\"number\" name=\"id_reservation\">\n");
      out.write("                <input type=\"submit\" value=\"Valider\">\n");
      out.write("            </p>\n");
      out.write("        </form> \n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
