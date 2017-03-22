/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.model.VehicleCategory', {
    extend: 'Ext.data.Model',
    idProperty: 'Id',
    schema: {
        namespace: 'VehicleCategory.model'
    },
    fields: [{
            name: 'code', mapping: 'code', type: 'string'
        }, {
            name: 'name', mapping: 'name', type: 'string'
        }, {
            name: 'description', mapping: 'description', type: 'string'
        }]
//    validations:[{
//            type:'presence',
//            field:'code'
//    }]
});

