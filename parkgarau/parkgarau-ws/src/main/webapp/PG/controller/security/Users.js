/* global Ext */

Ext.define('pg.controller.security.Users', {
    extend: 'Ext.app.Controller',
   requires: [
        'pg.view.security.Users'
    ],
    views: [
        'security.Users'
    ],
    refs: [
        {
            ref: 'usersList',
            selector: 'userslist'
        },{
            ref:'userPicture',
            selector:'image'
        }
    ],
    init: function (application) {
        this.control({
            "userslist": {
                render: this.onRender
            },
            "#usersadd": {
                click: this.onButtonClickAdd
            },
            "#usersedit": {
                click: this.onButtonClickEdit
            },
            "#profilesave": {
                click: this.onButtonClickSave
            },
            "#profilecancel": {
                click: this.onButtonClickCancel
            },
            //handle the HTML 5 File API
            "profile filefield": {
                change: this.onFilefieldChange
            },
            "#userdelete": {
                click: this.onButtonClickDelete
            }
        });
    },
    onRender: function (component, options) {
        component.getStore().load();
    },
    onButtonClickAdd: function (button, e, options) {
        var win = Ext.create('pg.view.security.Profile');
        win.setTitle('Add new User');
        win.show();
    },
    onButtonClickEdit: function (button, e, options) {
        var grid = this.getUsersList(), //So firstly, we will get the reference of the user Grid panel and get the selected records
                record = grid.getSelectionModel().getSelection();
        //Then, if there is any selected record ( #2 ) we will create the window instance; load the form with the information from the selected record ( #3 ), set the window title to the name of the user whose information we want to edit ( #5 ) and display the window.
        if (record[0]) {
            var editWindow = Ext.create('pg.view.security.Profile');
            editWindow.down('form').loadRecord(record[0]);
            if (record[0].get('picture')) {
                var img = editWindow.down('image');
                img.setSrc('resources/profile/' + record[0].
                        get('picture'));
            }
            editWindow.setTitle(record[0].get('fullName'));
            editWindow.show();
        }
    },
    onButtonClickSave: function (button, e, options) {
        var win = button.up('window'), // #1
                formPanel = win.down('form'), // #2
                store = this.getUsersList().getStore(); // #3
        if (formPanel.getForm().isValid()) { // #4
            formPanel.getForm().submit({// #5
                clientValidation: true,
                url: 'http://localhost:8080/parkgarau-ws/ws/park/user', // #6
                success: function (form, action) {
                    var result = action.result; // #7
                    if (result.code == "200") {
                        pg.util.Alert.msg('Success!', result.message); // #8
                        store.load();
                        win.close();
                    } else {
                        pg.util.Util.showErrorMsg(result.message); // #9
                    }
                },
                failure: function (form, action) {
                    switch (action.failureType) {
                        case Ext.form.action.Action.CLIENT_INVALID:
                            Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
                            break;
                        case Ext.form.action.Action.CONNECT_FAILURE:
                            Ext.Msg.alert('Failure', 'Ajax communication failed');
                            break;
                        case Ext.form.action.Action.SERVER_INVALID:
                            Ext.Msg.alert('Failure', action.result.msg);
                    }
                }
            });
        }
    },
    onButtonClickCancel: function (button, e, options) {
        button.up('window').close();
    },
    onFilefieldChange: function (filefield, value, options) {
        var file = filefield.fileInputEl.dom.files[0]; // #1
        var picture = this.getUserPicture(); // #2
        if (typeof FileReader !== "undefined" && (/image/i).test(file.
                type)) {
// #3
            var reader = new FileReader();
// #4
            reader.onload = function (e) {
// #5
                picture.setSrc(e.target.result); // #6
            };
            reader.readAsDataURL(file);
// #7
        } else if (!(/image/i).test(file.type)) { // #8
            Ext.Msg.alert('Warning', 'You can only upload image files!');
            filefield.reset();
// #9
        }
    },
    onButtonClickDelete: function (button, e, options) {
        var grid = this.getUsersList(),
                record = grid.getSelectionModel().getSelection(),
                store = grid.getStore();
        if (store.getCount() >= 2 && record[0]) {
            Ext.Msg.show({
                title: 'Delete?',
                msg: 'Are you sure you want to delete?',
                buttons: Ext.Msg.YESNO,
                icon: Ext.Msg.QUESTION,
                fn: function (buttonId) {
                    if (buttonId == 'yes') {
                        Ext.Ajax.request({
                            url: 'http://localhost:8080/parkgarau-ws/ws/park/userlist',
//                            params: {
//                                id: record[0].get('id')
//                            },
                            success: function (conn, response, options, eOpts) {
                                var result = pg.util.Util.decodeJSON(conn.responseText);
                                if (result.code=='200') {
                                    pg.util.Alert.msg('Success!', 'User deleted.');
                                    store.load();
                                } else {
                                    pg.util.Util.showErrorMsg(conn.responseText);
                                }
                            },
                            failure: function (conn, response, options, eOpts) {
                                pg.util.Util.showErrorMsg(conn.responseText);
                            }
                        });
                    }



                }
            });
        } else if (store.getCount() == 1) {
            Ext.Msg.show({
                title: 'Warning',
                msg: 'You cannot delete all the users from the application.',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.WARNING
            });
        }
    }
});