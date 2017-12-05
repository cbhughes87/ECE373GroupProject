package store.software;
public class Permissions {
	private boolean canShop, 
					canManageStock,
					canEditInventory,
					canAddUsers,
					canRemoveUsers,
					canEditPermissions,
					canViewLogs;
	public Permissions(boolean shop, 
			boolean stock, 
			boolean inv, 
			boolean add, 
			boolean rem, 
			boolean edit, 
			boolean logs){
		canShop = shop;
		canManageStock = stock;
		canEditInventory = inv;
		canAddUsers = add;
		canRemoveUsers = rem;
		canEditPermissions = edit;
		canViewLogs = logs;
	}
	
	/**
	 *  Possible keys:<br><br>
	 *  
	 *  "canShop" alias "shop"<br>
	 *  "canManageStock" alias "stock"<br>
	 *  "canEditInventory" alias "inventory"<br>
	 *  "canAddUsers" alias "add"<br>
	 *  "canRemoveUsers" alias "rem"<br>
	 *  "canEditPermissions" alias "perms"<br>
	 *  "canViewLogs" alias "logs"<br>
	 * */
	public boolean getPermission(String key){
		if(key.equals("canShop") || key.equals("shop"))
			return canShop;
		if(key.equals("canManageStock") || key.equals("stock"))
			return canManageStock;
		if(key.equals("canEditInventory") || key.equals("inventory"))
			return canEditInventory;
		if(key.equals("canAddUsers") || key.equals("add"))
			return canAddUsers;
		if(key.equals("canRemoveUsers") || key.equals("rem"))
			return canRemoveUsers;
		if(key.equals("canEditPermissions") || key.equals("perms"))
			return canEditPermissions;
		if(key.equals("canViewLogs") || key.equals("logs"))
			return canViewLogs;
		return false;
	}
}
