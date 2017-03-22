/* global Ext */

Ext.application({
    name: 'pg',
    appFolder: 'PG',
    requires: ['pg.controller.Login', 'pg.controller.menu.Menu', 'pg.controller.security.Users'],
    //below 2 loc is commented because now controller will load and update theview
//    requires:['Login.view.Login'],
//    views:['Login.view.Login'],
    
    controllers: ['Login', 'menu.Menu', 'security.Users'],
    
    init: function () {
        //create splashscreen
        splashscreen = Ext.getBody().mask('Loading ParkGarau Application', 'splashscreen');
        //class creted .splashscreen
        splashscreen.addCls('splashscreen');
        //after auto generated code .x-mask-msg look for this and add icon
        /*
         *The previous code will search for the first DIV tag that contains the .x-mask-msg
         class ( Ext.query('.x-mask-msg')[0] ) and will add a new DIV tag as child with the
         class x-splash-icon that will be responsible for adding the logo image above the
         loading message. 
         */
        Ext.DomHelper.insertFirst(Ext.query('.x-mask-msg')[0], {
            cls: 'x-splash-icon'
        });
    },
    splashscreen: {},
    launch: function () {
        Ext.tip.QuickTipManager.init();
        //for removing the splash screen-> yeah we did what user want first load anad image
        var task = new Ext.util.DelayedTask(function () {
            //it will remove first div not the one you add explicit tei k tah icon
            splashscreen.fadeOut({
                duration: 1000, //in milisec
                remove: true
            });
            splashscreen.next().fadeOut({
                duration: 1000,
                remove: true,
                listeners: {
                    afteranimate: function (e1, startTime, eOpts) {
                        Ext.widget('login');
                    }
                }
            });
            console.log("Launch this shit");
        });
        task.delay(2000);
        console.log('launch');
    }
});
