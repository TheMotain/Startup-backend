  
CREATE TRIGGER result_qcm_notify
    AFTER INSERT OR DELETE OR UPDATE 
    ON result_qcm
    FOR EACH ROW
    EXECUTE PROCEDURE table_update_notify();