<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Filiale</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Filiale</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Filialid:"/>
                    <h:outputText value="#{filiale.filiale.filialid}" title="Filialid" />
                    <h:outputText value="Filialname:"/>
                    <h:inputText id="filialname" value="#{filiale.filiale.filialname}" title="Filialname" />
                    <h:outputText value="Bundesland:"/>
                    <h:inputText id="bundesland" value="#{filiale.filiale.bundesland}" title="Bundesland" />
                    <h:outputText value="Region:"/>
                    <h:inputText id="region" value="#{filiale.filiale.region}" title="Region" />
                    <h:outputText value="BerichtCollection:"/>
                    <h:selectManyListbox id="berichtCollection" value="#{filiale.filiale.jsfcrud_transform[jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method.arrayToList].berichtCollection}" title="BerichtCollection" size="6" converter="#{bericht.converter}" >
                        <f:selectItems value="#{bericht.berichtItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{filiale.edit}" value="Save">
                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{filiale.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{filiale.listSetup}" value="Show All Filiale Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
