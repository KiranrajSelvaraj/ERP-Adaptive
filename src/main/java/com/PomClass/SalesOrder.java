package com.PomClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class SalesOrder {
	
	public static WebDriver driver;
	
	@FindBy(id="Create")
	public WebElement AddOrder;
	
	@FindBy(id = "Convert")
	public WebElement Convert;
	
	@FindBy(id = "FindOrderNumber")
	public WebElement FindOrderNo;
	
	@FindBy(id = "clear")
	public WebElement Clear;
	
	@FindBy(id = "searchstring")
	public WebElement Fetch;
	
	@FindBy(xpath ="//span[@id='select2-CustomerId-container']")
	public WebElement Customer;
	
	@FindBy(id="OrderNumber")
	public WebElement OrderNo;
	
	@FindBy(id="select2-GSTTypeId-container")
	public WebElement Gst;
	
	@FindBy(id="select2-CurrencyId-container")
	public WebElement CurrencyCode;
	
	@FindBy(id = "DiscountPercentage")
	public WebElement DiscountPercentage;
	
	@FindBy(id = "DiscountAmt")
	public WebElement DiscountAmount;
	
	@FindBy(id = "Discount")
	public WebElement OverAllDiscount;
	
	@FindBy(id = "DiscountType")
	public WebElement OverAllDiscountType;
	
	@FindBy(id="BillingAddress1")
	public WebElement BillingAddress1;
	
	@FindBy(id="BillingAddress2")
	public WebElement BillingAddress2;
	
	@FindBy(id="select2-BillingCountryId-container")
	public WebElement Country;
	
	@FindBy(id="BillingPostalCode")
	public WebElement PostalCode;
	
	@FindBy(id="ReferenceNumber")
	public WebElement ReferenceNo;
	
	@FindBy(id="select2-PaymentTermsId-container")
	public WebElement Terms;
	
	@FindBy(id="CurrencyRate")
	public WebElement CurrencyRate;
	
	@FindBy(id="DeliveryAddress1")
	public WebElement DeliveryAddress1;
	
	@FindBy(id="DeliveryAddress2")
	public WebElement DeliveryAddress2;
	
	@FindBy(id="select2-DeliveryCountryId-container")
	public WebElement DeliveryCountry;
	
	@FindBy(id="DeliveryPostalCode")
	public WebElement DeliveryPostalCode;
	
	@FindBy(id="QuotationNumber")
	public WebElement QuotationNo;
	
	@FindBy(id="select2-CustomerLocationsId-container")
	public WebElement Location;
	
	@FindBy(id="select2-CustomerLocationsId-container")
	public WebElement Salesman;
	
	@FindBy(id = "select2-WarehouseId-container")
	public WebElement Warehouse;
	
	@FindBy(id="select2-BranchId-container")
	public WebElement Branch;
	
	@FindBy(id="select2-ProductId-container")
	public WebElement Product;
	
	@FindBy(id = "OpenItem")
	public WebElement OpenProduct;
	
	@FindBy(xpath = "//span[@id='select2-UOMId-container']")
	public WebElement Uom;
	
	@FindBy(id="ItemQty")
	public WebElement Qty;
	
	@FindBy(id = "ItemPrice")
	public WebElement Price;
	
	@FindBy(id = "FOCDiv")
	public WebElement FocField;
	
	@FindBy(id = "FOC")
	public WebElement Foc;
	
	@FindBy(id = "IsLooseFOC")
	public WebElement IsFoc;
	
	@FindBy(xpath = "//div[@class='col-md-1 p2 showDiscountBlock showDiscountBlockHeader']")
	public WebElement DiscountField;
	
	@FindBy(id = "DiscountPercentage")
	public WebElement Discount;
	
	@FindBy(xpath = "//div[@class='col-md-1 p3 showDiscountBlock showDiscountBlockHeader']")
	public WebElement UnitDiscField;
	
	@FindBy(id = "IsUnits")
	public WebElement UnitDiscCheckBox;
	
	@FindBy(id = "DiscountAmt")
	public WebElement UnitDisc; 
	
	@FindBy(id = "RatingType")
	public WebElement UnitPrice;
	
	@FindBy(id="select2-HeaderGST-container")
	public WebElement GstPrecentage;
	
	@FindBy(id="btn_add")
	public WebElement Add;
	
	@FindBy(id="Create")
	public WebElement SaveandClose;
	
	@FindBy(xpath = "(//button[@id='Create']//following::button[@class='btn btn-primary btn-sm dropdown-toggle'])[1]")
	public WebElement SaveDropDown;
	
	@FindBy(id = "Save")
	public WebElement Save;
	
	@FindBy(id="popup_ok")
	public WebElement PopupOk;
	
	@FindBy(id="popup_cancel")
	public WebElement PopupCancel;
	
	@FindBy(id = "ConvertInvoice")
	public WebElement ConvertInvoice;
	
	@FindBy(id = "ConvertDeliveryOrder")
	public WebElement ConvertDeliveryOrder;
	
	@FindBy(xpath = "//input[@value='Back']")
	public WebElement Back;
	
	@FindBy(xpath = "//select[@name='ordertable_length']//following-sibling::option[4]")
	public WebElement ShowTableLength;
	
	@FindBy(id = "IsSpecialPrice")
	public WebElement IsSpecialPriceCheckBox;
	
	@FindBy(id = "SalesOrderedRemarks")
	public WebElement Remarks;
	
	@FindBy(id = "ProductCheck")
	public WebElement ProductCheckBox;
	
	@FindBy(id = "ServiceCheck")
	public WebElement ServiceCheckBox;
	
	@FindBy(id = "OpenCheck")
	public WebElement OpenCheckBox;
	
	@FindBy(id = "IsZeroRatedGST")
	public WebElement ZeroGstCheckBox;
	
	@FindBy(id = "HeaderCheck")
	public WebElement HeaderCheckBox;
	
	@FindBy(xpath = "//i[@class='fa fa-question-circle fa-lg']")
	public WebElement OldHistoryQuestionMark;
	
	
	
	
	
	
	

	public SalesOrder(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
