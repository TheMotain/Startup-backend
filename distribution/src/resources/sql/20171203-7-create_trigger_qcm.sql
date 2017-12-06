  
CREATE TRIGGER qcm_notify
    AFTER INSERT OR DELETE OR UPDATE 
    ON qcm
    FOR EACH ROW
    EXECUTE PROCEDURE table_update_notify();