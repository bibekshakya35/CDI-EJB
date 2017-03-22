/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.application({
    name: 'VehicleCategory',
    appFolder: 'park',
    autoCreateViewport: true,
    modals: ['VehicleCategory.model.VehicleCategory'],
    stores: ['VehicleCategory.store.VehicleCategorys'],
    controllers: ['VehicleCategory'],
    launch: function () {
        alert('Load....');
    }
});



