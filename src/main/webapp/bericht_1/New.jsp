<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Bericht</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Bericht</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{bericht.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Berichtid:"/>
                    <h:inputText id="berichtid" value="#{bericht.bericht.berichtid}" title="Berichtid" required="true" requiredMessage="The berichtid field is required." />
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
                <h:commandLink action="#{bericht.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{bericht.listSetup}" value="Show All Bericht Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
