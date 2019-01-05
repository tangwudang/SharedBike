package com.lishu.bike.utils;

import com.lishu.bike.model.AddressBookModel;

import java.util.Comparator;

public class PinyinComparator implements Comparator<AddressBookModel.AddressBook> {

	@Override
	public int compare(AddressBookModel.AddressBook lhs, AddressBookModel.AddressBook rhs) {
		// TODO Auto-generated method stub
		return sort(lhs, rhs);
	}

	private int sort(AddressBookModel.AddressBook lhs, AddressBookModel.AddressBook rhs) {

		int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
		int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);

		if (lhs_ascii < 65 || lhs_ascii > 90)
			return 1;
		else if (rhs_ascii < 65 || rhs_ascii > 90)
			return -1;
		else
			return lhs.getPinYin().compareTo(rhs.getPinYin());
	}

}
