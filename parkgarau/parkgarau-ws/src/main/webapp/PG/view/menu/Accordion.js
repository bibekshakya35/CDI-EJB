Ext.define('pg.view.menu.Accordion', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.mainmenu',
    width: 300,
    layout: {
        type: 'accordion'
    },
    collapsible: true,
    hideCollapseTool: true, 
    iconCls: 'sitemap',
    title: 'Park garau'
});