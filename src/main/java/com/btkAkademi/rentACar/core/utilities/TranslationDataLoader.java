package com.btkAkademi.rentACar.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.dataAccess.abstracts.LanguageDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.TranslationDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.WordDao;
import com.btkAkademi.rentACar.entities.concretes.Language;
import com.btkAkademi.rentACar.entities.concretes.Translation;
import com.btkAkademi.rentACar.entities.concretes.Word;

import lombok.var;

@Component
public class TranslationDataLoader implements CommandLineRunner {

	private TranslationDao translationDao;
	private LanguageDao languageDao;
	private WordDao wordDao;

	@Autowired
	public TranslationDataLoader(TranslationDao translationDao, LanguageDao languageDao, WordDao wordDao) {
		this.translationDao = translationDao;
		this.languageDao = languageDao;
		this.wordDao = wordDao;
	}

	@Override
	public void run(String... args) throws Exception {
		loadLanguageData();
		loadWordData();
		loadTranslationData();
	}

	private void loadLanguageData() {
		if (languageDao.count() == 0) {
			Language language = new Language();
			language.setName("English");
			this.languageDao.save(language);

			Language language1 = new Language();
			language1.setName("Turkish");
			this.languageDao.save(language1);
		}
	}

	private void loadWordData() {
		if (this.wordDao.count() == 0) {

			// kendi eklediklerim
			List<Word> words = new ArrayList<>();
			words.add(new Word(0, "additional.service.item.added", null));
			words.add(new Word(0, "additional.service.item.updated", null));
			words.add(new Word(0, "additional.service.item.deleted", null));
			words.add(new Word(0, "additional.service.item.not.found", null));
			words.add(new Word(0, "additional.service.item.list", null));
			words.add(new Word(0, "brand.name.exists", null));
			words.add(new Word(0, "car.damage.add", null));
			words.add(new Word(0, "car.damage.update", null));
			words.add(new Word(0, "car.damage.delete", null));
			words.add(new Word(0, "car.damage.not.found", null));
			words.add(new Word(0, "car.rented", null));
			words.add(new Word(0, "car.in.maintanance", null));
			words.add(new Word(0, "city.name.exists", null));
			words.add(new Word(0, "color.name.exists", null));
			words.add(new Word(0, "company.name.exists", null));
			words.add(new Word(0, "age.not.enough", null));
			words.add(new Word(0, "rental.not.finished", null));
			words.add(new Word(0, "payment.not.found", null));
			words.add(new Word(0, "promo.code.not.found", null));
			words.add(new Word(0, "promo.code.add", null));
			words.add(new Word(0, "promo.code.update", null));
			words.add(new Word(0, "promo.code.delete", null));
			words.add(new Word(0, "promo.code.already.exists", null));
			words.add(new Word(0, "dates.are.not.correct", null));
			words.add(new Word(0, "no.available.car.in.this.segment", null));
			words.add(new Word(0, "kilometer.error", null));
			words.add(new Word(0, "segment.add", null));
			words.add(new Word(0, "segment.update", null));
			words.add(new Word(0, "segment.delete", null));
			words.add(new Word(0, "segment.name.already.exists", null));
			words.add(new Word(0, "segment.not.found", null));
			this.wordDao.saveAll(words);

			// kendi eklediklerim biter

			Word nameValidationError = new Word();
			nameValidationError.setKey("validation.error");
			this.wordDao.save(nameValidationError);

			Word dataNotFound = new Word();
			dataNotFound.setKey("data.not.found");
			this.wordDao.save(dataNotFound);

			Word customerAdded = new Word();
			customerAdded.setKey("customer.added");
			this.wordDao.save(customerAdded);
			Word customerDeleted = new Word();
			customerDeleted.setKey("customer.deleted");
			this.wordDao.save(customerDeleted);
			Word customerUpdated = new Word();
			customerUpdated.setKey("customer.updated");
			this.wordDao.save(customerUpdated);
			Word customerListed = new Word();
			customerListed.setKey("customers.listed");
			this.wordDao.save(customerListed);
			Word customerGet = new Word();
			customerGet.setKey("customer.found");
			this.wordDao.save(customerGet);
			Word customerNotFound = new Word();
			customerNotFound.setKey("customer.not.found");
			this.wordDao.save(customerNotFound);
			Word customerAlreadyExists = new Word();
			customerAlreadyExists.setKey("customer.exists");
			this.wordDao.save(customerAlreadyExists);
			Word customersTaxNumberAlreadyExists = new Word();
			customersTaxNumberAlreadyExists.setKey("customers.tax.number.already.exists");
			this.wordDao.save(customersTaxNumberAlreadyExists);
			Word customersIdendityAlreadyExists = new Word();
			customersIdendityAlreadyExists.setKey("customer.national.identity.already.exists");
			this.wordDao.save(customersIdendityAlreadyExists);

			Word brandAdded = new Word();
			brandAdded.setKey("brand.added");
			this.wordDao.save(brandAdded);
			Word brandUpdated = new Word();
			brandUpdated.setKey("brand.updated");
			this.wordDao.save(brandUpdated);
			Word brandDeleted = new Word();
			brandDeleted.setKey("brand.deleted");
			this.wordDao.save(brandDeleted);
			Word brandListed = new Word();
			brandListed.setKey("brands.listed");
			this.wordDao.save(brandListed);
			Word brandNotFound = new Word();
			brandNotFound.setKey("brand.not.found");
			this.wordDao.save(brandNotFound);
			Word brandFound = new Word();
			brandFound.setKey("brand.found");
			this.wordDao.save(brandFound);
			Word brandExists = new Word();
			brandExists.setKey("brand.exists");
			this.wordDao.save(brandExists);

			Word carAdded = new Word();
			carAdded.setKey("car.added");
			this.wordDao.save(carAdded);
			Word carUpdated = new Word();
			carUpdated.setKey("car.updated");
			this.wordDao.save(carUpdated);
			Word carDeleted = new Word();
			carDeleted.setKey("car.deleted");
			this.wordDao.save(carDeleted);
			Word carListed = new Word();
			carListed.setKey("cars.listed");
			this.wordDao.save(carListed);
			Word carGet = new Word();
			carGet.setKey("car.found");
			this.wordDao.save(carGet);
			Word carNotFound = new Word();
			carNotFound.setKey("car.not.found");
			this.wordDao.save(carNotFound);
			Word carNotReturned = new Word();
			carNotReturned.setKey("car.not.returned");
			this.wordDao.save(carNotReturned);
			Word getCarByBrandId = new Word();
			getCarByBrandId.setKey("get.car.by.brand.id");
			this.wordDao.save(getCarByBrandId);
			Word getCarByColorId = new Word();
			getCarByColorId.setKey("get.car.by.color.id");
			this.wordDao.save(getCarByColorId);
			Word getCarByCityId = new Word();
			getCarByCityId.setKey("get.car.by.city.id");
			this.wordDao.save(getCarByCityId);
			Word carExists = new Word();
			carExists.setKey("car.exists");
			this.wordDao.save(carExists);

			Word cityAdded = new Word();
			cityAdded.setKey("city.added");
			this.wordDao.save(cityAdded);
			Word cityUpdated = new Word();
			cityUpdated.setKey("city.updated");
			this.wordDao.save(cityUpdated);
			Word cityDeleted = new Word();
			cityDeleted.setKey("city.deleted");
			this.wordDao.save(cityDeleted);
			Word cityListed = new Word();
			cityListed.setKey("cities.listed");
			this.wordDao.save(cityListed);
			Word cityNotFound = new Word();
			cityNotFound.setKey("city.not.found");
			this.wordDao.save(cityNotFound);
			Word cityGet = new Word();
			cityGet.setKey("city.found");
			this.wordDao.save(cityGet);
			Word cityAlreadyExists = new Word();
			cityAlreadyExists.setKey("city.exists");
			this.wordDao.save(cityAlreadyExists);

			Word colorAdded = new Word();
			colorAdded.setKey("color.added");
			this.wordDao.save(colorAdded);
			Word colorUpdated = new Word();
			colorUpdated.setKey("color.updated");
			this.wordDao.save(colorUpdated);
			Word colorDeleted = new Word();
			colorDeleted.setKey("color.deleted");
			this.wordDao.save(colorDeleted);
			Word colorListed = new Word();
			colorListed.setKey("colors.listed");
			this.wordDao.save(colorListed);
			Word colorGet = new Word();
			colorGet.setKey("color.found");
			this.wordDao.save(colorGet);
			Word colorExists = new Word();
			colorExists.setKey("color.exists");
			this.wordDao.save(colorExists);
			Word colorNotFound = new Word();
			colorNotFound.setKey("color.not.found");
			this.wordDao.save(colorNotFound);
			Word colorNameError = new Word();
			colorNameError.setKey("color.name.error");
			this.wordDao.save(colorNameError);

			Word rentalAdd = new Word();
			rentalAdd.setKey("rental.added");
			this.wordDao.save(rentalAdd);
			Word rentalUpdated = new Word();
			rentalUpdated.setKey("rental.updated");
			this.wordDao.save(rentalUpdated);
			Word rentalListed = new Word();
			rentalListed.setKey("rentals.listed");
			this.wordDao.save(rentalListed);
			Word rentalDeleted = new Word();
			rentalDeleted.setKey("rental.deleted");
			this.wordDao.save(rentalDeleted);
			Word rentalDateError = new Word();
			rentalDateError.setKey("rental.date.error");
			this.wordDao.save(rentalDateError);
			Word rentalDateSuccess = new Word();
			rentalDateSuccess.setKey("rental.date.success");
			this.wordDao.save(rentalDateSuccess);
			Word rentalFindexScoreError = new Word();
			rentalFindexScoreError.setKey("rental.findex.score.error");
			this.wordDao.save(rentalFindexScoreError);
			Word rentalMaintenanceError = new Word();
			rentalMaintenanceError.setKey("rental.maintenance.error");
			this.wordDao.save(rentalMaintenanceError);
			Word rentalGet = new Word();
			rentalGet.setKey("rental.found");
			this.wordDao.save(rentalGet);
			Word rentalNotFound = new Word();
			rentalNotFound.setKey("rental.not.found");
			this.wordDao.save(rentalNotFound);
			Word rentalBalance = new Word();
			rentalBalance.setKey("rental.balance.insufficient");
			this.wordDao.save(rentalBalance);
			Word rentalDateIsNull = new Word();
			rentalDateIsNull.setKey("rental.car.is.not.returned.yet");
			this.wordDao.save(rentalDateIsNull);
			Word rentedCarIsOnMaintenance = new Word();
			rentedCarIsOnMaintenance.setKey("rented.car.is.on.maintenance");
			this.wordDao.save(rentedCarIsOnMaintenance);

			Word carImageAdd = new Word();
			carImageAdd.setKey("car.image.added");
			this.wordDao.save(carImageAdd);
			Word carImageUpdated = new Word();
			carImageUpdated.setKey("car.image.updated");
			this.wordDao.save(carImageUpdated);
			Word carImageDeleted = new Word();
			carImageDeleted.setKey("car.image.deleted");
			this.wordDao.save(carImageDeleted);
			Word carImageListed = new Word();
			carImageListed.setKey("car.images.listed");
			this.wordDao.save(carImageListed);
			Word carImageLimitError = new Word();
			carImageLimitError.setKey("car.image.limit.error");
			this.wordDao.save(carImageLimitError);
			Word carImageDefault = new Word();
			carImageDefault.setKey("car.image.default");
			this.wordDao.save(carImageDefault);
			Word carImageEmpty = new Word();
			carImageEmpty.setKey("car.image.empty");
			this.wordDao.save(carImageEmpty);
			Word carImageTypeError = new Word();
			carImageTypeError.setKey("car.image.type.not.valid");
			this.wordDao.save(carImageTypeError);
			Word carImageGet = new Word();
			carImageGet.setKey("car.image.found");
			this.wordDao.save(carImageGet);
			Word carImageNotFound = new Word();
			carImageNotFound.setKey("car.image.not.found");
			this.wordDao.save(carImageNotFound);

			Word loginEmailError = new Word();
			loginEmailError.setKey("this.email.is.not.registered");
			this.wordDao.save(loginEmailError);
			Word loginPasswordError = new Word();
			loginPasswordError.setKey("wrong.password");
			this.wordDao.save(loginPasswordError);
			Word loginSuccess = new Word();
			loginSuccess.setKey("login.success");
			this.wordDao.save(loginSuccess);

			Word creditCardAdd = new Word();
			creditCardAdd.setKey("credit.card.added");
			this.wordDao.save(creditCardAdd);
			Word creditCardUpdated = new Word();
			creditCardUpdated.setKey("credit.card.updated");
			this.wordDao.save(creditCardUpdated);
			Word creditCardDeleted = new Word();
			creditCardDeleted.setKey("credit.card.deleted");
			this.wordDao.save(creditCardDeleted);
			Word creditCardListed = new Word();
			creditCardListed.setKey("credit.cards.listed");
			this.wordDao.save(creditCardListed);
			Word creditCardNumberError = new Word();
			creditCardNumberError.setKey("credit.card.number.error");
			this.wordDao.save(creditCardNumberError);
			Word creditCardNameError = new Word();
			creditCardNameError.setKey("credit.card.name.error");
			this.wordDao.save(creditCardNameError);
			Word creditCardDateError = new Word();
			creditCardDateError.setKey("credit.card.date.error");
			this.wordDao.save(creditCardDateError);
			Word creditCardCvvError = new Word();
			creditCardCvvError.setKey("credit.card.cvv.error");
			this.wordDao.save(creditCardCvvError);
			Word creditCardSave = new Word();
			creditCardSave.setKey("credit.card.registered");
			this.wordDao.save(creditCardSave);
			Word creditCardGet = new Word();
			creditCardGet.setKey("credit.card.found");
			this.wordDao.save(creditCardGet);
			Word creditCardNotSave = new Word();
			creditCardNotSave.setKey("credit.card.not.save");
			this.wordDao.save(creditCardNotSave);
			Word creditCardNotFound = new Word();
			creditCardNotFound.setKey("credit.card.not.found");
			this.wordDao.save(creditCardNotFound);
			Word creditCardAlreadyExists = new Word();
			creditCardAlreadyExists.setKey("credit.card.exists");
			this.wordDao.save(creditCardAlreadyExists);
			Word formatNotValid = new Word();
			formatNotValid.setKey("format.not.valid");
			this.wordDao.save(formatNotValid);

			Word paymentAdded = new Word();
			paymentAdded.setKey("payment.add");
			this.wordDao.save(paymentAdded);
			Word paymentUpdated = new Word();
			paymentUpdated.setKey("payment.updated");
			this.wordDao.save(paymentUpdated);
			Word paymentDeleted = new Word();
			paymentDeleted.setKey("payment.deleted");
			this.wordDao.save(paymentDeleted);
			Word paymentsListed = new Word();
			paymentsListed.setKey("payments.listed");
			this.wordDao.save(paymentsListed);
			Word paymentCardSave = new Word();
			creditCardSave.setKey("credit.card.saved");
			this.wordDao.save(creditCardSave);
			Word paymentCardNotSave = new Word();
			paymentCardNotSave.setKey("credit.card.not.saved");
			this.wordDao.save(paymentCardNotSave);
			Word creditCardFail = new Word();
			creditCardFail.setKey("payment.card.fail");
			this.wordDao.save(creditCardFail);

			Word invoiceAdd = new Word();
			invoiceAdd.setKey("invoice.added");
			this.wordDao.save(invoiceAdd);
			Word invoiceUpdated = new Word();
			invoiceUpdated.setKey("invoice.updated");
			this.wordDao.save(invoiceUpdated);
			Word invoiceDeleted = new Word();
			invoiceDeleted.setKey("invoice.deleted");
			this.wordDao.save(invoiceDeleted);
			Word invoiceListed = new Word();
			invoiceListed.setKey("invoices.listed");
			this.wordDao.save(invoiceListed);
			Word invoiceGet = new Word();
			invoiceGet.setKey("invoice.found");
			this.wordDao.save(invoiceGet);
			Word invoiceGetCustomer = new Word();
			invoiceGetCustomer.setKey("invoice.by.customer");
			this.wordDao.save(invoiceGetCustomer);
			Word invoiceNotFound = new Word();
			invoiceNotFound.setKey("invoice.not.found");
			this.wordDao.save(invoiceNotFound);
			Word invoiceExists = new Word();
			invoiceExists.setKey("invoice.exists");
			this.wordDao.save(invoiceExists);

			Word userEmailAlreadyExists = new Word();
			userEmailAlreadyExists.setKey("user.email.exists");
			this.wordDao.save(userEmailAlreadyExists);
			Word userFound = new Word();
			userFound.setKey("user.found");
			this.wordDao.save(userFound);
			Word userNotFound = new Word();
			userNotFound.setKey("user.not.found");
			this.wordDao.save(userNotFound);
			Word userListed = new Word();
			userListed.setKey("users.listed");
			this.wordDao.save(userListed);

			Word damageAdd = new Word();
			damageAdd.setKey("damage.added");
			this.wordDao.save(damageAdd);
			Word damageDeleted = new Word();
			damageDeleted.setKey("damage.deleted");
			this.wordDao.save(damageDeleted);
			Word damageUpdated = new Word();
			damageUpdated.setKey("damage.updated");
			this.wordDao.save(damageUpdated);
			Word damageListed = new Word();
			damageListed.setKey("damages.listed");
			this.wordDao.save(damageListed);
			Word damageNotFound = new Word();
			damageNotFound.setKey("damage.not.found");
			this.wordDao.save(damageNotFound);
			Word damageGet = new Word();
			damageGet.setKey("damage.found");
			this.wordDao.save(damageGet);

			Word additionalServiceAdded = new Word();
			additionalServiceAdded.setKey("additional.service.added");
			this.wordDao.save(additionalServiceAdded);
			Word additionalServiceUpdated = new Word();
			additionalServiceUpdated.setKey("additional.service.updated");
			this.wordDao.save(additionalServiceUpdated);
			Word additionalServiceDeleted = new Word();
			additionalServiceDeleted.setKey("additional.service.deleted");
			this.wordDao.save(additionalServiceDeleted);
			Word additionalServiceListed = new Word();
			additionalServiceListed.setKey("additional.services.listed");
			this.wordDao.save(additionalServiceListed);
			Word additionalServiceNotFound = new Word();
			additionalServiceNotFound.setKey("additional.service.not.found");
			this.wordDao.save(additionalServiceNotFound);

			Word additionalRentalItemAdded = new Word();
			additionalRentalItemAdded.setKey("additional.rental.item.added");
			this.wordDao.save(additionalRentalItemAdded);
			Word additionalRentalItemDeleted = new Word();
			additionalRentalItemDeleted.setKey("additional.rental.item.deleted");
			this.wordDao.save(additionalRentalItemDeleted);
			Word additionalRentalItemUpdated = new Word();
			additionalRentalItemUpdated.setKey("additional.rental.item.updated");
			this.wordDao.save(additionalRentalItemUpdated);
			Word additionalRentalItemListed = new Word();
			additionalRentalItemListed.setKey("additional.rental.items.listed");
			this.wordDao.save(additionalRentalItemListed);
			Word additionalRentalItemNotFound = new Word();
			additionalRentalItemNotFound.setKey("additional.rental.item.not.found");
			this.wordDao.save(additionalRentalItemNotFound);

			Word carMaintenanceAdded = new Word();
			carMaintenanceAdded.setKey("car.maintenance.added");
			this.wordDao.save(carMaintenanceAdded);
			Word carMaintenanceUpdated = new Word();
			carMaintenanceUpdated.setKey("car.maintenance.updated");
			this.wordDao.save(carMaintenanceUpdated);
			Word carMaintenanceDeleted = new Word();
			carMaintenanceDeleted.setKey("car.maintenance.deleted");
			this.wordDao.save(carMaintenanceDeleted);
			Word carMaintenanceListed = new Word();
			carMaintenanceListed.setKey("car.maintenances.listed");
			this.wordDao.save(carMaintenanceListed);
			Word carMaintenanceRentalError = new Word();
			carMaintenanceRentalError.setKey("car.maintenance.rental.error");
			this.wordDao.save(carMaintenanceRentalError);
			Word carMaintenanceNotFound = new Word();
			carMaintenanceNotFound.setKey("car.maintenance.not.found");
			this.wordDao.save(carMaintenanceNotFound);
			Word carMaintenanceAlreadyExists = new Word();
			carMaintenanceAlreadyExists.setKey("car.maintenance.exists");
			this.wordDao.save(carMaintenanceAlreadyExists);

			Word languageAdded = new Word();
			languageAdded.setKey("language.added");
			this.wordDao.save(languageAdded);

			Word languageUpdated = new Word();
			languageUpdated.setKey("language.updated");
			this.wordDao.save(languageUpdated);

			Word languageDeleted = new Word();
			languageDeleted.setKey("language.deleted");
			this.wordDao.save(languageDeleted);

			Word languagesListed = new Word();
			languagesListed.setKey("languages.listed");
			this.wordDao.save(languagesListed);

			Word languageFound = new Word();
			languageFound.setKey("language.found");
			this.wordDao.save(languageFound);

			Word languageNotFound = new Word();
			languageNotFound.setKey("language.not.found");
			this.wordDao.save(languageNotFound);

			Word languageExists = new Word();
			languageExists.setKey("language.exists");
			this.wordDao.save(languageExists);

			Word translationAdded = new Word();
			translationAdded.setKey("translation.added");
			this.wordDao.save(translationAdded);

			Word translationUpdated = new Word();
			translationUpdated.setKey("translation.updated");
			this.wordDao.save(translationUpdated);

			Word translationDeleted = new Word();
			translationDeleted.setKey("translation.deleted");
			this.wordDao.save(translationDeleted);

			Word translationsListed = new Word();
			translationsListed.setKey("translations.listed");
			this.wordDao.save(translationsListed);

			Word translationFound = new Word();
			translationFound.setKey("translation.found");
			this.wordDao.save(translationFound);

			Word translationNotFound = new Word();
			translationNotFound.setKey("translation.not.found");
			this.wordDao.save(translationNotFound);

			Word translationExists = new Word();
			translationExists.setKey("translation.exists");
			this.wordDao.save(translationExists);

			Word wordAdded = new Word();
			wordAdded.setKey("word.added");
			this.wordDao.save(wordAdded);

			Word wordUpdated = new Word();
			wordUpdated.setKey("word.updated");
			this.wordDao.save(wordUpdated);

			Word wordDeleted = new Word();
			wordDeleted.setKey("word.deleted");
			this.wordDao.save(wordDeleted);

			Word wordsListed = new Word();
			wordsListed.setKey("words.listed");
			this.wordDao.save(wordsListed);

			Word wordFound = new Word();
			wordFound.setKey("word.found");
			this.wordDao.save(wordFound);

			Word wordNotFound = new Word();
			wordNotFound.setKey("word.not.found");
			this.wordDao.save(wordNotFound);

			Word wordExists = new Word();
			wordExists.setKey("word.exists");
			this.wordDao.save(wordExists);
		}

	}

	private void loadTranslationData() {

		if (translationDao.count() == 0) {
			var english = languageDao.getLanguagesByName("English");

			if (english == null) {
				english = languageDao.getLanguagesByName("English");
			}
			// kendi eklediklerim
			/*
			 * Translation carMaintenanceAddedEnglishTranslation = new Translation();
			 * carMaintenanceAddedEnglishTranslation.setLanguage(english); var
			 * carMaintenanceAddedEnglish =
			 * this.wordDao.getWordsByKey("car.maintenance.added");
			 * carMaintenanceAddedEnglishTranslation.setWord(carMaintenanceAddedEnglish);
			 * carMaintenanceAddedEnglishTranslation.setTranslation("Car Maintenance added."
			 * ); this.translationDao.save(carMaintenanceAddedEnglishTranslation);
			 */

			// kendi eklediklerim biter
			List<Translation> translations = new ArrayList();
			translations.add(new Translation(0, "Additional Service Item Added",
					this.wordDao.getWordsByKey("additional.service.item.added"), english));
			translations.add(new Translation(0, "Additional Service Item Updated",
					this.wordDao.getWordsByKey("additional.service.item.updated"), english));
			translations.add(new Translation(0, "Additional Service Item Deleted",
					this.wordDao.getWordsByKey("additional.service.item.deleted"), english));
			translations.add(new Translation(0, "Additional Service Item Not Found",
					this.wordDao.getWordsByKey("additional.service.item.not.found"), english));
			translations.add(new Translation(0, "Additional Service Item Listed",
					this.wordDao.getWordsByKey("additional.service.item.list"), english));
			translations.add(new Translation(0, "Brand name already exists",
					this.wordDao.getWordsByKey("brand.name.exists"), english));
			translations
					.add(new Translation(0, "Car Damage Added", this.wordDao.getWordsByKey("car.damage.add"), english));
			translations.add(
					new Translation(0, "Car Damage Updated", this.wordDao.getWordsByKey("car.damage.update"), english));
			translations.add(
					new Translation(0, "Car Damage Deleted", this.wordDao.getWordsByKey("car.damage.delete"), english));
			translations.add(new Translation(0, "Car Damage Not Found",
					this.wordDao.getWordsByKey("car.damage.not.found"), english));
			translations.add(new Translation(0, "Car Rented ", this.wordDao.getWordsByKey("car.rented"), english));
			translations.add(new Translation(0, "Car In Maintanance", this.wordDao.getWordsByKey("car.in.maintanance"),
					english));
			translations.add(new Translation(0, "City Name Already Exists",
					this.wordDao.getWordsByKey("city.name.exists"), english));
			translations.add(new Translation(0, "Color Name Already Exists",
					this.wordDao.getWordsByKey("color.name.exists"), english));
			translations.add(new Translation(0, "Company Name Already Exists",
					this.wordDao.getWordsByKey("company.name.exists"), english));
			translations
					.add(new Translation(0, "Age Not Enough", this.wordDao.getWordsByKey("age.not.enough"), english));
			translations.add(new Translation(0, "Rental Not Finished",
					this.wordDao.getWordsByKey("rental.not.finished"), english));
			translations.add(
					new Translation(0, "Payment Not Found", this.wordDao.getWordsByKey("payment.not.found"), english));
			translations.add(new Translation(0, "Promo Code Not Found",
					this.wordDao.getWordsByKey("promo.code.not.found"), english));
			translations
					.add(new Translation(0, "Promo Code Added", this.wordDao.getWordsByKey("promo.code.add"), english));
			translations.add(
					new Translation(0, "Promo Code Updated", this.wordDao.getWordsByKey("promo.code.update"), english));
			translations.add(
					new Translation(0, "Promo Code Deleted", this.wordDao.getWordsByKey("promo.code.delete"), english));
			translations.add(new Translation(0, "Promo Code Already Exists",
					this.wordDao.getWordsByKey("promo.code.already.exists"), english));
			translations.add(new Translation(0, "Dates Are Not Correct",
					this.wordDao.getWordsByKey("dates.are.not.correct"), english));
			translations.add(new Translation(0, "No Available Car In This Segment",
					this.wordDao.getWordsByKey("no.available.car.in.this.segment"), english));
			translations.add(new Translation(0, "Kilometers Are Not Correct",
					this.wordDao.getWordsByKey("kilometer.error"), english));
			translations.add(new Translation(0, "Segment Added", this.wordDao.getWordsByKey("segment.add"), english));
			translations
					.add(new Translation(0, "Segment Updated", this.wordDao.getWordsByKey("segment.update"), english));
			translations
					.add(new Translation(0, "Segment Deleted", this.wordDao.getWordsByKey("segment.delete"), english));
			translations.add(new Translation(0, "Segment Name Already Exists",
					this.wordDao.getWordsByKey("segment.name.already.exists"), english));
			translations.add(
					new Translation(0, "Segment Not Found", this.wordDao.getWordsByKey("segment.not.found"), english));

			translationDao.saveAll(translations);

			// Car Maintenance English

			Translation carMaintenanceAddedEnglishTranslation = new Translation();
			carMaintenanceAddedEnglishTranslation.setLanguage(english);
			var carMaintenanceAddedEnglish = this.wordDao.getWordsByKey("car.maintenance.added");
			carMaintenanceAddedEnglishTranslation.setWord(carMaintenanceAddedEnglish);
			carMaintenanceAddedEnglishTranslation.setTranslation("Car Maintenance added.");
			this.translationDao.save(carMaintenanceAddedEnglishTranslation);

			Translation carMaintenanceDeletedEnglishTranslation = new Translation();
			carMaintenanceDeletedEnglishTranslation.setLanguage(english);
			var carMaintenanceDeletedEnglish = this.wordDao.getWordsByKey("car.maintenance.deleted");
			carMaintenanceDeletedEnglishTranslation.setWord(carMaintenanceDeletedEnglish);
			carMaintenanceDeletedEnglishTranslation.setTranslation("Car Maintenance deleted.");
			this.translationDao.save(carMaintenanceDeletedEnglishTranslation);

			Translation carMaintenanceUpdatedEnglishTranslation = new Translation();
			carMaintenanceUpdatedEnglishTranslation.setLanguage(english);
			var carMaintenanceUpdatedEnglish = this.wordDao.getWordsByKey("car.maintenance.updated");
			carMaintenanceUpdatedEnglishTranslation.setWord(carMaintenanceUpdatedEnglish);
			carMaintenanceUpdatedEnglishTranslation.setTranslation("Maintenance entry updated.");
			this.translationDao.save(carMaintenanceUpdatedEnglishTranslation);

			Translation carMaintenancesListedEnglishTranslation = new Translation();
			carMaintenancesListedEnglishTranslation.setLanguage(english);
			var carMaintenancesListedEnglish = this.wordDao.getWordsByKey("car.maintenances.listed");
			carMaintenancesListedEnglishTranslation.setWord(carMaintenancesListedEnglish);
			carMaintenancesListedEnglishTranslation.setTranslation("All maintenances are listed.");
			this.translationDao.save(carMaintenancesListedEnglishTranslation);

			Translation carMaintenanceRentalErrorEnglishTranslation = new Translation();
			carMaintenanceRentalErrorEnglishTranslation.setLanguage(english);
			var carMaintenanceRentalErrorEnglish = this.wordDao.getWordsByKey("car.maintenance.rental.error");
			carMaintenanceRentalErrorEnglishTranslation.setWord(carMaintenanceRentalErrorEnglish);
			carMaintenanceRentalErrorEnglishTranslation.setTranslation("Car is already rented.");
			this.translationDao.save(carMaintenanceRentalErrorEnglishTranslation);

			Translation carMaintenanceNotFoundEnglishTranslation = new Translation();
			carMaintenanceNotFoundEnglishTranslation.setLanguage(english);
			var carMaintenanceNotFoundEnglish = this.wordDao.getWordsByKey("car.maintenance.not.found");
			carMaintenanceNotFoundEnglishTranslation.setWord(carMaintenanceNotFoundEnglish);
			carMaintenanceNotFoundEnglishTranslation.setTranslation("Car Maintenance Not Found.");
			this.translationDao.save(carMaintenanceNotFoundEnglishTranslation);

			Translation carMaintenanceAlreadyExistsEnglishTranslation = new Translation();
			carMaintenanceAlreadyExistsEnglishTranslation.setLanguage(english);
			var carMaintenanceAlreadyExistsEnglish = this.wordDao.getWordsByKey("car.maintenance.exists");
			carMaintenanceAlreadyExistsEnglishTranslation.setWord(carMaintenanceAlreadyExistsEnglish);
			carMaintenanceAlreadyExistsEnglishTranslation.setTranslation("Car is already in maintenance.");
			this.translationDao.save(carMaintenanceAlreadyExistsEnglishTranslation);

			Translation dataNotFoundTranslation = new Translation();
			dataNotFoundTranslation.setLanguage(english);
			var dataNotFoundEnglish = this.wordDao.getWordsByKey("data.not.found");
			dataNotFoundTranslation.setWord(dataNotFoundEnglish);
			dataNotFoundTranslation.setTranslation("Data Not Found");
			this.translationDao.save(dataNotFoundTranslation);

			Translation formatNotValidEnglishTranslation = new Translation();
			formatNotValidEnglishTranslation.setLanguage(english);
			var formatNotValidEnglish = this.wordDao.getWordsByKey("format.not.valid");
			formatNotValidEnglishTranslation.setWord(formatNotValidEnglish);
			formatNotValidEnglishTranslation.setTranslation("Data Input Format Not Valid");
			this.translationDao.save(formatNotValidEnglishTranslation);

			Translation validationErrorEnglishTranslation = new Translation();
			validationErrorEnglishTranslation.setLanguage(english);
			var validationErrorEnglish = this.wordDao.getWordsByKey("validation.error");
			validationErrorEnglishTranslation.setWord(validationErrorEnglish);
			validationErrorEnglishTranslation.setTranslation("Validation Error");
			this.translationDao.save(validationErrorEnglishTranslation);

			Translation loginSuccessEnglishTranslation = new Translation();
			loginSuccessEnglishTranslation.setLanguage(english);
			var loginSuccessEnglish = this.wordDao.getWordsByKey("login.success");
			loginSuccessEnglishTranslation.setWord(loginSuccessEnglish);
			loginSuccessEnglishTranslation.setTranslation("Login Success");
			this.translationDao.save(loginSuccessEnglishTranslation);

			Translation loginPasswordErrorEnglishTranslation = new Translation();
			loginPasswordErrorEnglishTranslation.setLanguage(english);
			var loginPasswordErrorEnglish = this.wordDao.getWordsByKey("wrong.password");
			loginPasswordErrorEnglishTranslation.setWord(loginPasswordErrorEnglish);
			loginPasswordErrorEnglishTranslation.setTranslation("Login password Error.");
			this.translationDao.save(loginPasswordErrorEnglishTranslation);

			Translation loginEmailErrorEnglishTranslation = new Translation();
			loginEmailErrorEnglishTranslation.setLanguage(english);
			var loginEmailErrorEnglish = this.wordDao.getWordsByKey("this.email.is.not.registered");
			loginEmailErrorEnglishTranslation.setWord(loginEmailErrorEnglish);
			loginEmailErrorEnglishTranslation.setTranslation("Login Email Error.");
			this.translationDao.save(loginEmailErrorEnglishTranslation);

			Translation additionalRentalItemUpdatedEnglishTranslation = new Translation();
			additionalRentalItemUpdatedEnglishTranslation.setLanguage(english);
			var additionalRentalItemUpdatedEnglish = this.wordDao.getWordsByKey("additional.rental.item.updated");
			additionalRentalItemUpdatedEnglishTranslation.setWord(additionalRentalItemUpdatedEnglish);
			additionalRentalItemUpdatedEnglishTranslation.setTranslation("Additional Rental Item Updated.");
			this.translationDao.save(additionalRentalItemUpdatedEnglishTranslation);

			Translation additionalRentalItemAddedEnglishTranslation = new Translation();
			additionalRentalItemAddedEnglishTranslation.setLanguage(english);
			var additionalRentalItemAddedEnglish = this.wordDao.getWordsByKey("additional.rental.item.added");
			additionalRentalItemAddedEnglishTranslation.setWord(additionalRentalItemAddedEnglish);
			additionalRentalItemAddedEnglishTranslation.setTranslation("Additional Rental Item Added.");
			this.translationDao.save(additionalRentalItemAddedEnglishTranslation);

			Translation additionalRentalItemDeletedEnglishTranslation = new Translation();
			additionalRentalItemDeletedEnglishTranslation.setLanguage(english);
			var additionalRentalItemDeletedEnglish = this.wordDao.getWordsByKey("additional.rental.item.deleted");
			additionalRentalItemDeletedEnglishTranslation.setWord(additionalRentalItemDeletedEnglish);
			additionalRentalItemDeletedEnglishTranslation.setTranslation("Additional Rental Item Deleted.");
			this.translationDao.save(additionalRentalItemDeletedEnglishTranslation);

			Translation additionalRentalItemListedEnglishTranslation = new Translation();
			additionalRentalItemListedEnglishTranslation.setLanguage(english);
			var additionalRentalItemListedEnglish = this.wordDao.getWordsByKey("additional.rental.items.listed");
			additionalRentalItemListedEnglishTranslation.setWord(additionalRentalItemListedEnglish);
			additionalRentalItemListedEnglishTranslation.setTranslation("Additional Rental Item Listed.");
			this.translationDao.save(additionalRentalItemListedEnglishTranslation);

			Translation additionalRentalItemNotFoundEnglishTranslation = new Translation();
			additionalRentalItemNotFoundEnglishTranslation.setLanguage(english);
			var additionalRentalItemNotFoundEnglish = this.wordDao.getWordsByKey("additional.rental.item.not.found");
			additionalRentalItemNotFoundEnglishTranslation.setWord(additionalRentalItemNotFoundEnglish);
			additionalRentalItemNotFoundEnglishTranslation.setTranslation("Additional Rental Item Not Found.");
			this.translationDao.save(additionalRentalItemNotFoundEnglishTranslation);

			// -----Brand-------
			Translation brandAddedEnglishTranslation = new Translation();
			brandAddedEnglishTranslation.setLanguage(english);
			var brandAddedEnglish = this.wordDao.getWordsByKey("brand.added");
			brandAddedEnglishTranslation.setWord(brandAddedEnglish);
			brandAddedEnglishTranslation.setTranslation("Brand Added");
			this.translationDao.save(brandAddedEnglishTranslation);

			Translation brandUpdatedEnglishTranslation = new Translation();
			brandUpdatedEnglishTranslation.setLanguage(english);
			var brandUpdatedEnglish = this.wordDao.getWordsByKey("brand.updated");
			brandUpdatedEnglishTranslation.setWord(brandUpdatedEnglish);
			brandUpdatedEnglishTranslation.setTranslation("Brand updated");
			this.translationDao.save(brandUpdatedEnglishTranslation);

			Translation brandDeletedEnglishTranslation = new Translation();
			brandDeletedEnglishTranslation.setLanguage(english);
			var brandDeletedEnglish = this.wordDao.getWordsByKey("brand.deleted");
			brandDeletedEnglishTranslation.setWord(brandDeletedEnglish);
			brandDeletedEnglishTranslation.setTranslation("Brand Deleted");
			this.translationDao.save(brandDeletedEnglishTranslation);

			Translation brandsListedEnglishTranslation = new Translation();
			brandsListedEnglishTranslation.setLanguage(english);
			var brandsListedEnglish = this.wordDao.getWordsByKey("brands.listed");
			brandsListedEnglishTranslation.setWord(brandsListedEnglish);
			brandsListedEnglishTranslation.setTranslation("Brands Listed");
			this.translationDao.save(brandsListedEnglishTranslation);

			Translation brandFoundEnglishTranslation = new Translation();
			brandFoundEnglishTranslation.setLanguage(english);
			var brandfoundEnglish = this.wordDao.getWordsByKey("brand.found");
			brandFoundEnglishTranslation.setWord(brandfoundEnglish);
			brandFoundEnglishTranslation.setTranslation("Brand Found");
			this.translationDao.save(brandFoundEnglishTranslation);

			Translation brandNotFoundEnglishTranslation = new Translation();
			brandNotFoundEnglishTranslation.setLanguage(english);
			var brandNotFoundEnglish = this.wordDao.getWordsByKey("brand.not.found");
			brandNotFoundEnglishTranslation.setWord(brandNotFoundEnglish);
			brandNotFoundEnglishTranslation.setTranslation("Brand Not Found");
			this.translationDao.save(brandNotFoundEnglishTranslation);

			Translation brandExistsEnglishTranslation = new Translation();
			brandExistsEnglishTranslation.setLanguage(english);
			var brandExistsEnglish = this.wordDao.getWordsByKey("brand.exists");
			brandExistsEnglishTranslation.setWord(brandExistsEnglish);
			brandExistsEnglishTranslation.setTranslation("Brand Exists");
			this.translationDao.save(brandExistsEnglishTranslation);

			// -------------Color-------------

			Translation colorAddedEnglishTranslation = new Translation();
			colorAddedEnglishTranslation.setLanguage(english);
			var colorAddedEnglish = this.wordDao.getWordsByKey("color.added");
			colorAddedEnglishTranslation.setWord(colorAddedEnglish);
			colorAddedEnglishTranslation.setTranslation("Color Added");
			this.translationDao.save(colorAddedEnglishTranslation);

			Translation colorUpdatedEnglishTranslation = new Translation();
			colorUpdatedEnglishTranslation.setLanguage(english);
			var colorUpdatedEnglish = this.wordDao.getWordsByKey("color.updated");
			colorUpdatedEnglishTranslation.setWord(colorUpdatedEnglish);
			colorUpdatedEnglishTranslation.setTranslation("Color Updated");
			this.translationDao.save(colorUpdatedEnglishTranslation);

			Translation colorDeletedEnglishTranslation = new Translation();
			colorDeletedEnglishTranslation.setLanguage(english);
			var colorDeletedEnglish = this.wordDao.getWordsByKey("color.deleted");
			colorDeletedEnglishTranslation.setWord(colorDeletedEnglish);
			colorDeletedEnglishTranslation.setTranslation("Color Deleted");
			this.translationDao.save(colorDeletedEnglishTranslation);

			Translation colorsListedEnglishTranslation = new Translation();
			colorsListedEnglishTranslation.setLanguage(english);
			var colorsListedEnglish = this.wordDao.getWordsByKey("colors.listed");
			colorsListedEnglishTranslation.setWord(colorsListedEnglish);
			colorsListedEnglishTranslation.setTranslation("Colors Listed");
			this.translationDao.save(colorsListedEnglishTranslation);

			Translation colorFoundEnglishTranslation = new Translation();
			colorFoundEnglishTranslation.setLanguage(english);
			var colorFoundEnglish = this.wordDao.getWordsByKey("color.found");
			colorFoundEnglishTranslation.setWord(colorFoundEnglish);
			colorFoundEnglishTranslation.setTranslation("Color Found");
			this.translationDao.save(colorFoundEnglishTranslation);

			Translation colorNotFoundEnglishTranslation = new Translation();
			colorNotFoundEnglishTranslation.setLanguage(english);
			var colorNotFoundEnglish = this.wordDao.getWordsByKey("color.not.found");
			colorNotFoundEnglishTranslation.setWord(colorNotFoundEnglish);
			colorNotFoundEnglishTranslation.setTranslation("Color Not Found");
			this.translationDao.save(colorNotFoundEnglishTranslation);

			Translation colorExistsEnglishTranslation = new Translation();
			colorExistsEnglishTranslation.setLanguage(english);
			var colorExistsEnglish = this.wordDao.getWordsByKey("color.exists");
			colorExistsEnglishTranslation.setWord(colorExistsEnglish);
			colorExistsEnglishTranslation.setTranslation("Color Exists");
			this.translationDao.save(colorExistsEnglishTranslation);

			// --------------------------Car--------------------------

			Translation carAddedEnglishTranslation = new Translation();
			carAddedEnglishTranslation.setLanguage(english);
			var carAddedEnglish = this.wordDao.getWordsByKey("car.added");
			carAddedEnglishTranslation.setWord(carAddedEnglish);
			carAddedEnglishTranslation.setTranslation("Car Added");
			this.translationDao.save(carAddedEnglishTranslation);

			Translation carUpdatedEnglishTranslation = new Translation();
			carUpdatedEnglishTranslation.setLanguage(english);
			var carUpdatedEnglish = this.wordDao.getWordsByKey("car.updated");
			carUpdatedEnglishTranslation.setWord(carUpdatedEnglish);
			carUpdatedEnglishTranslation.setTranslation("Car Updated");
			this.translationDao.save(carUpdatedEnglishTranslation);

			Translation carDeletedEnglishTranslation = new Translation();
			carDeletedEnglishTranslation.setLanguage(english);
			var carDeletedEnglish = this.wordDao.getWordsByKey("car.deleted");
			carDeletedEnglishTranslation.setWord(carDeletedEnglish);
			carDeletedEnglishTranslation.setTranslation("Car Deleted");
			this.translationDao.save(carDeletedEnglishTranslation);

			Translation carFoundEnglishTranslation = new Translation();
			carFoundEnglishTranslation.setLanguage(english);
			var carFoundEnglish = this.wordDao.getWordsByKey("car.found");
			carFoundEnglishTranslation.setWord(carFoundEnglish);
			carFoundEnglishTranslation.setTranslation("Car Found");
			this.translationDao.save(carFoundEnglishTranslation);

			Translation carsListedEnglishTranslation = new Translation();
			carsListedEnglishTranslation.setLanguage(english);
			var carListedEnglish = this.wordDao.getWordsByKey("cars.listed");
			carsListedEnglishTranslation.setWord(carListedEnglish);
			carsListedEnglishTranslation.setTranslation("Cars Listed");
			this.translationDao.save(carsListedEnglishTranslation);

			Translation carNotFoundEnglishTranslation = new Translation();
			carNotFoundEnglishTranslation.setLanguage(english);
			var carNotFoundEnglish = this.wordDao.getWordsByKey("car.not.found");
			carNotFoundEnglishTranslation.setWord(carNotFoundEnglish);
			carNotFoundEnglishTranslation.setTranslation("Car Not Found");
			this.translationDao.save(carNotFoundEnglishTranslation);

			Translation getCarByBrandIdEnglishTranslation = new Translation();
			getCarByBrandIdEnglishTranslation.setLanguage(english);
			var getCarByBrandIdEnglish = this.wordDao.getWordsByKey("get.car.by.brand.id");
			getCarByBrandIdEnglishTranslation.setWord(getCarByBrandIdEnglish);
			getCarByBrandIdEnglishTranslation.setTranslation("Car has been brought by its brand ID.");
			this.translationDao.save(getCarByBrandIdEnglishTranslation);

			Translation getCarByColorIdEnglishTranslation = new Translation();
			getCarByColorIdEnglishTranslation.setLanguage(english);
			var getCarByColorIdEnglish = this.wordDao.getWordsByKey("get.car.by.color.id");
			getCarByColorIdEnglishTranslation.setWord(getCarByColorIdEnglish);
			getCarByColorIdEnglishTranslation.setTranslation("Car has been brought by its color ID.");
			this.translationDao.save(getCarByColorIdEnglishTranslation);

			Translation getCarByCityIdEnglishTranslation = new Translation();
			getCarByCityIdEnglishTranslation.setLanguage(english);
			var getCarByCityIdEnglish = this.wordDao.getWordsByKey("get.car.by.city.id");
			getCarByCityIdEnglishTranslation.setWord(getCarByCityIdEnglish);
			getCarByCityIdEnglishTranslation.setTranslation("Car has been brought by its city ID.");
			this.translationDao.save(getCarByCityIdEnglishTranslation);

			// -----------------CITY----------------------------

			Translation cityAddedEnglishTranslation = new Translation();
			cityAddedEnglishTranslation.setLanguage(english);
			var cityAddedEnglish = this.wordDao.getWordsByKey("city.added");
			cityAddedEnglishTranslation.setWord(cityAddedEnglish);
			cityAddedEnglishTranslation.setTranslation("City Added");
			this.translationDao.save(cityAddedEnglishTranslation);

			Translation cityUpdatedEnglishTranslation = new Translation();
			cityUpdatedEnglishTranslation.setLanguage(english);
			var cityUpdateEnglish = this.wordDao.getWordsByKey("city.updated");
			cityUpdatedEnglishTranslation.setWord(cityUpdateEnglish);
			cityUpdatedEnglishTranslation.setTranslation("City Updated");
			this.translationDao.save(cityUpdatedEnglishTranslation);

			Translation cityDeletedEnglishTranslation = new Translation();
			cityDeletedEnglishTranslation.setLanguage(english);
			var cityDeletedEnglish = this.wordDao.getWordsByKey("city.deleted");
			cityDeletedEnglishTranslation.setWord(cityDeletedEnglish);
			cityDeletedEnglishTranslation.setTranslation("City Deleted");
			this.translationDao.save(cityDeletedEnglishTranslation);

			Translation citiesListedEnglishTranslation = new Translation();
			citiesListedEnglishTranslation.setLanguage(english);
			var citiesListedEnglish = this.wordDao.getWordsByKey("cities.listed");
			citiesListedEnglishTranslation.setWord(citiesListedEnglish);
			citiesListedEnglishTranslation.setTranslation("Cities Listed");
			this.translationDao.save(citiesListedEnglishTranslation);

			Translation cityFoundEnglishTranslation = new Translation();
			cityFoundEnglishTranslation.setLanguage(english);
			var cityFoundEnglish = this.wordDao.getWordsByKey("city.found");
			cityFoundEnglishTranslation.setWord(cityFoundEnglish);
			cityFoundEnglishTranslation.setTranslation("City Found");
			this.translationDao.save(cityFoundEnglishTranslation);

			Translation cityNotFoundEnglishTranslation = new Translation();
			cityNotFoundEnglishTranslation.setLanguage(english);
			var cityNotFoundEnglish = this.wordDao.getWordsByKey("city.not.found");
			cityNotFoundEnglishTranslation.setWord(cityNotFoundEnglish);
			cityNotFoundEnglishTranslation.setTranslation("City Not Found");
			this.translationDao.save(cityNotFoundEnglishTranslation);

			Translation cityExistsEnglishTranslation = new Translation();
			cityExistsEnglishTranslation.setLanguage(english);
			var cityExistsEnglish = this.wordDao.getWordsByKey("city.exists");
			cityExistsEnglishTranslation.setWord(cityExistsEnglish);
			cityExistsEnglishTranslation.setTranslation("City Exists");
			this.translationDao.save(cityExistsEnglishTranslation);

			// --------------------Rental------------------------------

			Translation rentalAddedEnglishTranslation = new Translation();
			rentalAddedEnglishTranslation.setLanguage(english);
			var rentalAddedEnglish = this.wordDao.getWordsByKey("rental.added");
			rentalAddedEnglishTranslation.setWord(rentalAddedEnglish);
			rentalAddedEnglishTranslation.setTranslation("Rental Added");
			this.translationDao.save(rentalAddedEnglishTranslation);

			Translation rentalUpdatedEnglishTranslation = new Translation();
			rentalUpdatedEnglishTranslation.setLanguage(english);
			var rentalUpdatedEnglish = this.wordDao.getWordsByKey("rental.updated");
			rentalUpdatedEnglishTranslation.setWord(rentalUpdatedEnglish);
			rentalUpdatedEnglishTranslation.setTranslation("Rental Updated");
			this.translationDao.save(rentalUpdatedEnglishTranslation);

			Translation rentalDeletedEnglishTranslation = new Translation();
			rentalDeletedEnglishTranslation.setLanguage(english);
			var rentalDeletedEnglish = this.wordDao.getWordsByKey("rental.deleted");
			rentalDeletedEnglishTranslation.setWord(rentalDeletedEnglish);
			rentalDeletedEnglishTranslation.setTranslation("Rental Deleted");
			this.translationDao.save(rentalDeletedEnglishTranslation);

			Translation rentalsListedEnglishTranslation = new Translation();
			rentalsListedEnglishTranslation.setLanguage(english);
			var rentalsListedEnglish = this.wordDao.getWordsByKey("rentals.listed");
			rentalsListedEnglishTranslation.setWord(rentalsListedEnglish);
			rentalsListedEnglishTranslation.setTranslation("Rentals Listed");
			this.translationDao.save(rentalsListedEnglishTranslation);

			Translation rentalFoundEnglishTranslation = new Translation();
			rentalFoundEnglishTranslation.setLanguage(english);
			var rentalFoundEnglish = this.wordDao.getWordsByKey("rental.found");
			rentalFoundEnglishTranslation.setWord(rentalFoundEnglish);
			rentalFoundEnglishTranslation.setTranslation("Rental Found");
			this.translationDao.save(rentalFoundEnglishTranslation);

			Translation rentalExistsEnglishTranslation = new Translation();
			rentalExistsEnglishTranslation.setLanguage(english);
			var rentalExistsEnglish = this.wordDao.getWordsByKey("rental.car.is.not.returned.yet");
			rentalExistsEnglishTranslation.setWord(rentalExistsEnglish);
			rentalExistsEnglishTranslation.setTranslation("Car Is Still Rented And Not Returned");
			this.translationDao.save(rentalExistsEnglishTranslation);

			Translation rentalNotFoundEnglishTranslation = new Translation();
			rentalNotFoundEnglishTranslation.setLanguage(english);
			var rentalNotFoundEnglish = this.wordDao.getWordsByKey("rental.not.found");
			rentalNotFoundEnglishTranslation.setWord(rentalNotFoundEnglish);
			rentalNotFoundEnglishTranslation.setTranslation("Rent Information Not Found");
			this.translationDao.save(rentalNotFoundEnglishTranslation);

			Translation rentedCarIsOnManitenanceEnglishTranslation = new Translation();
			rentedCarIsOnManitenanceEnglishTranslation.setLanguage(english);
			var rentedCarIsOnManitenanceEnglish = this.wordDao.getWordsByKey("rented.car.is.on.maintenance");
			rentedCarIsOnManitenanceEnglishTranslation.setWord(rentedCarIsOnManitenanceEnglish);
			rentedCarIsOnManitenanceEnglishTranslation.setTranslation("Rented Car Is On Maintenance");
			this.translationDao.save(rentedCarIsOnManitenanceEnglishTranslation);

			Translation rentalFindexScoreErrorEnglishTranslation = new Translation();
			rentalFindexScoreErrorEnglishTranslation.setLanguage(english);
			var rentalFindexScoreErrorEnglish = this.wordDao.getWordsByKey("rental.findex.score.error");
			rentalFindexScoreErrorEnglishTranslation.setWord(rentalFindexScoreErrorEnglish);
			rentalFindexScoreErrorEnglishTranslation.setTranslation("Your Findex Score Is Not Enough To Rent This Car");
			this.translationDao.save(rentalFindexScoreErrorEnglishTranslation);

			Translation rentalDateErrorEnglishTranslation = new Translation();
			rentalDateErrorEnglishTranslation.setLanguage(english);
			var rentalDateErrorEnglish = this.wordDao.getWordsByKey("rental.date.error");
			rentalDateErrorEnglishTranslation.setWord(rentalDateErrorEnglish);
			rentalDateErrorEnglishTranslation.setTranslation("Rental's Rent Date Must Be Before Rental's Return Date");
			this.translationDao.save(rentalDateErrorEnglishTranslation);

			Translation carImageAddedEnglishTranslation = new Translation();
			carImageAddedEnglishTranslation.setLanguage(english);
			var carImageAddedEnglish = this.wordDao.getWordsByKey("car.image.added");
			carImageAddedEnglishTranslation.setWord(carImageAddedEnglish);
			carImageAddedEnglishTranslation.setTranslation("Car Image Added.");
			this.translationDao.save(carImageAddedEnglishTranslation);

			Translation carImageDeletedEnglishTranslation = new Translation();
			carImageDeletedEnglishTranslation.setLanguage(english);
			var carImageDeletedEnglish = this.wordDao.getWordsByKey("car.image.deleted");
			carImageDeletedEnglishTranslation.setWord(carImageDeletedEnglish);
			carImageDeletedEnglishTranslation.setTranslation("Car Image Deleted.");
			this.translationDao.save(carImageDeletedEnglishTranslation);

			Translation carImageUpdatedEnglishTranslation = new Translation();
			carImageUpdatedEnglishTranslation.setLanguage(english);
			var carImageUpdatedEnglish = this.wordDao.getWordsByKey("car.image.updated");
			carImageUpdatedEnglishTranslation.setWord(carImageUpdatedEnglish);
			carImageUpdatedEnglishTranslation.setTranslation("Car Image Updated.");
			this.translationDao.save(carImageUpdatedEnglishTranslation);

			Translation carImageListedEnglishTranslation = new Translation();
			carImageListedEnglishTranslation.setLanguage(english);
			var carImageListedEnglish = this.wordDao.getWordsByKey("car.images.listed");
			carImageListedEnglishTranslation.setWord(carImageListedEnglish);
			carImageListedEnglishTranslation.setTranslation("Car Images Are Listed.");
			this.translationDao.save(carImageListedEnglishTranslation);

			Translation carImageNameErrorEnglishTranslation = new Translation();
			carImageNameErrorEnglishTranslation.setLanguage(english);
			var carImageNameErrorEnglish = this.wordDao.getWordsByKey("car.image.type.not.valid");
			carImageNameErrorEnglishTranslation.setWord(carImageNameErrorEnglish);
			carImageNameErrorEnglishTranslation.setTranslation("Car Image Type Error.");
			this.translationDao.save(carImageNameErrorEnglishTranslation);

			Translation carImageLimitErrorEnglishTranslation = new Translation();
			carImageLimitErrorEnglishTranslation.setLanguage(english);
			var carImageLimitErrorEnglish = this.wordDao.getWordsByKey("car.image.limit.error");
			carImageLimitErrorEnglishTranslation.setWord(carImageLimitErrorEnglish);
			carImageLimitErrorEnglishTranslation.setTranslation("Car Image Limit Error.");
			this.translationDao.save(carImageLimitErrorEnglishTranslation);

			Translation carImageNotFoundEnglishTranslation = new Translation();
			carImageNotFoundEnglishTranslation.setLanguage(english);
			var carImageNotFoundEnglish = this.wordDao.getWordsByKey("car.image.not.found");
			carImageNotFoundEnglishTranslation.setWord(carImageNotFoundEnglish);
			carImageNotFoundEnglishTranslation.setTranslation("Car Image Not Found.");
			this.translationDao.save(carImageNotFoundEnglishTranslation);

			Translation creditCardAddedEnglishTranslation = new Translation();
			creditCardAddedEnglishTranslation.setLanguage(english);
			var creditCardAddedEnglish = this.wordDao.getWordsByKey("credit.card.added");
			creditCardAddedEnglishTranslation.setWord(creditCardAddedEnglish);
			creditCardAddedEnglishTranslation.setTranslation("Credit Card Added.");
			this.translationDao.save(creditCardAddedEnglishTranslation);

			Translation creditCardDeletedEnglishTranslation = new Translation();
			creditCardDeletedEnglishTranslation.setLanguage(english);
			var creditCardDeletedEnglish = this.wordDao.getWordsByKey("credit.card.deleted");
			creditCardDeletedEnglishTranslation.setWord(creditCardDeletedEnglish);
			creditCardDeletedEnglishTranslation.setTranslation("Credit Card Deleted.");
			this.translationDao.save(creditCardDeletedEnglishTranslation);

			Translation creditCardUpdatedEnglishTranslation = new Translation();
			creditCardUpdatedEnglishTranslation.setLanguage(english);
			var creditCardUpdatedEnglish = this.wordDao.getWordsByKey("credit.card.updated");
			creditCardUpdatedEnglishTranslation.setWord(creditCardUpdatedEnglish);
			creditCardUpdatedEnglishTranslation.setTranslation("Credit Card Updated.");
			this.translationDao.save(creditCardUpdatedEnglishTranslation);

			Translation creditCardListedEnglishTranslation = new Translation();
			creditCardListedEnglishTranslation.setLanguage(english);
			var creditCardListedEnglish = this.wordDao.getWordsByKey("credit.cards.listed");
			creditCardListedEnglishTranslation.setWord(creditCardListedEnglish);
			creditCardListedEnglishTranslation.setTranslation("Credit Cards Listed.");
			this.translationDao.save(creditCardListedEnglishTranslation);

			Translation creditCardNameErrorEnglishTranslation = new Translation();
			creditCardNameErrorEnglishTranslation.setLanguage(english);
			var creditCardNameErrorEnglish = this.wordDao.getWordsByKey("credit.card.name.error");
			creditCardNameErrorEnglishTranslation.setWord(creditCardNameErrorEnglish);
			creditCardNameErrorEnglishTranslation.setTranslation("Credit Card Name Error.");
			this.translationDao.save(creditCardNameErrorEnglishTranslation);

			Translation creditCardNumberErrorEnglishTranslation = new Translation();
			creditCardNumberErrorEnglishTranslation.setLanguage(english);
			var creditCardNumberErrorEnglish = this.wordDao.getWordsByKey("credit.card.number.error");
			creditCardNumberErrorEnglishTranslation.setWord(creditCardNumberErrorEnglish);
			creditCardNumberErrorEnglishTranslation.setTranslation("Credit Card Number Error.");
			this.translationDao.save(creditCardNumberErrorEnglishTranslation);

			Translation creditCardExpirationDateErrorEnglishTranslation = new Translation();
			creditCardExpirationDateErrorEnglishTranslation.setLanguage(english);
			var creditCardExpirationDateErrorEnglish = this.wordDao.getWordsByKey("credit.card.date.error");
			creditCardExpirationDateErrorEnglishTranslation.setWord(creditCardExpirationDateErrorEnglish);
			creditCardExpirationDateErrorEnglishTranslation.setTranslation("Credit Card Expiration Date Error.");
			this.translationDao.save(creditCardExpirationDateErrorEnglishTranslation);

			Translation creditCardNotFoundEnglishTranslation = new Translation();
			creditCardNotFoundEnglishTranslation.setLanguage(english);
			var creditCardNotFoundEnglish = this.wordDao.getWordsByKey("credit.card.not.found");
			creditCardNotFoundEnglishTranslation.setWord(creditCardNotFoundEnglish);
			creditCardNotFoundEnglishTranslation.setTranslation("Credit Card Not Found.");
			this.translationDao.save(creditCardNotFoundEnglishTranslation);

			Translation creditCardAlreadyExistsEnglishTranslation = new Translation();
			creditCardAlreadyExistsEnglishTranslation.setLanguage(english);
			var creditCardAlreadyExistsEnglish = this.wordDao.getWordsByKey("credit.card.exists");
			creditCardAlreadyExistsEnglishTranslation.setWord(creditCardAlreadyExistsEnglish);
			creditCardAlreadyExistsEnglishTranslation.setTranslation("Credit Card Already Exists.");
			this.translationDao.save(creditCardAlreadyExistsEnglishTranslation);

			Translation creditCardCvvErrorEnglishTranslation = new Translation();
			creditCardCvvErrorEnglishTranslation.setLanguage(english);
			var creditCardCvvErrorEnglish = this.wordDao.getWordsByKey("credit.card.cvv.error");
			creditCardCvvErrorEnglishTranslation.setWord(creditCardCvvErrorEnglish);
			creditCardCvvErrorEnglishTranslation.setTranslation("Invalid CVV code.");
			this.translationDao.save(creditCardCvvErrorEnglishTranslation);

			Translation invoiceAddedEnglishTranslation = new Translation();
			invoiceAddedEnglishTranslation.setLanguage(english);
			var invoiceAddedEnglish = this.wordDao.getWordsByKey("invoice.added");
			invoiceAddedEnglishTranslation.setWord(invoiceAddedEnglish);
			invoiceAddedEnglishTranslation.setTranslation("Invoice Added.");
			this.translationDao.save(invoiceAddedEnglishTranslation);

			Translation invoiceDeletedEnglishTranslation = new Translation();
			invoiceDeletedEnglishTranslation.setLanguage(english);
			var invoiceDeletedEnglish = this.wordDao.getWordsByKey("invoice.deleted");
			invoiceDeletedEnglishTranslation.setWord(invoiceDeletedEnglish);
			invoiceDeletedEnglishTranslation.setTranslation("Invoice Deleted.");
			this.translationDao.save(invoiceDeletedEnglishTranslation);

			Translation invoiceUpdatedEnglishTranslation = new Translation();
			invoiceUpdatedEnglishTranslation.setLanguage(english);
			var invoiceUpdatedEnglish = this.wordDao.getWordsByKey("invoice.updated");
			invoiceUpdatedEnglishTranslation.setWord(invoiceUpdatedEnglish);
			invoiceUpdatedEnglishTranslation.setTranslation("Invoice Updated.");
			this.translationDao.save(invoiceUpdatedEnglishTranslation);

			Translation invoiceListedEnglishTranslation = new Translation();
			invoiceListedEnglishTranslation.setLanguage(english);
			var invoiceListedEnglish = this.wordDao.getWordsByKey("invoices.listed");
			invoiceListedEnglishTranslation.setWord(invoiceListedEnglish);
			invoiceListedEnglishTranslation.setTranslation("Invoice Listed");
			this.translationDao.save(invoiceListedEnglishTranslation);

			Translation invoiceNotFoundEnglishTranslation = new Translation();
			invoiceNotFoundEnglishTranslation.setLanguage(english);
			var invoiceNotFoundEnglish = this.wordDao.getWordsByKey("invoice.not.found");
			invoiceNotFoundEnglishTranslation.setWord(invoiceNotFoundEnglish);
			invoiceNotFoundEnglishTranslation.setTranslation("Invoice Not Found.");
			this.translationDao.save(invoiceNotFoundEnglishTranslation);

			Translation invoiceNumberAlreadyExistsEnglishTranslation = new Translation();
			invoiceNumberAlreadyExistsEnglishTranslation.setLanguage(english);
			var invoiceNumberAlreadyExistsEnglish = this.wordDao.getWordsByKey("invoice.exists");
			invoiceNumberAlreadyExistsEnglishTranslation.setWord(invoiceNumberAlreadyExistsEnglish);
			invoiceNumberAlreadyExistsEnglishTranslation.setTranslation("Invoice already exists.");
			this.translationDao.save(invoiceNumberAlreadyExistsEnglishTranslation);

			Translation additionalServiceAddedEnglishTranslation = new Translation();
			additionalServiceAddedEnglishTranslation.setLanguage(english);
			var additionalServiceAddedEnglish = this.wordDao.getWordsByKey("additional.service.added");
			additionalServiceAddedEnglishTranslation.setWord(additionalServiceAddedEnglish);
			additionalServiceAddedEnglishTranslation.setTranslation("Additional Service Added.");
			this.translationDao.save(additionalServiceAddedEnglishTranslation);

			Translation additionalServiceDeletedEnglishTranslation = new Translation();
			additionalServiceDeletedEnglishTranslation.setLanguage(english);
			var additionalServiceDeletedEnglish = this.wordDao.getWordsByKey("additional.service.deleted");
			additionalServiceDeletedEnglishTranslation.setWord(additionalServiceDeletedEnglish);
			additionalServiceDeletedEnglishTranslation.setTranslation("Additional Service Deleted.");
			this.translationDao.save(additionalServiceDeletedEnglishTranslation);

			Translation additionalServiceUpdatedEnglishTranslation = new Translation();
			additionalServiceUpdatedEnglishTranslation.setLanguage(english);
			var additionalServiceUpdatedEnglish = this.wordDao.getWordsByKey("additional.service.updated");
			additionalServiceUpdatedEnglishTranslation.setWord(additionalServiceUpdatedEnglish);
			additionalServiceUpdatedEnglishTranslation.setTranslation("Additional Service Updated.");
			this.translationDao.save(additionalServiceUpdatedEnglishTranslation);

			Translation additionalServiceListedEnglishTranslation = new Translation();
			additionalServiceListedEnglishTranslation.setLanguage(english);
			var additionalServiceListedEnglish = this.wordDao.getWordsByKey("additional.services.listed");
			additionalServiceListedEnglishTranslation.setWord(additionalServiceListedEnglish);
			additionalServiceListedEnglishTranslation.setTranslation("Additional Service Listed.");
			this.translationDao.save(additionalServiceListedEnglishTranslation);

			Translation additionalServiceNotFoundEnglishTranslation = new Translation();
			additionalServiceNotFoundEnglishTranslation.setLanguage(english);
			var additionalServiceNotFoundEnglish = this.wordDao.getWordsByKey("additional.service.not.found");
			additionalServiceNotFoundEnglishTranslation.setWord(additionalServiceNotFoundEnglish);
			additionalServiceNotFoundEnglishTranslation.setTranslation("Additional Service Not Found.");
			this.translationDao.save(additionalServiceNotFoundEnglishTranslation);

			Translation userNotFoundEnglishTranslation = new Translation();
			userNotFoundEnglishTranslation.setLanguage(english);
			var userNotFoundEnglish = this.wordDao.getWordsByKey("user.not.found");
			userNotFoundEnglishTranslation.setWord(userNotFoundEnglish);
			userNotFoundEnglishTranslation.setTranslation("User Not Found.");
			this.translationDao.save(userNotFoundEnglishTranslation);

			Translation userEmailAlreadyExistsEnglishTranslation = new Translation();
			userEmailAlreadyExistsEnglishTranslation.setLanguage(english);
			var userEmailAlreadyExistsEnglish = this.wordDao.getWordsByKey("user.email.exists");
			userEmailAlreadyExistsEnglishTranslation.setWord(userEmailAlreadyExistsEnglish);
			userEmailAlreadyExistsEnglishTranslation.setTranslation("User Not Found.");
			this.translationDao.save(userEmailAlreadyExistsEnglishTranslation);

			Translation customerAddedEnglishTranslation = new Translation();
			customerAddedEnglishTranslation.setLanguage(english);
			var customerAddedEnglish = this.wordDao.getWordsByKey("customer.added");
			customerAddedEnglishTranslation.setWord(customerAddedEnglish);
			customerAddedEnglishTranslation.setTranslation("Customer Added");
			this.translationDao.save(customerAddedEnglishTranslation);

			Translation customerUpdatedEnglishTranslation = new Translation();
			customerUpdatedEnglishTranslation.setLanguage(english);
			var customerUpdatedEnglish = this.wordDao.getWordsByKey("customer.updated");
			customerUpdatedEnglishTranslation.setWord(customerUpdatedEnglish);
			customerUpdatedEnglishTranslation.setTranslation("Customer Updated");
			this.translationDao.save(customerUpdatedEnglishTranslation);

			Translation customerDeletedEnglishTranslation = new Translation();
			customerDeletedEnglishTranslation.setLanguage(english);
			var customerDeletedEnglish = this.wordDao.getWordsByKey("customer.deleted");
			customerDeletedEnglishTranslation.setWord(customerDeletedEnglish);
			customerDeletedEnglishTranslation.setTranslation("Customer Deleted");
			this.translationDao.save(customerDeletedEnglishTranslation);

			Translation customersFoundEnglishTranslation = new Translation();
			customersFoundEnglishTranslation.setLanguage(english);
			var customersFoundEnglish = this.wordDao.getWordsByKey("customers.listed");
			customersFoundEnglishTranslation.setWord(customersFoundEnglish);
			customersFoundEnglishTranslation.setTranslation("Customers Listed");
			this.translationDao.save(customersFoundEnglishTranslation);

			Translation customerFoundEnglishTranslation = new Translation();
			customerFoundEnglishTranslation.setLanguage(english);
			var customerFoundEnglish = this.wordDao.getWordsByKey("customer.found");
			customerFoundEnglishTranslation.setWord(customerFoundEnglish);
			customerFoundEnglishTranslation.setTranslation("Customers Found");
			this.translationDao.save(customerFoundEnglishTranslation);

			Translation customerNotFoundEnglishTranslation = new Translation();
			customerNotFoundEnglishTranslation.setLanguage(english);
			var customerNotFoundEnglish = this.wordDao.getWordsByKey("customer.not.found");
			customerNotFoundEnglishTranslation.setWord(customerNotFoundEnglish);
			customerNotFoundEnglishTranslation.setTranslation("Customer Not Found");
			this.translationDao.save(customerNotFoundEnglishTranslation);

			Translation customerAlreadyExistsEnglishTranslation = new Translation();
			customerAlreadyExistsEnglishTranslation.setLanguage(english);
			var customerAlreadyExistsEnglish = this.wordDao.getWordsByKey("customer.exists");
			customerAlreadyExistsEnglishTranslation.setWord(customerAlreadyExistsEnglish);
			customerAlreadyExistsEnglishTranslation.setTranslation("Customer Exists");
			this.translationDao.save(customerAlreadyExistsEnglishTranslation);

			Translation customerNationalIdentityAlreadyExistsEnglishTranslation = new Translation();
			customerNationalIdentityAlreadyExistsEnglishTranslation.setLanguage(english);
			var customerNationalIdentityAlreadyExistsEnglish = this.wordDao
					.getWordsByKey("customer.national.identity.already.exists");
			customerNationalIdentityAlreadyExistsEnglishTranslation
					.setWord(customerNationalIdentityAlreadyExistsEnglish);
			customerNationalIdentityAlreadyExistsEnglishTranslation
					.setTranslation("Customer National Identity Already Exists");
			this.translationDao.save(customerNationalIdentityAlreadyExistsEnglishTranslation);

			Translation customerTaxNumberAlreadyExistsEnglishTranslation = new Translation();
			customerTaxNumberAlreadyExistsEnglishTranslation.setLanguage(english);
			var customerTaxNumberAlreadyExistsEnglish = this.wordDao
					.getWordsByKey("customers.tax.number.already.exists");
			customerTaxNumberAlreadyExistsEnglishTranslation.setWord(customerTaxNumberAlreadyExistsEnglish);
			customerTaxNumberAlreadyExistsEnglishTranslation.setTranslation("Customer Tax Number Already Exists");
			this.translationDao.save(customerTaxNumberAlreadyExistsEnglishTranslation);

			// Language English

			Translation languageAddedEnglishTranslation = new Translation();
			languageAddedEnglishTranslation.setLanguage(english);
			var languageAddedEnglish = this.wordDao.getWordsByKey("language.added");
			languageAddedEnglishTranslation.setWord(languageAddedEnglish);
			languageAddedEnglishTranslation.setTranslation("Language added.");
			this.translationDao.save(languageAddedEnglishTranslation);

			Translation languageDeletedEnglishTranslation = new Translation();
			languageDeletedEnglishTranslation.setLanguage(english);
			var languageDeletedEnglish = this.wordDao.getWordsByKey("language.deleted");
			languageDeletedEnglishTranslation.setWord(languageDeletedEnglish);
			languageDeletedEnglishTranslation.setTranslation("Language deleted.");
			this.translationDao.save(languageDeletedEnglishTranslation);

			Translation languageUpdatedEnglishTranslation = new Translation();
			languageUpdatedEnglishTranslation.setLanguage(english);
			var languageUpdatedEnglish = this.wordDao.getWordsByKey("language.updated");
			languageUpdatedEnglishTranslation.setWord(languageUpdatedEnglish);
			languageUpdatedEnglishTranslation.setTranslation("Language updated.");
			this.translationDao.save(languageUpdatedEnglishTranslation);

			Translation languagesListedEnglishTranslation = new Translation();
			languagesListedEnglishTranslation.setLanguage(english);
			var languagesListedEnglish = this.wordDao.getWordsByKey("languages.listed");
			languagesListedEnglishTranslation.setWord(languagesListedEnglish);
			languagesListedEnglishTranslation.setTranslation("Languages listed.");
			this.translationDao.save(languagesListedEnglishTranslation);

			Translation languageNotFoundEnglishTranslation = new Translation();
			languageNotFoundEnglishTranslation.setLanguage(english);
			var languageNotFoundEnglish = this.wordDao.getWordsByKey("language.not.found");
			languageNotFoundEnglishTranslation.setWord(languageNotFoundEnglish);
			languageNotFoundEnglishTranslation.setTranslation("Language not found.");
			this.translationDao.save(languageNotFoundEnglishTranslation);

			Translation languageFoundEnglishTranslation = new Translation();
			languageFoundEnglishTranslation.setLanguage(english);
			var languageFoundEnglish = this.wordDao.getWordsByKey("language.found");
			languageFoundEnglishTranslation.setWord(languageFoundEnglish);
			languageFoundEnglishTranslation.setTranslation("Language found.");
			this.translationDao.save(languageFoundEnglishTranslation);

			Translation languageAlreadyExistsEnglishTranslation = new Translation();
			languageAlreadyExistsEnglishTranslation.setLanguage(english);
			var languageAlreadyExistsEnglish = this.wordDao.getWordsByKey("language.exists");
			languageAlreadyExistsEnglishTranslation.setWord(languageAlreadyExistsEnglish);
			languageAlreadyExistsEnglishTranslation.setTranslation("Language already exists.");
			this.translationDao.save(languageAlreadyExistsEnglishTranslation);

			// Word English

			Translation wordAddedEnglishTranslation = new Translation();
			wordAddedEnglishTranslation.setLanguage(english);
			var wordAddedEnglish = this.wordDao.getWordsByKey("word.added");
			wordAddedEnglishTranslation.setWord(wordAddedEnglish);
			wordAddedEnglishTranslation.setTranslation("Word added.");
			this.translationDao.save(wordAddedEnglishTranslation);

			Translation wordDeletedEnglishTranslation = new Translation();
			wordDeletedEnglishTranslation.setLanguage(english);
			var wordDeletedEnglish = this.wordDao.getWordsByKey("word.deleted");
			wordDeletedEnglishTranslation.setWord(wordDeletedEnglish);
			wordDeletedEnglishTranslation.setTranslation("Word deleted.");
			this.translationDao.save(wordDeletedEnglishTranslation);

			Translation wordUpdatedEnglishTranslation = new Translation();
			wordUpdatedEnglishTranslation.setLanguage(english);
			var wordUpdatedEnglish = this.wordDao.getWordsByKey("word.updated");
			wordUpdatedEnglishTranslation.setWord(wordUpdatedEnglish);
			wordUpdatedEnglishTranslation.setTranslation("Word updated.");
			this.translationDao.save(wordUpdatedEnglishTranslation);

			Translation wordsListedEnglishTranslation = new Translation();
			wordsListedEnglishTranslation.setLanguage(english);
			var wordsListedEnglish = this.wordDao.getWordsByKey("words.listed");
			wordsListedEnglishTranslation.setWord(wordsListedEnglish);
			wordsListedEnglishTranslation.setTranslation("Words listed.");
			this.translationDao.save(wordsListedEnglishTranslation);

			Translation wordNotFoundEnglishTranslation = new Translation();
			wordNotFoundEnglishTranslation.setLanguage(english);
			var wordNotFoundEnglish = this.wordDao.getWordsByKey("word.not.found");
			wordNotFoundEnglishTranslation.setWord(wordNotFoundEnglish);
			wordNotFoundEnglishTranslation.setTranslation("Word not found.");
			this.translationDao.save(wordNotFoundEnglishTranslation);

			Translation wordFoundEnglishTranslation = new Translation();
			wordFoundEnglishTranslation.setLanguage(english);
			var wordFoundEnglish = this.wordDao.getWordsByKey("word.found");
			wordFoundEnglishTranslation.setWord(wordFoundEnglish);
			wordFoundEnglishTranslation.setTranslation("Word found.");
			this.translationDao.save(wordFoundEnglishTranslation);

			Translation wordAlreadyExistsEnglishTranslation = new Translation();
			wordAlreadyExistsEnglishTranslation.setLanguage(english);
			var wordAlreadyExistsEnglish = this.wordDao.getWordsByKey("word.exists");
			wordAlreadyExistsEnglishTranslation.setWord(wordAlreadyExistsEnglish);
			wordAlreadyExistsEnglishTranslation.setTranslation("Word already exists.");
			this.translationDao.save(wordAlreadyExistsEnglishTranslation);

			// Translation English

			Translation translationAddedEnglishTranslation = new Translation();
			translationAddedEnglishTranslation.setLanguage(english);
			var translationAddedEnglish = this.wordDao.getWordsByKey("translation.added");
			translationAddedEnglishTranslation.setWord(translationAddedEnglish);
			translationAddedEnglishTranslation.setTranslation("Translation added.");
			this.translationDao.save(translationAddedEnglishTranslation);

			Translation translationDeletedEnglishTranslation = new Translation();
			translationDeletedEnglishTranslation.setLanguage(english);
			var translationDeletedEnglish = this.wordDao.getWordsByKey("translation.deleted");
			translationDeletedEnglishTranslation.setWord(translationDeletedEnglish);
			translationDeletedEnglishTranslation.setTranslation("Translation deleted.");
			this.translationDao.save(translationDeletedEnglishTranslation);

			Translation translationUpdatedEnglishTranslation = new Translation();
			translationUpdatedEnglishTranslation.setLanguage(english);
			var translationUpdatedEnglish = this.wordDao.getWordsByKey("translation.updated");
			translationUpdatedEnglishTranslation.setWord(translationUpdatedEnglish);
			translationUpdatedEnglishTranslation.setTranslation("Translation updated.");
			this.translationDao.save(translationUpdatedEnglishTranslation);

			Translation translationsListedEnglishTranslation = new Translation();
			translationsListedEnglishTranslation.setLanguage(english);
			var translationsListedEnglish = this.wordDao.getWordsByKey("translations.listed");
			translationsListedEnglishTranslation.setWord(translationsListedEnglish);
			translationsListedEnglishTranslation.setTranslation("Translations are listed.");
			this.translationDao.save(translationsListedEnglishTranslation);

			Translation translationNotFoundEnglishTranslation = new Translation();
			translationNotFoundEnglishTranslation.setLanguage(english);
			var translationNotFoundEnglish = this.wordDao.getWordsByKey("translation.not.found");
			translationNotFoundEnglishTranslation.setWord(translationNotFoundEnglish);
			translationNotFoundEnglishTranslation.setTranslation("Translation not found.");
			this.translationDao.save(translationNotFoundEnglishTranslation);

			Translation translationFoundEnglishTranslation = new Translation();
			translationFoundEnglishTranslation.setLanguage(english);
			var translationFoundEnglish = this.wordDao.getWordsByKey("translation.found");
			translationFoundEnglishTranslation.setWord(translationFoundEnglish);
			translationFoundEnglishTranslation.setTranslation("Translation found.");
			this.translationDao.save(translationFoundEnglishTranslation);

			Translation translationAlreadyExistsEnglishTranslation = new Translation();
			translationAlreadyExistsEnglishTranslation.setLanguage(english);
			var translationAlreadyExistsEnglish = this.wordDao.getWordsByKey("translation.exists");
			translationAlreadyExistsEnglishTranslation.setWord(translationAlreadyExistsEnglish);
			translationAlreadyExistsEnglishTranslation.setTranslation("Translation already exists.");
			this.translationDao.save(translationAlreadyExistsEnglishTranslation);

			// Car Damage English

			Translation damageAddEnglishTranslation = new Translation();
			damageAddEnglishTranslation.setLanguage(english);
			var damageAddEnglish = this.wordDao.getWordsByKey("damage.added");
			damageAddEnglishTranslation.setWord(damageAddEnglish);
			damageAddEnglishTranslation.setTranslation("Damage added.");
			this.translationDao.save(damageAddEnglishTranslation);

			Translation damageDeletedEnglishTranslation = new Translation();
			damageDeletedEnglishTranslation.setLanguage(english);
			var damageDeletedEnglish = this.wordDao.getWordsByKey("damage.deleted");
			damageDeletedEnglishTranslation.setWord(damageDeletedEnglish);
			damageDeletedEnglishTranslation.setTranslation("Damage deleted.");
			this.translationDao.save(damageDeletedEnglishTranslation);

			Translation damageUpdatedEnglishTranslation = new Translation();
			damageUpdatedEnglishTranslation.setLanguage(english);
			var damageUpdatedEnglish = this.wordDao.getWordsByKey("damage.updated");
			damageUpdatedEnglishTranslation.setWord(damageUpdatedEnglish);
			damageUpdatedEnglishTranslation.setTranslation("Damage updated.");
			this.translationDao.save(damageUpdatedEnglishTranslation);

			Translation damageListedEnglishTranslation = new Translation();
			damageListedEnglishTranslation.setLanguage(english);
			var damageListedEnglish = this.wordDao.getWordsByKey("damages.listed");
			damageListedEnglishTranslation.setWord(damageListedEnglish);
			damageListedEnglishTranslation.setTranslation("All damages are listed.");
			this.translationDao.save(damageListedEnglishTranslation);

			Translation damageNotFoundEnglishTranslation = new Translation();
			damageNotFoundEnglishTranslation.setLanguage(english);
			var damageNotFoundEnglish = this.wordDao.getWordsByKey("damage.not.found");
			damageNotFoundEnglishTranslation.setWord(damageNotFoundEnglish);
			damageNotFoundEnglishTranslation.setTranslation("Damage not found");
			this.translationDao.save(damageNotFoundEnglishTranslation);

			Translation damageFoundEnglishTranslation = new Translation();
			damageFoundEnglishTranslation.setLanguage(english);
			var damageFoundEnglish = this.wordDao.getWordsByKey("damage.found");
			damageFoundEnglishTranslation.setWord(damageFoundEnglish);
			damageFoundEnglishTranslation.setTranslation("Damage found.");
			this.translationDao.save(damageFoundEnglishTranslation);

			var turkish = languageDao.getLanguagesByName("Turkish");
			if (turkish == null) {
				turkish = languageDao.getLanguagesByName("English");
			}
			// kendi eklediklerim
			List<Translation> traslations2 = new ArrayList<>();
			traslations2.add(new Translation(0, "Ek Servis Sisteme Eklendi",
					this.wordDao.getWordsByKey("additional.service.item.added"), turkish));
			traslations2.add(new Translation(0, "Ek Servis Sistemden Güncellendi",
					this.wordDao.getWordsByKey("additional.service.item.updated"), turkish));
			traslations2.add(new Translation(0, "Ek Servis Sistemden Silindi",
					this.wordDao.getWordsByKey("additional.service.item.deleted"), turkish));
			traslations2.add(new Translation(0, "El Servis Sistemde Bulunamadı",
					this.wordDao.getWordsByKey("additional.service.item.not.found"), turkish));
			traslations2.add(new Translation(0, "Ek Servis Getirildi",
					this.wordDao.getWordsByKey("additional.service.item.list"), turkish));
			traslations2.add(new Translation(0, "Marka İsmi Zaten Var", this.wordDao.getWordsByKey("brand.name.exists"),
					turkish));
			traslations2.add(
					new Translation(0, "Araba Hasarı Eklendi", this.wordDao.getWordsByKey("car.damage.add"), turkish));
			traslations2.add(new Translation(0, "Araba Hasarı Güncellendi",
					this.wordDao.getWordsByKey("car.damage.update"), turkish));
			traslations2.add(new Translation(0, "Araba Hasarı Silindi", this.wordDao.getWordsByKey("car.damage.delete"),
					turkish));
			traslations2.add(new Translation(0, "Araba Hasarı Bulunamadı",
					this.wordDao.getWordsByKey("car.damage.not.found"), turkish));
			traslations2.add(new Translation(0, "Araba Kiralanmış", this.wordDao.getWordsByKey("car.rented"), turkish));
			traslations2.add(
					new Translation(0, "Araba Bakımda", this.wordDao.getWordsByKey("car.in.maintanance"), turkish));
			traslations2.add(
					new Translation(0, "Şehir Zaten Var", this.wordDao.getWordsByKey("city.name.exists"), turkish));
			traslations2.add(
					new Translation(0, "Renk Zaten Var", this.wordDao.getWordsByKey("color.name.exists"), turkish));
			traslations2.add(new Translation(0, "Şirket İsmi Zaten Var",
					this.wordDao.getWordsByKey("company.name.exists"), turkish));
			traslations2.add(
					new Translation(0, "Yaşınız Yeterli Değil", this.wordDao.getWordsByKey("age.not.enough"), turkish));
			traslations2.add(new Translation(0, "Kiralama Daha Bitmemiş",
					this.wordDao.getWordsByKey("rental.not.finished"), turkish));
			traslations2.add(
					new Translation(0, "Ödeme Bulunamadı", this.wordDao.getWordsByKey("payment.not.found"), turkish));
			traslations2.add(new Translation(0, "Promosyon Kodu Bulunamadı",
					this.wordDao.getWordsByKey("promo.code.not.found"), turkish));
			traslations2.add(new Translation(0, "Promosyon Kodu Eklendi", this.wordDao.getWordsByKey("promo.code.add"),
					turkish));
			traslations2.add(new Translation(0, "Promosyon Kodu Güncellendi",
					this.wordDao.getWordsByKey("promo.code.update"), turkish));
			traslations2.add(new Translation(0, "Promosyon Kodu Silindi",
					this.wordDao.getWordsByKey("promo.code.delete"), turkish));
			traslations2.add(new Translation(0, "Promosyon Kodu Zaten Var",
					this.wordDao.getWordsByKey("promo.code.already.exists"), turkish));
			traslations2.add(new Translation(0, "Tarihlerde Hata Mevcut",
					this.wordDao.getWordsByKey("dates.are.not.correct"), turkish));
			traslations2.add(new Translation(0, "Bu Sınıfta Araç Kalmadı",
					this.wordDao.getWordsByKey("no.available.car.in.this.segment"), turkish));
			traslations2.add(new Translation(0, "Kilometre Bilgisi Hatalı",
					this.wordDao.getWordsByKey("kilometer.error"), turkish));
			traslations2.add(new Translation(0, "Sınıf Eklendi", this.wordDao.getWordsByKey("segment.add"), turkish));
			traslations2.add(
					new Translation(0, "Sınıf Güncellendi", this.wordDao.getWordsByKey("segment.update"), turkish));
			traslations2
					.add(new Translation(0, "Sınıf Silindi", this.wordDao.getWordsByKey("segment.delete"), turkish));
			traslations2.add(new Translation(0, "Sınıf Zaten Mevcut",
					this.wordDao.getWordsByKey("segment.name.already.exists"), turkish));
			traslations2.add(
					new Translation(0, "Sınıf Bulunamadı", this.wordDao.getWordsByKey("segment.not.found"), turkish));

			translationDao.saveAll(traslations2);

			// kendi eklediklerim biter

			Translation dataNotFoundTurkishTranslation = new Translation();
			dataNotFoundTurkishTranslation.setLanguage(turkish);
			var dataNotFoundTurkish = this.wordDao.getWordsByKey("data.not.found");
			dataNotFoundTurkishTranslation.setWord(dataNotFoundTurkish);
			dataNotFoundTurkishTranslation.setTranslation("Data Not Found");
			this.translationDao.save(dataNotFoundTurkishTranslation);

			Translation customerAddedTurkishTranslation = new Translation();
			customerAddedTurkishTranslation.setLanguage(turkish);
			var customerAddedTurkish = this.wordDao.getWordsByKey("customer.added");
			customerAddedTurkishTranslation.setWord(customerAddedTurkish);
			customerAddedTurkishTranslation.setTranslation("Müşteri Eklendi.");
			this.translationDao.save(customerAddedTurkishTranslation);

			Translation customerDeletedTurkishTranslation = new Translation();
			customerDeletedTurkishTranslation.setLanguage(turkish);
			var customerDeletedTurkish = this.wordDao.getWordsByKey("customer.deleted");
			customerDeletedTurkishTranslation.setWord(customerDeletedTurkish);
			customerDeletedTurkishTranslation.setTranslation("Müşteri Silindi.");
			this.translationDao.save(customerDeletedTurkishTranslation);

			Translation customerUpdatedTurkishTranslation = new Translation();
			customerUpdatedTurkishTranslation.setLanguage(turkish);
			var customerUpdatedTurkish = this.wordDao.getWordsByKey("customer.updated");
			customerUpdatedTurkishTranslation.setWord(customerUpdatedTurkish);
			customerUpdatedTurkishTranslation.setTranslation("Müşteri güncellendi.");
			this.translationDao.save(customerUpdatedTurkishTranslation);

			Translation customerFoundTurkishTranslation = new Translation();
			customerFoundTurkishTranslation.setLanguage(turkish);
			var customerFoundTurkish = this.wordDao.getWordsByKey("customer.found");
			customerFoundTurkishTranslation.setWord(customerFoundTurkish);
			customerFoundTurkishTranslation.setTranslation("Müşteri bulundu.");
			this.translationDao.save(customerFoundTurkishTranslation);

			Translation customerListedTurkishTranslation = new Translation();
			customerListedTurkishTranslation.setLanguage(turkish);
			var customersListedTurkish = this.wordDao.getWordsByKey("customers.listed");
			customerListedTurkishTranslation.setWord(customersListedTurkish);
			customerListedTurkishTranslation.setTranslation("Müşteriler Listelendi.");
			this.translationDao.save(customerListedTurkishTranslation);

			Translation customerNotFoundTurkishTranslation = new Translation();
			customerNotFoundTurkishTranslation.setLanguage(turkish);
			var customerNotFoundTurkish = this.wordDao.getWordsByKey("customer.not.found");
			customerNotFoundTurkishTranslation.setWord(customerNotFoundTurkish);
			customerNotFoundTurkishTranslation.setTranslation("Müşteri Bulunamadı");
			this.translationDao.save(customerNotFoundTurkishTranslation);

			Translation customerAlreadyExistsTurkishTranslation = new Translation();
			customerAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var customerAlreadyExistsTurkish = this.wordDao.getWordsByKey("customer.exists");
			customerAlreadyExistsTurkishTranslation.setWord(customerAlreadyExistsTurkish);
			customerAlreadyExistsTurkishTranslation.setTranslation("Müşteri Zaten Kayıtlı");
			this.translationDao.save(customerAlreadyExistsTurkishTranslation);

			Translation customersTaxNumberAlreadyExistsTurkishTranslation = new Translation();
			customersTaxNumberAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var customersTaxNumberAlreadyExistsTurkish = this.wordDao
					.getWordsByKey("customers.tax.number.already.exists");
			customersTaxNumberAlreadyExistsTurkishTranslation.setWord(customersTaxNumberAlreadyExistsTurkish);
			customersTaxNumberAlreadyExistsTurkishTranslation
					.setTranslation("Müşterinin vergi numarası zaten kayıtlı.");
			this.translationDao.save(customersTaxNumberAlreadyExistsTurkishTranslation);

			Translation brandAddedTurkishTranslation = new Translation();
			brandAddedTurkishTranslation.setLanguage(turkish);
			var brandAddedTurkish = this.wordDao.getWordsByKey("brand.added");
			brandAddedTurkishTranslation.setWord(brandAddedTurkish);
			brandAddedTurkishTranslation.setTranslation("Marka eklendi.");
			this.translationDao.save(brandAddedTurkishTranslation);

			Translation brandDeletedTurkishTranslation = new Translation();
			brandDeletedTurkishTranslation.setLanguage(turkish);
			var brandDeletedTurkish = this.wordDao.getWordsByKey("brand.deleted");
			brandDeletedTurkishTranslation.setWord(brandDeletedTurkish);
			brandDeletedTurkishTranslation.setTranslation("Marka silindi.");
			this.translationDao.save(brandDeletedTurkishTranslation);

			Translation brandUpdatedTurkishTranslation = new Translation();
			brandUpdatedTurkishTranslation.setLanguage(turkish);
			var brandUpdatedTurkish = this.wordDao.getWordsByKey("brand.updated");
			brandUpdatedTurkishTranslation.setWord(brandUpdatedTurkish);
			brandUpdatedTurkishTranslation.setTranslation("Marka güncellendi.");
			this.translationDao.save(brandUpdatedTurkishTranslation);

			Translation brandListedTurkishTranslation = new Translation();
			brandListedTurkishTranslation.setLanguage(turkish);
			var brandListedTurkish = this.wordDao.getWordsByKey("brands.listed");
			brandListedTurkishTranslation.setWord(brandListedTurkish);
			brandListedTurkishTranslation.setTranslation("Marka listelendi.");
			this.translationDao.save(brandListedTurkishTranslation);

			Translation brandNotFoundTurkishTranslation = new Translation();
			brandNotFoundTurkishTranslation.setLanguage(turkish);
			var brandNotFoundTurkish = this.wordDao.getWordsByKey("brand.not.found");
			brandNotFoundTurkishTranslation.setWord(brandNotFoundTurkish);
			brandNotFoundTurkishTranslation.setTranslation("Marka bulunamadı.");
			this.translationDao.save(brandNotFoundTurkishTranslation);

			Translation brandFoundTurkishTranslation = new Translation();
			brandFoundTurkishTranslation.setLanguage(turkish);
			var brandFoundTurkish = this.wordDao.getWordsByKey("brand.found");
			brandFoundTurkishTranslation.setWord(brandFoundTurkish);
			brandFoundTurkishTranslation.setTranslation("Marka bulundu.");
			this.translationDao.save(brandFoundTurkishTranslation);

			Translation brandExistsTurkishTranslation = new Translation();
			brandExistsTurkishTranslation.setLanguage(turkish);
			var brandExistsTurkish = this.wordDao.getWordsByKey("brand.exists");
			brandExistsTurkishTranslation.setWord(brandExistsTurkish);
			brandExistsTurkishTranslation.setTranslation("Marka zaten kayıtlı.");
			this.translationDao.save(brandExistsTurkishTranslation);

			Translation colorAddedTurkishTranslation = new Translation();
			colorAddedTurkishTranslation.setLanguage(turkish);
			var colorAddedTurkish = this.wordDao.getWordsByKey("color.added");
			colorAddedTurkishTranslation.setWord(colorAddedTurkish);
			colorAddedTurkishTranslation.setTranslation("Renk eklendi.");
			this.translationDao.save(colorAddedTurkishTranslation);

			Translation colorDeletedTurkishTranslation = new Translation();
			colorDeletedTurkishTranslation.setLanguage(turkish);
			var colorDeletedTurkish = this.wordDao.getWordsByKey("color.deleted");
			colorDeletedTurkishTranslation.setWord(colorDeletedTurkish);
			colorDeletedTurkishTranslation.setTranslation("Renk silindi.");
			this.translationDao.save(colorDeletedTurkishTranslation);

			Translation colorUpdatedTurkishTranslation = new Translation();
			colorUpdatedTurkishTranslation.setLanguage(turkish);
			var colorUpdatedTurkish = this.wordDao.getWordsByKey("color.updated");
			colorUpdatedTurkishTranslation.setWord(colorUpdatedTurkish);
			colorUpdatedTurkishTranslation.setTranslation("Renk güncellendi.");
			this.translationDao.save(colorUpdatedTurkishTranslation);

			Translation colorListedTurkishTranslation = new Translation();
			colorListedTurkishTranslation.setLanguage(turkish);
			var colorListedTurkish = this.wordDao.getWordsByKey("colors.listed");
			colorListedTurkishTranslation.setWord(colorListedTurkish);
			colorListedTurkishTranslation.setTranslation("Renkler listelendi.");
			this.translationDao.save(colorListedTurkishTranslation);

			Translation colorAlreadyExistsTurkishTranslation = new Translation();
			colorAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var colorAlreadyExistsTurkish = this.wordDao.getWordsByKey("color.exists");
			colorAlreadyExistsTurkishTranslation.setWord(colorAlreadyExistsTurkish);
			colorAlreadyExistsTurkishTranslation.setTranslation("Renk zaten mevcut.");
			this.translationDao.save(colorAlreadyExistsTurkishTranslation);

			Translation colorFoundTurkishTranslation = new Translation();
			colorFoundTurkishTranslation.setLanguage(turkish);
			var colorFoundTurkish = this.wordDao.getWordsByKey("color.found");
			colorFoundTurkishTranslation.setWord(colorFoundTurkish);
			colorFoundTurkishTranslation.setTranslation("Renk bulundu.");
			this.translationDao.save(colorFoundTurkishTranslation);

			Translation colorNotFoundTurkishTranslation = new Translation();
			colorNotFoundTurkishTranslation.setLanguage(turkish);
			var colorNotFoundTurkish = this.wordDao.getWordsByKey("color.not.found");
			colorNotFoundTurkishTranslation.setWord(colorNotFoundTurkish);
			colorNotFoundTurkishTranslation.setTranslation("Renk bulunamadı.");
			this.translationDao.save(colorNotFoundTurkishTranslation);

			Translation carImageAddedTurkishTranslation = new Translation();
			carImageAddedTurkishTranslation.setLanguage(turkish);
			var carImageAddedTurkish = this.wordDao.getWordsByKey("car.image.added");
			carImageAddedTurkishTranslation.setWord(carImageAddedTurkish);
			carImageAddedTurkishTranslation.setTranslation("Araç görseli eklendi.");
			this.translationDao.save(carImageAddedTurkishTranslation);

			Translation carImageDeletedTurkishTranslation = new Translation();
			carImageDeletedTurkishTranslation.setLanguage(turkish);
			var carImageDeletedTurkish = this.wordDao.getWordsByKey("car.image.deleted");
			carImageDeletedTurkishTranslation.setWord(carImageDeletedTurkish);
			carImageDeletedTurkishTranslation.setTranslation("Araç görseli silindi.");
			this.translationDao.save(carImageDeletedTurkishTranslation);

			Translation carImageUpdatedTurkishTranslation = new Translation();
			carImageUpdatedTurkishTranslation.setLanguage(turkish);
			var carImageUpdatedTurkish = this.wordDao.getWordsByKey("car.image.updated");
			carImageUpdatedTurkishTranslation.setWord(carImageUpdatedTurkish);
			carImageUpdatedTurkishTranslation.setTranslation("Araç görseli güncellendi.");
			this.translationDao.save(carImageUpdatedTurkishTranslation);

			Translation carImageListedTurkishTranslation = new Translation();
			carImageListedTurkishTranslation.setLanguage(turkish);
			var carImageListedTurkish = this.wordDao.getWordsByKey("car.images.listed");
			carImageListedTurkishTranslation.setWord(carImageListedTurkish);
			carImageListedTurkishTranslation.setTranslation("Araç görselleri listelendi.");
			this.translationDao.save(carImageListedTurkishTranslation);

			Translation carImageNameErrorTurkishTranslation = new Translation();
			carImageNameErrorTurkishTranslation.setLanguage(turkish);
			var carImageNameErrorTurkish = this.wordDao.getWordsByKey("car.image.type.not.valid");
			carImageNameErrorTurkishTranslation.setWord(carImageNameErrorTurkish);
			carImageNameErrorTurkishTranslation.setTranslation("Lütfen görselin uzantısını kontrol ediniz.");
			this.translationDao.save(carImageNameErrorTurkishTranslation);

			Translation carImageNotFoundTurkishTranslation = new Translation();
			carImageNotFoundTurkishTranslation.setLanguage(turkish);
			var carImageNotFoundTurkish = this.wordDao.getWordsByKey("car.image.not.found");
			carImageNotFoundTurkishTranslation.setWord(carImageNotFoundTurkish);
			carImageNotFoundTurkishTranslation.setTranslation("Araç görseli bulunamadı.");
			this.translationDao.save(carImageNotFoundTurkishTranslation);

			Translation carAddedTurkishTranslation = new Translation();
			carAddedTurkishTranslation.setLanguage(turkish);
			var carAddedTurkish = this.wordDao.getWordsByKey("car.added");
			carAddedTurkishTranslation.setWord(carAddedTurkish);
			carAddedTurkishTranslation.setTranslation("Araç eklendi.");
			this.translationDao.save(carAddedTurkishTranslation);

			Translation carDeletedTurkishTranslation = new Translation();
			carDeletedTurkishTranslation.setLanguage(turkish);
			var carDeletedTurkish = this.wordDao.getWordsByKey("car.deleted");
			carDeletedTurkishTranslation.setWord(carDeletedTurkish);
			carDeletedTurkishTranslation.setTranslation("Araç silindi.");
			this.translationDao.save(carDeletedTurkishTranslation);

			Translation carUpdatedTurkishTranslation = new Translation();
			carUpdatedTurkishTranslation.setLanguage(turkish);
			var carUpdatedTurkish = this.wordDao.getWordsByKey("car.updated");
			carUpdatedTurkishTranslation.setWord(carUpdatedTurkish);
			carUpdatedTurkishTranslation.setTranslation("Araç güncellendi.");
			this.translationDao.save(carUpdatedTurkishTranslation);

			Translation carListedTurkishTranslation = new Translation();
			carListedTurkishTranslation.setLanguage(turkish);
			var carListedTurkish = this.wordDao.getWordsByKey("cars.listed");
			carListedTurkishTranslation.setWord(carListedTurkish);
			carListedTurkishTranslation.setTranslation("Araçlar listelendi.");
			this.translationDao.save(carListedTurkishTranslation);

			Translation carFoundTurkishTranslation = new Translation();
			carFoundTurkishTranslation.setLanguage(turkish);
			var carFoundTurkish = this.wordDao.getWordsByKey("car.found");
			carFoundTurkishTranslation.setWord(carFoundTurkish);
			carFoundTurkishTranslation.setTranslation("Araba bulundu");
			this.translationDao.save(carFoundTurkishTranslation);

			Translation carNotFoundTurkishTranslation = new Translation();
			carNotFoundTurkishTranslation.setLanguage(turkish);
			var carNotFoundTurkish = this.wordDao.getWordsByKey("car.not.found");
			carNotFoundTurkishTranslation.setWord(carNotFoundTurkish);
			carNotFoundTurkishTranslation.setTranslation("Araç bulunamadı.");
			this.translationDao.save(carNotFoundTurkishTranslation);

			Translation rentalAddedTurkishTranslation = new Translation();
			rentalAddedTurkishTranslation.setLanguage(turkish);
			var rentalAddedTurkish = this.wordDao.getWordsByKey("rental.added");
			rentalAddedTurkishTranslation.setWord(rentalAddedTurkish);
			rentalAddedTurkishTranslation.setTranslation("Kiralama eklendi.");
			this.translationDao.save(rentalAddedTurkishTranslation);

			Translation rentalDeletedTurkishTranslation = new Translation();
			rentalDeletedTurkishTranslation.setLanguage(turkish);
			var rentalDeletedTurkish = this.wordDao.getWordsByKey("rental.deleted");
			rentalDeletedTurkishTranslation.setWord(rentalDeletedTurkish);
			rentalDeletedTurkishTranslation.setTranslation("Kiralama silindi.");
			this.translationDao.save(rentalDeletedTurkishTranslation);

			Translation rentalUpdatedTurkishTranslation = new Translation();
			rentalUpdatedTurkishTranslation.setLanguage(turkish);
			var rentalUpdatedTurkish = this.wordDao.getWordsByKey("rental.updated");
			rentalUpdatedTurkishTranslation.setWord(rentalUpdatedTurkish);
			rentalUpdatedTurkishTranslation.setTranslation("Kiralama güncellendi.");
			this.translationDao.save(rentalUpdatedTurkishTranslation);

			Translation rentalListedTurkishTranslation = new Translation();
			rentalListedTurkishTranslation.setLanguage(turkish);
			var rentalListedTurkish = this.wordDao.getWordsByKey("rentals.listed");
			rentalListedTurkishTranslation.setWord(rentalListedTurkish);
			rentalListedTurkishTranslation.setTranslation("Kiralama listelendi.");
			this.translationDao.save(rentalListedTurkishTranslation);

			Translation rentalNotFoundTurkishTranslation = new Translation();
			rentalNotFoundTurkishTranslation.setLanguage(turkish);
			var rentalNotFoundTurkish = this.wordDao.getWordsByKey("rental.not.found");
			rentalNotFoundTurkishTranslation.setWord(rentalNotFoundTurkish);
			rentalNotFoundTurkishTranslation.setTranslation("Kiralama bulunamadı.");
			this.translationDao.save(rentalNotFoundTurkishTranslation);

			Translation rentalFoundTurkishTranslation = new Translation();
			rentalFoundTurkishTranslation.setLanguage(turkish);
			var rentalFoundTurkish = this.wordDao.getWordsByKey("rental.found");
			rentalFoundTurkishTranslation.setWord(rentalFoundTurkish);
			rentalFoundTurkishTranslation.setTranslation("Kiralama Found");
			this.translationDao.save(rentalFoundTurkishTranslation);

			Translation creditCardAddedTurkishTranslation = new Translation();
			creditCardAddedTurkishTranslation.setLanguage(turkish);
			var creditCardAddedTurkish = this.wordDao.getWordsByKey("credit.card.added");
			creditCardAddedTurkishTranslation.setWord(creditCardAddedTurkish);
			creditCardAddedTurkishTranslation.setTranslation("Kredi kartı eklendi.");
			this.translationDao.save(creditCardAddedTurkishTranslation);

			Translation creditCardDeletedTurkishTranslation = new Translation();
			creditCardDeletedTurkishTranslation.setLanguage(turkish);
			var creditCardDeletedTurkish = this.wordDao.getWordsByKey("credit.card.deleted");
			creditCardDeletedTurkishTranslation.setWord(creditCardDeletedTurkish);
			creditCardDeletedTurkishTranslation.setTranslation("Kredi kartı silindi.");
			this.translationDao.save(creditCardDeletedTurkishTranslation);

			Translation creditCardUpdatedTurkishTranslation = new Translation();
			creditCardUpdatedTurkishTranslation.setLanguage(turkish);
			var creditCardUpdatedTurkish = this.wordDao.getWordsByKey("credit.card.updated");
			creditCardUpdatedTurkishTranslation.setWord(creditCardUpdatedTurkish);
			creditCardUpdatedTurkishTranslation.setTranslation("Kredi kartı güncellendi.");
			this.translationDao.save(creditCardUpdatedTurkishTranslation);

			Translation creditCardListedTurkishTranslation = new Translation();
			creditCardListedTurkishTranslation.setLanguage(turkish);
			var creditCardListedTurkish = this.wordDao.getWordsByKey("credit.cards.listed");
			creditCardListedTurkishTranslation.setWord(creditCardListedTurkish);
			creditCardListedTurkishTranslation.setTranslation("Kredi kartları listelendi.");
			this.translationDao.save(creditCardListedTurkishTranslation);

			Translation creditCardNameErrorTurkishTranslation = new Translation();
			creditCardNameErrorTurkishTranslation.setLanguage(turkish);
			var creditCardNameErrorTurkish = this.wordDao.getWordsByKey("credit.card.name.error");
			creditCardNameErrorTurkishTranslation.setWord(creditCardNameErrorTurkish);
			creditCardNameErrorTurkishTranslation.setTranslation("Kredi kartı ismi hatalı.");
			this.translationDao.save(creditCardNameErrorTurkishTranslation);

			Translation creditCardNotFoundTurkishTranslation = new Translation();
			creditCardNotFoundTurkishTranslation.setLanguage(turkish);
			var creditCardNotFoundTurkish = this.wordDao.getWordsByKey("credit.card.not.found");
			creditCardNotFoundTurkishTranslation.setWord(creditCardNotFoundTurkish);
			creditCardNotFoundTurkishTranslation.setTranslation("Kredi kartı bulunamadı.");
			this.translationDao.save(creditCardNotFoundTurkishTranslation);

			Translation creditCardCvvErrorTurkishTranslation = new Translation();
			creditCardCvvErrorTurkishTranslation.setLanguage(turkish);
			var creditCardCvvErrorTurkish = this.wordDao.getWordsByKey("credit.card.cvv.error");
			creditCardCvvErrorTurkishTranslation.setWord(creditCardCvvErrorTurkish);
			creditCardCvvErrorTurkishTranslation.setTranslation("Kredi kartı cvv kodu hatalı.");
			this.translationDao.save(creditCardCvvErrorTurkishTranslation);

			Translation creditCardAlreadyExistsTurkishTranslation = new Translation();
			creditCardAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var creditCardAlreadyExistsTurkish = this.wordDao.getWordsByKey("credit.card.exists");
			creditCardAlreadyExistsTurkishTranslation.setWord(creditCardAlreadyExistsTurkish);
			creditCardAlreadyExistsTurkishTranslation.setTranslation("Kredi kartı zaten sisteme kayıtlı.");
			this.translationDao.save(creditCardAlreadyExistsTurkishTranslation);

			Translation creditCardExpirationDateErrorTurkishTranslation = new Translation();
			creditCardExpirationDateErrorTurkishTranslation.setLanguage(english);
			var creditCardExpirationDateErrorTurkish = this.wordDao.getWordsByKey("credit.card.date.error");
			creditCardExpirationDateErrorTurkishTranslation.setWord(creditCardExpirationDateErrorTurkish);
			creditCardExpirationDateErrorTurkishTranslation.setTranslation("Geçersiz son kullanma tarihi. ");
			this.translationDao.save(creditCardExpirationDateErrorTurkishTranslation);

			Translation invoiceAddedTurkishTranslation = new Translation();
			invoiceAddedTurkishTranslation.setLanguage(turkish);
			var invoiceAddedTurkish = this.wordDao.getWordsByKey("invoice.added");
			invoiceAddedTurkishTranslation.setWord(invoiceAddedTurkish);
			invoiceAddedTurkishTranslation.setTranslation("Fatura eklendi.");
			this.translationDao.save(invoiceAddedTurkishTranslation);

			Translation invoiceDeletedTurkishTranslation = new Translation();
			invoiceDeletedTurkishTranslation.setLanguage(turkish);
			var invoiceDeletedTurkish = this.wordDao.getWordsByKey("invoice.deleted");
			invoiceDeletedTurkishTranslation.setWord(invoiceDeletedTurkish);
			invoiceDeletedTurkishTranslation.setTranslation("Fatura silindi.");
			this.translationDao.save(invoiceDeletedTurkishTranslation);

			Translation invoiceUpdatedTurkishTranslation = new Translation();
			invoiceUpdatedTurkishTranslation.setLanguage(turkish);
			var invoiceUpdatedTurkish = this.wordDao.getWordsByKey("invoice.updated");
			invoiceUpdatedTurkishTranslation.setWord(invoiceUpdatedTurkish);
			invoiceUpdatedTurkishTranslation.setTranslation("Fatura güncellendi.");
			this.translationDao.save(invoiceUpdatedTurkishTranslation);

			Translation invoiceListedTurkishTranslation = new Translation();
			invoiceListedTurkishTranslation.setLanguage(turkish);
			var invoiceListedTurkish = this.wordDao.getWordsByKey("invoices.listed");
			invoiceListedTurkishTranslation.setWord(invoiceListedTurkish);
			invoiceListedTurkishTranslation.setTranslation("Fatura listelendi.");
			this.translationDao.save(invoiceListedTurkishTranslation);

			Translation invoiceNotFoundTurkishTranslation = new Translation();
			invoiceNotFoundTurkishTranslation.setLanguage(turkish);
			var invoiceNotFoundTurkish = this.wordDao.getWordsByKey("invoice.not.found");
			invoiceNotFoundTurkishTranslation.setWord(invoiceNotFoundTurkish);
			invoiceNotFoundTurkishTranslation.setTranslation("Fatura bulunamadı.");
			this.translationDao.save(invoiceNotFoundTurkishTranslation);

			Translation additionalServiceAddedTurkishTranslation = new Translation();
			additionalServiceAddedTurkishTranslation.setLanguage(turkish);
			var additionalServiceAddedTurkish = this.wordDao.getWordsByKey("additional.service.added");
			additionalServiceAddedTurkishTranslation.setWord(additionalServiceAddedTurkish);
			additionalServiceAddedTurkishTranslation.setTranslation("Ek Hizmet eklendi.");
			this.translationDao.save(additionalServiceAddedTurkishTranslation);

			Translation additionalServiceDeletedTurkishTranslation = new Translation();
			additionalServiceDeletedTurkishTranslation.setLanguage(turkish);
			var additionalServiceDeletedTurkish = this.wordDao.getWordsByKey("additional.service.deleted");
			additionalServiceDeletedTurkishTranslation.setWord(additionalServiceDeletedTurkish);
			additionalServiceDeletedTurkishTranslation.setTranslation("Ek Hizmet silindi.");
			this.translationDao.save(additionalServiceDeletedTurkishTranslation);

			Translation additionalServiceUpdatedTurkishTranslation = new Translation();
			additionalServiceUpdatedTurkishTranslation.setLanguage(turkish);
			var additionalServiceUpdatedTurkish = this.wordDao.getWordsByKey("additional.service.updated");
			additionalServiceUpdatedTurkishTranslation.setWord(additionalServiceUpdatedTurkish);
			additionalServiceUpdatedTurkishTranslation.setTranslation("Ek Hizmet güncellendi.");
			this.translationDao.save(additionalServiceUpdatedTurkishTranslation);

			Translation additionalServiceListedTurkishTranslation = new Translation();
			additionalServiceListedTurkishTranslation.setLanguage(turkish);
			var additionalServiceListedTurkish = this.wordDao.getWordsByKey("additional.services.listed");
			additionalServiceListedTurkishTranslation.setWord(additionalServiceListedTurkish);
			additionalServiceListedTurkishTranslation.setTranslation("Ek Hizmet listelendi.");
			this.translationDao.save(additionalServiceListedTurkishTranslation);

			Translation additionalServiceNotFoundTurkishTranslation = new Translation();
			additionalServiceNotFoundTurkishTranslation.setLanguage(turkish);
			var additionalServiceNotFoundTurkish = this.wordDao.getWordsByKey("additional.service.not.found");
			additionalServiceNotFoundTurkishTranslation.setWord(additionalServiceNotFoundTurkish);
			additionalServiceNotFoundTurkishTranslation.setTranslation("Ek Hizmet bulunamadı.");
			this.translationDao.save(additionalServiceNotFoundTurkishTranslation);

			Translation userNameErrorTurkishTranslation = new Translation();
			userNameErrorTurkishTranslation.setLanguage(turkish);
			var userNameErrorTurkish = this.wordDao.getWordsByKey("user.email.exists");
			userNameErrorTurkishTranslation.setWord(userNameErrorTurkish);
			userNameErrorTurkishTranslation.setTranslation("Kullanıcı maili sistemde kayıtlı");
			this.translationDao.save(userNameErrorTurkishTranslation);

			Translation userNotFoundTurkishTranslation = new Translation();
			userNotFoundTurkishTranslation.setLanguage(turkish);
			var userNotFoundTurkish = this.wordDao.getWordsByKey("user.not.found");
			userNotFoundTurkishTranslation.setWord(userNotFoundTurkish);
			userNotFoundTurkishTranslation.setTranslation("Kullanıcı bulunamadı.");
			this.translationDao.save(userNotFoundTurkishTranslation);

			Translation cityAddedTurkishTranslation = new Translation();
			cityAddedTurkishTranslation.setLanguage(turkish);
			var cityAddedTurkish = this.wordDao.getWordsByKey("city.added");
			cityAddedTurkishTranslation.setWord(cityAddedTurkish);
			cityAddedTurkishTranslation.setTranslation("Şehir eklendi.");
			this.translationDao.save(cityAddedTurkishTranslation);

			Translation cityDeletedTurkishTranslation = new Translation();
			cityDeletedTurkishTranslation.setLanguage(turkish);
			var cityDeletedTurkish = this.wordDao.getWordsByKey("city.deleted");
			cityDeletedTurkishTranslation.setWord(cityDeletedTurkish);
			cityDeletedTurkishTranslation.setTranslation("Şehir silindi.");
			this.translationDao.save(cityDeletedTurkishTranslation);

			Translation cityUpdatedTurkishTranslation = new Translation();
			cityUpdatedTurkishTranslation.setLanguage(turkish);
			var cityUpdatedTurkish = this.wordDao.getWordsByKey("city.updated");
			cityUpdatedTurkishTranslation.setWord(cityUpdatedTurkish);
			cityUpdatedTurkishTranslation.setTranslation("Şehir güncellendi.");
			this.translationDao.save(cityUpdatedTurkishTranslation);

			Translation citiesListedTurkishTranslation = new Translation();
			citiesListedTurkishTranslation.setLanguage(turkish);
			var citiesListedTurkish = this.wordDao.getWordsByKey("cities.listed");
			citiesListedTurkishTranslation.setWord(citiesListedTurkish);
			citiesListedTurkishTranslation.setTranslation("Şehirler listelendi.");
			this.translationDao.save(citiesListedTurkishTranslation);

			Translation cityNotFoundTurkishTranslation = new Translation();
			cityNotFoundTurkishTranslation.setLanguage(turkish);
			var cityNotFoundTurkish = this.wordDao.getWordsByKey("city.not.found");
			cityNotFoundTurkishTranslation.setWord(cityNotFoundTurkish);
			cityNotFoundTurkishTranslation.setTranslation("Şehir bulunamadı.");
			this.translationDao.save(cityNotFoundTurkishTranslation);

			Translation cityFoundTurkishTranslation = new Translation();
			cityFoundTurkishTranslation.setLanguage(turkish);
			var cityFoundTurkish = this.wordDao.getWordsByKey("city.found");
			cityFoundTurkishTranslation.setWord(cityFoundTurkish);
			cityFoundTurkishTranslation.setTranslation("Şehir bulundu.");
			this.translationDao.save(cityFoundTurkishTranslation);

			Translation cityExistsTurkishTranslation = new Translation();
			cityExistsTurkishTranslation.setLanguage(turkish);
			var cityExistsTurkish = this.wordDao.getWordsByKey("city.exists");
			cityExistsTurkishTranslation.setWord(cityExistsTurkish);
			cityExistsTurkishTranslation.setTranslation("Şehir zaten kayıtlı.");
			this.translationDao.save(cityExistsTurkishTranslation);

			Translation getCarByBrandIdTurkishTranslation = new Translation();
			getCarByBrandIdTurkishTranslation.setLanguage(turkish);
			var getCarByBrandIdTurkish = this.wordDao.getWordsByKey("get.car.by.brand.id");
			getCarByBrandIdTurkishTranslation.setWord(getCarByBrandIdTurkish);
			getCarByBrandIdTurkishTranslation.setTranslation("Marka ID'sine göre araç getirildi.");
			this.translationDao.save(getCarByBrandIdTurkishTranslation);

			Translation getCarByColorIdTurkishTranslation = new Translation();
			getCarByColorIdTurkishTranslation.setLanguage(turkish);
			var getCarByColorIdTurkish = this.wordDao.getWordsByKey("get.car.by.color.id");
			getCarByColorIdTurkishTranslation.setWord(getCarByColorIdTurkish);
			getCarByColorIdTurkishTranslation.setTranslation("Renk ID'sine göre araç getirildi.");
			this.translationDao.save(getCarByColorIdTurkishTranslation);

			Translation getCarByCityIdTurkishTranslation = new Translation();
			getCarByCityIdTurkishTranslation.setLanguage(turkish);
			var getCarByCityIdTurkish = this.wordDao.getWordsByKey("get.car.by.city.id");
			getCarByCityIdTurkishTranslation.setWord(getCarByCityIdTurkish);
			getCarByCityIdTurkishTranslation.setTranslation("Şehir ID'sine göre araç getirildi.");
			this.translationDao.save(getCarByCityIdTurkishTranslation);

			Translation validationErrorTurkishTranslation = new Translation();
			validationErrorTurkishTranslation.setLanguage(turkish);
			var validationErrorTurkish = this.wordDao.getWordsByKey("validation.error");
			validationErrorTurkishTranslation.setWord(validationErrorTurkish);
			validationErrorTurkishTranslation.setTranslation("Validation Error");
			this.translationDao.save(validationErrorTurkishTranslation);

			Translation loginSuccessTurkishTranslation = new Translation();
			loginSuccessTurkishTranslation.setLanguage(turkish);
			var loginSuccessTurkish = this.wordDao.getWordsByKey("login.success");
			loginSuccessTurkishTranslation.setWord(loginSuccessTurkish);
			loginSuccessTurkishTranslation.setTranslation("Giriş başarılı");
			this.translationDao.save(loginSuccessTurkishTranslation);

			Translation loginEmailErrorTurkishTranslation = new Translation();
			loginEmailErrorTurkishTranslation.setLanguage(turkish);
			var loginEmailErrorTurkish = this.wordDao.getWordsByKey("this.email.is.not.registered");
			loginEmailErrorTurkishTranslation.setWord(loginEmailErrorTurkish);
			loginEmailErrorTurkishTranslation.setTranslation("Bu mail adresi sistemde kayıtlı değil");
			this.translationDao.save(loginEmailErrorTurkishTranslation);

			Translation loginPasswordErrorTurkishTranslation = new Translation();
			loginPasswordErrorTurkishTranslation.setLanguage(turkish);
			var loginPasswordErrorTurkish = this.wordDao.getWordsByKey("wrong.password");
			loginPasswordErrorTurkishTranslation.setWord(loginPasswordErrorTurkish);
			loginPasswordErrorTurkishTranslation.setTranslation("Şifre Hatalı");
			this.translationDao.save(loginPasswordErrorTurkishTranslation);

			Translation rentalFindexScoreErrorTurkishTranslation = new Translation();
			rentalFindexScoreErrorTurkishTranslation.setLanguage(turkish);
			var rentalFindexScoreErrorTurkish = this.wordDao.getWordsByKey("rental.findex.score.error");
			rentalFindexScoreErrorTurkishTranslation.setWord(rentalFindexScoreErrorTurkish);
			rentalFindexScoreErrorTurkishTranslation
					.setTranslation("Findex skorunuz aracı kiralamak için yeterli değil");
			this.translationDao.save(rentalFindexScoreErrorTurkishTranslation);

			Translation carNotReturnedTurkishTranslation = new Translation();
			carNotReturnedTurkishTranslation.setLanguage(turkish);
			var carNotReturnedTurkish = this.wordDao.getWordsByKey("rental.car.is.not.returned.yet");
			carNotReturnedTurkishTranslation.setWord(carNotReturnedTurkish);
			carNotReturnedTurkishTranslation.setTranslation("Araç henüz geri dönmedi.");
			this.translationDao.save(carNotReturnedTurkishTranslation);

			Translation carImageLimitErrorTurkishTranslation = new Translation();
			carImageLimitErrorTurkishTranslation.setLanguage(turkish);
			var carImageLimitErrorTurkish = this.wordDao.getWordsByKey("car.image.limit.error");
			carImageLimitErrorTurkishTranslation.setWord(carImageLimitErrorTurkish);
			carImageLimitErrorTurkishTranslation.setTranslation("5'ten fazla araç görseli eklenemez.");
			this.translationDao.save(carImageLimitErrorTurkishTranslation);

			// Additional Items Turkish

			Translation additionalRentalItemUpdatedTurkishTranslation = new Translation();
			additionalRentalItemUpdatedTurkishTranslation.setLanguage(turkish);
			var additionalRentalItemUpdatedTurkish = this.wordDao.getWordsByKey("additional.rental.item.updated");
			additionalRentalItemUpdatedTurkishTranslation.setWord(additionalRentalItemUpdatedTurkish);
			additionalRentalItemUpdatedTurkishTranslation.setTranslation("Kiralanabilir ürün güncellendi.");
			this.translationDao.save(additionalRentalItemUpdatedTurkishTranslation);

			Translation additionalRentalItemAddedTurkishTranslation = new Translation();
			additionalRentalItemAddedTurkishTranslation.setLanguage(turkish);
			var additionalRentalItemAddedTurkish = this.wordDao.getWordsByKey("additional.rental.item.added");
			additionalRentalItemAddedTurkishTranslation.setWord(additionalRentalItemAddedTurkish);
			additionalRentalItemAddedTurkishTranslation.setTranslation("Kiralanabilir ürün eklendi.");
			this.translationDao.save(additionalRentalItemAddedTurkishTranslation);

			Translation additionalRentalItemDeletedTurkishTranslation = new Translation();
			additionalRentalItemDeletedTurkishTranslation.setLanguage(turkish);
			var additionalRentalItemDeletedTurkish = this.wordDao.getWordsByKey("additional.rental.item.deleted");
			additionalRentalItemDeletedTurkishTranslation.setWord(additionalRentalItemDeletedTurkish);
			additionalRentalItemDeletedTurkishTranslation.setTranslation("Kiralanabilir ürün silindi.");
			this.translationDao.save(additionalRentalItemDeletedTurkishTranslation);

			Translation additionalRentalItemsListedTurkishTranslation = new Translation();
			additionalRentalItemsListedTurkishTranslation.setLanguage(turkish);
			var additionalRentalItemsListedTurkish = this.wordDao.getWordsByKey("additional.rental.items.listed");
			additionalRentalItemsListedTurkishTranslation.setWord(additionalRentalItemsListedTurkish);
			additionalRentalItemsListedTurkishTranslation.setTranslation("Tüm kiralanabilir ürünler listelendi.");
			this.translationDao.save(additionalRentalItemsListedTurkishTranslation);

			Translation additionalRentalItemNotFoundTurkishTranslation = new Translation();
			additionalRentalItemNotFoundTurkishTranslation.setLanguage(turkish);
			var additionalRentalItemNotFoundTurkish = this.wordDao.getWordsByKey("additional.rental.item.not.found");
			additionalRentalItemNotFoundTurkishTranslation.setWord(additionalRentalItemNotFoundTurkish);
			additionalRentalItemNotFoundTurkishTranslation.setTranslation("Kiralanabilir ürün bulunamadı.");
			this.translationDao.save(additionalRentalItemNotFoundTurkishTranslation);

			// Car Damage Turkish

			Translation damageAddTurkishTranslation = new Translation();
			damageAddTurkishTranslation.setLanguage(turkish);
			var damageAddTurkish = this.wordDao.getWordsByKey("damage.added");
			damageAddTurkishTranslation.setWord(damageAddTurkish);
			damageAddTurkishTranslation.setTranslation("Hasar eklendi.");
			this.translationDao.save(damageAddTurkishTranslation);

			Translation damageDeletedTurkishTranslation = new Translation();
			damageDeletedTurkishTranslation.setLanguage(turkish);
			var damageDeletedTurkish = this.wordDao.getWordsByKey("damage.deleted");
			damageDeletedTurkishTranslation.setWord(damageDeletedTurkish);
			damageDeletedTurkishTranslation.setTranslation("Hasar silindi.");
			this.translationDao.save(damageDeletedTurkishTranslation);

			Translation damageUpdatedTurkishTranslation = new Translation();
			damageUpdatedTurkishTranslation.setLanguage(turkish);
			var damageUpdatedTurkish = this.wordDao.getWordsByKey("damage.updated");
			damageUpdatedTurkishTranslation.setWord(damageUpdatedTurkish);
			damageUpdatedTurkishTranslation.setTranslation("Hasar güncellendi.");
			this.translationDao.save(damageUpdatedTurkishTranslation);

			Translation damageListedTurkishTranslation = new Translation();
			damageListedTurkishTranslation.setLanguage(turkish);
			var damageListedTurkish = this.wordDao.getWordsByKey("damages.listed");
			damageListedTurkishTranslation.setWord(damageListedTurkish);
			damageListedTurkishTranslation.setTranslation("Hasarlar listelendi.");
			this.translationDao.save(damageListedTurkishTranslation);

			Translation damageNotFoundTurkishTranslation = new Translation();
			damageNotFoundTurkishTranslation.setLanguage(turkish);
			var damageNotFoundTurkish = this.wordDao.getWordsByKey("damage.not.found");
			damageNotFoundTurkishTranslation.setWord(damageNotFoundTurkish);
			damageNotFoundTurkishTranslation.setTranslation("Hasar bulunamadı.");
			this.translationDao.save(damageNotFoundTurkishTranslation);

			Translation damageFoundTurkishTranslation = new Translation();
			damageFoundTurkishTranslation.setLanguage(turkish);
			var damageFoundTurkish = this.wordDao.getWordsByKey("damage.found");
			damageFoundTurkishTranslation.setWord(damageFoundTurkish);
			damageFoundTurkishTranslation.setTranslation("Hasar bulundu.");
			this.translationDao.save(damageFoundTurkishTranslation);

			Translation creditCardNumberErrorTurkishTranslation = new Translation();
			creditCardNumberErrorTurkishTranslation.setLanguage(turkish);
			var creditCardNumberErrorTurkish = this.wordDao.getWordsByKey("credit.card.number.error");
			creditCardNumberErrorTurkishTranslation.setWord(creditCardNumberErrorTurkish);
			creditCardNumberErrorTurkishTranslation.setTranslation("Kredi Kartı Numarası Hatalı.");
			this.translationDao.save(creditCardNumberErrorTurkishTranslation);

			Translation formatNotValidTurkishTranslation = new Translation();
			formatNotValidTurkishTranslation.setLanguage(turkish);
			var formatNotValidTurkish = this.wordDao.getWordsByKey("format.not.valid");
			formatNotValidTurkishTranslation.setWord(formatNotValidTurkish);
			formatNotValidTurkishTranslation.setTranslation("Veri Giriş Formatı Hatalı");
			this.translationDao.save(formatNotValidTurkishTranslation);

			Translation invoiceNumberAlreadyExistsTurkishTranslation = new Translation();
			invoiceNumberAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var invoiceNumberAlreadyExistsTurkish = this.wordDao.getWordsByKey("invoice.exists");
			invoiceNumberAlreadyExistsTurkishTranslation.setWord(invoiceNumberAlreadyExistsTurkish);
			invoiceNumberAlreadyExistsTurkishTranslation.setTranslation("Fatura zaten sisteme kayıtlı.");
			this.translationDao.save(invoiceNumberAlreadyExistsTurkishTranslation);

			Translation carMaintenanceAddedTurkishTranslation = new Translation();
			carMaintenanceAddedTurkishTranslation.setLanguage(turkish);
			var carMaintenanceAddedTurkish = this.wordDao.getWordsByKey("car.maintenance.added");
			carMaintenanceAddedTurkishTranslation.setWord(carMaintenanceAddedTurkish);
			carMaintenanceAddedTurkishTranslation.setTranslation("Servis kaydı oluşturuldu.");
			this.translationDao.save(carMaintenanceAddedTurkishTranslation);

			Translation carMaintenanceDeletedTurkishTranslation = new Translation();
			carMaintenanceDeletedTurkishTranslation.setLanguage(turkish);
			var carMaintenanceDeletedTurkish = this.wordDao.getWordsByKey("car.maintenance.deleted");
			carMaintenanceDeletedTurkishTranslation.setWord(carMaintenanceDeletedTurkish);
			carMaintenanceDeletedTurkishTranslation.setTranslation("Servis kaydı silindi.");
			this.translationDao.save(carMaintenanceDeletedTurkishTranslation);

			Translation carMaintenanceUpdatedTurkishTranslation = new Translation();
			carMaintenanceUpdatedTurkishTranslation.setLanguage(turkish);
			var carMaintenanceUpdatedTurkish = this.wordDao.getWordsByKey("car.maintenance.updated");
			carMaintenanceUpdatedTurkishTranslation.setWord(carMaintenanceUpdatedTurkish);
			carMaintenanceUpdatedTurkishTranslation.setTranslation("Servis kaydı güncellendi.");
			this.translationDao.save(carMaintenanceUpdatedTurkishTranslation);

			Translation carMaintenancesListedTurkishTranslation = new Translation();
			carMaintenancesListedTurkishTranslation.setLanguage(turkish);
			var carMaintenancesListedTurkish = this.wordDao.getWordsByKey("car.maintenances.listed");
			carMaintenancesListedTurkishTranslation.setWord(carMaintenancesListedTurkish);
			carMaintenancesListedTurkishTranslation.setTranslation("Servis kayıtları listelendi");
			this.translationDao.save(carMaintenancesListedTurkishTranslation);

			Translation carMaintenanceRentalErrorTurkishTranslation = new Translation();
			carMaintenanceRentalErrorTurkishTranslation.setLanguage(turkish);
			var carMaintenanceRentalErrorTurkish = this.wordDao.getWordsByKey("car.maintenance.rental.error");
			carMaintenanceRentalErrorTurkishTranslation.setWord(carMaintenanceRentalErrorTurkish);
			carMaintenanceRentalErrorTurkishTranslation.setTranslation("İlgili araç kirada.");
			this.translationDao.save(carMaintenanceRentalErrorTurkishTranslation);

			Translation carMaintenanceNotFoundTurkishTranslation = new Translation();
			carMaintenanceNotFoundTurkishTranslation.setLanguage(turkish);
			var carMaintenanceNotFoundTurkish = this.wordDao.getWordsByKey("car.maintenance.not.found");
			carMaintenanceNotFoundTurkishTranslation.setWord(carMaintenanceNotFoundTurkish);
			carMaintenanceNotFoundTurkishTranslation.setTranslation("Servis kaydı bulunamadı.");
			this.translationDao.save(carMaintenanceNotFoundTurkishTranslation);

			Translation carMaintenanceAlreadyExistsTurkishTranslation = new Translation();
			carMaintenanceAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var carMaintenanceAlreadyExistsTurkish = this.wordDao.getWordsByKey("car.maintenance.exists");
			carMaintenanceAlreadyExistsTurkishTranslation.setWord(carMaintenanceAlreadyExistsTurkish);
			carMaintenanceAlreadyExistsTurkishTranslation.setTranslation("Araç zaten bakımda");
			this.translationDao.save(carMaintenanceAlreadyExistsTurkishTranslation);

			// Language Turkish

			Translation languageAddedTurkishTranslation = new Translation();
			languageAddedTurkishTranslation.setLanguage(turkish);
			var languageAddedTurkish = this.wordDao.getWordsByKey("language.added");
			languageAddedTurkishTranslation.setWord(languageAddedTurkish);
			languageAddedTurkishTranslation.setTranslation("Dil eklendi.");
			this.translationDao.save(languageAddedTurkishTranslation);

			/*
			 * Translation languageAddedTurkishTranslation2 = new Translation();
			 * languageAddedTurkishTranslation2.setLanguage(turkish); var
			 * languageAddedTurkish2 = this.wordDao.getWordsByKey("language.added");
			 * languageAddedTurkishTranslation2.setWord(languageAddedTurkish2);
			 * languageAddedTurkishTranslation2.setTranslation("Dil eklendi.");
			 * this.translationDao.save(languageAddedTurkishTranslation2);
			 */

			Translation languageDeletedTurkishTranslation = new Translation();
			languageDeletedTurkishTranslation.setLanguage(turkish);
			var languageDeletedTurkish = this.wordDao.getWordsByKey("language.deleted");
			languageDeletedTurkishTranslation.setWord(languageDeletedTurkish);
			languageDeletedTurkishTranslation.setTranslation("Dil silindi.");
			this.translationDao.save(languageDeletedTurkishTranslation);

			Translation languageUpdatedTurkishTranslation = new Translation();
			languageUpdatedTurkishTranslation.setLanguage(turkish);
			var languageUpdatedTurkish = this.wordDao.getWordsByKey("language.updated");
			languageUpdatedTurkishTranslation.setWord(languageUpdatedTurkish);
			languageUpdatedTurkishTranslation.setTranslation("Dil güncellendi.");
			this.translationDao.save(languageUpdatedTurkishTranslation);

			Translation languageListedTurkishTranslation = new Translation();
			languageListedTurkishTranslation.setLanguage(turkish);
			var languageListedTurkish = this.wordDao.getWordsByKey("languages.listed");
			languageListedTurkishTranslation.setWord(languageListedTurkish);
			languageListedTurkishTranslation.setTranslation("Dil listelendi.");
			this.translationDao.save(languageListedTurkishTranslation);

			Translation languageFoundTurkishTranslation = new Translation();
			languageFoundTurkishTranslation.setLanguage(turkish);
			var languageFoundTurkish = this.wordDao.getWordsByKey("language.found");
			languageFoundTurkishTranslation.setWord(languageFoundTurkish);
			languageFoundTurkishTranslation.setTranslation("Dil Bulundu.");
			this.translationDao.save(languageFoundTurkishTranslation);

			Translation languageNotFoundTurkishTranslation = new Translation();
			languageNotFoundTurkishTranslation.setLanguage(turkish);
			var languageNotFoundTurkish = this.wordDao.getWordsByKey("language.not.found");
			languageNotFoundTurkishTranslation.setWord(languageNotFoundTurkish);
			languageNotFoundTurkishTranslation.setTranslation("Dil Bulunamadı.");
			this.translationDao.save(languageNotFoundTurkishTranslation);

			Translation languageAlreadyExistsTurkishTranslation = new Translation();
			languageAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var languageAlreadyExistsTurkish = this.wordDao.getWordsByKey("language.exists");
			languageAlreadyExistsTurkishTranslation.setWord(languageAlreadyExistsTurkish);
			languageAlreadyExistsTurkishTranslation.setTranslation("Dil zaten kayıtlı.");
			this.translationDao.save(languageAlreadyExistsTurkishTranslation);

			Translation rentedCarIsOnMaintenanceTurkishTranslation = new Translation();
			rentedCarIsOnMaintenanceTurkishTranslation.setLanguage(turkish);
			var rentedCarIsOnMaintenanceTurkish = this.wordDao.getWordsByKey("rented.car.is.on.maintenance");
			rentedCarIsOnMaintenanceTurkishTranslation.setWord(rentedCarIsOnMaintenanceTurkish);
			rentedCarIsOnMaintenanceTurkishTranslation.setTranslation("Kiralanmak istenen araç bakımdadır.");
			this.translationDao.save(rentedCarIsOnMaintenanceTurkishTranslation);

			// Word Turkish

			Translation wordAddedTurkishTranslation = new Translation();
			wordAddedTurkishTranslation.setLanguage(turkish);
			var wordAddedTurkish = this.wordDao.getWordsByKey("word.added");
			wordAddedTurkishTranslation.setWord(wordAddedTurkish);
			wordAddedTurkishTranslation.setTranslation("Kelime eklendi.");
			this.translationDao.save(wordAddedTurkishTranslation);

			Translation wordDeletedTurkishTranslation = new Translation();
			wordDeletedTurkishTranslation.setLanguage(turkish);
			var wordDeletedTurkish = this.wordDao.getWordsByKey("word.deleted");
			wordDeletedTurkishTranslation.setWord(wordDeletedTurkish);
			wordDeletedTurkishTranslation.setTranslation("Kelime silindi.");
			this.translationDao.save(wordDeletedTurkishTranslation);

			Translation wordUpdatedTurkishTranslation = new Translation();
			wordUpdatedTurkishTranslation.setLanguage(turkish);
			var wordUpdatedTurkish = this.wordDao.getWordsByKey("word.updated");
			wordUpdatedTurkishTranslation.setWord(wordUpdatedTurkish);
			wordUpdatedTurkishTranslation.setTranslation("Kelime güncellendi.");
			this.translationDao.save(wordUpdatedTurkishTranslation);

			Translation wordsListedTurkishTranslation = new Translation();
			wordsListedTurkishTranslation.setLanguage(turkish);
			var wordsListedTurkish = this.wordDao.getWordsByKey("words.listed");
			wordsListedTurkishTranslation.setWord(wordsListedTurkish);
			wordsListedTurkishTranslation.setTranslation("Kelimeler listelendi.");
			this.translationDao.save(wordsListedTurkishTranslation);

			Translation wordNotFoundTurkishTranslation = new Translation();
			wordNotFoundTurkishTranslation.setLanguage(turkish);
			var wordNotFoundTurkish = this.wordDao.getWordsByKey("word.not.found");
			wordNotFoundTurkishTranslation.setWord(wordNotFoundTurkish);
			wordNotFoundTurkishTranslation.setTranslation("Kelime bulunamadı.");
			this.translationDao.save(wordNotFoundTurkishTranslation);

			Translation wordFoundTurkishTranslation = new Translation();
			wordFoundTurkishTranslation.setLanguage(turkish);
			var wordFoundTurkish = this.wordDao.getWordsByKey("word.found");
			wordFoundTurkishTranslation.setWord(wordFoundTurkish);
			wordFoundTurkishTranslation.setTranslation("Kelime bulundu.");
			this.translationDao.save(wordFoundTurkishTranslation);

			Translation wordAlreadyExistsTurkishTranslation = new Translation();
			wordAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var wordAlreadyExistsTurkish = this.wordDao.getWordsByKey("word.exists");
			wordAlreadyExistsTurkishTranslation.setWord(wordAlreadyExistsTurkish);
			wordAlreadyExistsTurkishTranslation.setTranslation("Kelime zaten kayıtlı.");
			this.translationDao.save(wordAlreadyExistsTurkishTranslation);

			// Translation Turkish

			Translation translationAddedTurkishTranslation = new Translation();
			translationAddedTurkishTranslation.setLanguage(turkish);
			var translationAddedTurkish = this.wordDao.getWordsByKey("translation.added");
			translationAddedTurkishTranslation.setWord(translationAddedTurkish);
			translationAddedTurkishTranslation.setTranslation("Çeviri eklendi.");
			this.translationDao.save(translationAddedTurkishTranslation);

			Translation translationDeletedTurkishTranslation = new Translation();
			translationDeletedTurkishTranslation.setLanguage(turkish);
			var translationDeletedTurkish = this.wordDao.getWordsByKey("translation.deleted");
			translationDeletedTurkishTranslation.setWord(translationDeletedTurkish);
			translationDeletedTurkishTranslation.setTranslation("Çeviri silindi.");
			this.translationDao.save(translationDeletedTurkishTranslation);

			Translation translationUpdatedTurkishTranslation = new Translation();
			translationUpdatedTurkishTranslation.setLanguage(turkish);
			var translationUpdatedTurkish = this.wordDao.getWordsByKey("translation.updated");
			translationUpdatedTurkishTranslation.setWord(translationUpdatedTurkish);
			translationUpdatedTurkishTranslation.setTranslation("Çeviri güncellendi.");
			this.translationDao.save(translationUpdatedTurkishTranslation);

			Translation translationsListedTurkishTranslation = new Translation();
			translationsListedTurkishTranslation.setLanguage(turkish);
			var translationsListedTurkish = this.wordDao.getWordsByKey("translations.listed");
			translationsListedTurkishTranslation.setWord(translationsListedTurkish);
			translationsListedTurkishTranslation.setTranslation("Çeviriler listelendi.");
			this.translationDao.save(translationsListedTurkishTranslation);

			Translation translationNotFoundTurkishTranslation = new Translation();
			translationNotFoundTurkishTranslation.setLanguage(turkish);
			var translationNotFoundTurkish = this.wordDao.getWordsByKey("translation.not.found");
			translationNotFoundTurkishTranslation.setWord(translationNotFoundTurkish);
			translationNotFoundTurkishTranslation.setTranslation("Çeviri bulunamadı.");
			this.translationDao.save(translationNotFoundTurkishTranslation);

			Translation translationFoundTurkishTranslation = new Translation();
			translationFoundTurkishTranslation.setLanguage(turkish);
			var translationFoundTurkish = this.wordDao.getWordsByKey("translation.found");
			translationFoundTurkishTranslation.setWord(translationFoundTurkish);
			translationFoundTurkishTranslation.setTranslation("Çeviri bulundu.");
			this.translationDao.save(translationFoundTurkishTranslation);

			Translation translationAlreadyExistsTurkishTranslation = new Translation();
			translationAlreadyExistsTurkishTranslation.setLanguage(turkish);
			var translationAlreadyExistsTurkish = this.wordDao.getWordsByKey("translation.exists");
			translationAlreadyExistsTurkishTranslation.setWord(translationAlreadyExistsTurkish);
			translationAlreadyExistsTurkishTranslation.setTranslation("Çeviri zaten kayıtlı.");
			this.translationDao.save(translationAlreadyExistsTurkishTranslation);

		}
	}
}