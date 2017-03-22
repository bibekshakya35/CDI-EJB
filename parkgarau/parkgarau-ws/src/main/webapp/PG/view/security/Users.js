/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('pg.view.security.Users', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.users',
    //we need to make sure the user Grid panel class is already loaded
    //so we need requires
    requires: [
        'pg.view.security.UsersList'
    
    ],
    controller:'companycontroller',
    layout: {
        type: 'fit'
    },
    items: [
        {
            xtype: 'userslist'
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            flex: 1,
            dock: 'top',
            items: [
                {
                    xtype: 'button',
                    text: 'Add',
                    itemId: 'usersadd',
                    iconCls: 'fa fa-plus'
                },
                {
                    xtype: 'button',
                    text: 'Edit',
                    itemId: 'usersedit',
                    iconCls: 'fa fa-pencil-square-o'
                },
                {
                    xtype: 'button',
                    text: 'Delete',
                    itemId: 'usersdelete',
                    iconCls: 'fa fa-trash-o'
                }
            ]
        }
    ]
});


