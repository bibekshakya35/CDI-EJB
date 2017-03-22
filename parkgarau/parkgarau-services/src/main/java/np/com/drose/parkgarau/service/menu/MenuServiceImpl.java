    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package np.com.drose.parkgarau.service.menu;

    import java.util.List;
    import javax.ejb.Stateless;
    import javax.inject.Inject;
    import np.com.drose.parkgarau.api.ParkGarauService;
    import np.com.drose.parkgarau.api.manager.Manager;
    import np.com.drose.parkgarau.api.updater.Updater;
    import np.com.drose.parkgarau.menu.Menu;

    /**
     * {Insert class description here}
     *
     * @version $Revision: 1.1.1.1 $
     * @since Build {insert version here} (MM YYYY)
     * @author bibek
     */
    @Stateless
    public class MenuServiceImpl implements ParkGarauService<Menu> {

        @Inject
        Manager<Menu> menuManager;
        @Inject
        Updater<Menu> menuUpdater;

        @Override
        public void add(Menu t) {
            this.menuUpdater.save(t);
        }

        @Override
        public void addAll(List<Menu> t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAll(List<Menu> t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void edit(Menu t) {
            this.menuUpdater.Update(t);
        }

        @Override
        public List<Menu> getList() {
            return this.menuManager.getList();
        }

        @Override
        public Menu finder(Object code) {
            return this.menuManager.finder(code);
        }

        @Override
        public List<Menu> findList(java.lang.Object code) {
            return this.menuManager.findAll(code);
        }

    }
