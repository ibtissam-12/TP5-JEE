<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Gestion Produits MVC2 + Hibernate</h2>

<form action="controller" method="post">
    <input type="hidden" name="action" value="${produitEdit != null ? 'update' : 'add'}"/>

    <input type="hidden" name="idProduit" value="${produitEdit.idProduit}" />

    Nom: <input type="text" name="nom" value="${produitEdit.nom}" required/>
    Description: <input type="text" name="description" value="${produitEdit.description}" required/>
    Prix: <input type="text" name="prix" value="${produitEdit.prix}" required/>

    <input type="submit" value="${produitEdit != null ? 'Modifier' : 'Ajouter'}"/>
</form>

<hr>

<table border="1">
<tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Description</th>
    <th>Prix</th>
    <th>Actions</th>
</tr>

<c:forEach var="p" items="${listeProduits}">
<tr>
    <td>${p.idProduit}</td>
    <td>${p.nom}</td>
    <td>${p.description}</td>
    <td>${p.prix}</td>
    <td>
        <a href="controller?action=edit&id=${p.idProduit}">Modifier</a>
        <a href="controller?action=delete&id=${p.idProduit}">Supprimer</a>
    </td>
</tr>
</c:forEach>

</table>