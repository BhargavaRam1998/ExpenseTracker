package Project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({ "ID", "Date", "Description", "Amount","Category" }) // Specify the field order
public class Expense {

    @JsonProperty("ID")
    private int ID;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate Date;

    @JsonProperty("Amount")
    private int Amount;

    @JsonProperty("Category")
    private String Category;

    @JsonIgnore // Exclude this field from JSON serialization/deserialization
    private LocalDate createdAt;

    @JsonIgnore // Exclude duplicate fields from JSON serialization/deserialization
    private int id;

    @JsonIgnore
    private String description;

    @JsonIgnore
    private int amount;

    @JsonIgnore
    private LocalDate date;

    @JsonIgnore
    private String category;

    public Expense(int ID, String description, int amount, String Category) {
        this.ID = ID;
        this.Description = description;
        Date = LocalDate.now();
        this.Amount = amount;
        this.Category = Category;
    }

    public Expense() {
    }

    @Override
    public String toString() {
        return "Expense{" +
                "ID=" + ID +
                ", Description='" + Description + '\'' +
                ", Date=" + Date +
                ", Amount=" + Amount +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        this.Date = date;
    }
    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
