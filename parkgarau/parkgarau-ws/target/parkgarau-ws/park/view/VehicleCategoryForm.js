/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('VehicleCategory.view.VehicleCategory', {
    extend: 'Ext.form.Panel',
    xtype: 'vehicleForm',
    title: 'Vehicle Category Form',
    controller: 'VehicleCategory',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',
                fieldDefaults: {
                    anchor: '100%',
                    labelAlign: 'left',
                    allowBlank: false,
                    combineErrors: true,
                    msgTarget: 'side'
                },
                items: [
                    {
                        xtype: 'textfield',
                        name: 'code',
                        fieldLabel: 'code'
                    },
                    {
                        xtype: 'textfield',
                        name: 'name',
                        fieldLabel: 'Name'
                    },
                    {
                        xtype: 'textfield',
                        name: 'description',
                        fieldLabel: 'Description'
                    }
                ]
            }
        ];

        this.dockedItems = [{
                xtype: 'toolbar',
                dock: 'bottom',
                id: 'buttons',
                ui: 'footer',
                items: ['->', {
                        iconCls: 'icon-save',
                        itemId: 'save',
                        text: 'Save',
                        action: 'save'
                    }, {
                        iconCls: 'icon-reset',
                        text: 'Cancel',
                        scope: this,
                        handler: this.close
                    }]
            }];

        this.callParent(arguments);
    }
});