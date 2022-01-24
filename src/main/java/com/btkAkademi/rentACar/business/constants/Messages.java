package com.btkAkademi.rentACar.business.constants;

public class Messages {

	public static final String DATANOTFOUND = "data.not.found";

	public static final String VALIDATIONERROR = "validation.error";

	public static final String FORMATERROR = "format.error";

	public static final String BRANDADD = "brand.added";
	public static final String BRANDUPDATE = "brand.updated";
	public static final String BRANDDELETE = "brand.deleted";
	public static final String BRANDLIST = "brands.listed";
	public static final String BRANDNAMEERROR = "brand.exists";
	public static final String BRANDFOUND = "brand.found";
	public static final String BRANDNOTFOUND = "brand.not.found";

	public static final String CARADD = "car.added";
	public static final String CARUPDATE = "car.updated";
	public static final String CARDELETE = "car.deleted";
	public static final String CARLIST = "cars.listed";
	public static final String CARFOUND = "car.found";
	public static final String CARNOTFOUND = "car.not.found";
	public static final String GETCARBYBRANDID = "get.car.by.brand.id";
	public static final String GETCARBYCOLORID = "get.car.by.color.id";
	public static final String GETCARBYCITYID = "get.car.by.city.id";

	public static final String CITYADD = "city.added";
	public static final String CITYUPDATE = "city.updated";
	public static final String CITYDELETE = "city.deleted";
	public static final String CITYLIST = "cities.listed";
	public static final String CITYNOTFOUND = "city.not.found";
	public static final String CITYFOUND = "city.found";
	public static final String CITYBYID = "City is brought by CityId";
	public static final String CITYEXISTS = "city.exists";

	public static final String COLORADD = "color.added";
	public static final String COLORUPDATE = "color.updated";
	public static final String COLORDELETE = "color.deleted";
	public static final String COLORLIST = "colors.listed";
	public static final String COLORNAMEERROR = "color.exists";
	public static final String COLORFOUND = "color.found";
	public static final String COLORNOTFOUND = "color.not.found";
	public static final String COLORERROR = "color.error";

	public static final String CUSTOMERADD = "customer.added";
	public static final String CUSTOMERUPDATE = "customer.updated";
	public static final String CUSTOMERDELETE = "customer.deleted";
	public static final String CUSTOMERLIST = "customers.listed";
	public static final String CUSTOMERGET = "customer.found";
	public static final String CUSTOMERNOTFOUND = "customer.not.found";
	public static final String CUSTOMERISALREADYEXISTS = "customer.exists";
	public static final String CUSTOMERTAXNUMBEREXISTS = "customers.tax.number.already.exists";

	public static final String EMAILERROR = "This email is already in use"; // yapılmadı

	public static final String RENTALADD = "rental.added";
	public static final String RENTALUPDATE = "rental.updated";
	public static final String RENTALDELETE = "rental.deleted";
	public static final String RENTALLIST = "rentals.listed";
	public static final String RENTALDATEERROR = "rental.date.error";
	public static final String RENTALDATESUCCESS = "rental.date.success";
	public static final String RENTALFINDEXSCOREERROR = "rental.findex.score.error";
	public static final String RENTALMAINTENANCEERROR = "rented.car.is.on.maintenance";
	public static final String RENTALFOUND = "rental.found";
	public static final String RENTALNOTFOUND = "rental.not.found";
	public static final String INSUFFICIENTBALANCE = "rental.balance.insufficient";
	public static final String SUFFICIENTBALANCE = "rental.balance.insufficient";
	public static final String RENTALDATEISNULL = "rental.car.is.not.returned.yet";

	public static final String CARIMAGEADD = "car.image.added";
	public static final String CARIMAGEUPDATE = "car.image.updated";
	public static final String CARIMAGEDELETE = "car.image.deleted";
	public static final String CARIMAGELIST = "car.images.listed";
	public static final String CARIMAGELIMITERROR = "car.image.limit.error";
	public static final String CARIMAGEDEFAULT = "showing.default.image";
	public static final String CARIMAGEEMPTY = "no.image.selected";
	public static final String CARIMAGETYPEERROR = "car.image.type.not.valid";
	public static final String CARIMAGEGET = "car.image.found";
	public static final String CARIMAGENOTFOUND = "car.image.not.found";

	public static final String LOGINEMAILERROR = "this.email.is.not.registered";
	public static final String LOGINPASSWORDERROR = "wrong.password";
	public static final String LOGINSUCCESS = "login.success";
	public static final String LOGINFAILED = "login.failed";

	public static final String CREDITCARDADD = "credit.card.added";
	public static final String CREDITCARDUPDATE = "credit.card.updated";
	public static final String CREDITCARDELETE = "credit.card.deleted";
	public static final String CREDITCARDLIST = "credit cards listed";
	public static final String CREDITCARDNUMBERERROR = "credit.card.number.error";
	public static final String CREDITCARDDATEERROR = "credit.card.date.error";
	public static final String CREDITCARDCVCERROR = "credit.card.cvv.error";
	public static final String CREDITCARDSAVE = "credit.card.registered";
	public static final String CREDITCARDNOTSAVE = "credit.card.not.save";
	public static final String CREDITCARDGET = "credit.card.found";
	public static final String CREDITCARDALREADYEXISTS = "credit.card.exists";

	public static final String DATEFORMATNOTVALID = "date.format.not.valid";

	public static final String PAYMENTADD = "payment.add";
	public static final String PAYMENTUPDATE = "payment.updated";
	public static final String PAYMENTDELETE = "payment.deleted";
	public static final String PAYMENTLIST = "payments.listed";
	public static final String PAYMENTCARDSAVE = "credit.card.saved";
	public static final String PAYMENTCARDNOTSAVE = "payment.card.not.saved";
	public static final String PAYMENTCARDFAIL = "payment.card.fail";

	public static final String CARMAINTENANCEADD = "car.maintenance.added";
	public static final String CARMAINTENANCEUPDATE = "car.maintenance.updated";
	public static final String CARMAINTENANCEDELETE = "car.maintenance.deleted";
	public static final String CARMAINTENANCELIST = "car.maintenances.listed";
	public static final String CARMAINTENANCERENTALERROR = "car.maintenance.rental.error";
	public static final String CARMAINTENANCENOTFOUND = "car.maintenance.not.found";
	public static final String CARMAINTENANCEALREADYEXISTS = "car.maintenance.exists";

	public static final String INVOICEADD = "invoice.added";
	public static final String INVOICEUPDATE = "invoice.updated";
	public static final String INVOICEDELETE = "invoice.deleted";
	public static final String INVOICELIST = "invoices.listed";
	public static final String INVOICEBYCUSTOMERLIST = "customers.invoices.listed";
	public static final String INVOICEFOUND = "invoice.found";
	public static final String INVOICENOTFOUND = "invoice.not.found";
	public static final String INVOICENUMBERAlREADYEXISTS = "invoice.exists";

	public static final String USEREMAILALREADYEXISTS = "user.email.exists";
	public static final String USERFOUND = "user.found";
	public static final String USERNOTFOUND = "user.not.found";
	public static final String USERLIST = "user.listed";

	public static final String DAMAGEADD = "damage.added";
	public static final String DAMAGEDELETE = "damage.deleted";
	public static final String DAMAGEUPDATE = "damage.updated";
	public static final String DAMAGELIST = "damages.listed";
	public static final String DAMAGENOTFOUND = "damage.not.found";
	public static final String DAMAGEFOUND = "damage.found";

	public static final String ADDITIONALSERVICEADD = "additional.service.added";
	public static final String ADDITIONALSERVICEUPDATE = "additional.service.updated";
	public static final String ADDITIONALSERVICEDELETE = "additional.service.deleted";
	public static final String ADDITIONALSERVICELIST = "additional.services.listed";
	public static final String ADDITIONALSERVICENOTFOUND = "additional.service.not.found";

	public static final String ADDITIONALRENTALITEMADD = "additional.rental.item.added";
	public static final String ADDITIONALRENTALITEMDELETE = "additional.rental.item.deleted";
	public static final String ADDITIONALRENTALITEMUPDATE = "additional.rental.item.updated";
	public static final String ADDITIONALRENTALITEMLIST = "additional.rental.items.listed";
	public static final String ADDITIONALRENTALITEMNOTFOUND = "additional.rental.item.not.found";

	public static final String LANGUAGEADD = "language.added";
	public static final String LANGUAGEDELETE = "language.deleted";
	public static final String LANGUAGEUPDATE = "language.updated";
	public static final String LANGUAGELIST = "languages.listed";
	public static final String LANGUAGENOTFOUND = "language.not.found";
	public static final String LANGUAGEFOUND = "language.found";
	public static final String LANGUAGEALREADYEXISTS = "language.exists";

	public static final String WORDADD = "word.added";
	public static final String WORDDELETED = "word.deleted";
	public static final String WORDUPDATED = "word.updated";
	public static final String WORDSLISTED = "words.listed";
	public static final String WORDNOTFOUND = "word.not.found";
	public static final String WORDFOUND = "word.found";
	public static final String WORDALREADYEXISTS = "word.exists";

	public static final String TRANSLATIONADD = "translation.added";
	public static final String TRANSLATIONDELETE = "translation.deleted";
	public static final String TRANSLATIONUPDATE = "translation.updated";
	public static final String TRANSLATIONSLISTED = "translations.listed";
	public static final String TRANSLATIONNOTFOUND = "translation.not.found";
	public static final String TRANSLATIONFOUND = "translation.found";
	public static final String TRANSLATIONEXISTS = "translation.exists";
	// kendi eklediklerim
	public static final String ADDITIONALSERVICEITEMADDED = "additional.service.item.added";
	public static final String ADDITIONALSERVICEITEMUPDATED = "additional.service.item.updated";
	public static final String ADDITIONALSERVICEITEMDELETED = "additional.service.item.deleted";

	public static final String ADDITIONALSERVICEITEMNOTFOUND = "additional.service.item.not.found";

	public static final String ADDITIONALSERVICEITEMLIST = "additional.service.item.list";

	public static final String BRANDNAMEEXISTS = "brand.name.exists";

	public static final String CARDAMAGEADD = "car.damage.add";

	public static final String CARDAMAGEUPDATE = "car.damage.update";

	public static final String CARDAMAGEDELETE = "car.damage.delete";

	public static final String CARDAMAGENOTFOUND = "car.damage.not.found";
	public static final String CARRENTED = "car.rented";
	public static final String CARINMANTANANCE = "car.in.maintanance";
	public static final String CITYNAMEEXISTS = "city.name.exists";
	public static final String COLORNAMEEXISTS = "color.name.exists";

	public static final String CAMPANYNAMEEXISTS = "company.name.exists";

	public static final String CREDITCARDNOTFOUND = "credit.card.not.found";

	public static final String AGENOTENOUGH = "age.not.enough";

	public static final String RENTALNOTFINISHED = "rental.not.finished";

	public static final String PAYMENTNOTFOUND = "payment.not.found";

	public static final String PROMOCODENOTFOUND = "promo.code.not.found";

	public static final String PROMOCODEADD = "promo.code.add";

	public static final String PROMOCODEUPDATE = "promo.code.update";

	public static final String PROMOCODEDELETE = "promo.code.delete";

	public static final String PROMOCODEALREADYEXISTS = "promo.code.already.exists";

	public static final String DATESARENOTCORRECT = "dates.are.not.correct";

	public static final String NOAVAILABLECARINTHISSEGMENT = "no.available.car.in.this.segment";

	public static final String KILOMETERERROR = "kilometer.error";

	public static final String SEGMENTADD = "segment.add";

	public static final String SEGMENTUPDATE = "segment.update";

	public static final String SEGMENTDELETE = "segment.delete";

	public static final String SEGMENTNAMEALREADYEXISTS = "segment.name.already.exists";

	public static final String SEGMENTNOTFOUND = "segment.not.found";

	public static final String LIST = "list";
	public static final String NOTFOUND = "not.found";
	
	public static final String PROMOCODEEXPIRED = "promo.code.expired";

	public static final String BRANDLIMITEXCEED = "brand.limit.exceed";

	public static final String CARDAMAGELIST = "car.damage.list";

	public static final String CUSTOMERREGISTRATIONSUCCESSFUL = "customer.registration.successful";

	public static final String CUSTOMERCARDDETAILLIST = "customer.card.detail.list";

	public static final String TOTALPRICECALCULATE = "total.price.calculate";

	public static final String PROMOCODELIST = "promo.code.list";

	public static final String SEGMENTLIST = "segment.list";

	//load a eklenecek
	
	public static final String DATAHAVERELATIONS = "data.have.relations";
	
	

}
