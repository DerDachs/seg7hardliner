<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Bericht</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Bericht</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Berichtid:"/>
                    <h:outputText value="#{bericht.bericht.berichtid}" title="Berichtid" />
                    <h:outputText value="Datum:"/>
                    <h:inputText id="datum" value="#{bericht.bericht.datum}" title="Datum" />
                    <h:outputText value="Gang:"/>
                    <h:inputText id="gang" value="#{bericht.bericht.gang}" title="Gang" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{bericht.bericht.name}" title="Name" />
                    <h:outputText value="Status:"/>
                    <h:inputText id="status" value="#{bericht.bericht.status}" title="Status" />
                    <h:outputText value="FkFiliale:"/>
                    <h:selectOneMenu id="fkFiliale" value="#{bericht.bericht.fkFiliale}" title="FkFiliale" >
                        <f:selectItems value="#{filiale.filialeItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{bericht.edit}" value="Save">
                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{bericht.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{bericht.listSetup}" value="Show All Bericht Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
