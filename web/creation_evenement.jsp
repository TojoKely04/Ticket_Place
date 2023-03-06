    <%-- 
    Document   : creation_evenement
    Created on : 28 janv. 2023, 18:56:03
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CreerEvenement" method="">
            <h2>Creer un evenement</h2>
            <p>Nom : <input type="text" name="nom"  required></p>
            <p>Date : <input type="date" name="date" required></p>
            <p>Nombre De Zone Privee: <input type="number" name="privee" required></p>
            <p>Nombre De Place Publique : <input type="number" name="public" required></p>
            <p>Prix (Place Publique) : <input type="number" name="prix" required></p>
            <p><input type="submit" value="Valider"></p>
        </form>
    </body>
</html>
