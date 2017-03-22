/* global Ext */

Ext.define('pg.view.security.Profile', {
    extend: 'Ext.window.Window',
    alias: 'widget.profile',
    height: 800,
    width: 800,
    requires: ['pg.util.Util'],
    layout: {
        type: 'fit'
    },
    title: 'User',
    items: [
        {
            xtype: 'form',
            bodyPadding: 5,
            layout: {
                type: 'hbox', // #1
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'fieldset',
                    flex: 2,
                    title: 'User Information',
                    defaults: {
                        afterLabelTextTpl: '<span style="color:red;font-weight:bold" data-qtip="Required"> *</span>', // #1
                        anchor: '100%',
                        xtype: 'textfield',
                        allowBlank: false,
                        labelWidth: 140
                    },
                    items: [
                        {
                            fieldLabel: 'Username',
                            name: 'userName'
                        },
                        {
                            fieldLabel: 'Name',
                            maxLength: 100,
                            name: 'fullName'
                        },
                        {
                            fieldLabel: 'Email',
                            maxLength: 100,
                            name: 'emailId'
                        },
                        {
                            fieldLabel: 'Land Line Number',
                            maxLength: 100,
                            name: 'landLineNumber'
                        },
                        {
                            fieldLabel: 'Mobile Number',
                            maxLength: 100,
                            name: 'mobileNumber'
                        },
                        {
                            xtype: 'combobox',
                            fieldLabel: 'Role Code',
                            name: 'roleId', // #1
                            id: 'example',
                            triggerAction: 'all',
                            mode: 'remote',
                            displayField: 'name',
                            valueField: 'code',
                            store: Ext.create('Ext.data.Store', {
                                fields: [
                                    {name: 'code'},
                                    {name: 'name'}
                                ],
                                //autoLoad: false,
                                proxy: {
                                    type: 'ajax',
                                    url: 'http://localhost:8080/parkgarau-ws/ws/park/role',
                                    reader: {
                                        type: 'json'
                                    }
                                }
                            }
                            ),
                            listeners: {
                                render: function (combobox) {
                                    Ext.getCmp('example').getStore().load();
                                    combobox.getStore().load();
                                }
                            }// #5
                        },
                        {
                            xtype: 'filefield',
                            fieldLabel: 'Picture',
                            name: 'picture',
                            allowBlank: true, // #6
                            afterLabelTextTpl: '' // #7
                        }


                    ]

                },
                {
                    xtype: 'fieldset',
                    title: 'Picture',
                    width: 400, // #1
                    items: [
                        {
                            xtype: 'image',
                            itemId: 'userpicture', // #2
                            height: 400,
                            width: 400,
                            src: '' // #3
                        }
                    ]
                }

            ]
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            flex: 1,
            dock: 'bottom',
            ui: 'footer',
            layout: {
                pack: 'end', // #1
                type: 'hbox'
            },
            items: [
                {
                    xtype: 'button',
                    text: 'Cancel',
                    itemId: 'profilecancel',
                    iconCls: 'icon-remove-sign'
                },
                {
                    xtype: 'button',
                    text: 'Save',
                    itemId: 'profilesave',
                    iconCls: 'fa-floppy-o '
                }
            ]
        }
    ]

});