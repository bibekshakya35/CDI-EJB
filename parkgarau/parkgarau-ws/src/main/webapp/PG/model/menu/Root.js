Ext.define('pg.model.menu.Root', {
    extend: 'Ext.data.Model',
    uses: [
        'pg.model.menu.Item'
    ],
    idProperty: 'id',
    fields: [
        {
            name: 'text'
        },
        {
            name: 'iconCls'
        },
        {
            name: 'id'
        }
    ],
    hasMany: {
        model: 'pg.model.menu.Item',
        foreignKey: 'parentId',
        name: 'items'
    }
});