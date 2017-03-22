Ext.define('pg.view.include.Header', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.appheader',
    height: 30,
    ui: 'footer',
    style: 'border-bottom: 4px solid #4c72a4;',
    items: [
        {
            xtype: 'label',
            html: '<div id="titleHeader">ParkGarau<span style="font-size:12px;"> -Drose Technology</span></div>'
        },
        {
            xtype: 'tbfill'
        },
        {
            xtype: 'tbseparator'
        },
        {
            xtype: 'button',
            text: "logout",
            itemId: 'logout',
            iconCls: 'fa fa-sign-out',
            cls: 'btn btn-danger'
        }
    ]
});