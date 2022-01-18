package com.btkAkademi.rentACar.core.utilities.Loaders;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceItemDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalServiceItem;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class AdditionalServiceItemDataLoader implements CommandLineRunner {
	private AdditionalServiceItemDao additionalServiceItemDao;
	@Override
	public void run(String... args) throws Exception {
		loadAdditionalServiceItems();
		
	}
	private void loadAdditionalServiceItems() {
		if(additionalServiceItemDao.count()==0) {
			HashSet<AdditionalServiceItem> items = new HashSet<>();
			items.add(new AdditionalServiceItem(0,"Bebek Koltuğu",75,null));
			items.add(new AdditionalServiceItem(0,"Navigasyon Aleti",150,null));
			items.add(new AdditionalServiceItem(0,"Ek Sürücü",500,null));
			items.add(new AdditionalServiceItem(0,"Kaza Sigortası",300,null));
			items.add(new AdditionalServiceItem(0,"Süper Kaza Sigortası",500,null));
			items.add(new AdditionalServiceItem(0,"Kış Lastiği",160,null));
			items.add(new AdditionalServiceItem(0,"Wifi Modem",300,null));
			additionalServiceItemDao.saveAll(items);
		}
	
	}

}
