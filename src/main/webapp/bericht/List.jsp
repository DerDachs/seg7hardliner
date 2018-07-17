<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Bericht Items</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Bericht Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Bericht Items Found)<br />" rendered="#{bericht.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{bericht.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{bericht.pagingInfo.firstItem + 1}..#{bericht.pagingInfo.lastItem} of #{bericht.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{bericht.prev}" value="Previous #{bericht.pagingInfo.batchSize}" rendered="#{bericht.pagingInfo.firstItem >= bericht.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{bericht.next}" value="Next #{bericht.pagingInfo.batchSize}" rendered="#{bericht.pagingInfo.lastItem + bericht.pagingInfo.batchSize <= bericht.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{bericht.next}" value="Remaining #{bericht.pagingInfo.itemCount - bericht.pagingInfo.lastItem}"
                                   rendered="#{bericht.pagingInfo.lastItem < bericht.pagingInfo.itemCount && bericht.pagingInfo.lastItem + bericht.pagingInfo.batchSize > bericht.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{bericht.berichtItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{bericht.editSetup}">
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{bericht.remove}">
                                <f:param name="jsfcrud.currentBericht" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][bericht.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{bericht.createSetup}" value="New Bericht"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
