/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.view.VehicleCategoryGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.VehicleCategoryGrid',
    initComponent: function () {
        this.store = 'VehicleCategory.store.VehicleCategorys';
        this.title = "List of Vehicle Category";
        this.border = true;
        this.height = 800;
        this.width = 800;
        this.columns = [{
                text: 'Code',
                dataIndex: 'code',
                flex: 1
            }, {
                text: 'Name',
                dataIndex: 'name',
                flex: 1
            }, {
                text: 'Description',
                dataIndex: 'description',
                flex: 1
            }];
        this.callParent(arguments);
    }
});


