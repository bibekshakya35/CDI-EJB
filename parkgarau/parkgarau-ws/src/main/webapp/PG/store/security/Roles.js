/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Ext */

Ext.define('pg.store.security.Roles', {
    extend: 'Ext.data.Store',
    requires: [
        'pg.model.security.Role' // #1
    ],
    autoLoad: true,
    model: 'pg.model.security.Role', // #2
    proxy: {
        type: 'ajax',
        url: 'http://localhost:8080/parkgarau-ws/ws/park/role',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }

});
