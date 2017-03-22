/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Client side validation
 * •	 The username and password must be mandatory—how are going to
 authenticate the user without a username and password?
 •	 The user can only enter alphanumeric characters (A-Z, a-z, and 0-9) in both
 the fields.
 •	 The user can only type between 3 and 25 chars in the username field.
 •	 The user can only type between 3 and 15 chars in the password field.
 */

Ext.define('pg.view.Login', {
    extend: 'Ext.form.Panel',
    alias: 'widget.login',
    //simply xtype also
    autoShow: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    iconCls: 'key',
    title: "Login",
    closeAction: 'hide',
    closable: false,
    resizable: false,
    draggable: false,
    cls: 'form-group',
    items: [{
            xtype: 'form',
            frame: false,
            bodyPadding: 25,
            defaults: {
                xtype: 'textfield',
                anchor: '100%',
                height: 25,
                width: 100,
                labelWidth: 60,
                allowBlank: false, //no blank
                vtype: 'alphanum', //only alpha and numeric
                minLength: 3,
                msgTarget: 'under',
                cls: 'form-control'
            },
            items: [{
                    name: 'username',
                    fieldLabel: "Username",
                    maxLength: 25
                }, {
                    inputType: 'password',
                    name: 'password',
                    fieldLabel: "Password",
                    maxLength: 15,
                    enableKeyEvents: true,
                    itemId: 'passwordTxt',
                    id: 'passwordTxt'
                }]
        }],
    dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom', //can be done top side 
            items: [{
                    xtype: 'tbfill'
                }, {
                    xtype: 'tbseparator'
                }, {
                    xtype: 'button',
                    itemId: 'logincancel',
                    iconCls: 'fa fa-close',
                    text: "cancel",
                    height: 40,
                    width: 100

                }, {
                    xtype: 'button',
                    itemId: 'loginsubmit',
                    formBind: true, //he button will only be enabled if the form has no error from the client validation.
                    iconCls: 'fa fa-sign-in',
                    text: "submit",
                    height: 40,
                    width: 100
                }]
        }]
});