Ext.define('pg.view.MainPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.mainpanel',
    activeTab: 0,
    items: [
        {
            xtype: 'panel',
            closable: false,
            iconCls: 'fa fa-home',
            title: 'Home'
        }
    ]
});