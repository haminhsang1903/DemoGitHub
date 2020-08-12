package poly.model;

import poly.entity.Tables;

public class TableService {
	private Tables tbl;
	private boolean status;
	public Tables getTbl() {
		return tbl;
	}
	public void setTbl(Tables tbl) {
		this.tbl = tbl;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public TableService(Tables tbl, boolean status) {
		super();
		this.tbl = tbl;
		this.status = status;
	}
	public TableService() {
		super();
	}
	
}
