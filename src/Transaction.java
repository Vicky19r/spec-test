import java.time.LocalDateTime;

public class Transaction {

    private long transactionId;
    private long customerId;
    //amount in integers ex: $12.34 -> 1234
    private long purchaseAmount;
    private LocalDateTime transactionDateTime;

    public Transaction(long transactionId, long customerId, long purchaseAmount, LocalDateTime transactionDateTime) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.purchaseAmount = purchaseAmount;
        this.transactionDateTime = transactionDateTime;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionMonth() {

        return transactionDateTime == null ? null : transactionDateTime.getMonth().name();
    }
}
