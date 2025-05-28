package service;

public interface DatabaseListener {
    void onChange();
};


/**
 * If the data is updated, the UI should always reflect the current state of the application.
 * Since the Controller manages the View lifecycle, it is responsible for pushing new data 
 * whenever updates occur.
 * 
 * Therefore, each Controller implements DatabaseListener, which contains a single method.
 * This method defines "what to do" when the database is updated.
 * 
 * When the database changes, the Database object will invoke this method on all registered
 * DatabaseListener instances — the collection of objects interested in receiving 
 * database updates.
 */
