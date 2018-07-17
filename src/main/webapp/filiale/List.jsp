<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Filiale Items</title>
            <link rel="stylesheet" type="text/css" href="/hardliner-1.0/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Filiale Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Filiale Items Found)<br />" rendered="#{filiale.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{filiale.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{filiale.pagingInfo.firstItem + 1}..#{filiale.pagingInfo.lastItem} of #{filiale.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{filiale.prev}" value="Previous #{filiale.pagingInfo.batchSize}" rendered="#{filiale.pagingInfo.firstItem >= filiale.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{filiale.next}" value="Next #{filiale.pagingInfo.batchSize}" rendered="#{filiale.pagingInfo.lastItem + filiale.pagingInfo.batchSize <= filiale.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{filiale.next}" value="Remaining #{filiale.pagingInfo.itemCount - filiale.pagingInfo.lastItem}"
                                   rendered="#{filiale.pagingInfo.lastItem < filiale.pagingInfo.itemCount && filiale.pagingInfo.lastItem + filiale.pagingInfo.batchSize > filiale.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{filiale.filialeItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Filialid"/>
                            </f:facet>
                            <h:outputText value="#{item.filialid}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Filialname"/>
                            </f:facet>
                            <h:outputText value="#{item.filialname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Bundesland"/>
                            </f:facet>
                            <h:outputText value="#{item.bundesland}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Region"/>
                            </f:facet>
                            <h:outputText value="#{item.region}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{filiale.detailSetup}">
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][filiale.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{filiale.editSetup}">
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][filiale.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{filiale.remove}">
                                <f:param name="jsfcrud.currentFiliale" value="#{jsfcrud_class['de.hspf.hardliner.view.bericht.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][filiale.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{filiale.createSetup}" value="New Filiale"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
