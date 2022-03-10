/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class DetailInvoice {

    private Account account;
    private Invoice invoice;
    private InvoiceProduct invoiceProduct;
    private Product product;
    private Dimension dimension;
    private ProductDetail productDetail;
    private Agency agency;

    public DetailInvoice() {
        account = new Account();
        invoice = new Invoice();
        invoiceProduct = new InvoiceProduct();
        product = new Product();
        dimension = new Dimension();
        productDetail = new ProductDetail();
        agency = new Agency();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceProduct getInvoiceProduct() {
        return invoiceProduct;
    }

    public void setInvoiceProduct(InvoiceProduct invoiceProduct) {
        this.invoiceProduct = invoiceProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
