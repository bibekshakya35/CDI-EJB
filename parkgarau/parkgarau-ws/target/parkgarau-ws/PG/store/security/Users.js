/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Ext */

Ext.define('pg.store.security.Users', {
    extend: 'Ext.data.Store',
    requires: [
        'pg.model.security.User' // #1
    ],
    model: 'pg.model.security.User', // #2
    proxy: {
        type: 'ajax',
        url: 'http://localhost:8080/parkgarau-ws/ws/park/userlist',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
