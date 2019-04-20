package sample.Domain;

public class InvoicesSumByClientModelView {
    private String Client;
    private double sum;

    public InvoicesSumByClientModelView(String client, double sum) {
        Client = client;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "InvoicesSumByClientModelView{" +
                "Client='" + Client + '\'' +
                ", sum=" + sum +
                '}';
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

