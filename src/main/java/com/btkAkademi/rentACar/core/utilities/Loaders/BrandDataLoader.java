package com.btkAkademi.rentACar.core.utilities.Loaders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class BrandDataLoader implements CommandLineRunner {
	private BrandDao brandDao;
	@Override
	public void run(String... args) throws Exception {
		loadBrands();
		
	}
	private void loadBrands() {
		if(brandDao.count()==0) {
			List<Brand> brands = new ArrayList<>();
			brands.add(new Brand(0,"Alfa Romeo",null));
			brands.add(new Brand(0,"Aston Martin",null));
			brands.add(new Brand(0,"Audi",null));
			brands.add(new Brand(0,"Bentley",null));
			brands.add(new Brand(0,"Bmw",null));
			brands.add(new Brand(0,"Bugaitti",null));
			brands.add(new Brand(0,"Buick",null));
			brands.add(new Brand(0,"Cadillac",null));
			brands.add(new Brand(0,"Chery",null));
			brands.add(new Brand(0,"Chevrolet",null));
			brands.add(new Brand(0,"Chrysler",null));
			brands.add(new Brand(0,"Citroen",null));
			brands.add(new Brand(0,"Dacia",null));
			brands.add(new Brand(0,"Daewoo",null));
			brands.add(new Brand(0,"Daihatsu",null));
			brands.add(new Brand(0,"Dodge",null));
			brands.add(new Brand(0,"Ferrari",null));
			brands.add(new Brand(0,"Fiat",null));
			brands.add(new Brand(0,"Ford",null));
			brands.add(new Brand(0,"Geely",null));
			brands.add(new Brand(0,"Honda",null));
			brands.add(new Brand(0,"Hyundai",null));
			brands.add(new Brand(0,"Infiniti",null));
			brands.add(new Brand(0,"Isuzu",null));
			brands.add(new Brand(0,"Iveco",null));
			brands.add(new Brand(0,"Jaguar",null));
			brands.add(new Brand(0,"Jeep",null));
			brands.add(new Brand(0,"Kia",null));
			brands.add(new Brand(0,"Lada",null));
			brands.add(new Brand(0,"Lamborghini",null));
			brands.add(new Brand(0,"Lancia",null));
			brands.add(new Brand(0,"Land Rover",null));
			brands.add(new Brand(0,"Lexus",null));
			brands.add(new Brand(0,"Lincoln",null));
			brands.add(new Brand(0,"Lotus",null));
			brands.add(new Brand(0,"Meserati",null));
			brands.add(new Brand(0,"Mazda",null));
			brands.add(new Brand(0,"McLaren",null));
			brands.add(new Brand(0,"Mercedes Benz",null));
			brands.add(new Brand(0,"Mini",null));
			brands.add(new Brand(0,"Mitsubishi",null));
			brands.add(new Brand(0,"Nissan",null));
			brands.add(new Brand(0,"Opel",null));
			brands.add(new Brand(0,"Peugeot",null));
			brands.add(new Brand(0,"Porsche",null));
			brands.add(new Brand(0,"Proton",null));
			brands.add(new Brand(0,"Renault",null));
			brands.add(new Brand(0,"Rolls Royce",null));
			brands.add(new Brand(0,"Rover",null));
			brands.add(new Brand(0,"Saab",null));
			brands.add(new Brand(0,"Seat",null));
			brands.add(new Brand(0,"Skoda",null));
			brands.add(new Brand(0,"Smart",null));
			brands.add(new Brand(0,"Ssangyong",null));
			brands.add(new Brand(0,"Subaru",null));
			brands.add(new Brand(0,"Suzuki",null));
			brands.add(new Brand(0,"Tata",null));
			brands.add(new Brand(0,"Tofaş",null));
			brands.add(new Brand(0,"Toyota",null));
			brands.add(new Brand(0,"Volkswagen",null));
			brands.add(new Brand(0,"Volvo",null));	
			
			brandDao.saveAll(brands);
		}
	}

}
