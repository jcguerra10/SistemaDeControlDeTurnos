package model;

import java.util.Comparator;

public class SortById implements Comparator<Client> {

	@Override
	public int compare(Client arg0, Client arg1) {
		int comp = 0;
		if (arg0.getId().compareTo(arg1.getId()) < 0) {
			comp = 1;
		}else if(arg0.getId().compareTo(arg1.getId()) < 0) {
			comp = -1;
		}else {
			comp = 0;
		}		
		return comp;
	}

}
