<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Bericht Detail</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Bericht Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Berichtid:"/>
                    <h:outputText value="#{bericht.bericht.berichtid}" title="Berichtid" />
                    <h:outputText value="Datum:"/>
                    <h:outputText value="#{bericht.bericht.datum}" title="Datum" />
                    <h:outputText value="Gang:"/>
                    <h:outputText value="#{bericht.bericht.gang}" title="Gang" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{bericht.bericht.name}" title="Name" />
                    <h:outputText value="Status:"/>
                    <h:outputText value="#{bericht.bericht.status}" title="Status" />
                    <h:outputText value="FkFiliale:"/>
                    <h:panelGroup>
                        <h:outputText value="#{bericht.bericht.fkFiliale}"/>
                        <h:panelGroup rendered="#{bericht.bericht.fkFiliale != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{filiale.detailSetup}">
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht.fkFiliale][filiale.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="bericht"/>
                                <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.BerichtController1"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{filiale.editSetup}">
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht.fkFiliale][filiale.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="bericht"/>
                                <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.BerichtController1"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{filiale.destroy}">
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht.fkFiliale][filiale.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="bericht"/>
                                <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.BerichtController1"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{bericht.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{bericht.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][bericht.bericht][bericht.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{bericht.createSetup}" value="New Bericht" />
                <br />
                <h:commandLink action="#{bericht.listSetup}" value="Show All Bericht Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
