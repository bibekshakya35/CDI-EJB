/* global Ext */

Ext.define('pg.view.security.UsersList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userslist',
    frame: true,
    store: Ext.create('pg.store.security.Users'), // #1
    columns: [
        {
            width: 150,
            dataIndex: 'userName',
            text: 'Username'
        },
        {
            width: 200,
            dataIndex: 'fullName',
            flex: 1,
            text: 'Name'
        }, {
            width: 250,
            dataIndex: 'emailId',
            text: 'Email'
        }, {
            width: 150,
            dataIndex: 'roleId',
            text: 'Role'

        }

    ]
});