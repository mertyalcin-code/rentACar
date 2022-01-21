package com.btkAkademi.rentACar.core.utilities.loaders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.ColorDao;
import com.btkAkademi.rentACar.entities.concretes.Color;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ColorDataLoader implements CommandLineRunner {
	private ColorDao colorDao;

	@Override
	public void run(String... args) throws Exception {
		loadColors();

	}

	private void loadColors() {
		if(colorDao.count()==0) {
			HashSet<Color> colors = new HashSet<>();
			colors.add(new Color(0,"Beyaz",null));
			colors.add(new Color(0,"Siyah",null));
			colors.add(new Color(0,"Gri",null));
			colors.add(new Color(0,"Gümüş",null));
			colors.add(new Color(0,"Mavi",null));
			colors.add(new Color(0,"Kırmızı",null));
			colors.add(new Color(0,"Kahverengi",null));
			colors.add(new Color(0,"Bej",null));
			colors.add(new Color(0,"Sarı",null));
			colors.add(new Color(0,"Altın",null));
			colors.add(new Color(0,"Yeşil",null));			
			colorDao.saveAll(colors);
		}
	
	}
}