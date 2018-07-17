<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Filiale Detail</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Filiale Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Filialid:"/>
                    <h:outputText value="#{filiale.filiale.filialid}" title="Filialid" />
                    <h:outputText value="Filialname:"/>
                    <h:outputText value="#{filiale.filiale.filialname}" title="Filialname" />
                    <h:outputText value="Bundesland:"/>
                    <h:outputText value="#{filiale.filiale.bundesland}" title="Bundesland" />
                    <h:outputText value="Region:"/>
                    <h:outputText value="#{filiale.filiale.region}" title="Region" />

                    <h:outputText value="BerichtCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty filiale.filiale.berichtCollection}" value="(No Items)"/>
                        <h:dataTable value="#{filiale.filiale.berichtCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty filiale.filiale.berichtCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Berichtid"/>
                                </f:facet>
                                <h:outputText value="#{item.berichtid}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Datum"/>
                                </f:facet>
                                <h:outputText value="#{item.datum}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Gang"/>
                                </f:facet>
                                <h:outputText value="#{item.gang}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value="#{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Status"/>
                                </f:facet>
                                <h:outputText value="#{item.status}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="FkFiliale"/>
                                </f:facet>
                                <h:outputText value="#{item.fkFiliale}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{bericht.detailSetup}">
                                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="filiale" />
                                    <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.FilialeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{bericht.editSetup}">
                                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="filiale" />
                                    <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.FilialeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{bericht.destroy}">
                                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="filiale" />
                                    <f:param name="jsfcrud.relatedControllerType" value="de.hspf.hardliner.view.bericht.FilialeController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{filiale.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{filiale.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][filiale.filiale][filiale.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{filiale.createSetup}" value="New Filiale" />
                <br />
                <h:commandLink action="#{filiale.listSetup}" value="Show All Filiale Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
