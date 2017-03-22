/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Ext */

Ext.define('pg.controller.Login', {
    extend: 'Ext.app.Controller',
    views: ['Login', 'include.Header', 'authentication.CapsLockTooltip'],
    /*
     * ref (reference) is an alternative to locate a component. It also uses the
     ComponentQuery syntax. ref is very useful, especially if we need to get a reference
     of a component several times inside a controller. The controller will also generate
     a get method automatically for a ref . In this case, the controller generates the
     getCapslockTooltip method for us.
     */
    refs: [{
            ref: 'capslockTooltip',
            selector: 'capslocktooltip'
        }],
    init: function (application) {
        this.control({
            /*
             * 'Ext.ComponentQuery selector': {
             eventWeWantToListenTo: functionOrMethodWeWantToExecute
             }
             */
            "#loginsubmit": {
                click: this.onButtonClickSubmit
            },
            "#logincancel": {
                click: this.onButtonClickCancel
            },
            "#passwordTxt": {
                specialkey: this.onEnterHit
            },
            "login form textfield[name=password]": {
                keypress: this.onTextFieldKeyPress
            },
            "#logout": {
                click: this.onLoggedOut
            }
        });
    },
    onButtonClickSubmit: function (button, e, options) {
        var formPanel = button.up('window').down('form'),
                login = button.up('login'),
                username = formPanel.down('textfield[name=username]').getValue(),
                password = formPanel.down('textfield[name=password]').getValue();
        var jsonFormat = {
            username: username,
            password: password,
            deviceId: 'deviceTab',
            companyCode: 'drose'
        };
        if (formPanel.getForm().isValid()) {
            Ext.get(login.getEl()).mask("Authenticating..... Please Wait......", "Loading......");
            Ext.Ajax.setDefaultHeaders({
                'version': '1'
            });
            Ext.Ajax.request({
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                jsonData: Ext.util.JSON.encode(jsonFormat),
                url: 'http://localhost:8080/parkgarau-ws/ws/park/login',
                success: function (con, resp, options, eOpts) {
                    Ext.get(login.getEl()).unmask();
                    var result = Ext.JSON.decode(con.responseText, true);
                    console.log(result);
                    if (!result) {
                        result = {};
                        result.code = '404';
                        result.message = con.responseText;
                    }
                    if (result.code == '200') {
                        login.close();
                        Ext.Msg.show({
                            title: 'Successful login',
                            msg: result.message,
                            icon: Ext.Msg.SUCCESS,
                            buttons: Ext.Msg.OK
                        });
                        Ext.create('pg.view.Template');
                        
                    } else {
                        Ext.Msg.show({
                            title: 'fail!!!',
                            msg: result.message,
                            icon: Ext.Msg.ERROR,
                            buttons: Ext.Msg.OK
                        });
                    }
                },
                failure: function (con, response, options, eOpts) {
                    Ext.get(login.getEl()).unmask();
                    Ext.Msg.show({
                        title: 'Error!!!',
                        msg: con.responseText,
                        icon: Ext.Msg.ERROR,
                        buttons: Ext.Msg.OK
                    });
                }
            });
        }

        console.log("Login submit" + username);
    },
    onButtonClickCancel: function (button, e, options) {
        button.up('window').down('form').getForm().reset();
    },
    onEnterHit: function (field, e, options) {
        console.log("Enter...");
        if (e.getKey() === e.ENTER) {
            var submitBtn = field.up('login').down("#loginsubmit");
            submitBtn.fireEvent('click', submitBtn, e, options);
        }
    },
    onTextFieldKeyPress: function (field, e, options) {
        var charCode = e.getCharCode();
        if ((e.shiftKey && charCode >= 97 && charCode <= 122) ||
                (!e.shiftKey && charCode >= 65 && charCode <= 90)) {
            //identify if there is ref for capslock if not then initiate new by xtype
            if (this.getCapslockTooltip() === undefined) {
                Ext.widget('capslocktooltip');
            }
            this.getCapslockTooltip().show();
        } else {
            if (this.getCapslockTooltip() !== undefined) {
                this.getCapslockTooltip().hide();
            }
        }
    },
    onLoggedOut: function (button, e, options) {
        button.up("template").destroy();
        window.location.reload();
    }

});


