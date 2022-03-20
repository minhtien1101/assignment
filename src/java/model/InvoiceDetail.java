package model;

public class InvoiceDetail {

    private InvoiceProduct invoiceProduct;

    public InvoiceDetail() {
        invoiceProduct = new InvoiceProduct();
    }

    public InvoiceProduct getInvoiceProduct() {
        return invoiceProduct;
    }

    public void setInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProduct = invoiceProduct;
    }

}
