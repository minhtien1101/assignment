
package model;

public class InvoiceProduct {
    private Invoice invoice;
    private ProductDetail proDetail;
    private int quantity;
    private float buyPrice;
    private int discount;

    public InvoiceProduct() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ProductDetail getProDetail() {
        return proDetail;
    }

    public void setProDetail(ProductDetail proDetail) {
        this.proDetail = proDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
 
}
