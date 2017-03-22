/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('pg.model.menu.Item', {
    extend: 'Ext.data.Model',
    uses: [
        'pg.model.menu.Root'
    ],
    idProperty: 'id',   
    fields: [{
            name: 'text'
        }, {
            name: 'iconCls'
        }, {
            name: 'className'
        }, {
            name: 'id'
        }, {
            name: 'parentId'
        }],
    belongsTo: {
        model: 'pg.model.menu.Root',
        foreignKey: 'parentId'
    }
});

