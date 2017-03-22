/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: ['VehicleCategory.view.VehicleCategoryGrid'],
    initComponent: function () {
        this.items = [{
                xtype: 'VehicleCategoryGrid'
            }];
        this.callParent(arguments);
    }
});


