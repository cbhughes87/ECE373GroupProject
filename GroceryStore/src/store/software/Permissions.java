package store.software;
import java.util.HashMap;
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
