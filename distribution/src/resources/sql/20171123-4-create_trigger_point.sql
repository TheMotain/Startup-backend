-- Creation du trigger de notification

CREATE FUNCTION table_update_notify()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    NOT LEAKPROOF 
AS $BODY$
BEGIN
  IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
  	PERFORM pg_notify('table_update', json_build_object('table', TG_TABLE_NAME, 'data', NEW, 'type', TG_OP)::text);
  ELSIF TG_OP = 'DELETE' THEN
	PERFORM pg_notify('table_update', json_build_object('table', TG_TABLE_NAME, 'data', OLD, 'type', TG_OP)::text);
  END IF;
  RETURN NEW;
END;
$BODY$;
    
CREATE TRIGGER point_notify
    AFTER INSERT OR DELETE OR UPDATE 
    ON point
    FOR EACH ROW
    EXECUTE PROCEDURE table_update_notify();