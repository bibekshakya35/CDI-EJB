/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.controller.VehicleCategory', {
    extend: 'Ext.app.Controller',
    init: function () {
        this.control({
            'VehicleCategoryGrid': {
                'itemclick': function (grid, record, item, index, e, eOpts) {
                    alert('You clicked row with index : ' +(index+1));
                }

            }
        });
    }
});

