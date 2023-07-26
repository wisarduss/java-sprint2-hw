public class MonthlyReportRecord {
    String itemName;
    Boolean isExpense;
    Integer quantity;
    Integer pricePerOne;

    public MonthlyReportRecord(String itemName, Boolean isExpense, Integer quantity, Integer pricePerOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.pricePerOne = pricePerOne;
    }

    public int totalPrice(){
        return quantity * pricePerOne;
    }
}
