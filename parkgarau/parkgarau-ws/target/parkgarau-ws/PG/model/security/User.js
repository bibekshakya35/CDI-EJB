/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define("pg.model.security.User", {
    extend: 'Ext.data.Model',
    idProperty: 'userName',
    autoLoad: true,
    fields: [
        {
            name: 'userName'
        }, {
            name: 'roleId'
        }, {
            name: 'fullName'
        }, {
            name: 'emailId'
        }, {
            name: 'mobileNumber'
        }, {
            name: 'landLineNumber'
        }, {
            name: 'picture'
        }
    ]
});


