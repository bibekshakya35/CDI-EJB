/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('pg.store.Menu',{
    extend: 'Ext.data.Store',
    requires:[
        'pg.model.menu.Root'
    ],
    model:'pg.model.menu.Root',
    autoLoad: true,
    proxy:{
        type:'ajax',
        url:'http://localhost:8080/parkgarau-ws/ws/park/menu',
        reader:{
            type:'json',
            rootProperty:'data'
        }
    }
});


