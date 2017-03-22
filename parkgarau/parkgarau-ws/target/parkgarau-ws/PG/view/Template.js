/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('pg.view.Template', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.template',
    requires: ['pg.view.include.Header', 'pg.view.MainPanel'],
    layout: {
        type: 'border'
    },
    items: [
        {//from menu/accordion
            xtype: 'mainmenu',
            width: 185,
            collapsible: true,
            region: 'west'
        }, {
            xtype: 'appheader',
            region: 'north'
        }, {
            xtype: 'mainpanel',
            region: 'center'
        }, {
            xtype: 'container',
            region: 'south',
            heigth: 30,
            style: 'border-top :1px solid $4c72a4;',
            html: '<div id="titleHeader"><center><span style="font-size:10px;">ParkGarau http://drose.com.np</span></center></div>'
        }
    ]

}
);


