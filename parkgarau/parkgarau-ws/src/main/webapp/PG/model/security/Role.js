Ext.define('PG.model.security.Role', {
    extend: 'Ext.data.Model',
    idProperty: 'code',
    fields: [
        {name: 'code'},
        {name: 'name'}
    ]
});