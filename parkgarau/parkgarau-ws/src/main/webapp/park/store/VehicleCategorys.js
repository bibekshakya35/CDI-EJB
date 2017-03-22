/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.store.VehicleCategorys', {
    extend: 'Ext.data.Store',
    model: 'VehicleCategory.model.VehicleCategory',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'http://localhost:8080/parkgarau-ws/ws/park/vehiclecategory',
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});


